import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(50); // numero de atendentes
        double inicio = System.currentTimeMillis();
        Random rand = new Random();
        while (System.currentTimeMillis() - inicio < 7200000) {  // 2 horas em milissegundos
            if ((System.currentTimeMillis() - inicio) / 1000 > 120) {  // 2 minutos em segundos
                break;
            }
            banco.adicionarCliente(new Cliente((System.currentTimeMillis() - inicio) / 1000));
            try {
                Thread.sleep((5000 + rand.nextInt(45000)) / 60);  // Dividir por 60
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}