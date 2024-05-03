import java.util.Random;

public class Cliente {
    private double chegada;
    private double atendimento;

    public Cliente(double chegada) {
        this.chegada = chegada;
        Random rand = new Random();
        this.atendimento = (30 + rand.nextInt(90)) / 8.0;  // Dividir por 8
    }

    public double getChegada() {
        return chegada;
    }

    public double getAtendimento() {
        return atendimento;
    }
}