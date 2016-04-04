package com.example.delve.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.delve.R;
import com.example.delve.service.controllers.ApplicationController;
import com.example.delve.service.iWebServiceResponseListener;
import com.example.delve.service.models.VenuesVO;
import com.example.delve.service.processors.VenueListDataProcessor;
import com.example.delve.service.processors.iSectionDataProcessor;
import com.example.delve.ui.OnListItemClickListener;
import com.example.delve.ui.views.VenuesListview;
import com.example.delve.utils.URLBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandingFragment extends BaseFragment implements OnListItemClickListener, iWebServiceResponseListener
{
    private VenuesListview _venuesListview;

    public LandingFragment()
    {
        // Required empty public constructor
    }

    @Override
    public int getFragmentLayoutId()
    {
        return R.layout.fragment_landing;
    }

    @Override
    protected int getContainerViewGroupId()
    {
        return R.id.lvVenues;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        _venuesListview = (VenuesListview) view.findViewById(R.id.lvVenues);
        _venuesListview.init(this);
        executeWebService();

    }
    private void executeWebService()
    {
        showStatus(STATUS.STATUS_LOADING, 0);
        ApplicationController.getInstance().getWebServiceManager().createGetRequest(this, new VenueListDataProcessor(), URLBuilder.getVenueListRequest("40.7,-74"));
    }


    @Override
    public void onListItemClick(View v, VenuesVO venue)
    {/*
        VenuesDetailsFragment fragment = new VenuesDetailsFragment();
        fragment.setVenue(venue);
        ((MainActivity) getActivity()).navigateTo(fragment, true, true);*/
    }

    @Override
    public void onWebServiceSuccess(iSectionDataProcessor sectionDataProcessor)
    {
        showStatus(STATUS.STATUS_SUCCESS, 0);
        if (sectionDataProcessor instanceof VenueListDataProcessor)
        {

            VenueListDataProcessor processor = (VenueListDataProcessor) sectionDataProcessor;
            _venuesListview.setData(processor.getVenuesVO());
        }
    }

    @Override
    public void onWebServiceFailed()
    {
        showStatus(STATUS.STATUS_NETWORK_ERROR, 0);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (_venuesListview != null)
        {
            _venuesListview.onDestroy();
            _venuesListview = null;
        }
    }

    @Override
    public void refreshActiveFragment()
    {
        if (_venuesListview.getList() == null || _venuesListview.getList().size() == 0)
        {
            executeWebService();
        }
    }
}
