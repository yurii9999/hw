package com.group144.kuzmin;

import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter extends Printer {
    @Override
    public void print(String string) {
        try (FileWriter writer = new FileWriter("output.txt", false)) {
            writer.append(string);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
