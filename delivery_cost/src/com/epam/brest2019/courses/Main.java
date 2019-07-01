package com.epam.brest2019.courses;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static final String EXIT = "q";
    private DeliveryCost deliveryCost;
    private Scanner scanner;
    BigDecimal result;
    String isExit;

    public Main() throws IOException {
        deliveryCost = new DeliveryCost();
        scanner = new Scanner(System.in);
        result = BigDecimal.ZERO;
        isExit = "";
    }

    public void start() {
        while (!isExit.toLowerCase().equals(EXIT)) {

            if ((result = deliveryCost.startOperation()) != null) {
                System.out.println(result);
            }
            System.out.println("Enter any character to continue or '" + EXIT + "' to exit");
            isExit = scanner.next();
        }
        System.out.println("Buy");
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.start();
    }

}
