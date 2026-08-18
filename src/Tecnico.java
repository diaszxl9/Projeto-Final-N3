public class Tecnico {

    private String nome;

    public Tecnico(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Técnico: " + nome;
    }
}