package co.okeyx.amatistestapplication.helper;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.okeyx.amatistestapplication.MyApplication;

public class TypefaceUtil {

    public static void overrideFonts(final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(MyApplication.iranSansTF);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }

    public static void overrideFonts(final View v, Typeface typeface) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child, typeface);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(typeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }

}