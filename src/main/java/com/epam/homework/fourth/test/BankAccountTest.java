package com.epam.homework.fourth.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.homework.fourth.BankAccount;
import com.epam.homework.fourth.Currency;
import com.epam.homework.fourth.exception.InsufficientFundsException;

public class BankAccountTest {

  private final BankAccount bankAccount = new BankAccount(100, 1, Currency.LIRA);

  @Test
  public void shouldReturnCorrectBalance() {

    double result = bankAccount.getBalance();
    assertEquals(100, result, 0);
  }

  @Test
  public void shouldReturnCorrectCurrency() {

    Currency result = bankAccount.getCurrency();
    assertEquals(Currency.LIRA, result);
  }

  @Test
  public void shouldReturnCorrectBalanceAfterDeposit() {

    bankAccount.deposit(500, Currency.LIRA);
    double result = bankAccount.getBalance();
    assertEquals(600, result, 0);
  }

  @Test
  public void shouldReturnCorrectBalanceAfterWithdraw() throws InsufficientFundsException {

    bankAccount.withdraw(10, Currency.LIRA);
    double result = bankAccount.getBalance();
    assertEquals(90, result, 0);
  }

  @Test
  public void withdraw_shouldThrownAnError() {

    assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(150, Currency.LIRA));
  }
}
