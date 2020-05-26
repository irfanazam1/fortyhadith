package com.pureapps.memorize.fortyhadith;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private LinearLayout hadithLayout;
    private LinearLayout mainLayout;
    private ImageButton hadithExpander;
    private Drawable expandIcon;
    private Drawable collapseIcon;
    private Drawable expandImage;
    private boolean isExpanded = false;
    private Drawable collapseImage;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
        loadSettings();
        configureEventListeners();
    }

    private void initControls(){
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this);
        listView.setAdapter(listAdapter);
        hadithLayout = (LinearLayout) findViewById(R.id.hadithLayout);
        hadithExpander = (ImageButton) findViewById(R.id.hidthExpander);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        expandIcon = ContextCompat.getDrawable(this, R.drawable.ic_expand_more);
        collapseIcon = ContextCompat.getDrawable(this, R.drawable.ic_expand_less);
        expandImage = ContextCompat.getDrawable(this, R.drawable.background);
        collapseImage = ContextCompat.getDrawable(this, R.drawable.background_one);
    }

    private void loadSettings(){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        HadithData.getInstance(this).setTranslationLanguage(sharedPref.getString(getResources().getString(R.string.language_key),
                getResources().getString(R.string.default_language)));
        Boolean textSizePref = sharedPref.getBoolean(getResources().getString(R.string.text_size_key), false);
        HadithData.getInstance(this).setStandardTextSize(!textSizePref);
    }

    private void configureEventListeners(){
        hadithExpander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpanded) {
                    hadithLayout.setVisibility(View.VISIBLE);
                    hadithExpander.setBackground(collapseIcon);
                    mainLayout.setBackground(collapseImage);
                } else {
                    hadithLayout.setVisibility(View.INVISIBLE);
                    hadithExpander.setBackground(expandIcon);
                    mainLayout.setBackground(expandImage);

                }
                isExpanded = !isExpanded;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return false;
    }

    public void openSettings(View view) {
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
        //LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        //View listView = inflater.inflate(R.layout., null);
        //listView.callOnClick();
    }
}
