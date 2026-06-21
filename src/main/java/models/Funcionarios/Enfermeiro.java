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
public class Enfermeiro {

    public static String getCoren() {
        return coren;
    }

    public static void setCoren(String coren) {
        Enfermeiro.coren = coren;
    }
    static String coren;
    
    public Enfermeiro(Funcionario func, String coren)
    {
        super();
        this.coren = coren;
    }
    
    
}
