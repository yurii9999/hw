package com.group144.kuzmin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Stack stack = new Stack();
        Scanner in = new Scanner(System.in);

        int toDo = 0;
        do {
            int value = 0;

            System.out.println("Push 0\nPop 1\nPeek 2\nPrint 3\nGet size 4\nExit 5");
            toDo = in.nextInt();
            switch (action.values()[toDo]) {
                case PUSH:
                    System.out.println("Enter value: ");
                    value = in.nextInt();
                    stack.push(value);
                    break;
                case POP:
                    System.out.println("Value: " + stack.pop());
                    break;
                case PEEK:
                    System.out.println("Value: " + stack.peek());
                    break;
                case PRINT:
                    stack.print();
                    break;
                case GET_SIZE:
                    System.out.println("Size: " + stack.size());
                    break;
                case EXIT:
                    break;
                    default:
                        break;
            }
        } while (action.values()[toDo] != action.EXIT);
    }

    public enum action {
        PUSH,
        POP,
        PEEK,
        PRINT,
        GET_SIZE,
        EXIT
    }
}
