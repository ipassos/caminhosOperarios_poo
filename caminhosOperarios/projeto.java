package caminhosOperarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class projeto {

    private static int id = 0;
    public String nome;
    private String descricao;

    // esse hashmap vai ser responsável por mapear o nome da rota com a rota em si;
    private HashMap<String, rota> rotas;

    public void getInformacaoLocais() {
        System.out.println("Informação todos os locais para todas as rotas");
        for (String key: rotas.keySet()) {
            rota rota = rotas.get(key);
            ArrayList<local> locais = rota.getLocal();
            System.out.println("    rota: " + rota.getNome());
            for (caminhosOperarios.local local : locais) {
                System.out.println("        Nome: " + local.getNome());
                System.out.println("        Descrição: " + local.getDescricao());
                System.out.println("        ------------ ");
            }
        }
        System.out.println();
    }

    public void getInformacaoLocal(String nomeRota) {
        rota rota = rotas.get(nomeRota);
        System.out.println("Informação de locais para a rota: " + rota.getNome());
        ArrayList<local> locais = rota.getLocal();
        for (caminhosOperarios.local local : locais) {
            System.out.println("    Nome: " + local.getNome());
            System.out.println("    Descrição: " + local.getDescricao());
            System.out.println("    ------------ ");
        }
        System.out.println();
    }

    public void getNomeRotas() {
        System.out.println("Nome das rotas");
        for (String key: rotas.keySet()) {
            rota rota = rotas.get(key);
            System.out.println("    ID: " + rota.getId() + "- " + rota.getNome());
        }

        System.out.println();
    }

    public void getDescricao() {
        System.out.printf("\nDescriçao do projeto: %s\n", descricao);
    }

    public projeto(String nome, String descricao, HashMap<String, rota> rotas) {
        this.nome = nome;
        this.descricao = descricao;
        this.rotas = rotas;
        id++;
    }

    public void getRotasCompleta() {
        System.out.println("Rotas completas");
        for (String key: rotas.keySet()) {
            rota rota = rotas.get(key);
            System.out.println("    " + rota.getNome() + ": " + rota.getRotaCompleta());
        }
        System.out.println();
    }

    public static projeto montaProjeto(String projeto) {

        List<String> lines = commaSeparatedValuesHandler.readCsv(constants.getValueFromKey(projeto), constants.ENCONDING);
        lines.removeFirst();

        HashMap<String, rota> hashmapRotas = new HashMap<String, rota>();
        for (String line : lines) {
            String[] partes = line.split(";");
            String localGoogleMapsUrl = utils.generateGoogleMapsLocalURL(partes[1]);

            local temp_local = new local(partes[2], localGoogleMapsUrl, partes[3]);

            String currentId = partes[0];
            int intCurrentId = Integer.parseInt(partes[0]);
            if (!hashmapRotas.containsKey(currentId)) {
                System.out.printf("\n    Não tenho o ID: %s e portanto estou adicionando no hashmap\n", partes[0]);

                System.out.print("    Adicionado rota atual no hashmap de rotas completas...");
                String currentRotaCompleta = utils.generateGoogleMapsRouteURL(partes[5]);
                rota currentRota = new rota(intCurrentId, partes[4], currentRotaCompleta);

                currentRota.adicionarLocal(temp_local);

                System.out.printf("\n    local de nome %s foi aficionado a rota de ID %s", temp_local.getNome(), currentId);
                hashmapRotas.put(currentId, currentRota);
            } else {

                rota currentRota = hashmapRotas.get(currentId);
                currentRota.adicionarLocal(temp_local);

                System.out.printf("\n    local de nome %s foi aficionado a rota de ID %s", temp_local.getNome(), currentId);
                hashmapRotas.put(currentId, currentRota);
            }
        }

        System.out.println("\n------------------------------------------\n");

        return new projeto("Memórias dos operários", constants.DESCRICAO_MEMORIAS, hashmapRotas);
    }
}
