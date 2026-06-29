package AtendimentoHospitalar;

import java.time.LocalDateTime;

/**
 * Prescrição registrada por um médico para um paciente.
 */
public class Receita {

    private int id;
    private int pacienteId;
    private Integer medicoId;
    private String pacienteNome;
    private String pacienteCpf;
    private String prescricao;
    private boolean aplicarNoHospital;
    private LocalDateTime dataEmissao;

    public Receita() {
    }

    public Receita(
            int id,
            int pacienteId,
            Integer medicoId,
            String pacienteNome,
            String pacienteCpf,
            String prescricao,
            boolean aplicarNoHospital,
            LocalDateTime dataEmissao) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.pacienteNome = pacienteNome;
        this.pacienteCpf = pacienteCpf;
        this.prescricao = prescricao;
        this.aplicarNoHospital = aplicarNoHospital;
        this.dataEmissao = dataEmissao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public String getPacienteCpf() {
        return pacienteCpf;
    }

    public void setPacienteCpf(String pacienteCpf) {
        this.pacienteCpf = pacienteCpf;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public boolean isAplicarNoHospital() {
        return aplicarNoHospital;
    }

    public void setAplicarNoHospital(boolean aplicarNoHospital) {
        this.aplicarNoHospital = aplicarNoHospital;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
