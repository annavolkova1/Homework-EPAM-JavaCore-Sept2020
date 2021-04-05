package com.epam.homework.fourth.test;

import com.epam.homework.fourth.ATM;
import com.epam.homework.fourth.ATMFactory;
import com.epam.homework.fourth.BankAccount;
import com.epam.homework.fourth.Currency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ATMFactoryTest {

  @Test
  public void shouldReturnNewATM() {

    ATMFactory atmFactory = new ATMFactory();
    ATM result = atmFactory.createATM(new BankAccount(100, 1, Currency.LIRA));
    assertNotNull(result);
  }
}
