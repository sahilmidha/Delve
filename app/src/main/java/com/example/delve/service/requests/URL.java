package com.example.delve.service.requests;

/**
 * Created by sahilmidha on 03/04/16.
 */
public class URL
{
    //REQUEST_TYPE type, iWebServiceResponseListener listener, List<RequestParams> queryParams, String requestBody)

    String _requestUrl;
    String _requestBody;

    public URL()
    {
    }

    public URL holdURL(String url, String requestBody)
    {
        _requestUrl = url;
        _requestBody = requestBody;
        return this;
    }

    public String getRequestUrl()
    {
        return _requestUrl;
    }

    public String getRequestBody()
    {
        return _requestBody;
    }

}
