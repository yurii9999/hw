package com.group144.kuzmin;

public class Operand extends Node {

    public Operand(String token) {
        data = token;
    }

    @Override
    public void print() {
        System.out.print(data);
    }

    @Override
    public int calculate() {
        return Integer.parseInt(data);
    }
}
