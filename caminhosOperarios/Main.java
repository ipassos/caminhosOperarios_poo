package caminhosOperarios;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/*
0: Rota ID
1: wkt com o valor do ponto específico
2: nome do local
3: descrição
4: nome da rota
4: rota completa

Na query do google maps temos que passar lng + lat
*/

public class Main {

    public static void main(String[] args) {
        String filePath_projeto_memorias = "./dados/dados.csv";
        Charset encoding = StandardCharsets.UTF_8;

        List<String> lines = commaSeparatedValuesHandler.readCsv(filePath_projeto_memorias, encoding);
        lines.removeFirst();

        Projeto memorias = montaProjeto(lines);
        memorias.getNomeRotas();
        memorias.getRotasCompleta();
        memorias.getInformacaoLocais();
    }

    public static Projeto montaProjeto(List<String> lines) {

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
