/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Relatorios;

import java.time.LocalDateTime;

/**
 *
 * @author eduardo-silva
 */
public class Relatorios {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
    private String tipo;
    private LocalDateTime dataGeracao;

    public Relatorios(int id, String tipo, LocalDateTime dataGeracao) {
        this.id = id;
        this.tipo = tipo;
        this.dataGeracao = dataGeracao;
    }
    
}
