package com.pureapps.memorize.fortyhadith;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HadithData {
    public List<HashMap<String, String>> translations = new ArrayList<>();
    public List<String> hadith = new ArrayList<>();
    private static volatile HadithData _instance;
    public static HadithData getInstance(Context context){
        if(_instance == null){
            _instance = new HadithData();
            _instance.initData(context);
        }
        return _instance;
    }
    private void initData(Context context){
        List<String> listHadith = getHadiths(context);
        List<String> listEnglishTranslations = getEnglishTranslations(context);
        List<String> listUrduTranslations = getUrduTranslations(context);
        for(int i = 0; i < listHadith.size(); i++){
            String englishTranslation = null;
            String urduTranslation = null;
            HashMap<String, String> map = new HashMap<>();
            if(i < listEnglishTranslations.size()) {
                englishTranslation = listEnglishTranslations.get(i);
                map.put("en", englishTranslation);
            }
            if(i < listUrduTranslations.size()){
                urduTranslation = listUrduTranslations.get(i);
                map.put("urdu", urduTranslation);
            }
            if(map.size() > 0) {
                translations.add(map);
            }
            hadith.add(listHadith.get(i));
        }
    }

    private List<String> getHadiths(Context context){
        return getFileLines(context.getResources().openRawResource(R.raw.hadith));
    }

    private List<String> getEnglishTranslations(Context context){
        return getFileLines(context.getResources().openRawResource(R.raw.translations_en));
    }

    private List<String> getUrduTranslations(Context context){
        return getFileLines(context.getResources().openRawResource(R.raw.translations_urdu));
    }

    private List<String> getFileLines(InputStream stream){
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while((line = reader.readLine()) != null){
                if(line != null && line.trim().length() != 0) {
                    lines.add(line);
                }
            }
        }
        catch(Exception e){}
        return lines;
    }

}
