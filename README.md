# Elearning Microservices – Docker Compose scaffold

Este repositório contém um *scaffold* para rodar **um Compose por microsserviço** e também uma forma de **subir tudo de uma vez** usando um comando com múltiplos `-f`.  
Cada microsserviço Java possui seu **próprio banco** (PostgreSQL ou Mongo, conforme o caso). O `gateway-api` (Node/Express) **não possui banco**.

## Requisitos
- Docker e Docker Compose v2+
- (Opcional) `make` instalado para usar os atalhos do Makefile

## Rede compartilhada
Criamos uma rede externa para que cada Compose individual se conecte:
```bash
docker network create elearning-net || true
```
O `Makefile` faz isso automaticamente com `make net` ou em `make all-up` / `make all-up-app`.

## Subir somente infraestrutura + gateway
Por padrão, os serviços Java (apps) estão sob o profile `app`. Isso evita builds antes do código existir.
```bash
make all-up          # sobe gateway + bancos de todos MS
make all-logs        # logs agregados
make all-down        # derruba tudo
```

## Subir TAMBÉM as aplicações Java
Quando já houver código (pom.xml, src/ etc.) em cada `ms-*`, rode:
```bash
make all-up-app      # inclui profile app e tenta buildar/rodar as apps
```

## Subir um microsserviço isolado (ex.: ms-cursos)
Cada pasta possui seu próprio `docker-compose.yml`:
```bash
# apenas bancos/infra do ms-cursos
cd ms-cursos && docker compose up -d

# para incluir a aplicação Java do ms-cursos
cd ms-cursos && docker compose --profile app up -d
```

## Portas padrão (host)
- gateway-api: 8080
- ms-auth: 8082
- ms-usuarios: 8101
- ms-cursos: 8102
- ms-avaliacoes: 8103
- ms-progresso: 8104
- ms-gamificacao: 8105
- ms-notificacoes: 8106

Ajuste no `.env` da raiz e/ou `.env` de cada serviço conforme necessário.

## Observações
- Os Dockerfiles Java assumem um projeto Spring Boot padrão com `pom.xml` e `src/`.
- Adapte suas variáveis de ambiente, secrets e configurações de CORS/JWT nos seus `application-*.yml`.
- Se quiser que o Gateway faça *proxy* para os MS, edite `gateway-api/server.js` adicionando rotas ou `http-proxy-middleware`.
