package com.epam.homework.third.сyclicBarrier;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    CyclicBarrier cyclicBarrier = new CyclicBarrier(UniversityCredit.values().length,
        () -> System.out.println("Student admitted to the session!"));

    ExecutorService executorService = Executors.newCachedThreadPool();

    Arrays.stream(UniversityCredit.values())
        .map(universityCredit -> new UniversityCreditRunnable(universityCredit, cyclicBarrier))
        .forEach(executorService::submit);

    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);
  }
}
