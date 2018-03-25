package com.group144.kuzmin;

public class Operand extends Node {

    public Operand(String token) {
        data = token;
    }

    @Override
    public String toString() {
        return data;
    }

    /** @return data.toString */
    @Override
    public int calculate() {
        return Integer.parseInt(data);
    }
}
