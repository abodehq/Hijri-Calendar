package com.mos7af.hijri;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.DatePicker;


class MyDatePicker extends DatePicker {

    public MyDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        Field[] fields = DatePicker.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (TextUtils.equals(field.getName(), "mMonthPicker")) {
                    Method m = field.getType().getDeclaredMethod("setRange", int.class, int.class, String[].class);
                    m.setAccessible(true);
                    String[] s = new String[] {"01","02","03","04","05","06","07","08","09","10","11","12"};
                    m.invoke(field.get(this), 1, 12, s);
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}