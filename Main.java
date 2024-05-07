import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(50); // numero de atendentes
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
        System.out.println("\n\n\n\n\nNúmero de clientes atendidos: " + banco.getNumeroClientesAtendidos());
        System.out.println("Tempo máximo de espera: " + banco.getTempoMaximoEspera());
        System.out.println("Tempo máximo de atendimento: " + banco.getTempoMaximoAtendimento());
        System.out.println("Tempo médio no banco: " + banco.getTempoMedioNoBanco());
    }
}