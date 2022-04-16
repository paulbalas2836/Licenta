package licenta.project.Utils;

import licenta.project.Repositories.Interfaces.ImageManipulationInterface;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class ImageManipulation implements ImageManipulationInterface {
    private final static Integer IMAGE_HEIGHT = 300;
    private final static Integer IMAGE_WIDTH = 300;
    @Override
    public String saveImage(MultipartFile image, String label) {
        try {
            byte[] imageByte = image.getBytes();
            InputStream inputStream = new ByteArrayInputStream(imageByte);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            BufferedImage resizedImage = Scalr.resize(bufferedImage,IMAGE_WIDTH,IMAGE_HEIGHT);
            String fileName = label + new Random().nextInt(10000) + new Random().nextInt(10000) + new Random().nextInt(10000);
            Path path = Paths.get("TrainImages", fileName+".jpg");

            if(Files.exists(path))
            {
                fileName = fileName+ new Random().nextInt(10000) + new Random().nextInt(10000) + new Random().nextInt(10000);
                path = Paths.get("TrainImages", fileName+".jpg");
            }

            File newImageFile = path.toFile();
            ImageIO.write(resizedImage, "jpg", newImageFile);

            return fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}