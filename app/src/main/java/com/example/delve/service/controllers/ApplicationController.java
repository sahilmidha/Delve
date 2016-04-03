package com.example.delve.service.controllers;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class ApplicationController
{
    private static ApplicationController _applicationController;
    private WebServiceManager _webServiceManager;
    private boolean _isInitied = false;

    private ApplicationController()
    {
        if (_applicationController != null)
        {
            throw new IllegalStateException("No two instances of this class can Co-Exist.");
        }
    }

    public static ApplicationController getInstance()
    {
        if (_applicationController == null)
        {
            synchronized (ApplicationController.class)
            {
                _applicationController = new ApplicationController();
            }
        }
        return _applicationController;
    }

    public void init()
    {
        if (!_isInitied)
        {
            _webServiceManager = new WebServiceManager();
            _isInitied = true;
        }
    }

    public WebServiceManager getWebServiceManager()
    {
        return _webServiceManager;
    }
}
