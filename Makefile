    NETWORK=elearning-net

    .PHONY: net all-up all-up-app all-down all-logs clean volumes

    net:
		docker network create $(NETWORK) || true

    all-up: net
		docker compose -f gateway-api/docker-compose.yml -f ms-auth/docker-compose.yml -f ms-usuarios/docker-compose.yml -f ms-cursos/docker-compose.yml -f ms-avaliacoes/docker-compose.yml -f ms-progresso/docker-compose.yml -f ms-gamificacao/docker-compose.yml -f ms-notificacoes/docker-compose.yml up -d

    all-up-app: net
		docker compose -f gateway-api/docker-compose.yml -f ms-auth/docker-compose.yml -f ms-usuarios/docker-compose.yml -f ms-cursos/docker-compose.yml -f ms-avaliacoes/docker-compose.yml -f ms-progresso/docker-compose.yml -f ms-gamificacao/docker-compose.yml -f ms-notificacoes/docker-compose.yml --profile app up -d --build

    all-down:
		docker compose -f gateway-api/docker-compose.yml -f ms-auth/docker-compose.yml -f ms-usuarios/docker-compose.yml -f ms-cursos/docker-compose.yml -f ms-avaliacoes/docker-compose.yml -f ms-progresso/docker-compose.yml -f ms-gamificacao/docker-compose.yml -f ms-notificacoes/docker-compose.yml down

    all-logs:
		docker compose -f gateway-api/docker-compose.yml -f ms-auth/docker-compose.yml -f ms-usuarios/docker-compose.yml -f ms-cursos/docker-compose.yml -f ms-avaliacoes/docker-compose.yml -f ms-progresso/docker-compose.yml -f ms-gamificacao/docker-compose.yml -f ms-notificacoes/docker-compose.yml logs -f --tail=200

    clean:
		docker compose -f gateway-api/docker-compose.yml -f ms-auth/docker-compose.yml -f ms-usuarios/docker-compose.yml -f ms-cursos/docker-compose.yml -f ms-avaliacoes/docker-compose.yml -f ms-progresso/docker-compose.yml -f ms-gamificacao/docker-compose.yml -f ms-notificacoes/docker-compose.yml down -v || true

    volumes:
		docker volume ls | grep elearning-ms || true
