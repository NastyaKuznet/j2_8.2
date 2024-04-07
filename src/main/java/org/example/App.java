package org.example;

import org.example.modules.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {
    private static List<Module> modules;
    @Autowired
    public App(List<Module> modules){
        App.modules = modules;
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Введите путь файла: ");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        Module module = null;
        for (Module m: modules) {
            if(m.formatWorks(path)){
                module = m;
            }
        }

        if(module != null){
            module.getDescription();
            while (true) {
                System.out.println("Выберите функцию: ");
                String n = scanner.nextLine();
                switch (n) {
                    case ("1"):
                        module.functionOne(path);
                        return;
                    case ("2"):
                        module.functionTwo(path);
                        return;
                    case ("3"):
                        module.functionThree(path);
                        return;
                    default:
                        System.out.println("Напишите номер функции корректно.");
                        break;
                }
            }
        }
        else {
            System.out.println("Формат не поддерживается.");
        }
    }
}
