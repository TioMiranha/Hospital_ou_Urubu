/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.Funcionarios;

/**
 *
 * @author eduardo-silva
 */
public class Medico extends Funcionario {
 

    private String crm;
    private String especialidade;
    
    public Medico(){};
    
    public Medico(Integer id, String nome, String cpf, String telefone, String crm,String especialidade ) {
        super(id, nome, cpf, telefone);
        this.crm = crm;
        this.especialidade = especialidade;
       
    }
    
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    

    
}
