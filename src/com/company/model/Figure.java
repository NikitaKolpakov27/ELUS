package com.company.model;

import com.company.enums.*;

public class Figure {
    Color color;
    Size size;
    Type type;

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }

    public Figure(Color color, Size size, Type type) {
        this.color = color;
        this.size = size;
        this.type = type;
    }

    @Override
    public String toString() {
        return size + " " + color + " " + type;
    }
}
