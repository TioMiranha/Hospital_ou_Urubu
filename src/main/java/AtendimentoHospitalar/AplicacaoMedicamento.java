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
public class AplicacaoMedicamento {
    private int id;
    private String dosagemAplicada;
    private String viaAplicacao;
    private LocalDateTime dataAplicacao;
    private String observacoes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDosagemAplicada() {
        return dosagemAplicada;
    }

    public void setDosagemAplicada(String dosagemAplicada) {
        this.dosagemAplicada = dosagemAplicada;
    }

    public String getViaAplicacao() {
        return viaAplicacao;
    }

    public void setViaAplicacao(String viaAplicacao) {
        this.viaAplicacao = viaAplicacao;
    }

    public LocalDateTime getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDateTime dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public AplicacaoMedicamento(int id, String dosagemAplicada, String viaAplicacao, LocalDateTime dataAplicacao, String observacoes) {
        this.id = id;
        this.dosagemAplicada = dosagemAplicada;
        this.viaAplicacao = viaAplicacao;
        this.dataAplicacao = dataAplicacao;
        this.observacoes = observacoes;
    }
}
