package com.example.delve.service.models;

import java.util.ArrayList;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class GroupVO
{
    ArrayList<ItemVO> items;

    public static class ItemVO
    {
        VenuesVO venue;

        public VenuesVO getVenues()
        {
            return venue;
        }
    }

    public ArrayList<ItemVO> getItems()
    {
        return items;
    }
}
