/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author eduardo-silva
 */
public class UsuarioDAO {
    /* DAO => Data  Access Object
       Objeto que centraliza o acesso de dados
    */
    
    public void cadastrar(Usuario user) throws Exception {
        if(user == null) {
            throw new Exception("Erro: Dados do Usuário vazio"); 
        }
        //Conex
        Connection con = null;
        //Sql a ser executado, com parâtros
        PreparedStatement ps = null;
        Conexao conexao = new Conexao();
        
        try {
            con = conexao.abrirConexao("localhost", "3306", "testizito","root","12345678");
           System.out.println("Conexão ok");
           String sql = "INSERT INTO usuario" + "(login, senha, permissao) VALUES" + "(?,?,?)";
           ps = con.prepareStatement(sql);
           // Parâmetros para substituirem as '?'â
           ps.setString(1, user.getLogin());
           ps.setString(2, user.getSenha());
           ps.setString(3, user.getPermissao());
           ps.executeUpdate();
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            // Executado independente se deu certo ou errado
            conexao.fecharConexao(con, ps, null);
        }
    }
        
    public Usuario Buscar(String login) throws Exception {
         if(login == null) {
            throw new Exception("Dado vazio");
        }
         if(login.length() < 3) {
             throw new Exception("Login inválido");
         }
         Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Conexao conexao  = new Conexao();
         try {
            con = conexao.abrirConexao("localhost", "3306", "testizito", "root", "12345678");
            String sql = "SELECT * FROM usuario WHERE login = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setLogin(login);
                user.setPermissao(rs.getString("permissao"));
                return user;
                
            }
            else 
                return null;
         } catch(Exception e) {
             throw new Exception(e.getMessage());
         } finally {
             conexao.fecharConexao(con, ps, rs);
         }
         
    }
    
     public Usuario Autenticar(String login, String senha) throws Exception {
         if(login == null) {
            throw new Exception("Dado vazio");
        }
         if(login.length() < 3) {
             throw new Exception("Login inválido");
         }
         if(senha == null) {
            throw new Exception("Dado vazio");
        }
         if(senha.length() != 128) {
             throw new Exception("Login inválido");
         }
         
         Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Conexao conexao  = new Conexao();
         try {
            con = conexao.abrirConexao("localhost", "3306", "testizito", "root", "12345678");
            String sql = "SELECT * FROM usuario WHERE login = ? AND " + "senha = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setLogin(login);
                user.setPermissao(rs.getString("permissao"));
                return user;
                
            }
            else 
                return null;
         } catch(Exception e) {
             throw new Exception(e.getMessage());
         } finally {
             conexao.fecharConexao(con, ps, rs);
         }
         
    }
     
     public void Desligar(int id) throws Exception{
        if(id < 1){
            throw new Exception("ID inválido");
        }
        
        Connection con = null;
        PreparedStatement ps = null;
        Conexao conexao = new Conexao();
    
        try{
            con = conexao.abrirConexao("localhost","3306","javaproject","root","mysql");
            String sql = "DELETE FROM usuario WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        
            
        
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }finally{
                conexao.fecharConexao(con, ps, null);
            }
    }
     
     public ArrayList<Usuario> Listar() throws Exception {
         
         Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Conexao conexao  = new Conexao();
         ArrayList<Usuario> resposta = new ArrayList();
         try {
            con = conexao.abrirConexao("localhost", "3306", "testizito", "root", "12345678");
            String sql = "SELECT * FROM usuario";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPermissao(rs.getString("permissao"));
                 resposta.add(user);
            }
            return resposta;
         } catch(Exception e) {
             throw new Exception(e.getMessage());
         } finally {
             conexao.fecharConexao(con, ps, rs);
         } 
    }
     
     public Usuario Buscar(int id) throws Exception{
        if(id == 0){
            throw new Exception("Dado vazio");
        }
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexao conexao = new Conexao();
    
        try{
            con = conexao.abrirConexao("localhost","3306","javaproject","root","mysql");
            String sql = "SELECT * FROM usuario WHERE login = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
        
            if(rs.next()){//achou o login
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPermissao(rs.getString("permissao"));
                return user;
            }else{
                //login não encontrado
                return null;
            }
        
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }finally{
                conexao.fecharConexao(con, ps, rs);
            }

    }
     
     
}
