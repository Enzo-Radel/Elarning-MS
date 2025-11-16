-- V1__init.sql - esquema inicial para MS Progresso

CREATE TABLE IF NOT EXISTS inscricoes (
  id BIGSERIAL PRIMARY KEY,
  funcionario_id VARCHAR(64) NOT NULL,
  curso_id VARCHAR(64) NOT NULL,
  data_inscricao TIMESTAMPTZ,
  data_inicio TIMESTAMPTZ,
  data_conclusao TIMESTAMPTZ,
  status VARCHAR(32),
  progresso_percentual NUMERIC(5,2)
);

CREATE INDEX IF NOT EXISTS idx_inscricoes_funcionario ON inscricoes(funcionario_id);
CREATE INDEX IF NOT EXISTS idx_inscricoes_curso ON inscricoes(curso_id);

CREATE TABLE IF NOT EXISTS progresso_modulos (
  id BIGSERIAL PRIMARY KEY,
  inscricao_id BIGINT NOT NULL REFERENCES inscricoes(id) ON DELETE CASCADE,
  modulo_id VARCHAR(64) NOT NULL,
  data_inicio TIMESTAMPTZ,
  data_conclusao TIMESTAMPTZ,
  tempo_gasto_seconds BIGINT
);

CREATE INDEX IF NOT EXISTS idx_progresso_inscricao ON progresso_modulos(inscricao_id);

CREATE TABLE IF NOT EXISTS certificados (
  id BIGSERIAL PRIMARY KEY,
  funcionario_id VARCHAR(64) NOT NULL,
  curso_id VARCHAR(64) NOT NULL,
  codigo_certificado VARCHAR(128) NOT NULL,
  data_emissao TIMESTAMPTZ,
  hash_validacao VARCHAR(256),
  url_pdf VARCHAR(512)
);

CREATE INDEX IF NOT EXISTS idx_certificados_funcionario ON certificados(funcionario_id);

-- Trilhas de Progresso (materializado por funcionário para trilhas personalizadas)
CREATE TABLE IF NOT EXISTS trilhas_progresso (
  id BIGSERIAL PRIMARY KEY,
  funcionario_id VARCHAR(64) NOT NULL,
  trilha_id VARCHAR(64) NOT NULL,
  progresso_percentual NUMERIC(5,2),
  cursos_concluidos INT DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_trilhas_funcionario ON trilhas_progresso(funcionario_id);
