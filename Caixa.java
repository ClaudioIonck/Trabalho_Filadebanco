import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Caixa extends Thread {
    private BlockingQueue<Cliente> fila = new LinkedBlockingQueue<>();
    private double tempoOcupado = 0; // new attribute
    private volatile boolean running = true; // new attribute

    public void adicionarCliente(Cliente cliente) {
        cliente.setTempoEntradaFila((System.currentTimeMillis() - cliente.getChegada()) / 1000);
        fila.add(cliente);
    }

    @Override
    public void run() {
        try {
            while (running) {
                Cliente cliente = fila.take();
                double tempoAtendimento = cliente.getAtendimento() * 1000;
                Thread.sleep((long) tempoAtendimento);
                cliente.setAtendimentoFinalizado((System.currentTimeMillis() - cliente.getChegada()) / 1000);
                tempoOcupado += tempoAtendimento / 1000; // increment the occupied time
                System.out.println("Atendeu cliente que chegou em " + cliente.getChegada() + " e atendeu por " + cliente.getAtendimento());
            }
        } catch (InterruptedException e) {
            running = false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getTamanhoFila() {
        return fila.size();
    }

    public double getTempoOcupado() {
        return tempoOcupado;
    }

    public void stopThread() {
        running = false;
    }
}