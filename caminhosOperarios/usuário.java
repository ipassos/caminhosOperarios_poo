package caminhosOperarios;

import java.util.ArrayList;

class Usuario {
        private String username;
        private String password;
        private String role; // Escopo: usuário, historiador, curador
        private int id;
        private ArrayList<rota> rotasVisitadas; // Lista de rotas visitadas pelo usuário
        private ArrayList<local> locaisVisitados; // Lista de locais visitados pelo usuário

        public Usuario(String username, String password, String role, int id) {
            this.username = username;
            this.password = password;
            this.role = role;
            this.id = id;
            this.rotasVisitadas = new ArrayList<>();
            this.locaisVisitados = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        public int getId() {
            return id;
        }

        // Método: visitarRota
        public ArrayList<local> visitarRota(rota rotaSelecionada) {
            if (!rotasVisitadas.contains(rotaSelecionada)) {
                rotasVisitadas.add(rotaSelecionada);
                rotaSelecionada.registerVisita();
                locaisVisitados.addAll(rotaSelecionada.getLocal());
            }
            return rotaSelecionada.getLocal();
        }

        // Método: verInfoRota
        public String verInfoRota(rota rotaSelecionada) {
            return String.format("Rota: %s\nDescrição: %s\nLocais:\n%s",
                    rotaSelecionada.getNome(),
                    rotaSelecionada.getRotaCompleta(),
                    rotaSelecionada.getLocal().stream()
                            .map(local -> "  - " + local.getNome())
                            .reduce("", (a, b) -> a + "\n" + b).trim());
        }

        // Método: verInfoLocal
        public String verInfoLocal(local localSelecionado, ArrayList<rota> rotas) {
            for (rota r : rotas) {
                if (r.getLocal().contains(localSelecionado)) {
                    return String.format("Local: %s\nDescrição: %s\nCoordenadas: %s\nParte da rota: %s",
                            localSelecionado.getNome(),
                            localSelecionado.getDescricao(),
                            localSelecionado.getUrlCoordenadas(),
                            r.getNome());
                }
            }
            return "Local não encontrado em nenhuma rota.";
        }

        // Método: visitarLocal
        public String visitarLocal(local localSelecionado, ArrayList<rota> rotas) {
            for (rota r : rotas) {
                if (r.getLocal().contains(localSelecionado)) {
                    if (!locaisVisitados.contains(localSelecionado)) {
                        locaisVisitados.add(localSelecionado);
                    }
                    return String.format("Local: %s\nDescrição: %s\nCoordenadas: %s\nParte da rota: %s",
                            localSelecionado.getNome(),
                            localSelecionado.getDescricao(),
                            localSelecionado.getUrlCoordenadas(),
                            r.getNome());
                }
            }
            return "Local não encontrado em nenhuma rota.";
        }
}

class Historiador extends Usuario {

    private ArrayList<rota> rotasEditadas; // Rotas editadas pelo historiador
    private ArrayList<rota> rotasCriadas; // Rotas criadas pelo historiador
    private ArrayList<local> locaisEditados; // Locais editados pelo historiador
    private ArrayList<local> locaisCriados; // Locais criados pelo historiador
    private ArrayList<String> projetosCriados; // Projetos criados pelo historiador
    private ArrayList<String> projetosEditados; // Projetos editados pelo historiador

    public Historiador(String username, String password, String role, int id) {
        super(username, password, role, id);
        this.rotasEditadas = new ArrayList<>();
        this.rotasCriadas = new ArrayList<>();
        this.locaisEditados = new ArrayList<>();
        this.locaisCriados = new ArrayList<>();
        this.projetosCriados = new ArrayList<>();
        this.projetosEditados = new ArrayList<>();
    }

    public ArrayList<rota> getRotasEditadas() {
        return rotasEditadas;
    }

    public ArrayList<rota> getRotasCriadas() {
        return rotasCriadas;
    }

    public ArrayList<local> getLocaisEditados() {
        return locaisEditados;
    }

    public ArrayList<local> getLocaisCriados() {
        return locaisCriados;
    }

    public ArrayList<String> getProjetosCriados() {
        return projetosCriados;
    }

    public ArrayList<String> getProjetosEditados() {
        return projetosEditados;
    }

    // Métodos para criar e editar rotas
    public void criarRota(int id, String nome, String rotaCompleta) {
        rota novaRota = new rota(id, nome, rotaCompleta);
        rotasCriadas.add(novaRota);
    }

    public void editarRota(rota rotaSelecionada, String novoNome, String novaDescricao) {
        if (!rotasEditadas.contains(rotaSelecionada)) {
            rotasEditadas.add(rotaSelecionada);
        }
        rotaSelecionada.verDescricao();
        rotaSelecionada = new rota(rotaSelecionada.getId(), novoNome, novaDescricao);
    }

    // Métodos para criar e editar locais
    public void criarLocal(String nome, String urlCoordenadas, String descricao) {
        local novoLocal = new local(nome, urlCoordenadas, descricao);
        locaisCriados.add(novoLocal);
    }

    public void editarLocal(local localSelecionado, String novoNome, String novaDescricao, String novasCoordenadas) {
        if (!locaisEditados.contains(localSelecionado)) {
            locaisEditados.add(localSelecionado);
        }
        localSelecionado = new local(novoNome, novasCoordenadas, novaDescricao);
    }

    // Métodos para criar e editar projetos
    public void criarProjeto(String nomeProjeto) {
        projetosCriados.add(nomeProjeto);
    }

    public void editarProjeto(String nomeProjetoAntigo, String nomeProjetoNovo) {
        if (!projetosEditados.contains(nomeProjetoAntigo)) {
            projetosEditados.add(nomeProjetoAntigo);
        }
        projetosCriados.remove(nomeProjetoAntigo);
        projetosCriados.add(nomeProjetoNovo);
    }
}

class Curador extends Usuario {

    private ArrayList<rota> rotasEditadas; // Rotas editadas pelo curador
    private ArrayList<local> locaisEditados; // Locais editados pelo curador

    public Curador(String username, String password, String role, int id) {
        super(username, password, role, id);

        this.rotasEditadas = new ArrayList<>();
        this.locaisEditados = new ArrayList<>();

    }

    public ArrayList<rota> getRotasEditadas() {
        return rotasEditadas;
    }

    public ArrayList<local> getLocaisEditados() {
        return locaisEditados;
    }

    // Método para editar rotas
    public void editarRota(rota rotaSelecionada, String novoNome, String novaDescricao) {
        if (!rotasEditadas.contains(rotaSelecionada)) {
            rotasEditadas.add(rotaSelecionada);
        }
        rotaSelecionada = new rota(rotaSelecionada.getId(), novoNome, novaDescricao);
    }

    // Método para editar locais
    public void editarLocal(local localSelecionado, String novoNome, String novaDescricao, String novasCoordenadas) {
        if (!locaisEditados.contains(localSelecionado)) {
            locaisEditados.add(localSelecionado);
        }
        localSelecionado = new local(novoNome, novasCoordenadas, novaDescricao);
    }

}