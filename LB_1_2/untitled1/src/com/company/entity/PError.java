package com.company.entity;

public class PError {
    private int code;
    private String shortMsg;
    private String additionalMsg;

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "PError{" +
                "code=" + code +
                ", shortMsg='" + shortMsg + '\'' +
                ", additionalMsg='" + additionalMsg + '\'' +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getShortMsg() {
        return shortMsg;
    }

    public void setShortMsg(String shortMsg) {
        this.shortMsg = shortMsg;
    }

    public String getAdditionalMsg() {
        return additionalMsg;
    }

    public void setAdditionalMsg(String additionalMsg) {
        this.additionalMsg = additionalMsg;
    }
}
