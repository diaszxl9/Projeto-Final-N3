import java.util.Stack;

public class Chamado implements Comparable<Chamado> {

    private Cliente cliente;
    private String descricao;
    private int prioridade;
    private Tecnico tecnicoResponsavel;
    private Stack<String> pilhaAcoes;

    public Chamado(Cliente cliente, String descricao, int prioridade) {

        this.cliente = cliente;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.pilhaAcoes = new Stack<>();
        this.tecnicoResponsavel = null;
    }

    public void setTecnicoResponsavel(Tecnico t) {

        this.tecnicoResponsavel = t;
    }

    public Tecnico getTecnicoResponsavel() {

        return this.tecnicoResponsavel;
    }

    public Cliente getCliente() {

        return cliente;
    }

    public String getDescricao() {

        return descricao;
    }

    public int getPrioridade() {

        return prioridade;
    }

    public void adicionarAcao(String acao) {

        String nomeTecnico = (tecnicoResponsavel != null) ? tecnicoResponsavel.getNome() : "Sistema";
        System.out.println("- Chamado " + cliente.getCpf() + " [" + nomeTecnico + "] - Nova ação: " + acao);
        this.pilhaAcoes.push(acao);
    }

    public String verUltimaAcao() {

        if(pilhaAcoes.isEmpty()) return "Nenhuma ação registrada.";
        return this.pilhaAcoes.peek();
    }

    public int contarPalavrasChave() {

        int contagem = 0;
        if (this.descricao == null) return 0;

        String[] palavras = this.descricao.toLowerCase().split(" ");
        String[] chaves = {"erro", "rede", "login", "lento", "senha"};

        for (String p : palavras) {

            for (String k : chaves) {

                if (p.equals(k)) contagem++;
            }
        }
        return contagem;
    }

    @Override
    public int compareTo(Chamado outro) {

        return Integer.compare(this.prioridade, outro.prioridade);
    }

    @Override
    public String toString() {

        String tech = (tecnicoResponsavel != null) ? tecnicoResponsavel.getNome() : "Pendente";
        return "Chamado -- Prioridade = " + prioridade +
                ", Cliente = " + cliente.getNome() +
                ", Técnico = " + tech +
                ", Desc = " + descricao + "--";
    }
}