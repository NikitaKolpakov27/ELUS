package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.enums.Type;
import com.company.model.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tools {
    static Predicate<Figure> diffColor = figure -> figure.getColor() != Color.BLUE || figure.getColor() == Color.YELLOW;
    static Predicate<Figure> sameColor = figure -> figure.getColor() == Color.BLUE || figure.getColor() != Color.YELLOW;

    static Predicate<Figure> diffSize = figure -> figure.getSize() != Size.BIG || figure.getSize() == Size.SMALL;
    static Predicate<Figure> sameSize = figure -> figure.getSize() == Size.BIG || figure.getSize() != Size.SMALL;

    static Predicate<Figure> diffType = figure -> figure.getType() != Type.CIRCLE || figure.getType() == Type.SQUARE;
    static Predicate<Figure> sameType = figure -> figure.getType() == Type.CIRCLE || figure.getType() != Type.SQUARE;

    public static List<Figure> filterDifferentParamsList(List<Figure> list, Object currObj, int neddedElems) {
        Random rand = new Random();
        List<Figure> copy_list = list;
        List<Figure> newList;

//        Predicate<Figure> diffColor = figure -> figure.getColor() != currObj;
//        Predicate<Figure> sameColor = figure -> figure.getColor() == currObj;
//
//        Predicate<Figure> diffSize = figure -> figure.getSize() != currObj;
//        Predicate<Figure> sameSize = figure -> figure.getSize() == currObj;
//
//        Predicate<Figure> diffType = figure -> figure.getType() != currObj;
//        Predicate<Figure> sameType = figure -> figure.getType() == currObj;

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
        for (int count = 0; count < neddedElems; count++) {
            if (count % 2 == 0) {
                Stream<Figure> tempStream = copy_list.stream().filter(predicate_even);
                List<Figure> tempList = tempStream.collect(Collectors.toList());
                int randomIndex = rand.nextInt(tempList.size());
                Figure neddedFig = tempList.get(randomIndex);
                newList.add(neddedFig);
            } else {
                Stream<Figure> tempStream = copy_list.stream().filter(predicate_odd);
                List<Figure> tempList = tempStream.collect(Collectors.toList());
                int randomIndex = rand.nextInt(tempList.size());
                Figure neddedFig = tempList.get(randomIndex);
                newList.add(neddedFig);
            }
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
}
