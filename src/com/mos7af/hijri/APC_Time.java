package com.mos7af.hijri;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Calendar;

/**
 *
 * @author http://www.cepmuvakkit.com
 */
public class APC_Time {
//------------------------------------------------------------------------------
//
// Mjd: Modified Julian Date from calendar date and time
//
// Input:
//
//   Year      Calendar date components
//   Month
//   Day
//   Hour      Time components (optional)
//   Min
//   Sec
//
// <return>:   Modified Julian Date
// *BU*
//------------------------------------------------------------------------------
    public static double Mjd(int Year, int Month, int Day,
            int Hour, int Min, double Sec) {
        //
        // Variables
        //
        long MjdMidnight;
        double FracOfDay;
        int b;


        if (Month <= 2) {
            Month += 12;
            --Year;
        }

        if ((10000L * Year + 100L * Month + Day) <= 15821004L) {
            b = -2 + ((Year + 4716) / 4) - 1179;     // Julian calendar
        } else {
            b = (Year / 400) - (Year / 100) + (Year / 4);  // Gregorian calendar
        }
        MjdMidnight = 365L * Year - 679004L + b + (int) (30.6001 * (Month + 1)) + Day;
        FracOfDay = APC_Math.Ddd(Hour, Min, Sec) / 24.0;

        return MjdMidnight + FracOfDay;
    }

//------------------------------------------------------------------------------
//
// CalDat: Calendar date and time from Modified Julian Date
//
// Input:
//
//   Mjd       Modified Julian Date
//
// Output:
//
//   Year      Calendar date components
//   Month
//   Day
//   Hour      Decimal hours
//
//------------------------------------------------------------------------------
    public static Calendar CalDat(double Mjd) {
        //
        // Variables
        //*BU*
        long a, b, c, d, e, f;
        double FracOfDay, hour;
        int Year, Month, Day, Hour, Minute;
        Calendar cal = Calendar.getInstance();

        //GregorianCalendar.set (Year,Month,Day,Hour);
        //Date result=new Date (Year,Month,Day,Hour);
        // Convert Julian day number to calendar date
        a = (long) (Mjd + 2400001.0);

        if (a < 2299161) {  // Julian calendar
            b = 0;
            c = a + 1524;
        } else {                // Gregorian calendar
            b = (long) ((a - 1867216.25) / 36524.25);
            c = a + b - (b / 4) + 1525;
        }

        d = (long) ((c - 122.1) / 365.25);
        e = 365 * d + d / 4;
        f = (long) ((c - e) / 30.6001);

        Day = (int) (c - e - (int) (30.6001 * f));
        Month = (int) (f - 1 - 12 * (f / 14));
        Year = (int) (d - 4715 - ((7 + Month) / 10));

        FracOfDay = Mjd - Math.floor(Mjd);

        hour = (24.0 * FracOfDay);//a dikkat/buray
        Minute = (int) Math.round((hour - (int) (hour)) * 60.0);
        //Minute =((int)(hour*60))%60;
        Hour = (int) hour;
        cal.set(Calendar.YEAR, Year);
        cal.set(Calendar.MONTH, Month - 1);
        cal.set(Calendar.DAY_OF_MONTH, Day);
        cal.set(Calendar.HOUR_OF_DAY, Hour);
        cal.set(Calendar.MINUTE, Minute);
        // cal.set(Year, Month, Day, Hour,0);
        return cal;
    }
}
