package caminhosOperarios;

import java.util.*;

public class auth {

    private static boolean montaDatabaseUsuarios() {

        List<String> lines = commaSeparatedValuesHandler.readCsv(constants.FILEPATH_USUARIOS, constants.ENCONDING);
        lines.removeFirst();

        for (String line : lines) {
            String[] partes = line.split(";");

            System.out.println(Arrays.toString(partes));
            try {
                dummyDatabase.put(partes[0], new Usuario(partes[0], partes[1], partes[2]));
            } catch (Exception e) {
                System.out.printf("Não foi possível montar o banco de dados: %s", e);
                return false;
            }
        }
        System.out.println("Banco de dados dos usuários montado com sucesso.");
        return true;

    }

    private static Map<String, Usuario> dummyDatabase = new HashMap<>();

    private static Usuario autenticaUsuario(String username, String password) {
        Usuario user = dummyDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static HashMap<String, String> validaUsuario() {

        if (!montaDatabaseUsuarios()) {
            System.out.println("Erro em valida usuario. Não foi possível de montar o banco de dados");
            return null;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema de Autenticação CLI!");
        System.out.print("Usuário: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String password = scanner.nextLine();

        // Autenticação
        Usuario usuarioAutenticado = auth.autenticaUsuario(username, password);
        if (usuarioAutenticado != null) {
            System.out.println("Autenticação bem-sucedida!");
            System.out.println("Bem-vindo, " + usuarioAutenticado.getUsername() + "!");
            System.out.println("Seu escopo é: " + usuarioAutenticado.getRole());
        } else {
            System.out.println("Falha na autenticação. Verifique suas credenciais.");
            return null;
        }

        HashMap<String, String> dadosUsuario = new HashMap<String, String>();
        dadosUsuario.put(usuarioAutenticado.getUsername(), usuarioAutenticado.getRole());

        scanner.close();
        return dadosUsuario;
    }
}
