import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SistemaHelpDesk sistema = new SistemaHelpDesk();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        Chamado chamadoEmAtendimento = null;

        while (opcao != 11) {

            System.out.println("-------------//---------------");
            System.out.println("\n-=-=- SISTEMA HELPDESK -=-=-");

            System.out.println("---- Gestão de Clientes ----");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Buscar Cliente (Por CPF)");
            System.out.println("3 - Remover Cliente");
            System.out.println("4 - Listar Clientes");

            System.out.println("\n---- Gestão de Chamados ----");

            System.out.println("5 - Criar Chamado");
            System.out.println("6 - Atender Chamado (Escalonar Técnico)");
            System.out.println("7 - Adicionar Ação");
            System.out.println("8 - Finalizar Chamado");
            System.out.println("9 - Consultar Histórico do Cliente");
            System.out.println("10 - Consultar Técnico do Chamado Atual"); // NOVO

            System.out.println("----------------------------");
            System.out.println("11 - Sair");

            System.out.print("Escolha: ");
            try {

                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = 0;
            }
            System.out.println("-------------//---------------");

            if (chamadoEmAtendimento == null && (opcao == 7 || opcao == 8 || opcao == 10)) {

                System.out.println("Erro! Nenhum chamado em atendimento no momento.");
                continue;
            }

            switch (opcao) {

                case 1:

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    sistema.cadastrarCliente(nome, cpf);

                    break;

                case 2:

                    System.out.print("Digite o CPF: ");
                    String buscaCpf = scanner.nextLine();
                    Cliente c = sistema.buscarCliente(buscaCpf);
                    if (c != null) System.out.println("Encontrado: " + c);
                    else System.out.println("Cliente não encontrado.");

                    break;

                case 3:

                    System.out.print("Digite o CPF para remover: ");
                    String removeCpf = scanner.nextLine();
                    sistema.removerCliente(removeCpf);

                    break;

                case 4:

                    sistema.listarTodos();

                    break;

                case 5:

                    System.out.print("Digite o CPF do Cliente: ");
                    String cpfCliente = scanner.nextLine();
                    System.out.print("Descreva o problema: ");
                    String desc = scanner.nextLine();
                    System.out.print("Prioridade (1 = Alta, 2 = Média, 3 = Baixa): ");
                    int prioridade = 3;
                    try {
                        prioridade = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Prioridade inválida, assumindo 3.");
                    }
                    sistema.criarChamado(cpfCliente, desc, prioridade);

                    break;

                case 6:

                    if (chamadoEmAtendimento != null) {

                        System.out.println("Você já está atendendo um chamado. Finalize-o antes.");

                    } else {

                        chamadoEmAtendimento = sistema.atenderChamado();
                        if (chamadoEmAtendimento != null) {

                            System.out.println("\n--- INICIANDO ATENDIMENTO ---");
                            System.out.println("Técnico Responsável: " + chamadoEmAtendimento.getTecnicoResponsavel().getNome());
                            System.out.println("Cliente: " + chamadoEmAtendimento.getCliente().getNome());
                            System.out.println("Descrição: " + chamadoEmAtendimento.getDescricao());
                        }
                    }

                    break;

                case 7:

                    System.out.print("Digite a anotação: ");
                    String acao = scanner.nextLine();
                    chamadoEmAtendimento.adicionarAcao(acao);

                    break;

                case 8:

                    sistema.finalizarChamado(chamadoEmAtendimento);
                    System.out.println("Chamado finalizado.");
                    chamadoEmAtendimento = null;

                    break;

                case 9:

                    System.out.print("Digite o CPF do cliente: ");
                    String cpfHist = scanner.nextLine();
                    Cliente cHist = sistema.buscarCliente(cpfHist);
                    if (cHist != null) cHist.exibirHistorico();
                    else System.out.println("Cliente não encontrado.");

                    break;

                case 10:

                    if (chamadoEmAtendimento != null) {

                        System.out.println("Chamado atual está sob responsabilidade de:");
                        System.out.println("--- " + chamadoEmAtendimento.getTecnicoResponsavel().getNome() + " ---");
                    }

                    break;

                case 11:

                    System.out.println("Sistema Finalizado!");

                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}