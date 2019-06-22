package com.epam.brest2019.courses;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private Properties properties;

    public Main() throws IOException {
        this.scanner = new Scanner(System.in);
        this.properties = loadProperties();
    }

    private Properties loadProperties() throws IOException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("price_per_km.properties");
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }

    private BigDecimal getValue() {
        BigDecimal value = null;
        String inputStream;
        while (value == null) {
            inputStream = scanner.nextLine();
            if (inputStream.toLowerCase().equals("q")) break;
            try {
                value = new BigDecimal(inputStream);
            } catch (NumberFormatException ex) {
                System.out.println("You entered wrong value, try again or enter 'q' to exit");
            }
        }
        return value;
    }

    private String calculations(BigDecimal weight, BigDecimal distance) {
        BigDecimal pricePerKm;
        BigDecimal pricePerKg = new BigDecimal(properties.getProperty("price.per.kg"));
        if (distance.compareTo(new BigDecimal(100)) == 1) {
            pricePerKm = new BigDecimal(properties.getProperty("price.per.km.where.weight.more100"));
        } else {
            pricePerKm = new BigDecimal(properties.getProperty("price.per.km.where.weight.less100"));
        }
        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm)).toString();
    }

    public void getSum() {
        System.out.println("Enter the weight in kilograms or 'q' to exit");
        BigDecimal weight = getValue();
        if (weight == null) {
            System.out.println("Buy!");
            return;
        }
        System.out.println("Enter the distance in kilometers or 'q' to exit");
        BigDecimal distance = getValue();
        if (distance == null) {
            System.out.println("Buy!");
            return;
        }
        System.out.println(calculations(weight, distance));
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.getSum();
    }
}
