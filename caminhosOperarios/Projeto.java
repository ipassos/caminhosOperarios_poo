package caminhosOperarios;

import java.util.ArrayList;
import java.util.HashMap;

public class Projeto {

    private static int id = 0;
    public String nome;
    private String descricao;

    // esse hashmap vai ser responsável por mapear o nome da Rota com a Rota em si;
    private HashMap<String, Rota> rotas;

    public void consultaNomeRotas() {
        for (String key: rotas.keySet()) {
            Rota rota = rotas.get(key);
            System.out.printf("%s: %s\n", key, rota.getNome());
        }
    }

    public void getInformacaoLocais(String nomeRota) {
        Rota rota = rotas.get(nomeRota);
        System.out.println("Informação de locais para a rota: " + rota.getNome());
        ArrayList<Local> locais = rota.getLocal();
        for (Local local : locais) {
            System.out.println("    Nome: " + local.getNome());
            System.out.println("    Descrição: " + local.getDescricao());
            System.out.println("    ------------ ");
        }
    }

    public void getNomeRotas() {
        System.out.println("Nome das rotas");
        for (String key: rotas.keySet()) {
            Rota rota = rotas.get(key);
            System.out.println("    " + rota.getNome());
        }
    }

    public void verDescricao() {
        System.out.printf("\nDescriçao do projeto: %s\n", descricao);
    }

    public Projeto(String nome, String descricao, HashMap<String, Rota> rotas) {
        this.nome = nome;
        this.descricao = descricao;
        this.rotas = rotas;
        id++;
    }
}
