import java.util.LinkedList;

public class Cliente {

    private String nome;
    private String cpf;

    private LinkedList<Chamado> historicoChamados;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.historicoChamados = new LinkedList<>();
    }

    public String getNome() {

        return nome;
    }

    public String getCpf() {

        return cpf;
    }

    public void adicionarAoHistorico(Chamado c) {

        System.out.println("Chamado finalizado e movido para o histórico do cliente " + this.nome);
        this.historicoChamados.add(c);
    }

    public void exibirHistorico() {

        if (historicoChamados.isEmpty()) {

            System.out.println("O cliente " + this.nome + " não possui histórico.");
            return;
        }

        System.out.println("-=-=- Histórico de " + this.nome + " -=-=-");

        for (Chamado c : historicoChamados) {

            System.out.println("  - " + c.getDescricao());
        }
    }

    @Override
    public String toString() {

        return "Nome: " + nome + "| CPF: " + cpf;
    }

    public String toCSV() {

        return nome + ";" + cpf;
    }
}