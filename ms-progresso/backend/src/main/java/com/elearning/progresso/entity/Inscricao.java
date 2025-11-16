package com.elearning.progresso.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "inscricoes")
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String funcionarioId;
    private String cursoId;
    private Instant dataInscricao;
    private Instant dataInicio;
    private Instant dataConclusao;
    private String status;
    private double progressoPercentual;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(String funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public Instant getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(Instant dataInscricao) { this.dataInscricao = dataInscricao; }
    public Instant getDataInicio() { return dataInicio; }
    public void setDataInicio(Instant dataInicio) { this.dataInicio = dataInicio; }
    public Instant getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(Instant dataConclusao) { this.dataConclusao = dataConclusao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getProgressoPercentual() { return progressoPercentual; }
    public void setProgressoPercentual(double progressoPercentual) { this.progressoPercentual = progressoPercentual; }
}
