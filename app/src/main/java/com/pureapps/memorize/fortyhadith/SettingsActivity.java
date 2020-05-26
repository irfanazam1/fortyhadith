package com.pureapps.memorize.fortyhadith;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SeekBarPreference;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            final Preference languagePref = (Preference) findPreference(getResources().getString(R.string.language_key));
            final Preference optionPreference = (Preference) findPreference(getResources().getString(R.string.text_size_key));
            setTextSizePrefTitle(optionPreference, HadithData.getInstance(getContext()).isStandardTextSize());
            if (languagePref != null) {
                languagePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        HadithData.getInstance(getContext()).setTranslationLanguage((String) newValue);
                        return true;
                    }
                });
            }
            optionPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Boolean value = (Boolean) newValue;
                    HadithData.getInstance(getContext()).setStandardTextSize(!value);
                    setTextSizePrefTitle(optionPreference, !value);
                    return true;
                }
            });
        }
        private void setTextSizePrefTitle(Preference pref, boolean value){
            if(pref != null) {
                if (!value) {
                    pref.setTitle(getResources().getString(R.string.text_size_large));
                } else {
                    pref.setTitle(getResources().getString(R.string.text_size_standard));
                }
            }
        }
    }
}