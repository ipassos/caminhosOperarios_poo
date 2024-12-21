package caminhosOperarios;

import java.util.ArrayList;

public class Rota {
    private static int id = 0;
    public String nome;
    private String descricao;
    private ArrayList<Local> local;
    private static String rotaCompleta;

    public void verDescricao() {
        System.out.printf("\nDescriçao da rota: %s\n", descricao);
    }

    public void consultaLocais() {
        System.out.printf("\nLocais da Rota %s", nome);
        for (Local local : this.local) {
            System.out.println("    Nome: " + local.getNome());
            System.out.println("    Descrição: " + local.getDescricao());
        }
    }

    public ArrayList<Local> getLocal() {
        return local;
    }

    public void adicionarLocal(Local local) {
        try {
            this.local.add(local);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar local no trajeto da Rota");
            System.out.println(e);
        }
    }

    public String getNome() {
        return nome;
    }

    public static int getId() {
        return id;
    }

    public static void getRotaCompleta() {
         System.out.println(rotaCompleta);
    }

    public Rota(String nome, String rotaCompleta) {
        this.nome = nome;
        rotaCompleta = rotaCompleta;
        this.local = new ArrayList<>();
        id++;
    }
}
