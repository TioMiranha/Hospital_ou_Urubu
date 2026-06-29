package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.Usuario;

public class UsuarioDAO {

    public void Cadastrar(Usuario user) throws Exception {
        if (user == null) {
            throw new Exception("Dados do usuário vazios.");
        }

        String sql = """
                INSERT INTO usuarios
                (funcionario_id, email, senha_hash, perfil, ativo, deve_trocar_senha)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (user.getFuncionarioId() == null) {
                ps.setNull(1, java.sql.Types.INTEGER);
            } else {
                ps.setInt(1, user.getFuncionarioId());
            }
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSenhaHash());
            ps.setString(4, user.getPerfil());
            ps.setBoolean(5, !Boolean.FALSE.equals(user.getAtivo()));
            ps.setBoolean(6, Boolean.TRUE.equals(user.getDeveTrocarSenha()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            throw new Exception("Erro ao cadastrar usuário: " + e.getMessage(), e);
        }
    }

    public void ChangeUserPassword(Usuario user) throws Exception {
        if (user == null || user.getId() <= 0) {
            throw new Exception("Usuário inválido.");
        }

        String sql = """
                UPDATE usuarios
                SET senha_hash = ?, deve_trocar_senha = FALSE
                WHERE id = ?
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getSenhaHash());
            ps.setInt(2, user.getId());

            if (ps.executeUpdate() == 0) {
                throw new Exception("Usuário não encontrado.");
            }
            user.setDeveTrocarSenha(false);
        }
    }

    public Usuario Buscar(String email) throws Exception {
        if (email == null || email.isBlank()) {
            throw new Exception("Email não informado.");
        }

        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarUsuario(rs);
                }
            }
        }
        return null;
    }

    public Usuario Autenticar(String email, String senha) throws Exception {
        if (senha == null || senha.length() != 128) {
            throw new Exception("Senha inválida.");
        }

        String sql = """
                SELECT * FROM usuarios
                WHERE email = ? AND senha_hash = ? AND ativo = TRUE
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarUsuario(rs);
                }
            }
        }
        return null;
    }

    public void Desligar(int id) throws Exception {
        String sql = "UPDATE usuarios SET ativo = FALSE WHERE id = ?";
        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ArrayList<Usuario> Listar() throws Exception {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY email";

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(criarUsuario(rs));
            }
        }
        return usuarios;
    }

    public Usuario Buscar(int id) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarUsuario(rs);
                }
            }
        }
        return null;
    }

    public boolean existeAdmin() throws Exception {
        String sql = "SELECT id FROM usuarios WHERE UPPER(perfil) = 'ADMIN' LIMIT 1";
        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    }

    private Usuario criarUsuario(ResultSet rs) throws Exception {
        Usuario user = new Usuario();
        user.setId(rs.getInt("id"));

        int funcionarioId = rs.getInt("funcionario_id");
        user.setFuncionarioId(rs.wasNull() ? null : funcionarioId);
        user.setEmail(rs.getString("email"));
        user.setSenhaHash(rs.getString("senha_hash"));
        user.setPerfil(rs.getString("perfil"));
        user.setAtivo(rs.getBoolean("ativo"));
        user.setDeveTrocarSenha(rs.getBoolean("deve_trocar_senha"));
        return user;
    }
}
