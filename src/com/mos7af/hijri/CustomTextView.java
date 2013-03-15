package com.mos7af.hijri;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends  TextView{
	 public CustomTextView(Context context, AttributeSet attrs) {
         super(context, attrs);
         initTextFont(context);
     }

     public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
         super(context, attrs, defStyle);
         initTextFont(context);
     }

     private void initTextFont(Context context) {
    	 String fontPath = "fonts/hallfetica.ttf";
	     Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
	     setTypeface(tf);
     }

}
