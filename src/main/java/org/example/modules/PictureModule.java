package org.example.modules;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

@Component
public class PictureModule implements Module{
    @Override
    public boolean formatWorks(String path) {
        return path.endsWith(".jpg");
    }

    @Override
    public void getDescription() {
        System.out.println("<- Модуль - изображение - >");
        System.out.println("Функция 1: вывод размера изображения.");
        System.out.println("Функция 2: вывод информации exif.");
        System.out.println("Функция 3: вывод версии exif.");
    }

    @Override
    public void functionOne(String path) {
        try {
            BufferedImage bimg = ImageIO.read(new File(path));
            System.out.println("Ширина изображения: " + bimg.getWidth());
            System.out.println("Высота изображения: " + bimg.getHeight());
        }
        catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void functionTwo(String path) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
            ExifSubIFDDirectory dr = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (dr != null) {
                for (Tag tag : dr.getTags()) {
                    System.out.println(tag.getTagName() + ", " + tag.getDescription());
                }
            }
            else {
                System.out.println("exif информации нет");
            }
        }
        catch (IOException | ImageProcessingException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void functionThree(String path) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
            ExifSubIFDDirectory dr = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (dr != null) {
                System.out.println("Версия exif: " + dr.getDescription(ExifSubIFDDirectory.TAG_EXIF_VERSION));
            }
            else {
                System.out.println("exif информации нет");
            }
        }
        catch (IOException | ImageProcessingException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }
}
