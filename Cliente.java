import java.util.Random;

public class Cliente {
    private double chegada;
    private double atendimento;
    private double atendimentoFinalizado;

    public Cliente(double chegada) {
        this.chegada = chegada;
        Random rand = new Random();
        this.atendimento = (30 + rand.nextInt(90)) / 60.0;  // Dividir por 60
    }

    public double getChegada() {
        return chegada;
    }

    public double getAtendimento() {
        return atendimento;
    }

    public void setAtendimentoFinalizado(double atendimentoFinalizado) {
        this.atendimentoFinalizado = atendimentoFinalizado;
    }

    public double getAtendimentoFinalizado() {
        return atendimentoFinalizado;
    }
}