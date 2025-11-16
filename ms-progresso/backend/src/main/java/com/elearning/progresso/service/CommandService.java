package com.elearning.progresso.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.elearning.progresso.entity.Certificado;
import com.elearning.progresso.entity.Inscricao;
import com.elearning.progresso.entity.ProgressoModulo;
import com.elearning.progresso.repository.CertificadoRepository;
import com.elearning.progresso.repository.InscricaoRepository;
import com.elearning.progresso.repository.ProgressoModuloRepository;

@Service
public class CommandService {
    private final InscricaoRepository inscricaoRepository;
    private final ProgressoModuloRepository progressoRepository;
    private final CertificadoRepository certificadoRepository;
    private final EventPublisher publisher;

    public CommandService(InscricaoRepository inscricaoRepository,
                          ProgressoModuloRepository progressoRepository,
                          CertificadoRepository certificadoRepository,
                          EventPublisher publisher) {
        this.inscricaoRepository = inscricaoRepository;
        this.progressoRepository = progressoRepository;
        this.certificadoRepository = certificadoRepository;
        this.publisher = publisher;
    }

    public Inscricao createInscricao(String funcionarioId, String cursoId) {
        Inscricao i = new Inscricao();
        i.setFuncionarioId(funcionarioId);
        i.setCursoId(cursoId);
        i.setDataInscricao(Instant.now());
        i.setStatus("EM_ANDAMENTO");
        i.setProgressoPercentual(0.0);
        Inscricao saved = inscricaoRepository.save(i);

        Map<String,Object> payload = new HashMap<>();
        payload.put("inscricaoId", saved.getId());
        payload.put("funcionarioId", funcionarioId);
        payload.put("cursoId", cursoId);
        publisher.publish("InscricaoCriada", payload);

        return saved;
    }

    public ProgressoModulo completeModulo(Long inscricaoId, String moduloId, long tempoGastoSeconds) {
        ProgressoModulo p = new ProgressoModulo();
        p.setInscricaoId(inscricaoId);
        p.setModuloId(moduloId);
        p.setDataInicio(Instant.now().minusSeconds(tempoGastoSeconds));
        p.setDataConclusao(Instant.now());
        p.setTempoGastoSeconds(tempoGastoSeconds);
        ProgressoModulo saved = progressoRepository.save(p);

        Map<String,Object> payload = new HashMap<>();
        payload.put("inscricaoId", inscricaoId);
        payload.put("moduloId", moduloId);
        payload.put("tempoGastoSeconds", tempoGastoSeconds);
        publisher.publish("ModuloConcluido", payload);

        inscricaoRepository.findById(inscricaoId).ifPresent(inscricao -> {
            double newProg = Math.min(100.0, inscricao.getProgressoPercentual() + 10.0);
            inscricao.setProgressoPercentual(newProg);
            if (newProg >= 100.0) {
                inscricao.setStatus("CONCLUIDA");
                inscricao.setDataConclusao(Instant.now());
                Map<String,Object> certPayload = new HashMap<>();
                certPayload.put("inscricaoId", inscricao.getId());
                certPayload.put("funcionarioId", inscricao.getFuncionarioId());
                certPayload.put("cursoId", inscricao.getCursoId());
                publisher.publish("CursoConcluido", certPayload);
            }
            inscricaoRepository.save(inscricao);
        });

        return saved;
    }

    public Certificado emitirCertificado(String funcionarioId, String cursoId) {
        Certificado c = new Certificado();
        c.setFuncionarioId(funcionarioId);
        c.setCursoId(cursoId);
        c.setCodigoCertificado("CERT-" + System.currentTimeMillis());
        c.setDataEmissao(Instant.now());
        c.setHashValidacao("hash-placeholder");
        c.setUrlPdf("/certs/" + c.getCodigoCertificado() + ".pdf");
        Certificado saved = certificadoRepository.save(c);

        Map<String,Object> payload = new HashMap<>();
        payload.put("certificadoId", saved.getId());
        payload.put("funcionarioId", funcionarioId);
        payload.put("cursoId", cursoId);
        publisher.publish("CertificadoEmitido", payload);

        return saved;
    }
}
