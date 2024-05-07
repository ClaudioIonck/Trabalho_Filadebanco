import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Caixa extends Thread {
    private BlockingQueue<Cliente> fila = new LinkedBlockingQueue<>();
    private double tempoOcupado = 0;
    private volatile boolean running = true;

    public void adicionarCliente(Cliente cliente) {
        cliente.setTempoEntradaFila((System.currentTimeMillis() - cliente.getChegada()) / 1000);
        fila.add(cliente);
    }

    @Override
    public void run() {
        try {
            while (running) {
                Cliente cliente = fila.poll();
                if (cliente == null) {
                    if (Main.simulacaoTerminada) {
                        break;
                    } else {
                        continue;
                    }
                }
                double tempoAtendimento = cliente.getAtendimento() * 1000;
                Thread.sleep((long) tempoAtendimento);
                cliente.finalizarAtendimento(); // Atualizar o tempo de atendimento no cliente
                tempoOcupado += tempoAtendimento / 1000;
                System.out.println("Atendeu cliente que chegou em " + cliente.getChegada() + " e atendeu por " + cliente.getAtendimento());
            }
        } catch (InterruptedException e) {
            // Thread interrompida, encerrar
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