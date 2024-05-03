import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Caixa> caixas = new ArrayList<>();

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
        System.out.println("Cliente adicionado ao caixa com " + caixaMenorFila.getTamanhoFila() + " clientes na fila.");
    }
}