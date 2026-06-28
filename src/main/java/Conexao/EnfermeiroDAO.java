/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import models.Funcionarios.Enfermeiro;

/**
 *
 * @author eduardo-silva
 */
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EnfermeiroDAO {

    public Integer salvar(Enfermeiro enf) throws Exception {
        if (enf == null) {
            throw new Exception("Erro: dados do enfermeiro vazio.");
        }

        if (enf.getId()<=0) {
            throw new Exception("Erro: ID do funcionário não definido para o enfermeiro.");
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
                INSERT INTO enfermeiros 
                (funcionario_id, coren)
                VALUES (?, ?)
            """;

            ps = con.prepareStatement(sql);

            ps.setInt(1, enf.getId());
            ps.setString(2, enf.getCoren());

            ps.executeUpdate();

            return enf.getId();

        } catch (Exception e) {
            throw new Exception("Erro ao salvar enfermeiro: " + e.getMessage());
        } finally {
            conexao.fecharConexao(con, ps, null);
        }
    }
}