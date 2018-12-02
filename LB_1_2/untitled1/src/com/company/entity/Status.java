package com.company.entity;

public enum Status {
    IN_PROGRESS("in progress"), DONE("done"), ERROR("error");

    private String textValue;
    Status(String value) {
        textValue = value;
    }

    public static Status fromString(String text) {
        for (Status b : Status.values()) {
            if (b.textValue.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
