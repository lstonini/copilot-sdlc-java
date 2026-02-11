import os
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("metrics.csv")
os.makedirs("charts", exist_ok=True)

def bar_chart(metric_name, title, ylabel, output_file):
    row = df[df["Métrica"] == metric_name].iloc[0]
    plt.figure()
    plt.bar(["Manual", "Com Copilot"], [row["Manual"], row["Com Copilot"]])
    plt.title(title)
    plt.ylabel(ylabel)
    plt.savefig(output_file, dpi=200, bbox_inches="tight")
    plt.close()

bar_chart("Tempo (min)", "Tempo para escrever testes (min)", "Minutos", "charts/grafico_tempo.png")
bar_chart("Cobertura (%)", "Cobertura de código (%)", "%", "charts/grafico_cobertura.png")
bar_chart("Nº de testes", "Quantidade de testes unitários", "Testes", "charts/grafico_qtd_testes.png")
bar_chart("Ajustes manuais (0-10)", "Esforço de ajustes manuais (0-10)", "Escala (0-10)", "charts/grafico_ajustes.png")

print("OK! Gráficos gerados na pasta charts/")
