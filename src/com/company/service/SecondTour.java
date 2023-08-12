package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.model.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.company.service.Game.ID_BIG_BLUE;
import static com.company.service.Game.figures;

public class SecondTour implements Tour {

    public GameProcess gameProcess;

    public SecondTour(GameProcess gameProcess) {
        this.gameProcess = gameProcess;
    }

    // 2-й раунд. Игра по размеру (если BIG => BLUE, SMALL => YELLOW)
    private void gameBySize(Size currSize) {
        List<Figure> threes = Tools.makeThrees();
        List<List<Figure>> answers = Tools.makeAnswers(threes, currSize);
        List<Figure> rightAnswers = answers.get(0);
        List<Figure> wrongAnswers = answers.get(1);

        this.gameProcess.playRound(Color.BLUE, rightAnswers, wrongAnswers, threes, 0, ID_BIG_BLUE);
    }

    @Override
    public void startTour() {
        //Рандомно получаем, как будем играть (по цвету, размеру или типу) (пока не используется)
        Object obj = null;
        Random random = new Random();
        int rand = random.nextInt(3);

        //Пока только с размером балуемся
        int ran = random.nextInt(2);
        Size currSize;
        Object choosableObj;

        ran = 0; // специально делаем пока что ток одно условие

        //на данный момент добавлено только условие "если БИГ ->  СИНИЙ, иначе -> ЖЁЛТЫЙ"
        if (ran == 0) {
            currSize = Size.BIG;
            gameBySize(currSize);
        } else {
            currSize = Size.SMALL;
            gameBySize(currSize);
        }
    }
}
