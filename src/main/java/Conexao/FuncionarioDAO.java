package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.Funcionarios.Funcionario;

public class FuncionarioDAO {

    public Integer salvar(Funcionario funcionario) throws Exception {
        String sql = "INSERT INTO funcionarios (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getTelefone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionario.setId(rs.getInt(1));
                    return funcionario.getId();
                }
            }
        }
        throw new Exception("Não foi possível cadastrar o funcionário.");
    }

    public ArrayList<Funcionario> listar() throws Exception {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios ORDER BY nome";

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }
}
