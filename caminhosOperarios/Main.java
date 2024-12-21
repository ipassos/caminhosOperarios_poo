package caminhosOperarios;

import java.util.HashMap;
import java.util.List;

/*
Informações do banco de dados
    0: Rota ID
    1: wkt com o valor do ponto específico
    2: nome do local
    3: descrição
    4: nome da rota
    5: rota completa

TODO
    Switch-case para as opções do usuário
    Logs da execução do montaProjeto

Fluxograma do uso:
        1. Escolhe o projeto
        2. Monta o projeto
        3. O usuário pega informações. Adiciona visitas. Etc...
        4. Usuário sai do projeto
        5. Usuário entra em outro projeto
            5.1.O usuário pega informações. Adiciona visitas. Etc...
            5.2 O usuário sai do projeto
        6. Usuário termina execução do programa

Observações:
    Na query do google maps temos que passar lng + lat
*/

public class Main {

    public static void main(String[] args) {

        HashMap<String, String> dadosUsuario = auth.validaUsuario();

        Projeto memorias = Projeto.montaProjeto("Memorias");
        memorias.getNomeRotas();
        memorias.getRotasCompleta();
        memorias.getInformacaoLocais();
    }

}
