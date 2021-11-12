package ru.krasnopolsky.enums;

public enum  Gender {
    MALE("m"),
    FEMALE("f");

    private String value;

    Gender(String gender) {
        this.value = gender;
    }

    public String getValue() {
        return value;
    }
}
