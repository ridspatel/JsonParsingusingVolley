package com.esp.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class Utils {
	/**
	 * System Upgrade for Database.
	 */
	public static void systemUpgrade(Context context) {
		DBHelper dbHelper = null;
		int level = 0;
		try {
			dbHelper = new DBHelper(context);
			level = Integer.parseInt(Pref.getValue(context, "LEVEL", "0"));

			if (level == 0) {
				dbHelper.upgrade(level);
				// Create not confirmed order
				level++;
			}
			Pref.setValue(context, "LEVEL", level + "");
		} catch (Exception e) {
			Log.print(e);
		} finally {
			if (dbHelper != null)
				dbHelper.close();
			dbHelper = null;
			level = 0;
		}

	}

	/**
	 * Check Connectivity of network.
	 */
	public static boolean isOnline(Context context) {
		try {
			if (context == null)
				return false;

			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (cm != null) {
				if (cm.getActiveNetworkInfo() != null) {
					return cm.getActiveNetworkInfo().isConnected();
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			Log.error("Exception", e);
			return false;
		}

	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	public static Typeface getFont(Context context, int tag) {
		if (tag == 100) {
			return Typeface.createFromAsset(context.getAssets(),
					"Roboto-Regular.ttf");
		}
		return Typeface.DEFAULT;
	}

}
