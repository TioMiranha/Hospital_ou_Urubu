/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import models.Funcionarios.Funcionario;
import models.Funcionarios.Medico;

/**
 *
 * @author eduardo-silva
 */
public class MedicoDAO {

    public Integer salvar(Medico med) throws Exception {
        if (med == null) {
            throw new Exception("Erro: dados do médico vazio.");
        }

        if (med.getId()<=0) {
            throw new Exception("Erro: ID do funcionário não definido para o médico.");
        }

        Connection con = null;
        PreparedStatement ps = null;
        Conexao conexao = new Conexao();

        try {
            con = conexao.abrirConexao(
                "localhost",
                "3306",
                "testizito",
                "root",
                "12345678"
            );

            System.out.println("Conexão ok");

            String sql = """
                INSERT INTO medicos 
                (funcionario_id, crm, especialidade)
                VALUES (?, ?, ?)
            """;

            ps = con.prepareStatement(sql);

            ps.setInt(1, med.getId());
            ps.setString(2, med.getCrm());
            ps.setString(3, med.getEspecialidade());

            ps.executeUpdate();

            return med.getId();

        } catch (Exception e) {
            throw new Exception("Erro ao salvar médico: " + e.getMessage());
        } finally {
            conexao.fecharConexao(con, ps, null);
        }
    }
}