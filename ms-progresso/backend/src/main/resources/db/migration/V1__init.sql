-- V1__init.sql - esquema inicial para MS Progresso (backend)

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

CREATE TABLE IF NOT EXISTS progresso_modulos (
  id BIGSERIAL PRIMARY KEY,
  inscricao_id BIGINT NOT NULL REFERENCES inscricoes(id) ON DELETE CASCADE,
  modulo_id VARCHAR(64) NOT NULL,
  data_inicio TIMESTAMPTZ,
  data_conclusao TIMESTAMPTZ,
  tempo_gasto_seconds BIGINT
);

CREATE TABLE IF NOT EXISTS certificados (
  id BIGSERIAL PRIMARY KEY,
  funcionario_id VARCHAR(64) NOT NULL,
  curso_id VARCHAR(64) NOT NULL,
  codigo_certificado VARCHAR(128) NOT NULL,
  data_emissao TIMESTAMPTZ,
  hash_validacao VARCHAR(256),
  url_pdf VARCHAR(512)
);

CREATE TABLE IF NOT EXISTS trilhas_progresso (
  id BIGSERIAL PRIMARY KEY,
  funcionario_id VARCHAR(64) NOT NULL,
  trilha_id VARCHAR(64) NOT NULL,
  progresso_percentual NUMERIC(5,2),
  cursos_concluidos INT DEFAULT 0
);
