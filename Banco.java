import java.util.List;
import java.util.ArrayList;

public class Banco {
    private List<Caixa> caixas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Banco(int numCaixas) {
        for (int i = 0; i < numCaixas; i++) {
            Caixa caixa = new Caixa();
            caixa.start();
            caixas.add(caixa);
        }
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
        return clientes.stream().mapToDouble(Cliente::getAtendimento).max().orElse(0);
    }

    public double getTempoMedioNoBanco() {
        return clientes.stream().mapToDouble(Cliente::getAtendimento).average().orElse(0); // Usar c.getAtendimento()
    }

    public double getTempoMedioEsperaFila() {
        return clientes.stream().mapToDouble(c -> c.getAtendimento() - c.getTempoEntradaFila()).average().orElse(0); // Usar c.getAtendimento()
    }

    public List<Caixa> getCaixas() {
        return caixas;
    }
}