/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AtendimentoHospitalar;

/**
 *
 * @author eduardo-silva
 */
public class Medicamento {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private String dosagemPadrao;

    public Medicamento(int id, String nome, String descricao, String tipo, String dosagemPadrao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dosagemPadrao = dosagemPadrao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDosagemPadrao() {
        return dosagemPadrao;
    }

    public void setDosagemPadrao(String dosagemPadrao) {
        this.dosagemPadrao = dosagemPadrao;
    }
    
}
