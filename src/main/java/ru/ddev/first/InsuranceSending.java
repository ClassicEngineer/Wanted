package ru.ddev.first;

import java.sql.Date;
import java.time.LocalDate;


public class InsuranceSending {

  private final VacationCheck vacationCheck;

  public InsuranceSending(VacationCheck vacationCheck) {
    this.vacationCheck = vacationCheck;
  }

  /**
   * Функция, определяющая ближайшую дату отправки списка в страховую с условием,
   * что отправка осуществляется 1, 10 и 20 числа каждого месяца в 18:00.
   * Если дата отправки попадает на нерабочий/праздничный день - то отправка осуществляется в следующий рабочий день.
   * @param date дата запроса == текущему времени
   * @return ближайшую дату отправки списка в страховую
   */
  public Date getNearestSendingDate(Date date) {
    LocalDate sendingDate = date.toLocalDate();
    if (sendingDate.getDayOfMonth() == 1 || sendingDate.getDayOfMonth() == 10 || sendingDate.getDayOfMonth() == 20) {
      return vacationCheck.getVacCheck(Date.valueOf(sendingDate));
    }
    if (sendingDate.getDayOfMonth() > 20) {
      sendingDate = sendingDate.plusMonths(1).withDayOfMonth(1);
      return vacationCheck.getVacCheck(Date.valueOf(sendingDate));
    } else if (sendingDate.getDayOfMonth() > 10) {
      sendingDate = sendingDate.withDayOfMonth(20);
      return vacationCheck.getVacCheck(Date.valueOf(sendingDate));
    } else {
      sendingDate = sendingDate.withDayOfMonth(10);
      return vacationCheck.getVacCheck(Date.valueOf(sendingDate));
    }
  }

}
