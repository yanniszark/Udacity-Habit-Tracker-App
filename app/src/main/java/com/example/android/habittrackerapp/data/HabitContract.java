/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Habit Tracker app.
 */
public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitEntry implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Title of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABBIT_TITLE = "title";

        /**
         * Description of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABBIT_DESCRIPTION = "description";

        /**
         * Frequency of the habit.
         * <p>
         * The only possible values are {@link #FREQUENCY_LOW}, {@link #FREQUENCY_MEDIUM},
         * or {@link #FREQUENCY_HIGH}.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_HABBIT_FREQUENCY = "frequency";

        /**
         * Time of the day that the habit is executed.
         * <p>
         * The only possible values are {@link #TIME_MORNING}, {@link #TIME_NOON},
         * {@link #TIME_AFTERNOON} or {@link #TIME_NIGHT}.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_HABBIT_TIME_OF_THE_DAY = "time_of_the_day";

        /**
         * Possible values for the frequency of the habit.
         */
        public static final int FREQUENCY_LOW = 0;
        public static final int FREQUENCY_MEDIUM = 1;
        public static final int FREQUENCY_HIGH = 2;

        /**
         * Possible values for the time of the day that the habit is executed.
         */
        public static final int TIME_MORNING = 0;
        public static final int TIME_NOON = 1;
        public static final int TIME_AFTERNOON = 2;
        public static final int TIME_NIGHT = 3;
    }

}

