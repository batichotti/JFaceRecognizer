package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.ImagemAjustada;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus Cohuzer
 */
public class MainGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JButton btRun = new JButton("RUN");
    JButton btProcurar = new JButton("Procurar Imagem");

////////////////////////////////// - MUTÁVEL - //////////////////////////////////
    String[] lista_haar = {
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalcatface_extended.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_profile_face.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_smile.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_fullbody.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_upperbody.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_lowerbody.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_smile.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_eye.xml",
        "D:\\Mateus Cohuzer\\Desktop\\Geral\\opencv\\build\\etc\\haarcascades\\haarcascade_eye_tree_eyeglasses.xml"
    };

    String output_img_path = "D:\\Mateus Cohuzer\\Documents\\NetBeansProjects\\JFaceRecog\\src\\imagem_com_faces.jpg";
    String std_img_path = "D:\\Mateus Cohuzer\\Documents\\NetBeansProjects\\JFaceRecog\\src\\standart.jpg";

    JComboBox<String> cbHaar = new JComboBox<>(lista_haar);
    JLabel lbPath = new JLabel("Caminho:");
    JTextField tfPath = new JTextField(30);
    JLabel lbOutput = new JLabel();
    ImagemAjustada imagemAjustada = new ImagemAjustada();

    private JFileChooser caixaDeDialogo = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
    ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    int tamX = 920; //w
    int tamY = 620; //h
    String caminho = "";
    List<String> texto = new ArrayList();

    public MainGUI() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pnCentro.setBackground(Color.WHITE);

        caixaDeDialogo.setFileFilter(filter);

        ImageIcon icon = imagemAjustada.getImagemAjustada(std_img_path, tamX, tamY);
        lbOutput.setIcon(icon);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("JFaceRecog");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

//        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        pnNorte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        pnNorte2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnNorte.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnNorte.add(lbPath);
        pnNorte.add(tfPath);
        pnNorte.add(btProcurar);
        pnNorte.add(btRun);

        tfPath.setEditable(false);

        pnCentro.add(lbOutput);
        lbOutput.setEnabled(true);
        lbOutput.setVisible(true);
        lbOutput.setFont(new Font("Arial", Font.BOLD, 24));

        texto = manipulaArquivo.abrirArquivo("D:\\Mateus Cohuzer\\Documents\\NetBeansProjects\\JFaceRecog\\src\\ImagemEscolhida.txt");
        if (texto.size() > 0) {
            caminho = texto.get(0);
            tfPath.setText(caminho);
        }

        //COLORS
        pnNorte.setBackground(Color.WHITE);

        btRun.setBackground(Color.WHITE);

        btProcurar.setBackground(Color.WHITE);

        //LISTENERS
        btRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String path = tfPath.getText();
                    String haar = (String) cbHaar.getSelectedItem();
                    OpenCV_tools openCV_tools = new OpenCV_tools();
                    openCV_tools.imgRecog(haar, path, output_img_path);
                    ImageIcon icon = imagemAjustada.getImagemAjustada(output_img_path, tamX, tamY);
                    lbOutput.setIcon(icon);
                } catch (Exception e123) {
                    System.out.println("Arruma aqui - btRun");
                }
            }
        });

        btProcurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File(caminho);
                if (file.exists()) {
                    caixaDeDialogo.setCurrentDirectory(file);
                } else {
                    file = new File("D:\\Mateus CohuzEr\\Documents\\NetBeansProjects\\GeradorRaposasB");
                    if (file.exists()) {
                        caixaDeDialogo.setCurrentDirectory(file);
                    } else {
                        caixaDeDialogo.setCurrentDirectory(null);
                    }
                }
                if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    caminho = caixaDeDialogo.getSelectedFile().getAbsolutePath();
                    tfPath.setText(caminho);
                    texto.clear();
                    texto.add(caminho);
                    manipulaArquivo.salvarArquivo("ProjetoEscolhido.txt", texto);
                }
            }
        }
        );

        setModal(
                true);
        setSize(
                1080, 720);
        setResizable(
                false);
        setLocationRelativeTo(
                null);//centraliza na tela
        setVisible(
                true);

    }//fim do construtor de GUI

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
