package com.test.myapp.thenewboston;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by hp on 16-07-2016.
 * Preferences
 */
public class prefs extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

    }
}
