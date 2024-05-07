import java.util.Random;

public class Cliente {
    private double chegada;
    private double atendimento;
    private double tempoEntradaFila;

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
        double tempoAtual = (System.currentTimeMillis() - chegada * 1000) / 1000; // Tempo em segundos
        atendimento = tempoAtual - tempoEntradaFila; // Atualizar o tempo de atendimento real
    }
}