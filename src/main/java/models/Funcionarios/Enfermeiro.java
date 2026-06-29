/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Funcionarios;

/**
 *
 * @author eduardo-silva
 */
public class Enfermeiro extends Funcionario {

    private String coren;
    
    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }
    
    public Enfermeiro(){};
    
    public Enfermeiro(Integer id, String nome, String cpf, String telefone, String email, Boolean ativo) {
        super(id, nome, cpf, telefone);
    }

   
}
