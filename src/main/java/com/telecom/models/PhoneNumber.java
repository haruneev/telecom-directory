package com.telecom.models;

public class PhoneNumber {
    private final String number;
    private boolean isActivated;

    public PhoneNumber(String number) {
        this.number = number;
        this.isActivated = false;
    }

    public String getNumber() {
        return number;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        this.isActivated = activated;
    }
}
