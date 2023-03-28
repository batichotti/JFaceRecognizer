package tools;

/**
 *
 * @author radames
 */
public class DiretorioDaAplicacao {

    private String diretorioDaAplicacao;

    public String getDiretorioDaAplicacao() {
        return System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        DiretorioDaAplicacao getDiretorioCorrente = new DiretorioDaAplicacao();
        System.out.println("Diretorio " + getDiretorioCorrente.getDiretorioDaAplicacao());
    }
}
