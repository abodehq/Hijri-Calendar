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
public class GregorianWidget extends AppWidgetProvider {
	  @Override
	    public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds )
	    {
	        RemoteViews remoteViews;
	        ComponentName watchWidget;
	        DateFormat format = SimpleDateFormat.getTimeInstance( SimpleDateFormat.MEDIUM, Locale.getDefault() );

	        remoteViews = new RemoteViews( context.getPackageName(), R.layout.main );
	        watchWidget = new ComponentName( context, GregorianWidget.class );
	        Calendar calendar = Calendar.getInstance();
	       remoteViews.setTextViewText( R.id.widget_day, Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
	       GregorianCalendar gregorianCalendar = new GregorianCalendar();
	      
	       remoteViews.setTextViewText( R.id.widget_month,  gregorianCalendar.getMonthName(calendar.get(Calendar.MONTH)));
	        appWidgetManager.updateAppWidget( watchWidget, remoteViews );
	    }
}

