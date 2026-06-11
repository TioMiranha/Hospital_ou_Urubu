/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author eduardo-silva
 */
public class Conexao {
      public Connection abrirConexao(String servidor, String porta, String dataBase, String usuario, String senha) throws Exception {
        
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        //Criar url da conexão
        String url = "jdbc:mysql://"+servidor+":"+porta+"/"+dataBase+"?useTimezone=true&serverTimeZone=UTC";
        //Abre a conexão e devolve o resultado
        return DriverManager.getConnection(url, usuario, senha);
        } catch(Exception e) {
            //Devolve o erro 
            throw new Exception(e.getMessage());
        }
    }
    
    public void fecharConexao(Connection con, Statement stmt, ResultSet rs) throws Exception {
        try {
            if(rs != null){rs.close();}
            else if(stmt != null) {stmt.close();}
            else if(con != null){con.close();}
    }catch(Exception e) {
            throw new Exception(e.getMessage());
    }
    /*
     Connection => Conexão com o DB
        Statement => SQL a ser executado
        ResultSet => resultado da execução do SQL
        */
    }
}
