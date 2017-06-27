package com.arena.software.insomniacure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import 	android.content.BroadcastReceiver;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

// http://www.vogella.com/tutorials/AndroidBroadcastReceiver/article.html
// https://developer.android.com/guide/topics/ui/controls/pickers.html
public class MainActivity extends AppCompatActivity{

    private BroadcastReceiver mBroadcastReceiver;
    private static AlarmManager alarmManager;
    private BroadcastReceiver alarmReceiver;
    private PendingIntent alarmIntent;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandablelist);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        //
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Look at this dialog!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();*/
    }
    /*
         * Preparing the list data
         */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Healthy sleeping habits");
        listDataHeader.add("Getting ready to sleep");
        listDataHeader.add("Relaxation techniques");


        // Adding child data
        List<String> firstCateg = new ArrayList<String>();
        firstCateg.add("Keep the room dark");
        firstCateg.add("Quiet room");
        firstCateg.add("Low room temperature");

        List<String> secondCateg = new ArrayList<String>();
        secondCateg.add("Warm milk");
        secondCateg.add("Dim lights");
        secondCateg.add("Spray some Lavander");


        List<String> thirdCateg = new ArrayList<String>();
        thirdCateg.add("4-7-8 Breathing technique");
        thirdCateg.add("5-5 Breathing technique");
        thirdCateg.add("Guided imagery");

        listDataChild.put(listDataHeader.get(0), firstCateg); // Header, Child data
        listDataChild.put(listDataHeader.get(1), secondCateg);
        listDataChild.put(listDataHeader.get(2), thirdCateg);
    }
    public void startAlert(View view) {
        //EditText text = (EditText) findViewById(R.id.time);
        //int i = Integer.parseInt(text.getText().toString());

        SharedPreferences settings = getSharedPreferences("Insomnia_cure", 0);
        int hour = settings.getInt("hourOfDay", 22);
        int minute = settings.getInt("minute", 10);

        Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
        //+ (i * 1000), pendingIntent);
        //
        Date dat = new Date();//initializes to now
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY, hour);//set the alarm time
        cal_alarm.set(Calendar.MINUTE, minute);
        cal_alarm.set(Calendar.SECOND, 0);
        if (cal_alarm.before(cal_now)) {//if its in the past increment
            cal_alarm.add(Calendar.DATE, 1);
        }
        //
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute),TimeUnit.HOURS.toMillis(24),pendingIntent);
        Toast.makeText(this, "Daily Alarm set at " + hour + " :" + minute,
                Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // show menu when menu button is pressed
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed
        String message = "";
        if (item.getItemId() == R.id.settings) {
            Intent j = new Intent(this, SettingsActivity.class);
            j.putExtra( SettingsActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.DataSyncPreferenceFragment.class.getName() );
            // j.putExtra( SettingsActivity.EXTRA_NO_HEADERS, true );
            startActivity(j);
            //message = "You selected settings!";
        }
        else if (item.getItemId() == R.id.statistics) {
            Intent j = new Intent(this, SettingsActivity.class);
            j.putExtra( SettingsActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.DataSyncPreferenceFragment.class.getName() );
            //j.putExtra( SettingsActivity.EXTRA_NO_HEADERS, true );
            startActivity(j);
            //message = "You selected statistics";
        }
        else {
            message = "Why would you select that!?";
        }

        // show message via toast
        // Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // toast.show();

        return true;
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

        startAlert(v);
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void OnOff(View v)
    {

    }
}
