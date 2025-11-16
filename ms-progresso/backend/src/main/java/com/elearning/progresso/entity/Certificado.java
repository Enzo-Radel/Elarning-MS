package com.elearning.progresso.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "certificados")
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String funcionarioId;
    private String cursoId;
    private String codigoCertificado;
    private Instant dataEmissao;
    private String hashValidacao;
    private String urlPdf;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(String funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public String getCodigoCertificado() { return codigoCertificado; }
    public void setCodigoCertificado(String codigoCertificado) { this.codigoCertificado = codigoCertificado; }
    public Instant getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(Instant dataEmissao) { this.dataEmissao = dataEmissao; }
    public String getHashValidacao() { return hashValidacao; }
    public void setHashValidacao(String hashValidacao) { this.hashValidacao = hashValidacao; }
    public String getUrlPdf() { return urlPdf; }
    public void setUrlPdf(String urlPdf) { this.urlPdf = urlPdf; }
}
