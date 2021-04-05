package com.epam.homework.third.semaphore;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Carousel {

  public static void main(String[] args) throws InterruptedException {

    Semaphore horse = new Semaphore(6);
    List<Thread> threads = Stream.of(
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse),
        new Child(horse)
    )
        .map(Thread::new)
        .peek(Thread::start)
        .collect(Collectors.toList());

    for (Thread thread : threads) {
      thread.join();
    }
  }
}
