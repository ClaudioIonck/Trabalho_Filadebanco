import java.util.List;
import java.util.ArrayList;

public class Banco {
    private final List<Caixa> caixas = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();

    public Banco(int numCaixas) {
        for (int i = 0; i < numCaixas; i++) {
            adicionarCaixa();
        }
    }

    public synchronized void adicionarCaixa() {
        Caixa caixa = new Caixa();
        caixa.start();
        caixas.add(caixa);
        notifyAll(); // Notificar todas as threads
    }

    public void adicionarCliente(Cliente cliente) {
        Caixa caixaMenorFila = caixas.get(0);
        for (Caixa caixa : caixas) {
            if (caixa.getTamanhoFila() < caixaMenorFila.getTamanhoFila()) {
                caixaMenorFila = caixa;
            }
        }
        caixaMenorFila.adicionarCliente(cliente);
        clientes.add(cliente);
        System.out.println("Cliente adicionado ao caixa com " + caixaMenorFila.getTamanhoFila() + " clientes na fila.");
    }

    public int getNumeroClientesAtendidos() {
        return clientes.size();
    }

    public double getTempoMaximoEspera() {
        return clientes.stream().mapToDouble(Cliente::getChegada).max().orElse(0);
    }

    public double getTempoMaximoAtendimento() {
        return clientes.stream().mapToDouble(Cliente::getAtendimentoOriginal).max().orElse(0);
    }

    public double getTempoMedioNoBanco() {
        return clientes.stream()
                .mapToDouble(c -> (c.getTempoEntradaFila() + c.getAtendimentoOriginal()) / 60000) // Conversão para minutos
                .average().orElse(0);
    }

    public List<Caixa> getCaixas() {
        return caixas;
    }
}