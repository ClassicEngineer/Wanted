package ru.ddev.first;

import com.groupstp.isdayoff.IsDayOff;
import com.groupstp.isdayoff.enums.DayType;
import com.groupstp.isdayoff.enums.DirectionType;
import com.groupstp.isdayoff.enums.LocalesType;

import java.sql.Date;

/**
 *  Сторонняя библиотека IsDayOff (https://github.com/Dakla/IsDayOff) используется только для более интересной реализации метода getVacCheck
 */
public class VacationCheck {

  private final IsDayOff isDayOff;

  public VacationCheck() {
    this.isDayOff = IsDayOff.Builder()
        .setLocale(LocalesType.RUSSIA)
        .build();
  }

  /**
   * Проверяет дату, является ли она рабочей.
   * @param modDate - дата для проверки
   * @return ближайший рабочий день следующий за выходными.
   */
  public Date getVacCheck(Date modDate) {
    DayType dayType = isDayOff.dayType(modDate);
    if (Boolean.TRUE.equals(dayType.isWorkingDay())) {
      return modDate;
    } else {
      return new Date(isDayOff.getFirstDayByType(modDate, DayType.WORKING_DAY, DirectionType.FUTURE).getTime());
    }
  }
}
