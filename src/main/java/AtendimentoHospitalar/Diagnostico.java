/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AtendimentoHospitalar;

import java.time.LocalDateTime;


/**
 *
 * @author eduardo-silva
 */
public class Diagnostico {
    private int id;
    private String descricao;
    private String observacoes;
    private LocalDateTime dataDiagnostico;

    public Diagnostico(int id, String descricao, String observacoes, LocalDateTime dataDiagnostico) {
        this.id = id;
        this.descricao = descricao;
        this.observacoes = observacoes;
        this.dataDiagnostico = dataDiagnostico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(LocalDateTime dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }
    
}
