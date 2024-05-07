import java.util.Random;

public class Cliente {
    private double chegada;
    private double atendimento;
    private double tempoEntradaFila;
    private double atendimentoOriginal;

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

    public double getTempoNoBanco() {
        return tempoEntradaFila + atendimento;
    }

    public void finalizarAtendimento() {
        atendimento = atendimentoOriginal;
    }

    public double getAtendimentoOriginal() {
        return atendimentoOriginal;
    }
}