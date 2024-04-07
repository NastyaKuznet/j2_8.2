package org.example.modules;

import org.springframework.stereotype.Component;

import java.io.File;
@Component
public class DirectoryModule implements Module{
    @Override
    public boolean formatWorks(String path) {
        return new File(path).isDirectory();
    }

    @Override
    public void getDescription() {
        System.out.println("<- Каталог - >");
        System.out.println("Функция 1: вывод списка файлов в каталоге.");
        System.out.println("Функция 2: подсчет размера всех файлов в каталоге.");
        System.out.println("Функция 3: вывод названия самого большого файла в каталоге.");
    }

    @Override
    public void functionOne(String path) {
        File[] files = new File(path).listFiles();
        if (files != null){
            System.out.println("Список файлов: .");
            for (File file : files) {
                if(file.isFile()){
                    System.out.println(file.getName());
                }
            }
        }
        else {
            System.out.println("В каталоге нет файлов.");
        }
    }

    @Override
    public void functionTwo(String path) {
        File[] files = new File(path).listFiles();
        Long size = 0L;
        if (files != null){
            for (File file : files) {
                if(file.isFile()){
                    size += file.length();
                }
            }
            System.out.println("Размер: " + size);
        }
        else {
            System.out.println("В каталоге нет файлов.");
        }
    }

    @Override
    public void functionThree(String path) {
        File[] files = new File(path).listFiles();
        Long maxSize = 0L;
        if (files != null){
            String name = files[0].getName();
            for (File file : files) {
                if(file.isFile() && file.length() > maxSize){
                    maxSize = file.length();
                    name = file.getName();
                }
            }
            System.out.println("Название самого большого файла в каталоге: " + name);
        }
        else {
            System.out.println("В каталоге нет файлов.");
        }
    }
}
