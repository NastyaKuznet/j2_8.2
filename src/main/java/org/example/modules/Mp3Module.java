package org.example.modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class Mp3Module implements Module{
    @Override
    public boolean formatWorks(String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public void getDescription() {
        System.out.println("<- Модуль - mp3 - >");
        System.out.println("Функция 1: вывод названия трека из тегов.");
        System.out.println("Функция 2: вывод длительности в секундах.");
        System.out.println("Функция 3: вывод жанра трека.");
    }

    @Override
    public void functionOne(String path) {
        try {
            AudioFile aud = AudioFileIO.read(new File(path));
            System.out.println("Название трека: " + aud.getTag().getFirst(FieldKey.TITLE));
        } catch (Exception e) {
            System.err.println("Ошибка при чтении mp3: " + e.getMessage());
        }
    }

    @Override
    public void functionTwo(String path) {
        try {
            AudioFile aud = AudioFileIO.read(new File(path));
            System.out.println("Длительность трека: " + aud.getAudioHeader().getTrackLength());
        } catch (Exception e) {
            System.err.println("Ошибка при чтении mp3: " + e.getMessage());
        }
    }

    @Override
    public void functionThree(String path) {
        try {
            AudioFile aud = AudioFileIO.read(new File(path));
            System.out.println("Жанр трека: " + aud.getTag().getFirst(FieldKey.GENRE));
        } catch (Exception e) {
            System.err.println("Ошибка при чтении mp3: " + e.getMessage());
        }
    }
}
