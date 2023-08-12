package com.company.service;

import com.company.enums.Color;
import com.company.enums.Size;
import com.company.model.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.company.service.Game.figures;

public class SecondTour {

    public GameProcess gameProcess;

    public SecondTour(GameProcess gameProcess) {
        this.gameProcess = gameProcess;
    }

    public void secondTour() {
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
            choosableObj = Color.BLUE;
            gameBySize_sec(currSize, (Color) choosableObj);
        } else {
            currSize = Size.SMALL;
            choosableObj = Color.YELLOW;
            gameBySize_sec(currSize, (Color) choosableObj);
        }
    }

    // 2-й раунд. Игра по размеру (если BIG => BLUE, SMALL => YELLOW)
    private void gameBySize_sec(Size currSize, Color currColor) {
        Stream<Figure> currSizeAnswers;
        Stream<Figure> wrongSizeAnswers;

        currSizeAnswers = figures.stream().filter(figure -> figure.getColor() == currColor);
        wrongSizeAnswers = figures.stream().filter(figure -> figure.getColor() != currColor);

        //Перевод из Стримов в Листы
        List<Figure> rightAnswers = currSizeAnswers.collect(Collectors.toList());
        List<Figure> wrongAnswers = wrongSizeAnswers.collect(Collectors.toList());

        List<Figure> threes;

        // Временный массив для правильной "тройки"
        List<Figure> rightAnswers_copy = new ArrayList<>(rightAnswers.subList(0, rightAnswers.size()));
        threes = Tools.exceptRepeatInList(rightAnswers_copy, 3); // АХТУНГ!!! Нужно переделать процесс формирования "тройки"

        //НОВОЕ
        threes = Tools.makeThrees();
        List<List<Figure>> answers = Tools.makeAnswers(threes, Size.BIG);
        rightAnswers = answers.get(0);
        wrongAnswers = answers.get(1);

        this.gameProcess.playRound(currSize, rightAnswers, wrongAnswers, threes, 0);
    }
}
