package com.pureapps.memorize.fortyhadith;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
    private boolean isExpanded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this);
        listView.setAdapter(listAdapter);
        hadithLayout = (LinearLayout) findViewById(R.id.hadithLayout);
        hadithExpander = (ImageButton)findViewById(R.id.hidthExpander);
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        final Drawable expandIcon = ContextCompat.getDrawable(this, R.drawable.ic_expand_more);
        final Drawable collapseIcon = ContextCompat.getDrawable(this, R.drawable.ic_expand_less);
        final Drawable expandImage = ContextCompat.getDrawable(this, R.drawable.background);
        final Drawable collapseImage = ContextCompat.getDrawable(this, R.drawable.background_one);
        //final Drawable collapseImage = ContextCompat.getDrawable(this, R.drawable);
        hadithExpander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isExpanded) {
                    hadithLayout.setVisibility(View.VISIBLE);
                    hadithExpander.setBackground(collapseIcon);
                    mainLayout.setBackground(collapseImage);
                }
                else{
                    hadithLayout.setVisibility(View.INVISIBLE);
                    hadithExpander.setBackground(expandIcon);
                    mainLayout.setBackground(expandImage);

                }
                isExpanded = !isExpanded;
            }
        });
    }
}
