package com.group144.kuzmin;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String file;
        try {
            FileInputStream ifstream = new FileInputStream("input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(ifstream));

            file = br.readLine();
        }
        catch (IOException e) {
            System.out.println("Cant read");
            return;
        }

        Expression expression = new Expression(file);

        expression.print();
        System.out.print(" = " + expression.calculate());
    }
}
