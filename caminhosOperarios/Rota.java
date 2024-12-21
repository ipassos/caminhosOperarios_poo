package caminhosOperarios;

import java.util.ArrayList;

public class Rota {
    private int id;
    private String nome;
    private String descricao;
    private ArrayList<Local> local;
    private String rotaCompleta;
    private static int visitas = 0;

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

    public int getId() {
        return id;
    }

    public String getRotaCompleta() {
         return rotaCompleta;
    }

    public void registerVisita() {
        visitas++;
    }

    public Rota(int id, String nome, String rotaCompleta) {
        this.id = id;
        this.nome = nome;
        this.rotaCompleta = rotaCompleta;
        this.local = new ArrayList<>();
    }
}
