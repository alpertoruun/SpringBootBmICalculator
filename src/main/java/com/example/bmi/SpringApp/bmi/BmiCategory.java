package com.example.bmi.SpringApp.bmi;

public enum BmiCategory {
    SEVERE_THINNESS("Severe Thinness", 0, 16),
    MODERATE_THINNESS("Moderate Thinness", 16, 17),
    MILD_THINNESS("Mild Thinness", 17, 18.5),
    NORMAL("Normal", 18.5, 25),
    OVERWEIGHT("Overweight", 25, 30),
    OBESE_CLASS_I("Obese Class I", 30, 35),
    OBESE_CLASS_II("Obese Class II", 35, 40),
    OBESE_CLASS_III("Obese Class III", 40, Double.MAX_VALUE);

    private final String name;
    private final double lowerBound;
    private final double upperBound;

    BmiCategory(String name, double lowerBound, double upperBound) {
        this.name = name;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }


    public String getName() {
        return name;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }


    public static BmiCategory findByValue(double value) {
        for (BmiCategory category : BmiCategory.values()) {
            if (value >= category.lowerBound && value < category.upperBound) {
                return category;
            }
        }

        throw new IllegalArgumentException("Invalid BMI value: " + value);
    }
}