package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.model.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.company.service.Game.figures;

public class Tools {
//    static Predicate<Figure> diffColor = figure -> figure.getColor() != Color.BLUE || figure.getColor() == Color.YELLOW;
//    static Predicate<Figure> sameColor = figure -> figure.getColor() == Color.BLUE || figure.getColor() != Color.YELLOW;
//
//    static Predicate<Figure> diffSize = figure -> figure.getSize() != Size.BIG || figure.getSize() == Size.SMALL;
//    static Predicate<Figure> sameSize = figure -> figure.getSize() == Size.BIG || figure.getSize() != Size.SMALL;
//
//    static Predicate<Figure> diffType = figure -> figure.getType() != Type.CIRCLE || figure.getType() == Type.SQUARE;
//    static Predicate<Figure> sameType = figure -> figure.getType() == Type.CIRCLE || figure.getType() != Type.SQUARE;

    static Predicate<Figure> diffColor;
    static Predicate<Figure> sameColor;

    static Predicate<Figure> diffSize;
    static Predicate<Figure> sameSize;

    static Predicate<Figure> diffType;
    static Predicate<Figure> sameType;

    public static List<Figure> filterDifferentParamsList(List<Figure> list, Object currObj, int neddedElems) {
        Random rand = new Random();
        List<Figure> copy_list = list;
        List<Figure> newList;

        diffColor = figure -> figure.getColor() != currObj;
        sameColor = figure -> figure.getColor() == currObj;

        diffSize = figure -> figure.getSize() != currObj;
        sameSize = figure -> figure.getSize() == currObj;

        diffType = figure -> figure.getType() != currObj;
        sameType = figure -> figure.getType() == currObj;

        if (currObj instanceof Color) {
            newList = filterListByPredicates(diffColor, sameColor, copy_list, neddedElems);
        } else if (currObj instanceof Size) {
            newList = filterListByPredicates(diffSize, sameSize, copy_list, neddedElems);
        } else {
            newList = filterListByPredicates(diffType, sameType, copy_list, neddedElems);
        }

        return newList;
    }

    private static List<Figure> filterListByPredicates(Predicate<Figure> predicate_even,
                                Predicate<Figure> predicate_odd, List<Figure> copy_list, int neddedElems) {
        Random rand = new Random();
        List<Figure> newList = new ArrayList<>();
        Stream<Figure> tempStream;

        for (int count = 0; count < neddedElems; count++) {

            if (count % 2 == 0) {
                tempStream = copy_list.stream().filter(predicate_even);
            } else {
                tempStream = copy_list.stream().filter(predicate_odd);
            }

            List<Figure> tempList = tempStream.toList();
            int randomIndex = rand.nextInt(tempList.size());
            Figure neddedFig = tempList.get(randomIndex);
            newList.add(neddedFig);

        }
        return newList;
    }

    public static List<Figure> exceptRepeatInList(List<Figure> list, int elems) {
        Random rand = new Random();
        List<Figure> copy_list = list;
        List<Figure> newList = new ArrayList<>();

        for (int i = 0; i < elems; i++) {
            int randomIndex = rand.nextInt(copy_list.size());
            Figure randomElement = copy_list.get(randomIndex);
            newList.add(randomElement);
            copy_list.remove(randomIndex);
        }
        return newList;
    }

    public static List<Figure> makeRandomOrderList(List<Figure> list) {
        Random rand = new Random();

        List<Figure> choices_copy = new ArrayList<>(list.subList(0, list.size()));
        List<Figure> tempList = new ArrayList<>();

        //Создание рандомного порядка возможных ответов
        int numberOfElements = 3;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(choices_copy.size());
            Figure randomElement = choices_copy.get(randomIndex);
            tempList.add(randomElement);
            choices_copy.remove(randomIndex);
        }

        return tempList;
    }

    /*
    * Возвращает список из двух списков: 1 = правильные ответы, 2 = неправильные
    *
    *
    *
    * */
    public static List<List<Figure>> makeAnswers(List<Figure> threes, Param rightParam) {
        Figure lastFigure = threes.get(threes.size() - 1);
        List<Figure> rightAnswers;
        List<Figure> wrongAnswers;

        // Проверка rightParam на параметр - ЦВЕТ, ТИП или РАЗМЕР

        // Например: если размер BIG -> цвет = BLUE
        if (rightParam instanceof Size) {

            if (lastFigure.getSize() == rightParam) {
                rightAnswers = Game.figures_only_blue;
                wrongAnswers = Game.figures_only_yellow;
            } else {
                rightAnswers = Game.figures_only_yellow;
                wrongAnswers = Game.figures_only_blue;
            }

        // Например: если цвет BLUE -> тип = CIRCLE
        } else if (rightParam instanceof Color) {

            if (lastFigure.getColor() == rightParam) {
                rightAnswers = Game.figures_only_circles;
                wrongAnswers = Game.figures_only_squares;
            } else {
                rightAnswers = Game.figures_only_squares;
                wrongAnswers = Game.figures_only_circles;
            }

        // Например: если тип SQUARE -> размер = BIG
        } else {

            if (lastFigure.getType() == rightParam) {
                rightAnswers = Game.figures_only_big;
                wrongAnswers = Game.figures_only_small;
            } else {
                rightAnswers = Game.figures_only_small;
                wrongAnswers = Game.figures_only_big;
            }
        }

        List<List<Figure>> answers = new ArrayList<>();
        answers.add(rightAnswers);
        answers.add(wrongAnswers);

        return answers;
    }

    // 2nd Tour
    public static List<Figure> makeThrees() {
        Random rand = new Random();
        Figure firstFigure = figures.get(rand.nextInt(0, figures.size()));
        Figure secondFigure;
        Figure thirdFigure;

        // Проверяем первую фигуру и на ее основе формируем вторую
        if (firstFigure.getSize() == Size.BIG) {
            secondFigure = Game.figures_only_blue.get(rand.nextInt(0, Game.figures_only_blue.size()));
        } else {
            secondFigure = Game.figures_only_yellow.get(rand.nextInt(0, Game.figures_only_yellow.size()));
        }

        // По аналогии формируем третью фигуру
        if (secondFigure.getSize() == Size.BIG) {
            thirdFigure = Game.figures_only_blue.get(rand.nextInt(0, Game.figures_only_blue.size()));
        } else {
            thirdFigure = Game.figures_only_yellow.get(rand.nextInt(0, Game.figures_only_yellow.size()));
        }

        List<Figure> threes = new ArrayList<>();
        threes.add(firstFigure);
        threes.add(secondFigure);
        threes.add(thirdFigure);

        return threes;
    }

    /*
    * Метод для выбора правильного и неправильных ответов
    *
    * @rightAnswers - правильные ответы (правильные фигуры)
    * @wrongAnswers - неправильные ответы (неправильные фигуры)
    * @threes - массив, состоящий из 3-х фигур, предложенных для выбора игроку (2 неправильных и 1 правильный)
    * @bool - условие, что игра идет последовательно (0 - идти тем же цветом/типом/размером; 1 - наоборот)
    * @currObj - объект, показывающий, относительно чего будет вестись проверка (цвет, размер, тип)
    *
    *
    * */
    public static List<Figure> getChoices(List<Figure> rightAnswers, List<Figure> wrongAnswers,
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
}
