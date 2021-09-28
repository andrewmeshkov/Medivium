package com.kstudio.quizapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kstudio.quizapp.R;

public class CustomGridView extends BaseAdapter {
    private Context mContext;
    private final String[] ViewString;

    public CustomGridView(Context context, String[] gridViewString) {
        mContext = context;
        this.ViewString = gridViewString;
    }



    @Override
    public int getCount() {
        return ViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.gridview_layout, null);
            TextView textViewAndroid = gridViewAndroid.findViewById(R.id.android_gridview_text);
            textViewAndroid.setText(ViewString[i]);
        } else {
            gridViewAndroid = convertView;
        }

        return gridViewAndroid;
    }
}
