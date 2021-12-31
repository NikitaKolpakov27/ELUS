package com.company;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.enums.Type;
import com.company.model.Figure;
import com.company.service.Game;
import com.company.service.Tools;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.firstTour();

//        List<Figure> baseList = List.of(
//                new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
//                new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE),
//                new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
//                new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE)
//        );
//
//        Stream<Figure> stream = baseList.stream().filter(Tools.diffColor);
//
//        List<Figure> newList = Tools.filterDifferentParamsList(baseList, Size.BIG, 8);
////        List<Figure> threes = Tools.filterDifferentParamsList(baseList, Color.BLUE, 3);
//
//        newList = stream.collect(Collectors.toList());
//
//        System.out.println("New List: ");
//
//        for (Figure figure: newList) {
//            System.out.println(figure.toString());
//        }



//        List<String> strings = givenList_whenNumberElementsChosen_shouldReturnRandomElementsNoRepeat();
//        System.out.println("list: " + strings);
    }

    public static List<String> givenList_whenNumberElementsChosen_shouldReturnRandomElementsNoRepeat() {
        Random rand = new Random();
        List<String> givenList = new ArrayList<>(List.of("BIG BLUE CIRCLE", "SMALL BLUE CIRCLE",
                "BIG YELLOW CIRCLE", "SMALL YELLOW CIRCLE", "SMALL YELLOW CIRCLE"));
        List<String> newList = new ArrayList<>();

        int numberOfElements = 3;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            String randomElement = givenList.get(randomIndex);
            newList.add(randomElement);
            System.out.println(randomElement);
            givenList.remove(randomIndex);
        }
        return newList;
    }
}
