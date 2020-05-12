package com.pureapps.memorize.fortyhadith;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;

    public ExpandableListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return HadithData.getInstance(context).hadith.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return HadithData.getInstance(context).hadith.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(groupPosition < HadithData.getInstance(context).translations.size()  && HadithData.getInstance(context).translations.get(groupPosition).containsKey("urdu")){
            return HadithData.getInstance(context).translations.get(groupPosition).get("urdu");
        }
        return HadithData.getInstance(context).translations.get(groupPosition).get("en");
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String title = (String)getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }
        String[] values = title.split(";");
        TextView lblListHeader1 = (TextView)convertView.findViewById(R.id.lblListHeader1);
        lblListHeader1.setTypeface(null, Typeface.ITALIC);
        lblListHeader1.setText(values[0]);
        TextView lblListHeader2 = (TextView)convertView.findViewById(R.id.lblListHeader2);
        lblListHeader2.setTypeface(null, Typeface.BOLD);
        lblListHeader2.setText(values[1]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition, childPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        TextView textChildItem = (TextView)convertView.findViewById(R.id.lblLisItem);
        if(childText != null && childText.length() > 0) {
            textChildItem.setText(childText);
        }
        else{
            textChildItem.setText("Not Found");
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
