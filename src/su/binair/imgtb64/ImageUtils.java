package su.binair.imgtb64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageUtils {
	
	public static String encodeImageFromPath(String imagePath) {
        File file = new File(imagePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                baos.write(buf, 0, readNum);
            }
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void decodeImageFromString(String imageString, String fileName){
        byte[] imageByte = Base64.getDecoder().decode(imageString);
        String path = fileName + ".png";
        File dir = new File("images");
        if(!dir.exists()) dir.mkdir();
        File file = new File(dir, path);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageByte);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
