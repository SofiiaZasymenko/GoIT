package module8;

import module8.shapes.Circle;
import module8.shapes.Oval;
import module8.shapes.Pentagon;
import module8.shapes.Quad;
import module8.shapes.Shape;
import module8.shapes.Triangle;

public class NamePrinter {
    public void printName(Shape shape) {
        System.out.println(shape.getName());
    }

    public static void main(String[] args) {
        NamePrinter namePrinter = new NamePrinter();
        namePrinter.printName(new Circle());
        namePrinter.printName(new Quad());
        namePrinter.printName(new Triangle());
        namePrinter.printName(new Pentagon());
        namePrinter.printName(new Oval());
    }
}
