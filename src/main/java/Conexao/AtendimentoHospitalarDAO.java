package Conexao;

import AtendimentoHospitalar.AplicacaoMedicamento;
import AtendimentoHospitalar.Diagnostico;
import AtendimentoHospitalar.Receita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoHospitalarDAO {

    public Integer salvarReceita(Receita receita) throws Exception {
        validarReceita(receita);

        String sql = """
                INSERT INTO receitas
                (paciente_id, medico_id, prescricao, aplicar_no_hospital, data_emissao)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, receita.getPacienteId());
            definirInteiroOuNulo(ps, 2, receita.getMedicoId());
            ps.setString(3, receita.getPrescricao());
            ps.setBoolean(4, receita.isAplicarNoHospital());
            ps.setTimestamp(5, Timestamp.valueOf(
                    receita.getDataEmissao() == null ? LocalDateTime.now() : receita.getDataEmissao()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    receita.setId(rs.getInt(1));
                    return receita.getId();
                }
            }
        }
        throw new Exception("Não foi possível registrar a receita.");
    }

    public Receita buscarReceita(int id) throws Exception {
        String sql = """
                SELECT r.*, p.nome AS paciente_nome, p.cpf AS paciente_cpf
                FROM receitas r
                INNER JOIN pacientes p ON p.id = r.paciente_id
                WHERE r.id = ?
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarReceita(rs);
                }
            }
        }
        return null;
    }

    public Integer salvarAplicacaoMedicamento(AplicacaoMedicamento aplicacao) throws Exception {
        validarAplicacao(aplicacao);

        Receita receita = buscarReceita(aplicacao.getReceitaId());
        if (receita == null) {
            throw new Exception("Receita não encontrada.");
        }
        if (!receita.isAplicarNoHospital()) {
            throw new Exception("A receita não autoriza aplicação no hospital.");
        }
        if (receita.getPacienteId() != aplicacao.getPacienteId()) {
            throw new Exception("O paciente informado não pertence à receita.");
        }

        String sql = """
                INSERT INTO aplicacoes_medicamento
                (receita_id, paciente_id, enfermeiro_id, dosagem_aplicada,
                 via_aplicacao, observacoes, data_aplicacao)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, aplicacao.getReceitaId());
            ps.setInt(2, aplicacao.getPacienteId());
            definirInteiroOuNulo(ps, 3, aplicacao.getEnfermeiroId());
            ps.setString(4, aplicacao.getDosagemAplicada());
            ps.setString(5, aplicacao.getViaAplicacao());
            ps.setString(6, aplicacao.getObservacoes());
            ps.setTimestamp(7, Timestamp.valueOf(
                    aplicacao.getDataAplicacao() == null
                            ? LocalDateTime.now() : aplicacao.getDataAplicacao()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    aplicacao.setId(rs.getInt(1));
                    return aplicacao.getId();
                }
            }
        }
        throw new Exception("Não foi possível registrar a aplicação.");
    }

    public List<AplicacaoMedicamento> listarAplicacoesPorReceita(int receitaId) throws Exception {
        List<AplicacaoMedicamento> aplicacoes = new ArrayList<>();
        String sql = """
                SELECT * FROM aplicacoes_medicamento
                WHERE receita_id = ?
                ORDER BY data_aplicacao
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, receitaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aplicacoes.add(criarAplicacao(rs));
                }
            }
        }
        return aplicacoes;
    }

    public Integer salvarDiagnostico(Diagnostico diagnostico) throws Exception {
        if (diagnostico == null || diagnostico.getDescricao() == null
                || diagnostico.getDescricao().isBlank()) {
            throw new Exception("Descrição do diagnóstico não informada.");
        }

        String sql = """
                INSERT INTO diagnosticos
                (paciente_id, medico_id, descricao, observacoes, data_diagnostico)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, diagnostico.getPacienteId());
            definirInteiroOuNulo(ps, 2, diagnostico.getMedicoId());
            ps.setString(3, diagnostico.getDescricao());
            ps.setString(4, diagnostico.getObservacoes());
            ps.setTimestamp(5, Timestamp.valueOf(
                    diagnostico.getDataDiagnostico() == null
                            ? LocalDateTime.now() : diagnostico.getDataDiagnostico()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    diagnostico.setId(rs.getInt(1));
                    return diagnostico.getId();
                }
            }
        }
        throw new Exception("Não foi possível registrar o diagnóstico.");
    }

    public List<Diagnostico> listarDiagnosticosPorPaciente(int pacienteId) throws Exception {
        List<Diagnostico> diagnosticos = new ArrayList<>();
        String sql = """
                SELECT d.*, p.nome AS paciente_nome, p.cpf AS paciente_cpf
                FROM diagnosticos d
                INNER JOIN pacientes p ON p.id = d.paciente_id
                WHERE d.paciente_id = ?
                ORDER BY d.data_diagnostico
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pacienteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    diagnosticos.add(criarDiagnostico(rs));
                }
            }
        }
        return diagnosticos;
    }

    public List<Receita> listarReceitasPorPaciente(int pacienteId) throws Exception {
        List<Receita> receitas = new ArrayList<>();
        String sql = """
                SELECT r.*, p.nome AS paciente_nome, p.cpf AS paciente_cpf
                FROM receitas r
                INNER JOIN pacientes p ON p.id = r.paciente_id
                WHERE r.paciente_id = ?
                ORDER BY r.data_emissao
                """;

        try (Connection con = new Conexao().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pacienteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    receitas.add(criarReceita(rs));
                }
            }
        }
        return receitas;
    }

    private Receita criarReceita(ResultSet rs) throws Exception {
        Receita receita = new Receita();
        receita.setId(rs.getInt("id"));
        receita.setPacienteId(rs.getInt("paciente_id"));
        receita.setMedicoId(lerInteiroOuNulo(rs, "medico_id"));
        receita.setPacienteNome(rs.getString("paciente_nome"));
        receita.setPacienteCpf(rs.getString("paciente_cpf"));
        receita.setPrescricao(rs.getString("prescricao"));
        receita.setAplicarNoHospital(rs.getBoolean("aplicar_no_hospital"));
        receita.setDataEmissao(rs.getTimestamp("data_emissao").toLocalDateTime());
        return receita;
    }

    private Diagnostico criarDiagnostico(ResultSet rs) throws Exception {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(rs.getInt("id"));
        diagnostico.setPacienteId(rs.getInt("paciente_id"));
        diagnostico.setMedicoId(lerInteiroOuNulo(rs, "medico_id"));
        diagnostico.setPacienteNome(rs.getString("paciente_nome"));
        diagnostico.setPacienteCpf(rs.getString("paciente_cpf"));
        diagnostico.setDescricao(rs.getString("descricao"));
        diagnostico.setObservacoes(rs.getString("observacoes"));
        diagnostico.setDataDiagnostico(rs.getTimestamp("data_diagnostico").toLocalDateTime());
        return diagnostico;
    }

    private AplicacaoMedicamento criarAplicacao(ResultSet rs) throws Exception {
        AplicacaoMedicamento aplicacao = new AplicacaoMedicamento();
        aplicacao.setId(rs.getInt("id"));
        aplicacao.setReceitaId(rs.getInt("receita_id"));
        aplicacao.setPacienteId(rs.getInt("paciente_id"));
        aplicacao.setEnfermeiroId(lerInteiroOuNulo(rs, "enfermeiro_id"));
        aplicacao.setDosagemAplicada(rs.getString("dosagem_aplicada"));
        aplicacao.setViaAplicacao(rs.getString("via_aplicacao"));
        aplicacao.setObservacoes(rs.getString("observacoes"));
        aplicacao.setDataAplicacao(rs.getTimestamp("data_aplicacao").toLocalDateTime());
        return aplicacao;
    }

    private void validarReceita(Receita receita) throws Exception {
        if (receita == null || receita.getPacienteId() <= 0) {
            throw new Exception("Dados da receita inválidos.");
        }
        if (receita.getPrescricao() == null || receita.getPrescricao().isBlank()) {
            throw new Exception("Descrição da receita não informada.");
        }
    }

    private void validarAplicacao(AplicacaoMedicamento aplicacao) throws Exception {
        if (aplicacao == null || aplicacao.getReceitaId() <= 0) {
            throw new Exception("Dados da aplicação inválidos.");
        }
        if (aplicacao.getDosagemAplicada() == null
                || aplicacao.getDosagemAplicada().isBlank()) {
            throw new Exception("Dosagem aplicada não informada.");
        }
        if (aplicacao.getViaAplicacao() == null
                || aplicacao.getViaAplicacao().isBlank()) {
            throw new Exception("Via de aplicação não informada.");
        }
    }

    private void definirInteiroOuNulo(PreparedStatement ps, int indice, Integer valor)
            throws Exception {
        if (valor == null) {
            ps.setNull(indice, java.sql.Types.INTEGER);
        } else {
            ps.setInt(indice, valor);
        }
    }

    private Integer lerInteiroOuNulo(ResultSet rs, String coluna) throws Exception {
        int valor = rs.getInt(coluna);
        return rs.wasNull() ? null : valor;
    }
}
