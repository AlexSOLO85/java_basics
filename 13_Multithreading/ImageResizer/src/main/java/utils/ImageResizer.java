package utils;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageResizer extends Thread {
    private static final int NEW_WIDTH = 768;
    private static final int NEW_HEIGHT = 1024;
    private static final String FORMAT_NAME = "jpg";
    private static final String DELIMITER = "/";

    private final File[] files;
    private final String dstFolder;
    private final long start;

    public ImageResizer(File[] files, String dstFolder, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        resizeScl();
    }

    private void resizeStd() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) NEW_WIDTH));

                BufferedImage newImage = new BufferedImage(
                        NEW_WIDTH, newHeight, BufferedImage.TYPE_INT_RGB);

                int widthStep = image.getWidth() / NEW_WIDTH;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < NEW_WIDTH; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + DELIMITER + file.getName());
                ImageIO.write(newImage, FORMAT_NAME, newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +
                " finish - duration: " + (System.currentTimeMillis() - start));
    }

    private void resizeScl() {
        BufferedImage image;
        for (File file : files) {
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                File newFile = new File(dstFolder + DELIMITER + file.getName());
                ImageIO.write(Scalr.resize(Objects.requireNonNull(image),
                        ImageResizer.NEW_WIDTH, ImageResizer.NEW_HEIGHT), FORMAT_NAME, newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() +
                " finish - duration: " + (System.currentTimeMillis() - start));
    }
}