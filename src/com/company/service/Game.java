package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.enums.Type;
import com.company.model.Figure;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    List<Figure> figures = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE),
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));
    private int attempts = 3;
    private int points;


    public void firstRound() {

        //Рандомно получаем, как будем играть (по цвету, размеру или типу)
        Object obj = null;
        Random random = new Random();
        int rand = random.nextInt(3);

        if (rand == 0) {
            obj = Color.BLUE;
        } if (rand == 1) {
            obj = Size.BIG;
        } if (rand == 2) {
            obj = Type.CIRCLE;
        }

        //Выбор правильного цвета для игры
        if (obj instanceof Color) {
            int ran = random.nextInt(2);
            Color currColor;

            if (ran == 0) {
                currColor = Color.BLUE;
                gameByColor(currColor);
            } else {
                currColor = Color.YELLOW;
                gameByColor(currColor);
            }

        //Выбор правильного размера для игры
        } else if (obj instanceof Size) {
            int ran = random.nextInt(2);
            Size currSize;

            if (ran == 0) {
                currSize = Size.BIG;
                gameBySize(currSize);
            } else {
                currSize = Size.SMALL;
                gameBySize(currSize);
            }

        //Выбор правильного типа для игры
        } else {
            int ran = random.nextInt(2);
            Type currType;

            if (ran == 0) {
                currType = Type.CIRCLE;
                gameByType(currType);
            } else {
                currType = Type.SQUARE;
                gameByType(currType);
            }
        }
    }

    private void gameByColor(Color currColor) {
        Random rand = new Random();

        //Создание массивов нужного цвета ("правильных") и неправильного ("неверных")
        Stream<Figure> currColorAnswers = figures.stream().filter(figure -> figure.getColor() == currColor);
        Stream<Figure> wrongColorAnswers = figures.stream().filter(figure -> figure.getColor() != currColor);

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currColorAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongColorAnswers.collect(Collectors.toList());

//        System.out.println("Right answers: " + rightAnswers);
//        int rand_first = rand.nextInt(rightAnswers.size());
//        int rand_sec = rand.nextInt(rightAnswers.size());
//        int rand_third = rand.nextInt(rightAnswers.size());
//        List<Figure> threes = new ArrayList<>(List.of(rightAnswers.get(rand_first), rightAnswers.get(rand_sec),
//                rightAnswers.get(rand_third)));

//        List<Figure> threes = exceptRepeatInList(rightAnswers);

        //Копирование листа правильных ответов (а то почему-то удаляются элементы после вызова exceptRepeatInList)
        List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));
        //Лист из начальных 3-х элементов (правильных)
        List<Figure> threes = exceptRepeatInList(rightAnswers_copy);

        System.out.println();
        System.out.println("Right answers: " + rightAnswers);
        System.out.println("Wrong answers: " + wrongAnswers);

        //Пока есть попытки, можно играть
        firstRoundGameProcess(currColor, rightAnswers, wrongAnswers, threes);
    }

    private void gameBySize(Size currSize) {
        Random rand = new Random();

        //Создание массивов нужного цвета ("правильных") и неправильного ("неверных")
        Stream<Figure> currSizeAnswers = figures.stream().filter(figure -> figure.getSize() == currSize);
        Stream<Figure> wrongSizeAnswers = figures.stream().filter(figure -> figure.getSize() != currSize);

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currSizeAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongSizeAnswers.collect(Collectors.toList());

//        System.out.println("Right answers: " + rightAnswers);
//        int rand_first = rand.nextInt(rightAnswers.size());
//        int rand_sec = rand.nextInt(rightAnswers.size());
//        int rand_third = rand.nextInt(rightAnswers.size());
//        List<Figure> threes = new ArrayList<>(List.of(rightAnswers.get(rand_first), rightAnswers.get(rand_sec),
//                rightAnswers.get(rand_third)));
//        List<Figure> threes = exceptRepeatInList(rightAnswers);

        //Копирование листа правильных ответов (а то почему-то удаляются элементы после вызова exceptRepeatInList)
        List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));
        //Лист из начальных 3-х элементов (правильных)
        List<Figure> threes = exceptRepeatInList(rightAnswers_copy);

        System.out.println();
        System.out.println("Right answers: " + rightAnswers);
        System.out.println("Wrong answers: " + wrongAnswers);

        //Пока есть попытки, можно играть
        firstRoundGameProcess(currSize, rightAnswers, wrongAnswers, threes);
    }

    private void gameByType(Type currType) {
        Random rand = new Random();

        //Создание массивов нужного цвета ("правильных") и неправильного ("неверных")
        Stream<Figure> currTypeAnswers = figures.stream().filter(figure -> figure.getType() == currType);
        Stream<Figure> wrongTypeAnswers = figures.stream().filter(figure -> figure.getType() != currType);

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currTypeAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongTypeAnswers.collect(Collectors.toList());

//        System.out.println("Right answers: " + rightAnswers);
//        int rand_first = rand.nextInt(rightAnswers.size());
//        int rand_sec = rand.nextInt(rightAnswers.size());
//        int rand_third = rand.nextInt(rightAnswers.size());
//        List<Figure> threes = new ArrayList<>(List.of(rightAnswers.get(rand_first), rightAnswers.get(rand_sec),
//                rightAnswers.get(rand_third)));


        //Копирование листа правильных ответов (а то почему-то удаляются элементы после вызова exceptRepeatInList)
        List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));
        //Лист из начальных 3-х элементов (правильных)
        List<Figure> threes = exceptRepeatInList(rightAnswers_copy);

        System.out.println();
        System.out.println("Right answers: " + rightAnswers);
        System.out.println("Wrong answers: " + wrongAnswers);

        //Пока есть попытки, можно играть
        firstRoundGameProcess(currType, rightAnswers, wrongAnswers, threes);
    }



    //Метод для выбора правильного и неправильных ответов
    private List<Figure> getChoices(List<Figure> rightAnswers, List<Figure> wrongAnswers) {
        Random rand = new Random();

        int ran = rand.nextInt(rightAnswers.size()); //Рандомный индекс по "правильному" листу

        int ran_wr_1 = rand.nextInt(wrongAnswers.size()); //Рандомный индекс_1 по "неправильному" листу
        int ran_wr_2 = rand.nextInt(wrongAnswers.size()); //Рандомный индекс_2 по "неправильному" листу

        //Создание выбора (состоит из 1 правильного и 2-х неправильных вариантов)
        return List.of(rightAnswers.get(ran), wrongAnswers.get(ran_wr_1), wrongAnswers.get(ran_wr_2));
    }

    //Игровой процесс. Вынесен в отдельный метод для оптимизации
    private void firstRoundGameProcess(Object currObj, List<Figure> rightAnswers,
                                       List<Figure> wrongAnswers, List<Figure> threes) {
        Random rand = new Random();

        //Пока есть попытки, можно играть
        while (attempts > 0) {
            System.out.println("Текущая последовательность: " + threes);
            System.out.println("\n");

            List<Figure> choices = getChoices(rightAnswers, wrongAnswers);
            List<Figure> choices_copy = new ArrayList<>(choices.subList(0, choices.size()));
            List<Figure> tempList = new ArrayList<>();

            //Создание рандомного порядка возможных ответов
            int numberOfElements = 3;
            for (int i = 0; i < numberOfElements; i++) {
                int randomIndex = rand.nextInt(choices_copy.size());
                Figure randomElement = choices_copy.get(randomIndex);
                tempList.add(randomElement);
                choices_copy.remove(randomIndex);
            }
            choices = tempList;

            System.out.println("Choices: " + choices);
            System.out.println("Выберете правильный ответ(цифра): ");

            Scanner scn = new Scanner(System.in);
            int ans = scn.nextInt();

            //Процесс выбора
            //В зависимости от переданного объекта, выбирается, какой метод будет вызываться
            Object condidiion = null;
            String name_of_game = "Пойти фигурой того же цвета.";
            if (currObj instanceof Color) {
                condidiion = choices.get(ans - 1).getColor();
            } else if (currObj instanceof Size) {
                condidiion = choices.get(ans - 1).getSize();
                name_of_game = "Пойти фигурой того же размера.";
            } else {
                condidiion = choices.get(ans - 1).getType();
                name_of_game = "Пойти фигурой того же типа.";
            }

            if (condidiion == currObj) {
                System.out.println("Правильно" + "\n");
                points += 2;
                threes.add(choices.get(ans - 1));

                if (threes.size() == 8) {
                    System.out.println("Поздравляем! Вы прошли первый раунд! Условием было: " + name_of_game);
                    System.out.println("Набрано очков: " + points);
                    break;
                }

            } else {
                attempts--;
                System.out.println("Неправильно. У вас осталось " + attempts + " попыток" + "\n");
                points -= 1;
                if (attempts == 0) {
                    System.out.println("Игра окончена. Вы дисквалифицированы.");
                    System.out.println("Набрано очков: " + points);
                    break;
                }
            }
        }
    }

    private List<Figure> exceptRepeatInList(List<Figure> list) {
        Random rand = new Random();
        List<Figure> copy_list = list;
        List<Figure> newList = new ArrayList<>();

        System.out.println("Copy_list = " + copy_list);

        int numberOfElements = 3;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(copy_list.size());
            Figure randomElement = copy_list.get(randomIndex);
            newList.add(randomElement);
            System.out.println(randomElement);
            copy_list.remove(randomIndex);
        }
        return newList;
    }
}
