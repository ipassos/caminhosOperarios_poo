package caminhosOperarios;

public class Local {

    private static int id = 0;
    public String nome;
    private String urlCoordenadas;
    private String descricao;

    public void verDescricao() {
        System.out.printf("\nDescriçao do local: %s\n", descricao);
    }

    public Local(String nome, String urlCoordenadas, String descricao) {
        this.nome = nome;
        this.urlCoordenadas = urlCoordenadas;
        this.descricao = descricao;
        id++;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getUrlCoordenadas() {
        return urlCoordenadas;
    }

    public void descreveLocal() {
        System.out.println("Descriçao do local");
        System.out.printf("    Nome: %s\n", getNome());
        System.out.printf("    Coordenadas: %s\n", getUrlCoordenadas());
        System.out.printf("    Descriçao: %s\n", getDescricao());

    }
}
