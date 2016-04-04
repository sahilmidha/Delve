package com.example.delve.utils;

import com.example.delve.R;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class URLBuilder
{
    private static final String CLIENT_ID = "LFHNYAE311I5ZUMLYSP2WRVTM5RPHUZ0MM1X1QKLQ4CBIADO";
    private static final String CLIENT_SECRET = "ERRFYFA1KVVJHFMZ21MMJJBG2LEVVSXFDHE4TUTG231XXUO4";

    private static final String BASE_URL = "https://api.foursquare.com/v2";
    private static final String VENUE_EXPLORE = "/venues/explore";
    private static final String VENUE_DETAILS = "/venues/";

    private static final String CURRENT_VERSION_DATE = "20160207";

    public static String getVenueListRequest(String location)
    {
        return String.format("%s%s?ll=%s&venuePhotos=1&%s", BASE_URL, VENUE_EXPLORE, location, getAuthString());
    }

    public static String getVenueDetailsRequest(String venueId)
    {
        return String.format("%s%s/%s?%s", BASE_URL, VENUE_DETAILS, venueId, getAuthString());
    }

    private static String getAuthString()
    {
        return String.format("client_id=%s&client_secret=%s&v=%s", CLIENT_ID, CLIENT_SECRET, CURRENT_VERSION_DATE);
    }
}
