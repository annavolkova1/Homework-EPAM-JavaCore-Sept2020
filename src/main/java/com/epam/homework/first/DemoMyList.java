package com.epam.homework.first;

import java.util.Arrays;

public class DemoMyList {

  public static void main(String[] args) {

    MyList<String> list = new MyList<>(String.class);
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");
    list.add("1");
    list.add("2");
    list.add("3");

    System.out.println(Arrays.toString(list.getMyList()));
    System.out.println(list.getSize());

    System.out.println(list.remove(2));
    System.out.println(list.remove("2"));

    MyList<String> list2 = new MyList<>(String.class);
    list2.add("1_");
    list2.add("2_");
    list2.add("3_");
    list2.add("4_");
    list2.add("5_");
    list2.add("1_");
    list2.add("2_");
    list2.add("3_");

    System.out.println(Arrays.toString(list.getMyList()));
    System.out.println(Arrays.toString(list2.getMyList()));

    list.copy(list, list2);

    System.out.println(Arrays.toString(list.getMyList()));
  }
}
