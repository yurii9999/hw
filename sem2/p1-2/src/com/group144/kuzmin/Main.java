package com.group144.kuzmin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List list = new List();

        Scanner in = new Scanner(System.in);
        int toDo = 0;
        do {
            System.out.println("ADD -- 0\nPRINT -- 1\nDELET E -- 2\nPEEK -- 3\nGET_SIZE -- 4\nEXIT -- 5\nEnter toDo: ");
            int value = 0;
            int position = 0;

            toDo = in.nextInt();
            switch (action.values()[toDo]) {
                case ADD:
                    System.out.println("Enter value & position: ");
                    value = in.nextInt();
                    position = in.nextInt();
                    list.add(value, position);
                    break;
                case PRINT:
                    list.print();
                    break;
                case GET_SIZE:
                    System.out.print("Size: ");
                    System.out.println(list.size());
                    break;
                case PEEK:
                    System.out.println("Enter position: ");
                    position = in.nextInt();
                    System.out.println("Position: " + position + " value: " + list.peek(position));
                    break;
                case DELETE:
                    System.out.println("Enter position: ");
                    position = in.nextInt();
                    list.delete(position);
                    break;
                case EXIT:
                    break;
                    default:
                        break;
            }
        } while (action.values()[toDo] != action.EXIT);

    }

    public enum action {
        ADD,
        PRINT,
        DELETE,
        PEEK,
        GET_SIZE,
        EXIT
    }
}