package com.example.delve.service.controllers;

import com.example.delve.service.iWebServiceResponseListener;
import com.example.delve.service.network.DataFetcher;
import com.example.delve.service.processors.iSectionDataProcessor;
import com.example.delve.service.requests.URL;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class WebServiceManager
{
    public void createGetRequest(iWebServiceResponseListener listener, iSectionDataProcessor responseProcessor, String url)
    {
        this.createRequest(listener, responseProcessor, url, null);
    }

    public void createPostRequest(iWebServiceResponseListener listener, iSectionDataProcessor responseProcessor, String url, String requestBody)
    {
        this.createRequest(listener, responseProcessor, url, requestBody);
    }

    private void createRequest(iWebServiceResponseListener listener, iSectionDataProcessor responseProcessor, String url, String requestBody)
    {
        URL requestURL = new URL().holdURL(url, requestBody);
        new DataFetcher().fetchData(requestURL, responseProcessor, listener);
    }
}
