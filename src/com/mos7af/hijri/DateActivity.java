package com.mos7af.hijri;

import java.util.Calendar;

import com.mos7af.hijri.OnWheelChangedListener;
import com.mos7af.hijri.WheelView;
import com.mos7af.hijri.adapters.ArrayWheelAdapter;
import com.mos7af.hijri.adapters.NumericWheelAdapter;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DateActivity extends Activity {
	
	public static int sel_year ;
	public static int sel_month;
	public static int sel_day ;
	int minimum_year = 623;
	public static boolean isHijri = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);

        Calendar calendar = Calendar.getInstance();

        final WheelView month = (WheelView) findViewById(R.id.month);
        final WheelView year = (WheelView) findViewById(R.id.year);
        final WheelView day = (WheelView) findViewById(R.id.day);
        
        OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays(year, month, day);
            }
        };

        // month
        int curMonth = sel_month;
        String months[] = new String[] {"كانون الثاني","شباط","آذار","نيسان","أيار","حزيران","تموز","آب","أيلول","تشرين الأول","تشرين الثاني","كانون الأول"};
        if(isHijri)
        months = new String[] {"محرم", "صفر", "ربيع الأول", "ربيع الآخر","جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان","رمضان", "شوال", "ذو القعدة", "ذو الحجة"};
        
        
        month.setViewAdapter(new DateArrayAdapter(this, months, curMonth));
        month.setCurrentItem(curMonth);
        month.addChangingListener(listener);
    
        // year
        int curYear = sel_year;
        year.setViewAdapter(new DateNumericAdapter(this, minimum_year, 2500, curYear-minimum_year));
        year.setCurrentItem(curYear-minimum_year);
        year.addChangingListener(listener);
        
        
        //day
        day.setCurrentItem(sel_day);
        updateDays(year, month, day);
        day.setCurrentItem(sel_day);
        
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			
				DateActivity.sel_year = year.getCurrentItem()+minimum_year;
				DateActivity.sel_month = month.getCurrentItem();
				DateActivity.sel_day = day.getCurrentItem();
				
				Intent in = new Intent(getApplicationContext(),
						Hijri.class);
				setResult(100, in);
				finish();

			}
		});
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			
				Intent in = new Intent(getApplicationContext(),
						Hijri.class);
				setResult(200, in);
				finish();

			}
		});
    }
    
    /**
     * Updates day wheel. Sets max days according to selected month and year
     */
    void updateDays(WheelView year, WheelView month, WheelView day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, month.getCurrentItem());
        
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setViewAdapter(new DateNumericAdapter(this, 1, maxDays, sel_day));
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);
        //day.setCurrentItem(sel_day);
    }
    
    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class DateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;
        
        /**
         * Constructor
         */
        public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
            super(context, minValue, maxValue);
            this.currentValue = current;
            setTextSize(16);
        }
        
        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }
        
        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
    
    /**
     * Adapter for string based wheel. Highlights the current value.
     */
    private class DateArrayAdapter extends ArrayWheelAdapter<String> {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;
        
        /**
         * Constructor
         */
        public DateArrayAdapter(Context context, String[] items, int current) {
            super(context, items);
            this.currentValue = current;
            setTextSize(16);
        }
        
        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }
        
        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
}
