package com.isil.am2template.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.am2template.R;
import com.isil.am2template.model.entity.Place;

import java.util.List;

/**
 * Created by Pablo Claus on 11/5/2017.
 */

public class PlaceAdapter extends BaseAdapter {

    private Context context;
    private List<Place> lsPlace;

    public PlaceAdapter(Context context, List<Place> lsPlace) {
        this.context = context;
        this.lsPlace = lsPlace;
    }

    @Override
    public int getCount() {
        return lsPlace.size();
    }

    @Override
    public Object getItem(int i) {
        return lsPlace.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.row_place, null);
            ViewHolder holder = new ViewHolder();
            holder.tviName = (TextView)v.findViewById(R.id.tviName);
            holder.tviAddress =(TextView)v.findViewById(R.id.tviAddress);
            holder.chkCart = (CheckBox)v.findViewById(R.id.chkCart);

            v.setTag(holder);
        }
        final Place entry = lsPlace.get(position);
        if(entry != null) {
            ViewHolder holder = (ViewHolder)v.getTag();
            holder.tviName.setText(entry.getName());
            holder.tviAddress.setText(entry.getDescription());
            holder.chkCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    entry.setChecked(isChecked);
                }
            });







        }

        return v;
    }

    static class ViewHolder
    {
        ImageView iviNote;
        TextView tviName;
        TextView tviAddress;
        CheckBox chkCart;
    }
}
