package com.pureapps.memorize.fortyhadith;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

import mehdi.sakout.aboutpage.Element;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupPageContent();
    }

    private void setupPageContent(){
        mehdi.sakout.aboutpage.Element element = new Element();
        element.setTitle("Sahih Al-Bukhari Easy Ahadith to Memorize");
        View aboutPage = new mehdi.sakout.aboutpage.AboutPage(this)
                .isRTL(false)
                .setDescription(getDescription())
                .addEmail(getResources().getString(R.string.email))
                .addPlayStore(getResources().getString(R.string.playstore))
                .addGitHub(getResources().getString(R.string.github))
                .addItem(element)
                .addItem(getCopyRight())
                .create();
        setContentView(aboutPage);
    }

    private Element getCopyRight(){
        Element element = new Element();
        String copyRight = String.format("Copyright - PureApps (%s)", Calendar.getInstance().get(Calendar.YEAR));
        element.setTitle(copyRight);
        return element;
    }

    private String getDescription(){
        String description = "This application provides a collection of short Ahadith from Sahih Al-Bukhari which are not only easy to memorize but also possess great importance to be practiced in a Muslim’s life. If we memorize, practice, and pass on the Ahadith to others then In sha Allah it will become a means of acquiring good deeds even after we pass away. Also, we shall become a medium of passing on sayings of our beloved Prophet Muhammad (peace and blessings be upon him) to the future generations.\n" +
                "\n" +
                "\n" +
                "عَنْ زَيْدِ بْنِ ثَابِتٍ، قَالَ سَمِعْتُ رَسُولَ اللَّهِ صلى الله عليه وسلم يَقُولُ \u200F \"\u200F نَضَّرَ اللَّهُ امْرَأً سَمِعَ مِنَّا حَدِيثًا فَحَفِظَهُ حَتَّى يُبَلِّغَهُ فَرُبَّ حَامِلِ فِقْهٍ إِلَى مَنْ هُوَ أَفْقَهُ مِنْهُ وَرُبَّ حَامِلِ فِقْهٍ لَيْسَ بِفَقِيهٍ \u200F\"\u200F\n" +
                "\n" +
                "Narrated Zayd ibn Thabit:\n" +
                "\n" +
                "I heard the Messenger of Allah (ﷺ) say: May Allah brighten a man who hears a tradition from us, gets it by heart and passes it on to others. Many a bearer of knowledge conveys it to one who is more versed than he is; and many a bearer of knowledge is not versed in it.\n" +
                "\n" +
                "عَنْ أَبِي هُرَيْرَةَ، أَنَّ رَسُولَ اللَّهِ صلى الله عليه وسلم قَالَ: \u200F إِذَا مَاتَ الإِنْسَانُ انْقَطَعَ عَمَلُهُ إِلاَّ مِنْ ثَلاَثَةٍ مِنْ صَدَقَةٍ جَارِيَةٍ وَعِلْمٍ يُنْتَفَعُ بِهِ وَوَلَدٍ صَالِحٍ يَدْعُو لَهُ -\u200F\n" +
                "\n" +
                "It was narrated from Abu Hurairah (RA) that the Messenger of Allah (Peace and blessings be upon him) said:\n" +
                "\"When a man dies all his good deeds come to an end except three: Ongoing charity (Sadaqah Jariyah), beneficial knowledge and a righteous son who prays for him.\"";
        return description;
    }
}
