package com.example.delve.service.models;

import java.util.ArrayList;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class PhotosVO
{
    ArrayList<PhotoGroups> groups;

    public static class PhotoGroups
    {
        ArrayList<PhotoItems> items;

        public static class PhotoItems
        {
            String prefix;
            String suffix;
            String width;
            String height;

            public String getPhotoUrl()
            {

                return String.format("%s%sx%s%s", prefix, width, height, suffix);
            }
        }

        public ArrayList<PhotoItems> getItems()
        {
            return items;
        }
    }

    public String getDefaultPhotoUrl()
    {
        return groups.get(0).getItems().get(0).getPhotoUrl();
    }

    public ArrayList<PhotoGroups> getGroups()
    {
        return groups;
    }
}
