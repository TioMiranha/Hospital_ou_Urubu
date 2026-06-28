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
     public Integer salvar(Medico med) throws Exception
     {
        
        Connection con = null;
        PreparedStatement ps = null;
        Conexao conexao = new Conexao();
        /*
         private String crm;
    private String especialidade;*/
        try {
           con = conexao.abrirConexao("localhost", "3306", "testizito","root","12345678");
           System.out.println("Conexão ok");
           String sql = "INSERT INTO usuario" + "(crm, especialidade) VALUES" + "(?,?)";
           ps = con.prepareStatement(sql);
           ps.setString(1, med.getCrm());
           ps.setString(2, med.getEspecialidade());
           ps.executeUpdate();
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            conexao.fecharConexao(con, ps, null);
            return 1;
        }
    }
}
