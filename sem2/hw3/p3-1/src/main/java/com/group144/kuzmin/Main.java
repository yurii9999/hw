package com.group144.kuzmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hasher hasher = new ClassicHasher();
        HashTable table = new HashTable(hasher , 4);

        try {
            Scanner ifstream = new Scanner(new File("input.txt"));
            while (ifstream.hasNext()) {
                Element a = Reader.readElement(ifstream);
                table.add(a);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }

        System.out.println("Add 0; Delete 1; Rebuild 2; Get element 3; Print 4;\nIs included 5; Load factor 6; Size 7; Array size 8;\nAmount conflicts 9; Max length 10; Exit 11");
        Scanner in = new Scanner(System.in);
        Action action = null;
        do {
            int nextAction = in.nextInt();
            action = Action.values()[nextAction];
            String key;
            int value;
            switch (action) {
                case ADD:
                    System.out.println("Enter key & int: ");
                    key = in.next();
                    value = in.nextInt();

                    table.add(new Element(key, value));
                    break;
                case DELETE:
                    System.out.println("Enter key: ");
                    key = in.next();
                    try {
                        table.delete(key);
                    } catch (NotFoundExcaption notFoundExcaption) {
                        System.out.println("Incorrect key");
                    }
                    break;
                case REBUILD:
                    System.out.println("Enter newSize: ");
                    int newSize = in.nextInt();
                    System.out.println("Choose hasher 0 - ClassicHasher; 1 - SimpleHasher: ");
                    HasherChoice hasherChoice = HasherChoice.values()[in.nextInt()];
                    switch (hasherChoice) {
                        case CLASSIC_HASHER:
                            hasher = new ClassicHasher();
                            break;
                        case SIMPLE_HASHER:
                            hasher = new SimpleHasher();
                            break;
                    }

                    table.rebuild(hasher, newSize);
                    break;
                case GET_ELEMENT:
                    System.out.println("Enter key: ");
                    key = in.next();

                    Element result = null;
                    try {
                        result = table.getElement(key);
                        System.out.println(result);
                    } catch (NotFoundExcaption notFoundExcaption) {
                        System.out.println("Incorrect key");
                    }
                    break;
                case PRINT:
                    table.print();
                    break;
                case IS_INCLUDED:
                    System.out.println("Enter key: ");
                    key = in.next();
                    System.out.println(table.isIncluded(key));
                    break;
                case LOAD_FACTOR:
                    System.out.println(table.loadFactor());
                    break;
                case SIZE:
                    System.out.println(table.size());
                    break;
                case ARRAY_SIZE:
                    System.out.println(table.arraySize());
                    break;
                case CONFLICTS:
                    System.out.println(table.amountConflicts());
                    break;
                case MAX_LENGTH:
                    System.out.println(table.maxLength());
                    break;
                case EXIT:
                    break;
            }
        } while (action != Action.EXIT);
    }

    private static class Reader {
        public static Element readElement(Scanner in) {
            String string = in.next();
            int integer = in.nextInt();

            return new Element(string, integer);
        }
    }

    private enum Action {
        ADD, DELETE, REBUILD, GET_ELEMENT, PRINT, IS_INCLUDED, LOAD_FACTOR, SIZE, ARRAY_SIZE, CONFLICTS, MAX_LENGTH, EXIT
    }

    private enum HasherChoice {
        CLASSIC_HASHER, SIMPLE_HASHER
    }
}
