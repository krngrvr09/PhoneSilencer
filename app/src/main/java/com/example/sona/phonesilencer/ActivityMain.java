package com.example.sona.phonesilencer;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityMain extends Activity {

    public static WifiManager mWifiManager;
    public static String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mWifiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
        //IntentFilter intentFilter = new IntentFilter();

        //registerReceiver(mWiFiBroadcastReceiver, intentFilter);
        //Log.d("wifi",x);
        WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
        WifiInfo w = mWiFiManager.getConnectionInfo();
        Log.d("scanresults", String.valueOf(mWiFiManager.getScanResults()));
        Toast.makeText(this, "APN Name = "+w.getBSSID(), Toast.LENGTH_SHORT).show();
        Button silence = (Button) findViewById(R.id.silenceButton);
        final TextView bssid1 = (TextView) findViewById(R.id.bssid1);
        final TextView bssid2 = (TextView) findViewById(R.id.bssid2);

        /*silence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Silencing...", Toast.LENGTH_SHORT).show();
                //AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                //audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
                WifiInfo s = mWiFiManager.getConnectionInfo();
                bssid1.setText(s.getBSSID());


        });*/
        /*Button loud = (Button) findViewById(R.id.loud);
        loud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Loud-ing...", Toast.LENGTH_SHORT).show();
                //AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                //audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                WifiManager mWiFiManager = (WifiManager) getSystemService(getApplicationContext().WIFI_SERVICE);
                WifiInfo w = mWiFiManager.getConnectionInfo();
                bssid2.setText(w.getBSSID());

        });*/

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
}
