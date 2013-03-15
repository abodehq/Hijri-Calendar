package com.mos7af.hijri;

import java.util.Calendar;



import android.os.Bundle;
import android.app.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.graphics.Typeface;

import android.text.format.DateFormat;
import android.text.format.Time;

import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DigitalClock;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.widget.TextView;
import java.lang.reflect.Field;


import com.mos7af.hijri.WheelView;

public class Hijri extends Activity {

	Calendar calendar;
	Typeface tf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_hijri);
		  String fontPath = "fonts/hallfetica.ttf";

			 tf = Typeface.createFromAsset(
					Hijri.this.getAssets(), fontPath);
			
		calendar = Calendar.getInstance();
		
	
		HijriCalendar hijriCalendar =new HijriCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        String hijriDate=hijriCalendar.getHicriTakvim();
        String hijriDay=Integer.toString( hijriCalendar.getHijriDay());
        String hijriMonth=Integer.toString(  hijriCalendar.getHijriMonth());//hijriCalendar.getHijriMonthName();
        String hijriyear=Integer.toString(  hijriCalendar.getHijriYear());
        String hijriMonthName = hijriCalendar.getHijriMonthName();
        TextView widget_day = (TextView)findViewById(R.id.widget_day);
       TextView widget_month = (TextView)findViewById(R.id.widget_month);
        TextView widget_month_name = (TextView)findViewById(R.id.widget_month_name);
        
        widget_day.setText(hijriDay);
       
        widget_month.setText(hijriMonth +" , " + hijriyear);
        widget_month_name.setText(hijriMonthName);
        widget_day.setTypeface(tf);
        widget_month.setTypeface(tf);
        
        
        TextView text_hijri = (TextView)findViewById(R.id.text_hijri);
        text_hijri.setTypeface(tf);
        text_hijri.setText(hijriDay+ " , "+hijriMonth +" , " + hijriyear);

        
        GregorianCalendar g= new GregorianCalendar();
    
        TextView widget_mil_day = (TextView)findViewById(R.id.widget_mil_day);
        TextView widget_mil_month = (TextView)findViewById(R.id.widget_mil_month);
        TextView widget_mil_month_name = (TextView)findViewById(R.id.widget_mil_month_name);
       
        widget_mil_month_name.setText(  g.getMonthName(calendar.get(Calendar.MONTH)));
        widget_mil_day.setText( Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
        widget_mil_day.setTypeface(tf);
        widget_mil_month.setText( Integer.toString( calendar.get(Calendar.MONTH)+1)+" , "+Integer.toString(calendar.get(Calendar.YEAR)));
        widget_mil_month.setTypeface(tf);
        TextView day_name = (TextView)findViewById(R.id.day_name);
      
		day_name.setTypeface(tf);
        day_name.setText(  g.getDayName(calendar.get(Calendar.DAY_OF_WEEK)));
        
        DigitalClock digitalClock=(DigitalClock)findViewById(R.id.digitalClock1);
        digitalClock.setTypeface(tf);
        
        TextView text_mili = (TextView)findViewById(R.id.text_mili);
        text_mili.setTypeface(tf);
        text_mili.setText(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))+" , "+ Integer.toString( calendar.get(Calendar.MONTH)+1)+" , "+Integer.toString(calendar.get(Calendar.YEAR)));
       
    
        mili_year = calendar.get(Calendar.YEAR);
		mili_month = calendar.get(Calendar.MONTH);
		mili_day = calendar.get(Calendar.DAY_OF_MONTH);
		
		hijri_year = hijriCalendar.getHijriYear();
		hijri_month = hijriCalendar.getHijriMonth();
		hijri_day = hijriCalendar.getHijriDay();
	
        
        Button btnHijri = (Button) findViewById(R.id.btnHijri);
        btnHijri.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// abed();
				DateActivity.isHijri = false;
				DateActivity.sel_year = mili_year;
				DateActivity.sel_month = mili_month;
				DateActivity.sel_day = mili_day-1;
				
				Intent i = new Intent(getApplicationContext(), DateActivity.class);
				startActivityForResult(i, 100);		

			}
		});
        
        Button btnMiladi = (Button) findViewById(R.id.btnMiladi);
        btnMiladi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				DateActivity.isHijri = true;
				DateActivity.sel_year = hijri_year;
				DateActivity.sel_month = hijri_month-1;
				DateActivity.sel_day = hijri_day-1;
				
				Intent i = new Intent(getApplicationContext(), DateActivity.class);
				startActivityForResult(i, 100);			

			}
		});
       
	}


	int hijri_year;
	int hijri_month;
	int hijri_day;
	private void SetHijriDate(int year,int month,int day)
	{
		hijri_year = year;
		hijri_month = month;
		hijri_day = day;
	
        
		TextView text_hijri = (TextView)findViewById(R.id.text_hijri);
		text_hijri.setTypeface(tf);
		text_hijri.setText(Integer.toString(day)+" , "+ Integer.toString( month)+" , "+Integer.toString(year));
		//SetMiliDate(year,month,day);
	}
	int mili_year;
	int mili_month;
	int mili_day;
	private void SetMiliDate(int year,int month,int day)
	{
		mili_year = year;
		mili_month = month;
		mili_day = day;
		
        
		TextView text_mili = (TextView)findViewById(R.id.text_mili);
	    text_mili.setTypeface(tf);
	    text_mili.setText(Integer.toString(day)+" , "+ Integer.toString( month+1)+" , "+Integer.toString(year));
	       
	}
	

	@Override
    protected void onActivityResult(int requestCode,
                                     int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
        	if(!DateActivity.isHijri)
        	{
        		SetMiliDate(DateActivity.sel_year,DateActivity.sel_month,DateActivity.sel_day+1);
        		HijriCalendar hijriCalendar =new HijriCalendar(DateActivity.sel_year,DateActivity.sel_month+1,DateActivity.sel_day+1);
                String hijriDate=hijriCalendar.getHicriTakvim();
        		SetHijriDate(hijriCalendar.getHijriYear(),hijriCalendar.getHijriMonth(),hijriCalendar.getHijriDay());
        	}else
        	{
        		 GregorianCalendar g= new GregorianCalendar();
        		int[] res =  g.islToChr(DateActivity.sel_year,DateActivity.sel_month,DateActivity.sel_day+1, 0);
        		SetMiliDate(res[2],res[1],res[0]);
        		SetHijriDate(DateActivity.sel_year,DateActivity.sel_month+1,DateActivity.sel_day+1);
        	}
        }
 
    }
	 

}
