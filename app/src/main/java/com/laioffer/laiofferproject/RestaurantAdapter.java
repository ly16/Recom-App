package com.laioffer.laiofferproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RestaurantAdapter extends BaseAdapter {
    Context context;
    List<Restaurant> restaurantData;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantData) {
        this.context = context;
        //restaurantData = DataService.getRestaurantData();
        this.restaurantData = restaurantData;
    }

    @Override
    public int getCount() {
        return restaurantData.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurantData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.restaurant_item,
                    parent, false);
        }

        ImageView restuarantImage = (ImageView) convertView.findViewById(R.id.restaurant_thumbnail);
        ImageView restaurantThumbnail = (ImageView) convertView.findViewById(
                R.id.restaurant_thumbnail);
        ImageView restaurantRating = (ImageView) convertView.findViewById(
                R.id.restaurant_rating);

        TextView restaurantName = (TextView) convertView.findViewById(
                R.id.restaurant_name);
        TextView restaurantAddress = (TextView) convertView.findViewById(
                R.id.restaurant_address);
        TextView restaurantType = (TextView) convertView.findViewById(
                R.id.restaurant_type);

        Restaurant r = restaurantData.get(position);
        restaurantName.setText(r.getName());
        restaurantAddress.setText(r.getAddress());
        restaurantType.setText(r.getType());

        restaurantThumbnail.setImageBitmap(r.getThumbnail());
        restaurantRating.setImageBitmap(r.getRating());



//        if (position < 2) {
//            restuarantImage.setImageDrawable(context.getDrawable(R.drawable.restaurant_thumbnail));
//        } else if (position >= 2 && position < 4) {
//            restuarantImage.setImageDrawable(context.getDrawable(R.drawable.banana));
//        } else if (position >= 4 && position < 6) {
//            restuarantImage.setImageDrawable(context.getDrawable(R.drawable.orange));
//        } else {
//            restuarantImage.setImageDrawable(context.getDrawable(R.drawable.pear));
//        }


        return convertView;
    }
}
