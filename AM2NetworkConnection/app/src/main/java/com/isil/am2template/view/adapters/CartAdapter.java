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
import android.widget.ToggleButton;

import com.isil.am2template.R;
import com.isil.am2template.model.entity.Cart;
import com.isil.am2template.model.entity.Place;

import java.util.List;

/**
 * Created by Pablo Claus on 11/11/2017.
 */

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<Cart> lsCart;

    public CartAdapter(Context context, List<Cart> lsCart) {
        this.context = context;
        this.lsCart = lsCart;
    }


    @Override
    public int getCount() {
        return lsCart.size();
    }

    @Override
    public Object getItem(int i) {
        return lsCart.get(i);
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
            v = inflater.inflate(R.layout.row_cart, null);
            CartAdapter.ViewHolder holder = new CartAdapter.ViewHolder();
            holder.tviTitle = (TextView)v.findViewById(R.id.tviTitle);
            holder.tviCode =(TextView)v.findViewById(R.id.tviCode);
            holder.tviPrice = (TextView)v.findViewById(R.id.tviPrice);

            v.setTag(holder);
        }
        Cart entry = lsCart.get(position);
        if(entry != null) {
            CartAdapter.ViewHolder holder = (CartAdapter.ViewHolder)v.getTag();
            holder.tviCode.setText(entry.getId());
            holder.tviTitle.setText(entry.getTitle());








        }

        return v;
    }

    static class ViewHolder
    {
        ImageView iviCart;
        TextView tviTitle;
        TextView tviPrice;
        TextView tviCode;
        TextView tviQty;


    }
}
