/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Funcionarios.Medico;
import models.Usuario;
import models.Usuario;
import utils.Criptografia;

/**
 *
 * @author eduardo-silva
 */
public class UsuarioDAO {
  
    
    public void Cadastrar(Usuario user)  throws Exception{
        if(user == null) {
            throw new Exception("Erro: Dados do Usuário vazio"); 
        }
        Connection con = null;
        PreparedStatement ps = null;
        Conexao conexao = new Conexao();
        
        try {
           con = conexao.abrirConexao("localhost", "3306", "testizito","root","12345678");
           String sql = "INSERT INTO usuario" + "(email, senhaHash, perfil) VALUES" + "(?,?,?)";
           ps = con.prepareStatement(sql);
           ps.setString(1, user.getEmail());
           ps.setString(2, user.getSenhaHash());
           ps.setString(3, user.getPerfil());
           ps.executeUpdate();
        } catch(Exception e) {
              throw new Exception(e.getMessage());
        } finally {
            conexao.fecharConexao(con, ps, null);
        }
    }
    
    public void ChangeUserPassword(Usuario user) throws Exception {
    if (user == null) {
        throw new Exception("Erro: Dados do usuário vazio");
    }

    if (user.getId()<0) {
        throw new Exception("Erro: ID do usuário vazio");
    }

    if (user.getSenhaHash() == null || user.getSenhaHash().trim().isEmpty()) {
        throw new Exception("Erro: Senha vazia");
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

        String sql = """
            UPDATE usuario 
            SET senhaHash = ?, deveTrocarSenha = ?
            WHERE id = ?
        """;

        ps = con.prepareStatement(sql);

        ps.setString(1, user.getSenhaHash());
        ps.setBoolean(2, false);
        ps.setInt(3, user.getId());

        int linhasAfetadas = ps.executeUpdate();

        if (linhasAfetadas == 0) {
            throw new Exception("Nenhum usuário encontrado para alterar a senha.");
        }

    } catch (Exception e) {
        throw new Exception("Erro ao alterar senha: " + e.getMessage());
    } finally {
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
                user.setEmail(login);
                user.setPerfil(rs.getString("permissao"));
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
    
     public Usuario Autenticar(String email, String senha) throws Exception {
         
         if(senha.length() != 128) {
             throw new Exception("Senha inválida");
         }
         
         Connection con = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Conexao conexao  = new Conexao();
         try {
            con = conexao.abrirConexao("localhost", "3306", "testizito", "root", "12345678");
            String sql = "SELECT * FROM usuario WHERE login = ? AND " + "senha = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setEmail(email);
                user.setPerfil(rs.getString("perfil"));
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
                user.setEmail(rs.getString("email"));
                user.setPerfil(rs.getString("perfil"));
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
                user.setEmail(rs.getString("email"));
                user.setPerfil(rs.getString("perfil"));
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
     
    public boolean existeAdmin() throws Exception {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Conexao conexao = new Conexao();

    try {
        con = conexao.abrirConexao("localhost", "3306", "testizito", "root", "12345678");

        String sql = "SELECT id FROM usuarios WHERE perfil = ? LIMIT 1";

        ps = con.prepareStatement(sql);
        ps.setString(1, "ADMIN");

        rs = ps.executeQuery();

        return rs.next();

    } catch (Exception e) {
        throw new Exception("Erro ao verificar admin: " + e.getMessage());
    } finally {
        conexao.fecharConexao(con, ps, rs);
    }
}
     
     
}
        
    

