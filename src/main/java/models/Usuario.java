/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Conexao.UsuarioDAO;
import Main.Main;
import javax.swing.JOptionPane;
import utils.Criptografia;

/**
 *
 * @author eduardo-silva
 */
public class Usuario {
    private int id;
    private String email;
    private String senhaHash;
    private String perfil;
    private Boolean ativo;
    
    public Usuario(){};
    
    public Usuario(int id, String email, String senhaHash, String perfil, Boolean ativo) {
        this.id = id;
        this.email = email;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    public Boolean autenticar(String email, String senha)
    {
        if(email.length() < 3)
        {   
            return false;
        }
        
        if(senha.length() < 3)
        {
            return false;
        }
        
        char pass =  senha;
        
        try {
            Criptografia crip = new Criptografia();
            String HashedPassword = crip.criptografar(senha);
            UsuarioDAO dao = new UsuarioDAO();
            Usuario user = dao.Autenticar(email, senha);
            if(user == null) {
                JOptionPane.showMessageDialog(this, "Erro: Credencial inválida");
            }
            else {
                Main tela = new Main(user);
                tela.setVisible(true);
                this.dispose();
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: "+ e.getMessage());
        }
        
    }
}
