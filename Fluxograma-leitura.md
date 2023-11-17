```mermaid
graph TD
    A[Início] --> B{Extrai informações da leitura}
    B --> C{Cria chave única}
    C --> D{A chave existe no registro e a última leitura foi há menos de 5 segundos?}
    D -- Não --> E[Adiciona/atualiza a chave no registro com o novo timestamp]
    D -- Sim --> F[Remove a chave do registro]
    F --> E
    E --> G[Envia leitura para a fila do RabbitMQ]
    G --> H[Fim]
```
