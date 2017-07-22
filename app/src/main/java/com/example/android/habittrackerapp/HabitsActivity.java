package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

public class HabitsActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        mDbHelper = new HabitDbHelper(this);
        insertHabbit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Helper method to insert hardcoded habit data into the database.
     */
    private void insertHabbit() {
        /* Gets the database in write mode */
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        /* Insert a dummy habbit in the database */
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABBIT_TITLE, "Sleeping");
        values.put(HabitEntry.COLUMN_HABBIT_DESCRIPTION, "Laying in bed and closing eyes.");
        values.put(HabitEntry.COLUMN_HABBIT_FREQUENCY, HabitEntry.FREQUENCY_HIGH);
        values.put(HabitEntry.COLUMN_HABBIT_TIME_OF_THE_DAY, HabitEntry.TIME_NIGHT);


        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /**
     * Helper method to display all the habits in the database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABBIT_TITLE,
                HabitEntry.COLUMN_HABBIT_DESCRIPTION,
                HabitEntry.COLUMN_HABBIT_FREQUENCY,
                HabitEntry.COLUMN_HABBIT_TIME_OF_THE_DAY};

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.textview_habits);

        try {
            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABBIT_TITLE + " - " +
                    HabitEntry.COLUMN_HABBIT_DESCRIPTION + " - " +
                    HabitEntry.COLUMN_HABBIT_FREQUENCY + " - " +
                    HabitEntry.COLUMN_HABBIT_TIME_OF_THE_DAY + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABBIT_TITLE);
            int descriptionColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABBIT_DESCRIPTION);
            int frequencyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABBIT_FREQUENCY);
            int timeColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABBIT_TIME_OF_THE_DAY);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(titleColumnIndex);
                String currentDescription = cursor.getString(descriptionColumnIndex);
                int currentFrequency = cursor.getInt(frequencyColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentTitle + " - " +
                        currentDescription + " - " +
                        currentFrequency + " - " +
                        currentTime));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
