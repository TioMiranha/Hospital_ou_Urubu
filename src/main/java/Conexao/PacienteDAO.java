/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import models.Funcionarios.Medico;
import models.Pacientes.Paciente;

/**
 *
 * @author eduardo-silva
 */
public class PacienteDAO {

    public Integer salvar(Paciente pac) throws Exception {
        if (pac == null) {
            throw new Exception("Erro: dados do paciente vazio.");
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

            System.out.println("Conexão ok");

            String sql = """
                INSERT INTO paciente 
                (nome, cpf, dataDeNascimento, sexo, email, telefone)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, pac.getNome());
            ps.setString(2, pac.getCpf());
            ps.setDate(3, java.sql.Date.valueOf(pac.getDataNascimento()));
            ps.setString(4, pac.getSexo());
            ps.setString(5, pac.getEmail());
            ps.setString(6, pac.getTelefone());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                Integer idGerado = rs.getInt(1);
                pac.setId(idGerado);
                return idGerado;
            }

            throw new Exception("Erro ao obter ID do paciente cadastrado.");

        } catch (Exception e) {
            throw new Exception("Erro ao salvar paciente: " + e.getMessage());
        } finally {
            conexao.fecharConexao(con, ps, rs);
        }
    }
}