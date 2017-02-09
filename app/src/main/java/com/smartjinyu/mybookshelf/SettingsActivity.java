package com.smartjinyu.mybookshelf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * settings activity
 * Created by smartjinyu on 2017/2/8.
 */

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(R.string.settings_settings);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(savedInstanceState==null){
            SettingsFragment settingsFragment = new SettingsFragment();
            getFragmentManager().beginTransaction().add(R.id.activity_settings_container,settingsFragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}