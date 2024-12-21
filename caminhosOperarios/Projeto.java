package caminhosOperarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Projeto {

    private static int id = 0;
    public String nome;
    private String descricao;

    // esse hashmap vai ser responsável por mapear o nome da Rota com a Rota em si;
    private HashMap<String, Rota> rotas;

    public void getInformacaoLocais() {
        System.out.println("Informação todos os locais para todas as rotas");
        for (String key: rotas.keySet()) {
            Rota rota = rotas.get(key);
            ArrayList<Local> locais = rota.getLocal();
            System.out.println("    Rota: " + rota.getNome());
            for (Local local : locais) {
                System.out.println("        Nome: " + local.getNome());
                System.out.println("        Descrição: " + local.getDescricao());
                System.out.println("        ------------ ");
            }
        }
        System.out.println();
    }

    public void getInformacaoLocal(String nomeRota) {
        Rota rota = rotas.get(nomeRota);
        System.out.println("Informação de locais para a rota: " + rota.getNome());
        ArrayList<Local> locais = rota.getLocal();
        for (Local local : locais) {
            System.out.println("    Nome: " + local.getNome());
            System.out.println("    Descrição: " + local.getDescricao());
            System.out.println("    ------------ ");
        }
        System.out.println();
    }

    public void getNomeRotas() {
        System.out.println("Nome das rotas");
        for (String key: rotas.keySet()) {
            Rota rota = rotas.get(key);
            System.out.println("    ID: " + rota.getId() + "- " + rota.getNome());
        }

        System.out.println();
    }

    public void getDescricao() {
        System.out.printf("\nDescriçao do projeto: %s\n", descricao);
    }

    public Projeto(String nome, String descricao, HashMap<String, Rota> rotas) {
        this.nome = nome;
        this.descricao = descricao;
        this.rotas = rotas;
        id++;
    }

    public void getRotasCompleta() {
        System.out.println("Rotas completas");
        for (String key: rotas.keySet()) {
            Rota rota = rotas.get(key);
            System.out.println("    " + rota.getNome() + ": " + rota.getRotaCompleta());
        }
        System.out.println();
    }

    public static Projeto montaProjeto(String projeto) {

        List<String> lines = commaSeparatedValuesHandler.readCsv(constants.getValueFromKey(projeto), constants.ENCONDING);
        lines.removeFirst();

        HashMap<String, Rota> hashmapRotas = new HashMap<String, Rota>();
        for (String line : lines) {
            String[] partes = line.split(";");
            String localGoogleMapsUrl = utils.generateGoogleMapsLocalURL(partes[1]);

            Local temp_local = new Local(partes[2], localGoogleMapsUrl, partes[3]);

            String currentId = partes[0];
            int intCurrentId = Integer.parseInt(partes[0]);
            if (!hashmapRotas.containsKey(currentId)) {
                System.out.printf("\n    Não tenho o ID: %s e portanto estou adicionando no hashmap\n", partes[0]);

                System.out.print("    Adicionado rota atual no hashmap de rotas completas...");
                String currentRotaCompleta = utils.generateGoogleMapsRouteURL(partes[5]);
                Rota currentRota = new Rota(intCurrentId, partes[4], currentRotaCompleta);

                currentRota.adicionarLocal(temp_local);

                System.out.printf("\n    Local de nome %s foi aficionado a rota de ID %s", temp_local.getNome(), currentId);
                hashmapRotas.put(currentId, currentRota);
            } else {

                Rota currentRota = hashmapRotas.get(currentId);
                currentRota.adicionarLocal(temp_local);

                System.out.printf("\n    Local de nome %s foi aficionado a rota de ID %s", temp_local.getNome(), currentId);
                hashmapRotas.put(currentId, currentRota);
            }
        }

        System.out.println("\n------------------------------------------\n");

        return new Projeto("Memórias dos operários", constants.DESCRICAO_MEMORIAS, hashmapRotas);
    }
}
