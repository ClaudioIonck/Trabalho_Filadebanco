import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {

    public static volatile boolean simulacaoTerminada = false;

    public static void main(String[] args) {
        try {
            Banco banco = new Banco(5); // numero de atendentes
            double inicio = System.currentTimeMillis();
            Random rand = new Random();
            while ((System.currentTimeMillis() - inicio) / 1000 <= 120) {  // 2 minutos em segundos
                banco.adicionarCliente(new Cliente((System.currentTimeMillis() - inicio) / 1000));
                try {
                    sleep((5000 + rand.nextInt(45000)) /60);  // Dividir por 60
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
            simulacaoTerminada = true; // Sinalizar término da simulação

            for (Caixa caixa : banco.getCaixas()) {
                try {
                    caixa.join(); // esperar a thread terminar
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
            double tempoTotalSimulacao = (System.currentTimeMillis() - inicio) / 1000;
            System.out.println("\n\n\nNúmero de clientes atendidos: " + banco.getNumeroClientesAtendidos());
            double tempoMaximoEspera = banco.getTempoMaximoEspera();
            System.out.println("Tempo máximo de espera: " + formatTime(tempoMaximoEspera));
            System.out.println("Tempo máximo de atendimento: " + String.format("%.2fmin", banco.getTempoMaximoAtendimento()));
            System.out.println("Tempo médio no banco: " + formatTime(Math.abs(banco.getTempoMedioNoBanco()) / 60));

            // Adicione esta linha para imprimir o tempo médio de espera na fila
            System.out.println("Tempo médio de espera na fila: " + formatTime(banco.getTempoMedioEspera()));

            // Verificação do objetivo de 2 minutos
            if (tempoMaximoEspera <= 120) {
                System.out.println("Objetivo de 2 minutos de espera máxima atingido.");
            } else {
                System.out.println("Objetivo de 2 minutos de espera máxima não foi atingido.");
            }

            // Estatísticas de utilização dos caixas
            for (int i = 0; i < banco.getCaixas().size(); i++) {
                Caixa caixa = banco.getCaixas().get(i);
                double porcentagemOcupacao = (caixa.getTempoOcupado() / tempoTotalSimulacao) * 100;
                System.out.println("Caixa " + (i + 1) + " ficou ocupado " + porcentagemOcupacao + "% do tempo.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during the simulation: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    // formatacao do tempo
    private static String formatTime(double timeInSeconds) {
        if (timeInSeconds >= 60) {
            return String.format("%.2fmin", timeInSeconds / 60);
        } else {
            return String.format("%.2fs", timeInSeconds);
        }
    }
}