package com.company;

import com.company.enums.*;
import com.company.model.Figure;
import com.company.service.Game;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.firstRound();



        List<String> strings = givenList_whenNumberElementsChosen_shouldReturnRandomElementsNoRepeat();
        System.out.println("list: " + strings);
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
