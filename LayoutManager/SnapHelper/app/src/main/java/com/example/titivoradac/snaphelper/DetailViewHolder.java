package com.example.titivoradac.snaphelper;

/**
 * Created by titivorada.c on 7/21/2017.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class DetailViewHolder extends RecyclerView.ViewHolder {
    public TextView tvList;

    public DetailViewHolder(View itemView) {
        super(itemView);

        tvList = (TextView) itemView.findViewById(R.id.tv_list);

    }
}
