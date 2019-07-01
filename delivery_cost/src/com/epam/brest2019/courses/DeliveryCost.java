package com.epam.brest2019.courses;

import com.epam.brest2019.courses.files.CSVFileReader;
import com.epam.brest2019.courses.files.FileReader;
import com.epam.brest2019.courses.menu.CorrectValue;
import com.epam.brest2019.courses.menu.EnteredValue;
import com.epam.brest2019.courses.scanner.ConsoleReader;
import com.epam.brest2019.courses.scanner.InputReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class DeliveryCost {

    public static final String PRICE_PER_KG = "price_per_kg.csv";
    public static final String PRICE_PER_KM = "price_per_km.csv";

    private FileReader fileReader;
    private InputReader inputReader;
    private Map<BigDecimal, BigDecimal> weightPrices;
    private Map<BigDecimal, BigDecimal> distancePrices;

    public DeliveryCost() throws IOException {
        fileReader = new CSVFileReader();
        inputReader = new ConsoleReader();
        weightPrices = fileReader.readData(PRICE_PER_KG);
        distancePrices = fileReader.readData(PRICE_PER_KM);
    }

    public BigDecimal startOperation() {
        EnteredValue distance = inputReader.receiveValue("Enter distance in km or 'q' for quit");
        if (isReturn(distance)) return null;
        EnteredValue weight = inputReader.receiveValue("Enter weight in kg or 'q' for quit");
        if (isReturn(weight)) return null;
        return costCalculate((CorrectValue) distance, (CorrectValue) weight);
    }

    private boolean isReturn(EnteredValue value) {
        return value.getType() == EnteredValue.Types.EXIT ?  true : false;
    }

    private BigDecimal costCalculate(CorrectValue distance, CorrectValue weight) {
        BigDecimal pricePerKm = getUnitPrice(distance.getValue(), distancePrices);
        BigDecimal pricePerKg = getUnitPrice(weight.getValue(), weightPrices);
        return distance.getValue().multiply(pricePerKm).add(weight.getValue().multiply(pricePerKg));
    }

    private BigDecimal getUnitPrice(BigDecimal key, Map<BigDecimal, BigDecimal> prices) {
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal oldKey =BigDecimal.ZERO;
        for (Map.Entry<BigDecimal, BigDecimal> map : prices.entrySet()) {
            if (key.compareTo(map.getKey()) >= 0 && map.getKey().compareTo(oldKey) ==1) {
                price = map.getValue();
                oldKey = map.getKey();
            }
        }
        return price;
    }
}
