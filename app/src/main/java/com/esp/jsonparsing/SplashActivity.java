package com.esp.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.esp.util.Utils;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        
        /** to create database */
        Utils.systemUpgrade(SplashActivity.this);
        Thread splashThread = new Thread() {
			public void run() {
				try {
					sleep(2000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		};
		splashThread.start();

	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			startActivity(new Intent(SplashActivity.this, ListActivity.class));
		}
	};

}
