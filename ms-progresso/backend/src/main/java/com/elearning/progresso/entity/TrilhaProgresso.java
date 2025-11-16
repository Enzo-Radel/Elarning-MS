package com.elearning.progresso.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trilhas_progresso")
public class TrilhaProgresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String funcionarioId;
    private String trilhaId;
    private double progressoPercentual;
    private int cursosConcluidos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(String funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getTrilhaId() { return trilhaId; }
    public void setTrilhaId(String trilhaId) { this.trilhaId = trilhaId; }
    public double getProgressoPercentual() { return progressoPercentual; }
    public void setProgressoPercentual(double progressoPercentual) { this.progressoPercentual = progressoPercentual; }
    public int getCursosConcluidos() { return cursosConcluidos; }
    public void setCursosConcluidos(int cursosConcluidos) { this.cursosConcluidos = cursosConcluidos; }
}
