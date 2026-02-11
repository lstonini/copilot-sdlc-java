# copilot-sdlc-java (Java + JUnit 5 + JaCoCo)

Projeto pequeno para experimento do trabalho: **GitHub Copilot na geração de testes unitários**.

## Requisitos
- Java 17+
- Maven 3.8+

## Rodar testes
```bash
mvn test
```

## Gerar relatório de cobertura (JaCoCo)
Após `mvn test`, abra:
- `target/site/jacoco/index.html`

## Como usar no experimento (sugestão)
1. Rode os testes e anote cobertura.
2. Apague/zerar parte dos testes e gere novamente com Copilot.
3. Compare tempo, cobertura e necessidade de ajustes manuais.

## Métricas + gráficos
- Edite `metrics.csv` com seus números
- Rode `python charts.py` para gerar PNGs em `charts/`
