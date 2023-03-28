package tools;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author radames
 */
public class ImagemAjustada {

    private Image imagemAux;

    public ImageIcon getImagemAjustada(String origem, int tamX, int tamY) {
        //exemplo de origem  String origem = "/icones/cancel.png";
        //começa com / (barra)

        //  System.out.println("Caminho " + origem);
        File img = new File(origem);
        if (img.exists()) {
            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(tamX, tamY, Image.SCALE_FAST));
            return icone;
        } else {
            // System.out.println("erro ao carregar imagem " + e.getLocalizedMessage());
            String dirApp = new DiretorioDaAplicacao().getDiretorioDaAplicacao();
            origem = dirApp + "/src/fotos/silhueta.png";
            System.out.println("silhueta "+origem);      
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(tamX, tamY, Image.SCALE_FAST));
            return icone;
        }

    }

}
