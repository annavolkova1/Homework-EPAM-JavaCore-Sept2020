package com.epam.homework.fourth;

import com.epam.homework.fourth.ATM;

public class ATMFactory {
  public ATM createATM(BankAccount bankAccount) {
    return new ATM(bankAccount);
  }

}
