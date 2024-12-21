package caminhosOperarios;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class constants {
    public static final String DESCRICAO_MEMORIAS = "O Caminhos Operários em Porto Alegre é um projeto organizado pelo historiador Frederico Duarte Bartz, que busca resgatar a memória da classe trabalhadora e de suas organizações no período formativo do movimento operário local, através de aulas e caminhadas temáticas. Desde 2019, ele vem sendo oferecido em forma de cursos de extensão pela Biblioteca da Faculdade de Arquitetura da UFRGS, local onde seu organizador atua como técnico em assuntos educacionais.";
    public static final String FILEPATH_USUARIOS = "./dados/usuarios.csv";
    public static final String FILEPATH_MEMORIAS = "./dados/dados.csv";
    public static final Charset ENCONDING = StandardCharsets.UTF_8;

    private static HashMap<String, String> mapProjetoToFilePath = new HashMap<>();

    static {
        mapProjetoToFilePath.put("Memorias", FILEPATH_MEMORIAS);
    }

    public static String getValueFromKey(String key) {
        return mapProjetoToFilePath.get(key);
    }
}
