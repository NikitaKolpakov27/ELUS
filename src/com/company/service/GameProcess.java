package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.enums.Type;
import com.company.model.Figure;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameProcess {
    private int attempts = 3; // Количество попыток
    private int points; // Очки игрока

    //Игровой процесс. Вынесен в отдельный метод для оптимизации
    public void playRound(Object currObj, List<Figure> rightAnswers,
                                       List<Figure> wrongAnswers, List<Figure> threes, int bool) {
        Random rand = new Random();

        //Пока есть попытки, можно играть
        int rounds = 1;
        while (attempts > 0) {
            System.out.println("Текущая последовательность: " + threes);
            System.out.println("\n");

            List<Figure> choices = Tools.getChoices(rightAnswers, wrongAnswers, threes, bool, currObj);

            // Получение списка ответов для выбора (в рандомном порядке)
            choices = Tools.makeRandomOrderList(choices);

            System.out.println("Choices: " + choices);
            System.out.println("Выберете правильный ответ(цифра): ");

            Scanner scn = new Scanner(System.in);
            int ans = scn.nextInt();

            //Процесс выбора
            //В зависимости от переданного объекта, выбирается, какой метод будет вызываться
            Object condidiion = null;
            String name_of_game = "";

            if (currObj instanceof Color && bool == 0) {
                condidiion = choices.get(ans - 1).getColor();
                name_of_game = "Пойти фигурой того же цвета.";

            } else if (currObj instanceof Color && bool == 1) {
                condidiion = choices.get(ans - 1).getColor();
                name_of_game = "Пойти фигурой иного цвета.";
            }

            if (currObj instanceof Size && bool == 0) {
                condidiion = choices.get(ans - 1).getSize();
                name_of_game = "Пойти фигурой того же размера.";
            } else if (currObj instanceof Size && bool == 1) {
                condidiion = choices.get(ans - 1).getSize();
                name_of_game = "Пойти фигурой иного размера.";
            }

            if (currObj instanceof Type && bool == 0) {
                condidiion = choices.get(ans - 1).getType();
                name_of_game = "Пойти фигурой того же типа.";
            } else if (currObj instanceof Type && bool == 1) {
                condidiion = choices.get(ans - 1).getType();
                name_of_game = "Пойти фигурой иного типа.";
            }

            boolean cond_right_2 = condidiion != currObj && rounds % 2 == 0 && bool == 1;
            boolean cond_right_1 = condidiion == currObj && rounds % 2 != 0 && bool == 1;
            boolean cond_right_default = condidiion == currObj && bool == 0;


            if (cond_right_default || cond_right_1 || cond_right_2) {
                System.out.println("Правильно" + "\n");
                points += 2;
                rounds += 1;
                threes.add(choices.get(ans - 1));

                if (threes.size() == 8) {
                    System.out.println("Поздравляем! Вы прошли первый тур! Условием было: " + name_of_game);
                    System.out.println("Набрано очков: " + points);
                    break;
                }

            } else {
                attempts--;
                System.out.println("Неправильно. У вас осталось " + attempts + " попыток" + "\n");
                points -= 1;

                if (attempts == 0) {
                    System.out.println("Игра окончена. Вы дисквалифицированы.");
                    System.out.println("Условием было: " + name_of_game);
                    System.out.println("Набрано очков: " + points);
                    break;
                }
            }
        }
    }
}
