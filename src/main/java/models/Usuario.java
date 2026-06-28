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
    private Integer funcionarioId;
    private String email;
    private String senhaHash;
    private String perfil;
    private Boolean ativo;
    private Boolean deveTrocarSenha;
    
    public Usuario(){};
    
    public Usuario(int id, String email, String senhaHash, String perfil, Boolean ativo, Boolean deveTrocarSenha, Integer funcionarioId) {
        this.id = id;
        this.email = email;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.ativo = ativo;
        this.deveTrocarSenha = deveTrocarSenha;
        this.funcionarioId = funcionarioId;
    }

    public int getId() {
        return id;
    }

    public Boolean getDeveTrocarSenha() {
        return deveTrocarSenha;
    }

    public void setDeveTrocarSenha(Boolean deveTrocarSenha) {
        this.deveTrocarSenha = deveTrocarSenha;
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

    public Integer getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Integer funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
    
}
