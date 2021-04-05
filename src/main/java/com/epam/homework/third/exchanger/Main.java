package com.epam.homework.third.exchanger;

import java.util.concurrent.Exchanger;

public class Main {

  private static final Exchanger<String> exchanger = new Exchanger<>();

  public static void main(String[] args) {

    new Post(exchanger);
    new Person(exchanger);
  }
}
