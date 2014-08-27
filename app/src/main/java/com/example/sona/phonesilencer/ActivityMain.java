package com.example.sona.phonesilencer;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityMain extends Activity {

    public static WifiManager mWifiManager;
    public static int x;
    public static String bssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mWifiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
        //IntentFilter intentFilter = new IntentFilter();
        final Context context = getApplicationContext();
        //registerReceiver(mWiFiBroadcastReceiver, intentFilter);
        //Log.d("wifi",x);
        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "hahahaha ", Toast.LENGTH_SHORT).show();
                Log.d("haha","haha");
            }
        }, 1000);*/
        x=0;

        new Thread(new ScanAPs()).start();

        WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
        WifiInfo w = mWiFiManager.getConnectionInfo();
        Log.d("scanresults", String.valueOf(mWiFiManager.getScanResults()));
        Toast.makeText(this, "APN Name = " + w.getBSSID(), Toast.LENGTH_SHORT).show();
        Button silence = (Button) findViewById(R.id.silenceButton);
        final TextView bssid1 = (TextView) findViewById(R.id.bssid1);
        final TextView bssid2 = (TextView) findViewById(R.id.bssid2);
        bssid1.setText(bssid);
        bssid2.setText(String.valueOf(x));
        silence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Silencing...", Toast.LENGTH_SHORT).show();
                //AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                //audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
                WifiInfo s = mWiFiManager.getConnectionInfo();
                bssid1.setText(s.getBSSID());


            }
        });

        Button loud = (Button) findViewById(R.id.loud);
        loud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Loud-ing...", Toast.LENGTH_SHORT).show();
                //AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                //audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
                WifiInfo w = mWiFiManager.getConnectionInfo();
                bssid2.setText(w.getBSSID());

            }
        });
    }
    /*final BroadcastReceiver mWiFiBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            StringBuilder sb = new StringBuilder();
            for (ScanResult sr : mWifiManager.getScanResults()) {
                sb.append("ACCESS POINT NAME: " + sr.SSID);
                sb.append("\n");
                sb.append("BSSID: " + sr.BSSID);
                sb.append("\n");
                sb.append("SIGNAL: " + sr.level);
                sb.append("\n");
            }
            x = sb.toString();
        }

    };*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class ScanAPs implements Runnable {
        @Override
        public void run() {
            for(int i=0;i<100;i++){
                Log.d("haha","haha"+String.valueOf(x));
                WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
                WifiInfo s = mWiFiManager.getConnectionInfo();
                bssid = s.getBSSID();
                Log.d("bssid",bssid+"+"+bssid.equals("c4:0a:cb:2d:ca:8a"));

                if(/*bssid.equals("c4:0a:cb:2d:ca:85")||bssid.equals("b0:00:b4:20:77:23")||bssid.equals("c4:0a:cb:2d:98:3a")||*/bssid.equals("c4:0a:cb:2d:ca:8a")){
                    Log.d("match","match");
                    AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }
                else if(bssid.equals("c4:0a:cb:2d:81:b5")){
                    AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_SILENT);

                }
                else{
                    AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                }
                x++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
