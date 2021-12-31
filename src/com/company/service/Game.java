package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.enums.Type;
import com.company.model.Figure;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final List<Figure> figures = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE),
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    private final List<Figure> figures_only_big = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE));

    private final List<Figure> figures_only_small = List.of(
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    private final List<Figure> figures_only_blue = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE));

    private final List<Figure> figures_only_yellow = List.of(
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    private int attempts = 3;
    private int points;


    public void firstTour() {

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

        //Создание "булевой" переменной для понятия, в какой режим играть (идентичный цвет\размер\тип или иной)
        int bool = rand.nextInt(2);


        Stream<Figure> currColorAnswers;
        Stream<Figure> wrongColorAnswers;
        if (bool == 0) {
            //Создание массивов нужного цвета ("правильных") и неправильного ("неверных")
            currColorAnswers = figures.stream().filter(figure -> figure.getColor() == currColor);
            wrongColorAnswers = figures.stream().filter(figure -> figure.getColor() != currColor);
        } else {
            currColorAnswers = figures.stream();
            wrongColorAnswers = figures.stream();
        }

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currColorAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongColorAnswers.collect(Collectors.toList());

        List<Figure> threes;

        //Режим с иным типом\цветом\размером специфичен и весьма заметно отличается от режима с одинаковым параметром.
        // Так что для создания "правильных" и "неправильных" массивов вызывается дополнительная функция
        if (bool == 1) {
            //rightAnswers = Tools.filterDifferentParamsList(figures, currColor, 8);

            threes = Tools.filterDifferentParamsList(figures, currColor, 3);

            System.out.println("Diff color");

            firstRoundGameProcess(currColor, rightAnswers, wrongAnswers, threes, bool);
        } else {
            List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));

            threes = Tools.exceptRepeatInList(rightAnswers_copy, 3);

            firstRoundGameProcess(currColor, rightAnswers, wrongAnswers, threes, bool);
        }
    }

    private void gameBySize(Size currSize) {
        Random rand = new Random();

        int bool = rand.nextInt(2);


        Stream<Figure> currSizeAnswers;
        Stream<Figure> wrongSizeAnswers;
        if (bool == 0) {
            //Создание массивов нужного цвета ("правильных") и неправильного ("неверных")
            currSizeAnswers = figures.stream().filter(figure -> figure.getSize() == currSize);
            wrongSizeAnswers = figures.stream().filter(figure -> figure.getSize() != currSize);
        } else {
            currSizeAnswers = figures.stream();
            wrongSizeAnswers = figures.stream();
        }

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currSizeAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongSizeAnswers.collect(Collectors.toList());

        List<Figure> threes;

        //Режим с иным типом\цветом\размером специфичен и весьма заметно отличается от режима с одинаковым параметром.
        // Так что для создания "правильных" и "неправильных" массивов вызывается дополнительная функция
        if (bool == 1) {
//            rightAnswers = Tools.filterDifferentParamsList(figures, currSize, 8);

            threes = Tools.filterDifferentParamsList(figures, currSize, 3);

            System.out.println("Diff size");

            firstRoundGameProcess(currSize, rightAnswers, wrongAnswers, threes, bool);
        } else {
            List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));

            threes = Tools.exceptRepeatInList(rightAnswers_copy, 3);

            firstRoundGameProcess(currSize, rightAnswers, wrongAnswers, threes, bool);
        }
    }

    private void gameByType(Type currType) {
        Random rand = new Random();

        int bool = rand.nextInt(2);


        Stream<Figure> currTypeAnswers;
        Stream<Figure> wrongTypeAnswers;
        if (bool == 0) {
            //Создание массивов нужного цвета ("правильных") и неправильного ("неверных")
            currTypeAnswers = figures.stream().filter(figure -> figure.getType() == currType);
            wrongTypeAnswers = figures.stream().filter(figure -> figure.getType() != currType);
        } else {
            currTypeAnswers = figures.stream();
            wrongTypeAnswers = figures.stream();
        }

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currTypeAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongTypeAnswers.collect(Collectors.toList());

        //Лист из начальных 3-х элементов (правильных)
        List<Figure> threes;

        //Режим с иным типом\цветом\размером специфичен и весьма заметно отличается от режима с одинаковым параметром.
        // Так что для создания "правильных" и "неправильных" массивов вызывается дополнительная функция
        if (bool == 1) {
//            rightAnswers = Tools.filterDifferentParamsList(figures, currType, 8);

            threes = Tools.filterDifferentParamsList(figures, currType, 3);

            System.out.println("Diff type");

            firstRoundGameProcess(currType, rightAnswers, wrongAnswers, threes, bool);
        } else {
            //Копирование листа правильных ответов (а то почему-то удаляются элементы после вызова exceptRepeatInList)
            List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));

            //Лист из начальных 3-х элементов (правильных)
            threes = Tools.exceptRepeatInList(rightAnswers_copy, 3);

            firstRoundGameProcess(currType, rightAnswers, wrongAnswers, threes, bool);
        }
    }



    //Метод для выбора правильного и неправильных ответов
    private List<Figure> getChoices(List<Figure> rightAnswers, List<Figure> wrongAnswers,
                                    List<Figure> threes, int bool, Object currObj) {
        Random rand = new Random();

        if (bool == 1) {
            Stream<Figure> wrongAnswers_stream;
            Stream<Figure> rightAnswers_stream;

            if (currObj instanceof Color) {
                if (threes.get(threes.size() - 1).getColor() == currObj) {
                    wrongAnswers_stream = figures.stream().filter(Tools.sameColor);
                    rightAnswers_stream = figures.stream().filter(Tools.diffColor);
                } else {
                    wrongAnswers_stream = figures.stream().filter(Tools.diffColor);
                    rightAnswers_stream = figures.stream().filter(Tools.sameColor);
                }
            } else if (currObj instanceof Size) {
                if (threes.get(threes.size() - 1).getSize() == currObj) {
                    wrongAnswers_stream = figures.stream().filter(Tools.sameSize);
                    rightAnswers_stream = figures.stream().filter(Tools.diffSize);
                } else {
                    wrongAnswers_stream = figures.stream().filter(Tools.diffSize);
                    rightAnswers_stream = figures.stream().filter(Tools.sameSize);
                }
            } else  {
                if (threes.get(threes.size() - 1).getType() == currObj) {
                    wrongAnswers_stream = figures.stream().filter(Tools.sameType);
                    rightAnswers_stream = figures.stream().filter(Tools.diffType);
                } else {
                    wrongAnswers_stream = figures.stream().filter(Tools.diffType);
                    rightAnswers_stream = figures.stream().filter(Tools.sameType);
                }
            }

            rightAnswers = rightAnswers_stream.collect(Collectors.toList());
            wrongAnswers = wrongAnswers_stream.collect(Collectors.toList());
        }

        int ran = rand.nextInt(rightAnswers.size()); //Рандомный индекс по "правильному" листу

        int ran_wr_1 = rand.nextInt(wrongAnswers.size()); //Рандомный индекс_1 по "неправильному" листу
        int ran_wr_2 = rand.nextInt(wrongAnswers.size()); //Рандомный индекс_2 по "неправильному" листу

        //Создание выбора (состоит из 1 правильного и 2-х неправильных вариантов)
        return List.of(rightAnswers.get(ran), wrongAnswers.get(ran_wr_1), wrongAnswers.get(ran_wr_2));


    }

    //Игровой процесс. Вынесен в отдельный метод для оптимизации
    private void firstRoundGameProcess(Object currObj, List<Figure> rightAnswers,
                                   List<Figure> wrongAnswers, List<Figure> threes, int bool) {
        Random rand = new Random();

        //Пока есть попытки, можно играть
        int rounds = 1;
        while (attempts > 0) {
            System.out.println("Текущая последовательность: " + threes);
            System.out.println("\n");

            List<Figure> choices = getChoices(rightAnswers, wrongAnswers, threes, bool, currObj);
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

//            if ( (condidiion == currObj && bool == 0) || (rounds % 2 == 0 && bool == 1) ) {
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
//                rounds += 1;

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
