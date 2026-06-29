package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Funcionarios.Enfermeiro;

public class EnfermeiroDAO {

    public Integer salvar(Enfermeiro enfermeiro) throws Exception {
        String sql = "INSERT INTO enfermeiros (funcionario_id, coren) VALUES (?, ?)";

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, enfermeiro.getId());
            ps.setString(2, enfermeiro.getCoren());
            ps.executeUpdate();
            return enfermeiro.getId();
        }
    }

    public ArrayList<Enfermeiro> listar() throws Exception {
        ArrayList<Enfermeiro> enfermeiros = new ArrayList<>();
        String sql = """
                SELECT f.id, f.nome, f.cpf, f.telefone, e.coren
                FROM funcionarios f
                INNER JOIN enfermeiros e ON e.funcionario_id = f.id
                ORDER BY f.nome
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Enfermeiro enfermeiro = new Enfermeiro();
                enfermeiro.setId(rs.getInt("id"));
                enfermeiro.setNome(rs.getString("nome"));
                enfermeiro.setCpf(rs.getString("cpf"));
                enfermeiro.setTelefone(rs.getString("telefone"));
                enfermeiro.setCoren(rs.getString("coren"));
                enfermeiros.add(enfermeiro);
            }
        }
        return enfermeiros;
    }
}
