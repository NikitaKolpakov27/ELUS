package com.company.service;
import com.company.enums.Color;
import com.company.enums.Size;
import com.company.enums.Type;
import com.company.model.Figure;
import java.util.*;

public class Game {
    public static final List<Figure> figures = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE),
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    public static final List<Figure> figures_only_big = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE));

    public static final List<Figure> figures_only_small = List.of(
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    public static final List<Figure> figures_only_blue = List.of(
            new Figure(Color.BLUE, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.SQUARE),
            new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.SQUARE));

    public static final List<Figure> figures_only_yellow = List.of(
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    public static final List<Figure> figures_only_circles = List.of(
            new Figure(Color.YELLOW, Size.BIG, Type.CIRCLE), new Figure(Color.BLUE, Size.BIG, Type.CIRCLE),
            new Figure(Color.YELLOW, Size.SMALL, Type.CIRCLE), new Figure(Color.BLUE, Size.SMALL, Type.CIRCLE));

    public static final List<Figure> figures_only_squares = List.of(
            new Figure(Color.BLUE, Size.SMALL, Type.SQUARE), new Figure(Color.YELLOW, Size.BIG, Type.SQUARE),
            new Figure(Color.BLUE, Size.BIG, Type.SQUARE), new Figure(Color.YELLOW, Size.SMALL, Type.SQUARE));

    public Tour tour;

    public Game(Tour tour) {
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }
}
