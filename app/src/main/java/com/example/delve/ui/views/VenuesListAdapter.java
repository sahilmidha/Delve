package com.example.delve.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.delve.R;
import com.example.delve.service.models.VenuesVO;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by sahilmidha on 05/04/16.
 */
public class VenuesListAdapter extends BaseAdapter
{
    private ArrayList<VenuesVO> _venuesVOs;
    private Context _context;

    public VenuesListAdapter(Context context, ArrayList<VenuesVO> venuesVOs)
    {
        _venuesVOs = venuesVOs;
        _context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount()
    {
        return _venuesVOs.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position)
    {
        return position;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null)
        {
            view = LayoutInflater.from(_context).inflate(R.layout.venue_row_layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.setData(_context, _venuesVOs.get(position));
        return view;
    }
    private static class ViewHolder
    {
        ImageView _venueImage;
        TextView _venueName;
        TextView _venueAddress;
        TextView _venueRating;

        ViewHolder(View view)
        {
            _venueImage = (ImageView) view.findViewById(R.id.imgVenue);
            _venueName = (TextView) view.findViewById(R.id.tvVenueName);
            _venueAddress = (TextView) view.findViewById(R.id.tvVenueAddress);
            _venueRating = (TextView) view.findViewById(R.id.tvVenueRating);
        }

        public void setData(Context context, VenuesVO data)
        {
            Picasso.with(context).load(data.getPhotos().getDefaultPhotoUrl()).into(_venueImage);
            _venueName.setText(data.getName());
            _venueAddress.setText(String.format("%s, %s, %s", data.getLocation().getAddress(), data.getLocation().getCrossStreet(), data.getLocation().getCity()));
            _venueRating.setText("Rating: " + new DecimalFormat("#0.0").format(data.getRating()));
        }
    }
    public ArrayList<VenuesVO> getVenuesVOs()
    {
        return _venuesVOs;
    }

    public void addData(ArrayList<VenuesVO> venuesVOs)
    {
        if (_venuesVOs != null)
        {
            _venuesVOs.clear();
            _venuesVOs.addAll(venuesVOs);
            notifyDataSetChanged();
        }

    }
}
