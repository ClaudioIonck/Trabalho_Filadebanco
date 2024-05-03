import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Caixa extends Thread {
    private BlockingQueue<Cliente> fila = new LinkedBlockingQueue<>();

    public void adicionarCliente(Cliente cliente) {
        fila.add(cliente);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Cliente cliente = fila.take();
                Thread.sleep((long) cliente.getAtendimento() * 1000);
                System.out.println("Atendeu cliente que chegou em " + cliente.getChegada() + " e atendeu por " + cliente.getAtendimento());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTamanhoFila() {
        return fila.size();
    }
}