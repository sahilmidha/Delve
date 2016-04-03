package com.example.delve.service.models.details;

import java.util.ArrayList;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class HereNowVO
{
    int count;
    String summary;
    ArrayList<Groups> groups;

    public int getCount()
    {
        return count;
    }

    public String getSummary()
    {
        return summary;
    }

    public ArrayList<Groups> getGroups()
    {
        return groups;
    }

    private static class Groups
    {
        private String type;
        private int count;
        private ArrayList<String> items;
    }
}
