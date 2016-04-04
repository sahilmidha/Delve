package com.example.delve.ui;

import android.view.View;

import com.example.delve.service.models.VenuesVO;

/**
 * Created by sahilmidha on 05/04/16.
 */
public interface OnListItemClickListener
{
    public void onListItemClick(View v, VenuesVO venue);
}
