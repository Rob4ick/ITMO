package utility;

import java.util.Objects;
import java.util.Scanner;

public interface Console {
    void print(Object obj);
    void println(Object obj);
    String readln();
    void printError(Object obj);
    void printMessage(Object obj);
    void printMessag(Object obj);
    void setFileScanner(Scanner obj);
 //   void selectConsoleScanner();
}