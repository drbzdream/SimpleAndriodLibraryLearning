package com.example.titivoradac.androidstaggeredgrid;

/**
 * Created by titivorada.c on 7/20/2017.
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class CustomAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private Context mContext;

    private static final SparseArray<Double> sPositionHeightRatios =
         new SparseArray<Double>();
    private final Random mRandom;



    static class ViewHolder {
        DynamicHeightImageView imageView;
    }

    public CustomAdapter(final Context context, final int staggeredId) {
        super(context, staggeredId);
        mContext =context;
        mInflater = LayoutInflater.from(context);
        mRandom = new Random();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_simple, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView =
                    convertView.findViewById(R.id.image_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);

        viewHolder.imageView.setHeightRatio(positionHeight);

        String path = getItem(position);
        Picasso.with(mContext)
                .load(path)
                .into(viewHolder.imageView);

        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);

        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0;
    }
}
