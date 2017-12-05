package com.laioffer.laiofferproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class RestaurantBackendAdapter extends BaseAdapter {
    private Context context;
    private List<Restaurant> restaurantData;
    private DataService dataService;

    public RestaurantBackendAdapter(Context context, List<Restaurant> restaurantData) {
        this.context = context;
        this.restaurantData = restaurantData;
        dataService = new DataService();
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


    static class ViewHolder {
        public TextView restaurantName;
        public TextView restaurantCategory;
        public TextView restaurantStreet;
        public TextView restaurantCity;
        public TextView restaurantState;

        public ImageView restaurantImage;
        public ImageView restaurantFavorate;
        public RatingBar restaurantRatingBar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.restaurant_backend_item,
                    parent, false);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.restaurantName = (TextView) rowView.findViewById(R.id.restaurant_backend_name);
            viewHolder.restaurantCategory = (TextView) rowView.findViewById(R.id.
                    restaurant_backend_category);
            viewHolder.restaurantStreet = (TextView) rowView.findViewById(R.id.restaurant_backend_street);
            viewHolder.restaurantCity = (TextView) rowView.findViewById(R.id.restaurant_backend_city);
            viewHolder.restaurantState = (TextView) rowView.findViewById(R.id.restaurant_backend_state);
            viewHolder.restaurantFavorate = (ImageView) rowView.findViewById(R.id.restaurant_backend_favorate);
            viewHolder.restaurantImage = (ImageView) rowView.findViewById(R.id.restaurant_backend_image);
            rowView.setTag(viewHolder);
            viewHolder.restaurantRatingBar = (RatingBar) rowView.findViewById(R.id.restaurant_backend_ratingbar);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        final Restaurant restaurant = restaurantData.get(position);
        holder.restaurantName.setText(restaurant.getName());
        StringBuilder builder = new StringBuilder();
        builder.append("category");
        for (String category : restaurant.getCategories()) {
            builder.append(category + ", ");
        }

        holder.restaurantCategory.setText(builder.toString());
        String[] address = restaurant.getAddress().split(",");
        if (address.length >= 3) {
            holder.restaurantStreet.setText(address[0]);
            holder.restaurantCity.setText(address[1]);
            holder.restaurantState.setText(address[2]);
        }

        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... voids) {
                Clock clock = new Clock();
                clock.start();
                Bitmap bitmap = dataService.getBitmapFromURL(restaurant.getUrl());
                clock.stop();
                Log.e("Image loading time", clock.getCurrentInterval() + "");
                return bitmap;

            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                holder.restaurantImage.setImageBitmap(bitmap);
            }
        }.execute();

        holder.restaurantRatingBar.setRating((float) restaurant.getStars());

        holder.restaurantFavorate.setImageResource(
                restaurant.isFavorate() ?
                        R.mipmap.ic_favorite_black_24dp :
                        R.mipmap.ic_favorite_border_black_24dp
        );

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create explicit intent to start map activity class
                Bundle bundle = new Bundle();
                bundle.putParcelable(RestaurantMapActivity.EXTRA_LATLNG,
                        new LatLng(restaurant.getLat(), restaurant.getLng()));
                Intent intent = new Intent(view.getContext(), RestaurantMapActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.restaurantFavorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFavorate(restaurant.getItem_id());
            }
        });


        return rowView;
    }

    private void addFavorate(String item_id) {
        final JSONObject body = new JSONObject();
        String[] data = {item_id};
        final JSONArray array = new JSONArray(Arrays.asList(data));
        try {
            body.put("user_id", "1111");
            body.put("favorite", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String urlSearch = "http://34.211.21.63/Titan/history";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlSearch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }


        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
