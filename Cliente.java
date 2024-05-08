import java.util.Random;

public class Cliente {
    private final double chegada;
    private double atendimento;
    private double tempoEntradaFila;
    private final double atendimentoOriginal;

    public Cliente(double chegada) {
        this.chegada = chegada;
        Random rand = new Random();
        this.atendimentoOriginal = (30 + rand.nextInt(90)) / 60.0;
        this.atendimento = atendimentoOriginal;
    }

    public double getChegada() {
        return chegada;
    }

    public double getAtendimento() {
        return atendimento;
    }

    public double getTempoEntradaFila() {
        return tempoEntradaFila;
    }

    public void setTempoEntradaFila(double tempoEntradaFila) {
        this.tempoEntradaFila = tempoEntradaFila;
    }

    public void finalizarAtendimento() {
        atendimento = atendimentoOriginal;
    }

    public double getAtendimentoOriginal() {
        return atendimentoOriginal;
    }
}