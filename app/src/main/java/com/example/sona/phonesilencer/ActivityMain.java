package com.example.sona.phonesilencer;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("start", "start");
        Button silence = (Button) findViewById(R.id.silenceButton);
        silence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Silencing...", Toast.LENGTH_SHORT).show();
                AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }/* Some Code */
        });
        Button loud = (Button) findViewById(R.id.loud);
        loud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Loud-ing...", Toast.LENGTH_SHORT).show();
                AudioManager audiomanage = (AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);
                audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }/* Some Code */
        });

    }


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
