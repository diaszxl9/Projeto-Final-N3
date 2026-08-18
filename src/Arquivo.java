import java.io.*;
import java.util.LinkedList;
import java.util.Map;

public class Arquivo {

    private static final String NOME_ARQUIVO = "DadosClientes.txt";

    public static void salvar(LinkedList<Cliente> lista) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {

            for (Cliente c : lista) {

                writer.write(c.toCSV());
                writer.newLine();
            }

            System.out.println("Sistema - Dados salvos no arquivo.");

        } catch (IOException e) {

            System.err.println("Erro! Falha ao salvar arquivo: " + e.getMessage());
        }
    }

    public static void carregar(LinkedList<Cliente> lista, Map<String, Cliente> tabelaHash) {

        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] partes = linha.split(";");

                if (partes.length == 2) {

                    String nome = partes[0];
                    String cpf = partes[1];

                    Cliente c = new Cliente(nome, cpf);

                    lista.add(c);
                    tabelaHash.put(cpf, c);
                }
            }

            System.out.println("Sistema - Dados carregados com sucesso.");

        } catch (IOException e) {

            System.err.println("Erro! Falha ao ler arquivo: " + e.getMessage());
        }
    }
}