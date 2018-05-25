package com.group144.kuzmin;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new UniqueList<>();

        for (int i = 0; i < 11; i++) {
            int element = i / 2;
            try {
                list.add(element, 0);
            } catch (AlreadyIncludedException e) {
                System.out.println("Already included: " + element);
            }
        }
        System.out.println("List: " + list);

        for (int i = 0; i < 11; i ++) {
            int element = i / 3;
            try {

                list.delete((Integer) element);
            } catch (NotFoundException e) {
                System.out.println("Not found: " + element);
            }
        }
        System.out.println("List: " + list);
    }
}
