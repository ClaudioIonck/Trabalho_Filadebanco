import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(3); // numero de atendentes
        double inicio = System.currentTimeMillis();
        Random rand = new Random();
        while (true) {
            if ((System.currentTimeMillis() - inicio) / 1000 > 120) {  // 2 minutos em segundos
                break;
            }
            banco.adicionarCliente(new Cliente((System.currentTimeMillis() - inicio) / 1000));
            try {
                Thread.sleep((5000 + rand.nextInt(45000)) / 60);  // Dividir por 60
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((System.currentTimeMillis() - inicio) / 1000 > 120) {  // 2 minutos em segundos
                break;
            }
        }
        System.out.println("\n\n\nNúmero de clientes atendidos: " + banco.getNumeroClientesAtendidos());
        System.out.println("Tempo máximo de espera: " + formatTime(banco.getTempoMaximoEspera() * 60)); // Multiplicar por 60 pois fatoramso o tempo em 1 de 60
        System.out.println("Tempo máximo de atendimento: " + formatTime(banco.getTempoMaximoAtendimento() * 60));
        System.out.println("Tempo médio no banco: " + formatTime(banco.getTempoMedioNoBanco() * 60));
    }

    // formatacao do tempo
    private static String formatTime(double timeInSeconds) {
        if (timeInSeconds >= 3600) {
            return String.format("%.2fh", timeInSeconds / 3600);
        } else if (timeInSeconds >= 60) {
            return String.format("%.2fmin", timeInSeconds / 60);
        } else {
            return String.format("%.2fs", timeInSeconds);
        }
    }
}