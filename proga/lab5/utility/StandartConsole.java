package utility;

import java.util.Scanner;

public class StandartConsole implements Console{
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private static Scanner fileScanner = null;
    private static Scanner sc = new Scanner(System.in);
    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }

    @Override
    public void println(Object obj) {
         System.out.println(obj);
    }

    @Override
    public String readln() {
        if(fileScanner != null){
            return fileScanner.nextLine();
        }
        return sc.nextLine();
    }

    @Override
    public void printMessage(Object obj) {
        if(fileScanner == null)
            System.out.println(YELLOW_TEXT + obj + RESET);
    }
    public void printMessag(Object obj){
        System.out.print(obj);
    }
    @Override
    public void printError(Object obj) {
        if(fileScanner == null)
            System.out.println(RED_TEXT + "Error: " + obj + RESET);
    }

    public void setFileScanner(Scanner scanner) {
        this.fileScanner = scanner;
    }
}