package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.Pacientes.Paciente;

public class PacienteDAO {

    public Integer salvar(Paciente paciente) throws Exception {
        String sql = """
                INSERT INTO pacientes
                (nome, cpf, data_nascimento, sexo, telefone, endereco, email)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setDate(3, java.sql.Date.valueOf(paciente.getDataNascimento()));
            ps.setString(4, paciente.getSexo());
            ps.setString(5, paciente.getTelefone());
            ps.setString(6, paciente.getEndereco());
            ps.setString(7, paciente.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setId(rs.getInt(1));
                    return paciente.getId();
                }
            }
        }
        throw new Exception("Não foi possível cadastrar o paciente.");
    }

    public ArrayList<Paciente> listar() throws Exception {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes ORDER BY nome";

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pacientes.add(criarPaciente(rs));
            }
        }
        return pacientes;
    }

    public Paciente buscar(int id) throws Exception {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarPaciente(rs);
                }
            }
        }
        return null;
    }

    public Paciente buscarPorCpf(String cpf) throws Exception {
        String sql = "SELECT * FROM pacientes WHERE cpf = ?";
        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cpf == null ? "" : cpf.replaceAll("\\D", ""));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarPaciente(rs);
                }
            }
        }
        return null;
    }

    private Paciente criarPaciente(ResultSet rs) throws Exception {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setNome(rs.getString("nome"));
        paciente.setCpf(rs.getString("cpf"));
        paciente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        paciente.setSexo(rs.getString("sexo"));
        paciente.setTelefone(rs.getString("telefone"));
        paciente.setEndereco(rs.getString("endereco"));
        paciente.setEmail(rs.getString("email"));
        return paciente;
    }
}
