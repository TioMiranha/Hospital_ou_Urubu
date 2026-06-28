/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Funcionarios;

import models.Usuario;

/**
 *
 * @author eduardo-silva
 */
public class Enfermeiro extends Funcionario {

    static String coren;
    
    public static String getCoren() {
        return coren;
    }

    public static void setCoren(String coren) {
        Enfermeiro.coren = coren;
    }
    
    public Enfermeiro(){};
    
    public Enfermeiro(Integer id, String nome, String cpf, String telefone, String email, Boolean ativo) {
        super(id, nome, cpf, telefone);
    }

   
}
