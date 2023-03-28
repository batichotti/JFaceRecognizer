package Main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author Mateus Cohuzer
 */

public class OpenCV_tools {

    public void imgRecog(String haar, String path, String output_path) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat imagem = Imgcodecs.imread(path);
        
        CascadeClassifier detector = new CascadeClassifier(haar);

        MatOfRect faces = new MatOfRect();
        detector.detectMultiScale(imagem, faces);

        for (Rect face : faces.toArray()) {
            Imgproc.rectangle(imagem, face.tl(), face.br(), new Scalar(0, 0, 255), 3);
        }

        Imgcodecs.imwrite(output_path, imagem);
        System.out.println("Imagem Escrita");
    }

}
