# Luci Modas – Sistema de Gestão de Produção

Este projeto é um **backend completo em Spring Boot** desenvolvido para a confecção **Luci Modas**, com o objetivo de registrar e gerenciar todas as produções de roupas, incluindo **dados, imagens e cálculos automáticos** de valor total.

O sistema funciona em **ambiente local**, utilizando o banco H2, e está preparado para **CRUD completo**: criar, listar, atualizar e deletar produções.

---

## 🧱 Arquitetura do Projeto

O projeto segue o padrão **MVC + Service Layer**, garantindo organização e separação de responsabilidades:

- **Model**: entidade `Producao` com todos os campos da produção e caminho da imagem.
- **Repository**: interface `ProducaoRepository` para persistência de dados no banco.
- **Service**: `ProducaoService` contendo regras de negócio, como cálculo automático do valor total.
- **Controller**: `ProducaoController` expondo os endpoints REST para cadastro, listagem, atualização e exclusão.

---

## 💻 Funcionalidades

O backend implementa as seguintes funcionalidades:

1. **Cadastrar produção com imagem**
   - Upload de uma ou várias imagens
   - Recebe JSON da produção
   - Calcula automaticamente o valor total (`quantidade x valorUnitario`)

2. **Listar produções**
   - Endpoint GET retorna todas as produções cadastradas

3. **Atualizar produção**
   - Permite alterar qualquer campo da produção
   - Upload de nova imagem é opcional

4. **Excluir produção**
   - Remove do banco de dados uma produção pelo ID
   - Retorna status 204 No Content

5. **Upload de imagens**
   - As imagens são salvas localmente em `C:/luci-modas/imagens/`
   - Cada imagem recebe um **nome único (UUID)** para evitar conflitos

---

## 🌐 Endpoints da API

### 1. Cadastrar produção
POST /api/producoes
Content-Type: multipart/form-data


Form-data:
- `producao` → JSON com os dados da produção
- `imagem` → arquivo da imagem

Exemplo de JSON:
```json
{
  "nomeProducao": "Vestido Floral",
  "donaTrabalho": "Luci Modas",
  "quemTrouxe": "Maria",
  "codigo": "PROD-001",
  "descricao": "Vestido tamanho M",
  "quantidade": 50,
  "valorUnitario": 45.9,
  "dataChegada": "2026-01-20",
  "dataPrimeiraEntrega": "2026-01-25",
  "dataEntregaFinal": "2026-01-30",
  "tamanho": "M"
}
2. Listar produções
GET /api/producoes
Retorna todas as produções cadastradas com os campos completos.

3. Atualizar produção
PUT /api/producoes/{id}
Content-Type: multipart/form-data
Form-data:

producao → JSON com os campos atualizados

imagem → arquivo de imagem (opcional)

4. Deletar produção
DELETE /api/producoes/{id}
Remove a produção do banco de dados

Retorna 204 No Content quando sucesso

🔧 Tecnologias Utilizadas
Java 21

Spring Boot

Spring Web

Spring Data JPA

H2 Database

Swagger (OpenAPI)

Multipart File Upload

Maven

📌 Observações
Todas as imagens são salvas localmente e associadas a cada produção

O backend está pronto para integração futura com frontend

Possui regras de negócio para cálculo automático do valor total

Projeto pensado para funcionar localmente sem internet, mas preparado para futuras implementações online

📝 Como Testar
Clone o projeto e abra no IntelliJ ou VS Code

Execute a aplicação

Utilize o Postman para testar os endpoints

GET /api/producoes

POST /api/producoes (com form-data e upload de imagem)

PUT /api/producoes/{id} (atualizando dados ou imagem)

DELETE /api/producoes/{id}

Verifique a pasta de imagens C:/luci-modas/imagens/ para confirmar upload

(Opcional) Abra o Swagger para documentação visual da API:

http://localhost:8080/swagger-ui.html
✅ Status do Projeto
Backend completo, funcional e testado

CRUD de produções funcionando

Upload de imagens implementado

Regras de negócio (cálculo de valor total) aplicadas

Preparado para integração com frontend

👨‍💻 Observação Final
Este README foi escrito por mim, explicando todo o funcionamento do backend e minhas decisões de implementação. Posso detalhar cada endpoint, como funciona o upload de imagens e a lógica de cálculo de valor total durante uma entrevista ou avaliação de portfólio.


---

