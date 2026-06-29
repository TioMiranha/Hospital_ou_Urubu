/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;

/**
 *
 * @author eduardo-silva
 */
public class TestarConexao {
    public static void main(String[] args) {
        Conexao con = new Conexao();
        try {
            Connection c = con.abrirConexao();
           System.out.println("Conexão ok");
           con.fecharConexao(c, null, null);
        }
        catch(Exception e) {
            System.out.println("Erro:"+e.getMessage());
        }
    }
    
}
