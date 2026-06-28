/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Funcionarios.Funcionario;
import models.Funcionarios.Medico;
import utils.Criptografia;

/**
 *
 * @author eduardo-silva
 */
public class FuncionarioDAO {
    public Integer salvar(Funcionario func) throws Exception
     {
        
        Connection con = null;
        PreparedStatement ps = null;
        Conexao conexao = new Conexao();
        ResultSet rs = null;
    
        try {
           con = conexao.abrirConexao("localhost", "3306", "testizito","root","12345678");
           System.out.println("Conexão ok");
           String sql = "INSERT INTO usuario" + "(nome, cpf, telefone) VALUES" + "(?,?,?)";
           ps = con.prepareStatement(sql);
           ps.setString(1, func.getNome());
           ps.setString(2, func.getCpf());
           ps.setString(3, func.getTelefone());
          ps.executeUpdate();

            rs = ps.getGeneratedKeys();

        if (!rs.next()) {
          throw new Exception("Erro ao obter o ID gerado.");
        }
         
          return rs.getInt(1);
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            conexao.fecharConexao(con, ps, null);
            
        }
    }
}
