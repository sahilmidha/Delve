package com.example.delve.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.delve.service.models.VenuesVO;
import com.example.delve.ui.OnListItemClickListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by sahilmidha on 05/04/16.
 */
public class VenuesListview extends ListView implements AdapterView.OnItemClickListener
{
    private VenuesListAdapter _venuesListAdapter;
    private WeakReference<OnListItemClickListener> _listener;

    public VenuesListview(Context context)
    {
        super(context);
    }

    public VenuesListview(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public VenuesListview(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void init(OnListItemClickListener listener)
    {
        _listener = new WeakReference<OnListItemClickListener>(listener);
        _venuesListAdapter = new VenuesListAdapter(getContext(), new ArrayList<VenuesVO>());
        setAdapter(_venuesListAdapter);
        setOnItemClickListener(this);
    }

    public void setData(ArrayList<VenuesVO> dataList)
    {
        if (_venuesListAdapter != null)
        {
            _venuesListAdapter.addData(dataList);
        }

    }

    public ArrayList<VenuesVO> getList()
    {
        if (_venuesListAdapter != null)
        {
            return _venuesListAdapter.getVenuesVOs();
        }
        return null;
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        if (_listener != null)
        {
            _listener.get().onListItemClick(view, _venuesListAdapter.getVenuesVOs().get(position));
        }

    }

    public void onDestroy()
    {
        _venuesListAdapter = null;
        _listener = null;
    }
}
