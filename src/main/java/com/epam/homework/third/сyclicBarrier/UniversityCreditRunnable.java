package com.epam.homework.third.сyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class UniversityCreditRunnable implements Runnable{
  private final CyclicBarrier cyclicBarrier;
  private final UniversityCredit universityCredit;

  public UniversityCreditRunnable(UniversityCredit universityCredit, CyclicBarrier cyclicBarrier) {
    this.universityCredit = universityCredit;
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    System.out.println("Student receives credit " + universityCredit);
    try {
      TimeUnit.SECONDS.sleep(5);
      System.out.printf("The student has received a %s credit. \n", universityCredit);
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }

}
