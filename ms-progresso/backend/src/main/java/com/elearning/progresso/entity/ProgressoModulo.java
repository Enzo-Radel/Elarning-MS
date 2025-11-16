package com.elearning.progresso.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "progresso_modulos")
public class ProgressoModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long inscricaoId;
    private String moduloId;
    private Instant dataInicio;
    private Instant dataConclusao;
    private long tempoGastoSeconds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getInscricaoId() { return inscricaoId; }
    public void setInscricaoId(Long inscricaoId) { this.inscricaoId = inscricaoId; }
    public String getModuloId() { return moduloId; }
    public void setModuloId(String moduloId) { this.moduloId = moduloId; }
    public Instant getDataInicio() { return dataInicio; }
    public void setDataInicio(Instant dataInicio) { this.dataInicio = dataInicio; }
    public Instant getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(Instant dataConclusao) { this.dataConclusao = dataConclusao; }
    public long getTempoGastoSeconds() { return tempoGastoSeconds; }
    public void setTempoGastoSeconds(long tempoGastoSeconds) { this.tempoGastoSeconds = tempoGastoSeconds; }
}
