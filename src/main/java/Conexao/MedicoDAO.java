package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Funcionarios.Medico;

public class MedicoDAO {

    public Integer salvar(Medico medico) throws Exception {
        String sql = """
                INSERT INTO medicos (funcionario_id, crm, especialidade)
                VALUES (?, ?, ?)
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, medico.getId());
            ps.setString(2, medico.getCrm());
            ps.setString(3, medico.getEspecialidade());
            ps.executeUpdate();
            return medico.getId();
        }
    }

    public ArrayList<Medico> listar() throws Exception {
        ArrayList<Medico> medicos = new ArrayList<>();
        String sql = """
                SELECT f.id, f.nome, f.cpf, f.telefone, m.crm, m.especialidade
                FROM funcionarios f
                INNER JOIN medicos m ON m.funcionario_id = f.id
                ORDER BY f.nome
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medicos.add(medico);
            }
        }
        return medicos;
    }
}
