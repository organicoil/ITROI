package com.company.entity;

public enum Currency {
    HRYVNIA("hryvnia"), RUBLE("ruble"), DOLLARS("dollars"), EUROS("euros"), POUNDS("pounds");

    private String textValue;
    Currency(String value) {
        textValue = value;
    }

    public static Currency fromString(String text) {
        for (Currency b : Currency.values()) {
            if (b.textValue.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
