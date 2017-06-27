package com.arena.software.insomniacure;

/**
 * Created by Muc on 1/27/2017.
 */
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            SharedPreferences settings = getActivity().getSharedPreferences("Insomnia_cure", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("hourOfDay", hourOfDay);
            editor.putInt("minute", minute);

            // Commit the edits!
            editor.commit();
    }
}
