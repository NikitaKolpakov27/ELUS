package com.company;

import com.company.service.FirstTour;
import com.company.service.Game;
import com.company.service.GameProcess;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        GameProcess gameProcess = new GameProcess();
        FirstTour firstTour = new FirstTour(gameProcess);
        Game game = new Game(firstTour);
        game.getFirstTour().startFirstTour();

    }
}
