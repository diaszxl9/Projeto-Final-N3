import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class SistemaHelpDesk {

    private LinkedList<Cliente> listaClientes;
    private Map<String, Cliente> tabelaHash;
    private PriorityQueue<Chamado> filaChamadosPendentes;

    private ArrayList<Tecnico> listaTecnicos;
    private int indiceTecnicoAtual;

    public SistemaHelpDesk() {

        this.listaClientes = new LinkedList<>();
        this.tabelaHash = new HashMap<>();
        this.filaChamadosPendentes = new PriorityQueue<>();

        this.listaTecnicos = new ArrayList<>();
        inicializarTecnicos();
        this.indiceTecnicoAtual = 0;

        Arquivo.carregar(this.listaClientes, this.tabelaHash);
    }

    private void inicializarTecnicos() {

        listaTecnicos.add(new Tecnico("Alice Rodrigues")); //0
        listaTecnicos.add(new Tecnico("Matheus Silva")); //1
        listaTecnicos.add(new Tecnico("Guilherme Alvez")); //2
    }

    public void criarChamado(String cpfCliente, String descricao, int prioridade) {

        Cliente c = buscarCliente(cpfCliente);

        if (c == null) {

            System.out.println("Erro! Cliente não encontrado para abrir chamado.");
            return;
        }

        Chamado novoChamado = new Chamado(c, descricao, prioridade);
        filaChamadosPendentes.add(novoChamado);

        System.out.println("Chamado criado e colocado na fila de prioridade!");
    }

    public Chamado atenderChamado() {

        if (filaChamadosPendentes.isEmpty()) {

            System.out.println("Nenhum chamado pendente!");
            return null;
        }

        Chamado chamado = filaChamadosPendentes.poll();

        Tecnico tecnicoDaVez = listaTecnicos.get(indiceTecnicoAtual);

        chamado.setTecnicoResponsavel(tecnicoDaVez);

        System.out.println("--- Chamado atribuído ao técnico: " + tecnicoDaVez.getNome());

        indiceTecnicoAtual = (indiceTecnicoAtual + 1) % listaTecnicos.size();

        return chamado;
    }

    public void finalizarChamado(Chamado chamadoResolvido) {

        if (chamadoResolvido == null) return;
        chamadoResolvido.getCliente().adicionarAoHistorico(chamadoResolvido);
    }

    public void cadastrarCliente(String nome, String cpf) {

        if (tabelaHash.containsKey(cpf)) {
            System.out.println("Erro! CPF já cadastrado!");
            return;
        }

        Cliente novo = new Cliente(nome, cpf);
        listaClientes.add(novo);
        tabelaHash.put(cpf, novo);
        System.out.println("Cliente cadastrado com sucesso!");
        Arquivo.salvar(listaClientes);
    }

    public Cliente buscarCliente(String cpf) {

        return tabelaHash.get(cpf);
    }

    public void removerCliente(String cpf) {

        Cliente c = tabelaHash.get(cpf);

        if (c != null) {

            listaClientes.remove(c);
            tabelaHash.remove(cpf);
            System.out.println("Cliente removido.");
            Arquivo.salvar(listaClientes);

        } else {

            System.out.println("Erro! Cliente não encontrado.");
        }
    }

    public void listarTodos() {

        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Clientes ---");

        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
    }
}