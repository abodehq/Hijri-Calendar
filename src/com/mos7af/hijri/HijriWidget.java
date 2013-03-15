package com.mos7af.hijri;

import android.appwidget.AppWidgetProvider;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
public class HijriWidget extends AppWidgetProvider {
	  @Override
	    public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	    {
	        RemoteViews remoteViews;
	        ComponentName watchWidget;
	        DateFormat format = SimpleDateFormat.getTimeInstance( SimpleDateFormat.MEDIUM, Locale.getDefault() );

	        remoteViews = new RemoteViews( context.getPackageName(), R.layout.main );
	        watchWidget = new ComponentName( context, HijriWidget.class );
	        Calendar calendar = Calendar.getInstance();
	        HijriCalendar hijriCalendar =new HijriCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
	        String hijriDay=Integer.toString( hijriCalendar.getHijriDay());
	        String hijriMonth= hijriCalendar.getHijriMonthName();
	       remoteViews.setTextViewText( R.id.widget_day, hijriDay);
	       remoteViews.setTextViewText( R.id.widget_month, hijriMonth);
	        appWidgetManager.updateAppWidget( watchWidget, remoteViews );
	    }
}

