package com.epam.homework.third.exchanger;

import java.util.concurrent.Exchanger;

public class Post extends Thread {

  Exchanger<String> exchanger;

  public Post(Exchanger<String> exchanger) {

    this.exchanger = exchanger;
    start();
  }

  @Override
  public void run() {

    try {
      System.out.println(exchanger.exchange("Good afternoon. Please give notice."));
      sleep(2000);
      System.out.println("Keep your parcel!");
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
