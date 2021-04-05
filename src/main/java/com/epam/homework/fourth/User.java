package com.epam.homework.fourth;

public class User {

  public User(int PIN, int ID) {

    this.PIN = PIN;
    this.ID = ID;
  }

  private final int PIN;
  private final int ID;

  public boolean checkPIN(int PIN) {

    return this.PIN == PIN;
  }

  public int getID() {

    return ID;
  }
}
