package com.elearning.progresso.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.progresso.entity.Certificado;
import com.elearning.progresso.entity.Inscricao;
import com.elearning.progresso.repository.CertificadoRepository;
import com.elearning.progresso.repository.InscricaoRepository;
import com.elearning.progresso.repository.ProgressoModuloRepository;

@Service
public class QueryService {
    private final InscricaoRepository inscricaoRepository;
    private final ProgressoModuloRepository progressoRepository;
    private final CertificadoRepository certificadoRepository;

    public QueryService(InscricaoRepository inscricaoRepository,
                        ProgressoModuloRepository progressoRepository,
                        CertificadoRepository certificadoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.progressoRepository = progressoRepository;
        this.certificadoRepository = certificadoRepository;
    }

    public Inscricao getInscricao(Long id) { return inscricaoRepository.findById(id).orElse(null); }
    public List<Inscricao> listByFuncionario(String funcionarioId) { return inscricaoRepository.findByFuncionarioId(funcionarioId); }
    public List<Certificado> certificadosByFuncionario(String funcionarioId) { return certificadoRepository.findByFuncionarioId(funcionarioId); }
}
