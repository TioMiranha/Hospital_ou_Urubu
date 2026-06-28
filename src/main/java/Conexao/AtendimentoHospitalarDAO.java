/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import AtendimentoHospitalar.AplicacaoMedicamento;
import AtendimentoHospitalar.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author eduardo-silva
 */
public class AtendimentoHospitalarDAO {
    
    /*
         private int id;
    private String dosagemAplicada;
    private String viaAplicacao;
    private LocalDateTime dataAplicacao;
    private String observacoes;
    */
    
   public Integer salvarAplicacaoMedicamento(AplicacaoMedicamento addMed) throws Exception {
    if (addMed == null) {
        throw new Exception("Erro: dados da aplicação de medicamento vazios.");
    }

    if (addMed.getDosagemAplicada() == null || addMed.getDosagemAplicada().trim().isEmpty()) {
        throw new Exception("Erro: dosagem aplicada não informada.");
    }

    if (addMed.getViaAplicacao() == null || addMed.getViaAplicacao().trim().isEmpty()) {
        throw new Exception("Erro: via de aplicação não informada.");
    }

    if (addMed.getDataAplicacao() == null) {
        throw new Exception("Erro: data da aplicação não informada.");
    }

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Conexao conexao = new Conexao();

    try {
        con = conexao.abrirConexao(
            "localhost",
            "3306",
            "testizito",
            "root",
            "12345678"
        );

        String sql = """
            INSERT INTO aplicacoes_medicamento
            (dosagemAplicada, viaAplicacao, dataAplicacao, observacoes)
            VALUES (?, ?, ?, ?)
        """;

        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, addMed.getDosagemAplicada());
        ps.setString(2, addMed.getViaAplicacao());
        ps.setDate(3, java.sql.Date.valueOf(addMed.getDataAplicacao()));
        ps.setString(4, addMed.getObservacoes());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();

        if (rs.next()) {
            Integer idGerado = rs.getInt(1);
            addMed.setId(idGerado);
            return idGerado;
        }

        throw new Exception("Erro ao obter ID da aplicação de medicamento.");

    } catch (Exception e) {
        throw new Exception("Erro ao salvar aplicação de medicamento: " + e.getMessage());
    } finally {
        conexao.fecharConexao(con, ps, rs);
    }
}  
   
   public Integer salvarReceita(Medicamento addRec) throws Exception {
    if (addRec == null) {
        throw new Exception("Erro: dados da aplicação de medicamento vazios.");
    }

    if (addRec.getDosagemPadrao() == null || addRec.getDosagemPadrao().trim().isEmpty()) {
        throw new Exception("Erro: dosagem aplicada não informada.");
    }

    if (addRec.getDescricao() == null || addRec.getDescricao().trim().isEmpty()) {
        throw new Exception("Erro: via de aplicação não informada.");
    }

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Conexao conexao = new Conexao();

    try {
        con = conexao.abrirConexao(
            "localhost",
            "3306",
            "testizito",
            "root",
            "12345678"
        );

        String sql = """
            INSERT INTO aplicacoes_medicamento
            (dosagemAplicada, viaAplicacao, dataAplicacao, observacoes)
            VALUES (?, ?, ?, ?)
        """;

        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, addMed.getDosagemAplicada());
        ps.setString(2, addMed.getViaAplicacao());
        ps.setDate(3, java.sql.Date.valueOf(addMed.getDataAplicacao()));
        ps.setString(4, addMed.getObservacoes());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();

        if (rs.next()) {
            Integer idGerado = rs.getInt(1);
            addMed.setId(idGerado);
            return idGerado;
        }

        throw new Exception("Erro ao obter ID da aplicação de medicamento.");

    } catch (Exception e) {
        throw new Exception("Erro ao salvar aplicação de medicamento: " + e.getMessage());
    } finally {
        conexao.fecharConexao(con, ps, rs);
    }
}  
    
}
