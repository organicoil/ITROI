package com.company.entity;

public class Info {
    private Status status;

    @Override
    public String toString() {
        return "Info{" +
                "status=" + status +
                ", PError=" + PError +
                '}';
    }

    private PError PError;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PError getPError() {
        return PError;
    }

    public void setPError(PError PError) {
        this.PError = PError;
    }
}
