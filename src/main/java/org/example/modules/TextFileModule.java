package org.example.modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class TextFileModule implements Module{

    @Override
    public boolean formatWorks(String path) {
        return path.endsWith(".txt");
    }

    @Override
    public void getDescription() {
        System.out.println("<- Модуль - текстовый файл - >");
        System.out.println("Функция 1: подсчет и вывод количества строк.");
        System.out.println("Функция 2: вывод частоты вхождения каждого символа.");
        System.out.println("Функция 3: вывод первого часто используемого слова.");
    }

    @Override
    public void functionOne(String path) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count++;
            }
            System.out.println("Количество строк в файле " + path + ": " + count);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void functionTwo(String path) {
        Map<Character, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()){
                        map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
            for (Map.Entry<Character, Integer> pair : map.entrySet()) {
                System.out.println(pair.getKey() + ": " + pair.getValue());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void functionThree(String path) {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String w : words){
                    if(!w.isEmpty())
                        map.put(w, map.getOrDefault(w, 0) + 1);
                }
            }
            System.out.println("Первое часто встречаемое слово: " + Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
