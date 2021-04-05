package com.epam.homework.third.exchanger;

import java.util.concurrent.Exchanger;

public class Person extends Thread {

  Exchanger<String> exchanger;

  public Person(Exchanger<String> exchanger) {

    this.exchanger = exchanger;
    start();
  }

  @Override
  public void run() {

    try {
      System.out.println(exchanger.exchange("Hello. Here is my post notice."));
      sleep(3000);
      System.out.println("Thanks! Have a nice day!");
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
