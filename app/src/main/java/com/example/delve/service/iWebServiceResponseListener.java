package com.example.delve.service;

import com.example.delve.service.processors.iSectionDataProcessor;

/**
 * Created by sahilmidha on 03/04/16.
 */
public interface iWebServiceResponseListener
{
    public void onWebServiceSuccess(iSectionDataProcessor sectionDataProcessor);

    public void onWebServiceFailed();
}
