package basicAPI;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_INSTANT;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateTimeAPI {

    /*
        Java8から追加された DateTimeAPI は以下のような特徴を持つ。

        * immutableである。（スレッドセーフ）
        * 月の指定が 1 ~ 12。（Calendarクラスの場合は、0 ~ 11 だった。）
        * 日付や時間操作のための便利なメソッドが追加されている。
        * 時間は24時間で扱うため、午前/午後の概念は無い。

        DateTimeAPI のクラスは、

        * LocalDate
        * LocalTime
        * LocalDateTime

        の３つ。それぞで、日付を扱う、時間を扱う、日時を扱うクラスとなっている。
        LocalDate は 日付専用のクラスなので、時間の情報は保持していない。
        逆に LocalTime は時間専用のクラスなので、日付の情報は保持しない。

        また、差分を扱うためのクラスも用意されている。

        * Duration : 時刻の差分を扱う。
        * Period   : 日付の差分を扱う。

     */

    @Test
    public void test_LocalDate_until() {

        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(10);

        Period period = from.until(to);
        assertThat(10, is(period.getDays()));

        /*
            LocalDate#until メソッドは、日付の差分クラス（Period）を取得するメソッド。
            ※Period = 期間

            LocalDate の差分を取得する方法は、untilメソッドの他に

            public static Period Period.between(LocalDate, LocalDate)

            メソッドを用いる方法がある。
         */
    }

    @Test
    public void test_LocalDate_minus() {

        LocalDate localDate = LocalDate.now();
        localDate.minusDays(1);
        localDate.minusMonths(1);
        localDate.minusWeeks(1);
        localDate.minusYears(1);

        /*
            見たまま。
            ある期間から、指定された期間を引くメソッド。
            plusメソッドもある。
         */
    }

    @Test
    public void test_LocalDateTime_format() {

        LocalDateTime localDateTime = LocalDateTime.of(2017, 1, 1, 0, 0, 0);

        String basicISODate = localDateTime.format(BASIC_ISO_DATE);
        String isoDateTime = localDateTime.format(ISO_DATE_TIME);

        System.out.println(basicISODate);
        System.out.println(isoDateTime);
    }


}
