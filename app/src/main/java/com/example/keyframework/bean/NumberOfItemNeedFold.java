package com.example.keyframework.bean;

public class NumberOfItemNeedFold {
    int number;

    public NumberOfItemNeedFold(int number, int lastLenth) {
        this.number = number;
        this.lastLenth = lastLenth;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLastLenth() {
        return lastLenth;
    }

    public void setLastLenth(int lastLenth) {
        this.lastLenth = lastLenth;
    }

    int lastLenth;
}
