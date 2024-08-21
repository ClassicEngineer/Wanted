package ru.ddev.first;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsuranceSendingTest {

  private InsuranceSending insuranceSending;

  @Mock
  private VacationCheck vacationCheck;

  @BeforeEach
  public void setUp() {
    insuranceSending = new InsuranceSending(vacationCheck);
  }

  @Test
  void shouldReturnOriginDateWhenDateIsFirstAndWorkingDay() {
    LocalDate firstNonWorkingDay = LocalDate.of(2024, 8, 1);
    Date date = Date.valueOf(firstNonWorkingDay);
    when(vacationCheck.getVacCheck(date)).thenReturn(date);

    Date nearestSendingDate = insuranceSending.getNearestSendingDate(date);

    Assertions.assertEquals(date, nearestSendingDate);
  }

  @Test
  void shouldReturnNextWorkingDateWhenDateIsFirstAndWorkingDay() {
    LocalDate firstWorkingDay = LocalDate.of(2024, 8, 1);
    LocalDate nextWorkingDay = LocalDate.of(2024, 8,2 );
    Date date = Date.valueOf(firstWorkingDay);
    Date working = Date.valueOf(nextWorkingDay);
    when(vacationCheck.getVacCheck(date)).thenReturn(working);

    Date nearestSendingDate = insuranceSending.getNearestSendingDate(date);

    Assertions.assertEquals(working, nearestSendingDate);
  }

  @Test
  void shouldReturnNextWorkingDateInNextMonthWhenDateIsTwentyAndNonWorkingDay() {
    LocalDate firstWorkingDay = LocalDate.of(2024, 8, 20);
    LocalDate nextWorkingDay = LocalDate.of(2024, 9,1);
    Date date = Date.valueOf(firstWorkingDay);
    Date working = Date.valueOf(nextWorkingDay);
    when(vacationCheck.getVacCheck(date)).thenReturn(working);

    Date nearestSendingDate = insuranceSending.getNearestSendingDate(date);

    Assertions.assertEquals(working, nearestSendingDate);
  }

}