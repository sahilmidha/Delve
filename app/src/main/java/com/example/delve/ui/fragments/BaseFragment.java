package com.example.delve.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.delve.R;
import com.example.delve.ui.IRefreshViewsListener;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, IRefreshViewsListener
{

    private static final String TAG = BaseFragment.class.getSimpleName();
    private final int requestCode = 0;

    public BaseFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        ViewGroup subFragmentViewContainer = (ViewGroup) view.findViewById(R.id.subFragmentViewContainer);
        View subFragmentView = inflater.inflate(getFragmentLayoutId(), subFragmentViewContainer, false);

        subFragmentViewContainer.removeAllViews();
        subFragmentViewContainer.addView(subFragmentView);

        Button _btnRetry = (Button) view.findViewById(R.id.btnRetry);
        _btnRetry.setOnClickListener(this);
        Button _btnSettings = (Button) view.findViewById(R.id.btnSettings);
        _btnSettings.setOnClickListener(this);

        return view;
    }

    public abstract int getFragmentLayoutId();

    protected abstract int getContainerViewGroupId();

    protected void showStatus(STATUS status, String statusText)
    {
        if (getView() == null)
        {
            return;
        }

        View rootView = getView();

        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.pbBaseFragmentProgress);

        View fragmentContentView = rootView.findViewById(getContainerViewGroupId());
        View errorDisplayNetworkContainer = rootView.findViewById(R.id.containerErrorDisplay);
        View errorDisplayNoContentContainer = rootView.findViewById(R.id.tvNoContentAvailable);

        switch (status)
        {
            case STATUS_LOADING:
                progressBar.setVisibility(View.VISIBLE);
                fragmentContentView.setVisibility(View.GONE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_SUCCESS:
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.VISIBLE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_ERROR:
                Toast.makeText(getActivity(), getResources().getString(R.string.networkError), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.VISIBLE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_NETWORK_ERROR:
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.GONE);
                errorDisplayNetworkContainer.setVisibility(View.VISIBLE);
                errorDisplayNoContentContainer.setVisibility(View.GONE);
                break;

            case STATUS_EMPTY://Specialised case of Error in Watchlist/download cases where the content is Empty.
                progressBar.setVisibility(View.GONE);
                fragmentContentView.setVisibility(View.GONE);
                errorDisplayNetworkContainer.setVisibility(View.GONE);
                errorDisplayNoContentContainer.setVisibility(View.VISIBLE);
                break;
        }
    }

    protected void showStatus(STATUS status, int stringResourceId)
    {
        showStatus(status, stringResourceId == 0 ? null : getString(stringResourceId));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRetry:
                refreshActiveFragment();
                break;
            case R.id.btnSettings:
                startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), requestCode);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public enum STATUS
    {
        STATUS_LOADING, STATUS_SUCCESS, STATUS_ERROR, STATUS_EMPTY, STATUS_NETWORK_ERROR
    }
}
