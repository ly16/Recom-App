package com.laioffer.laiofferproject;


import android.os.AsyncTask;
import android.os.Bundle;
//import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackendListFragment extends Fragment {

    private static final String TAG = BackendListFragment.class.getSimpleName();
    private ListView mListView;
    private LocationTracker locationTracker;


    public BackendListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_backend_list, container, false);
        mListView = (ListView) view.findViewById(R.id.backend_list);
        getNearbyRestaurantThroughBackend();
        return view;
    }

    public void getNearbyRestaurantThroughBackend() {
        locationTracker = new LocationTracker(getActivity());
        locationTracker.getLocation();
        String urlSearch = "http://34.211.21.63/Titan/search?lat=" +
                Double.toString(locationTracker.getLatitude()) +
                "&lon=" + Double.toString(locationTracker.getLongitude())+"&user_id=1111";

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET,
                urlSearch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d(TAG, response);
                new GetRestaurantsFromBackendAsyncTask(response).execute();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest2);
    }

    private class GetRestaurantsFromBackendAsyncTask extends
            AsyncTask<Void, Void, Void> {
        private String response;
        private List<Restaurant> restaurantList;

        public GetRestaurantsFromBackendAsyncTask(String response) {
            this.response = response;
            Log.i("dddddd", this.response );
            restaurantList = new ArrayList<Restaurant>();
        }

        // Create restaurants data
        @Override
        protected Void doInBackground(Void... params) {
            try {
                JSONArray reader = new JSONArray(response);
                for(int index = 0; index < reader.length(); index++){
                    JSONObject item = reader.getJSONObject(index);
                    Restaurant restaurant = new Restaurant();

                    restaurant.setName(item.getString("name"));
                    restaurant.setAddress(item.getString("address"));
                    restaurant.setLat(item.getDouble("latitude"));
                    restaurant.setLng(item.getDouble("longitude"));
                    restaurant.setStars(item.getDouble("rating"));
                    restaurant.setUrl(item.getString("image_url"));
                    restaurant.setFavorate(item.getBoolean("favorite"));
                    restaurant.setItem_id(item.getString("item_id"));

                    JSONArray category = item.getJSONArray("categories");
                    List<String> cat = new ArrayList<String>();
                    for (int j = 0; j < category.length(); j++) {
                        cat.add(category.get(j).toString());
                    }
                    restaurant.setCategories(cat);

                    restaurantList.add(restaurant);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            RestaurantBackendAdapter adapter = new RestaurantBackendAdapter(getContext(),
                    restaurantList);
            mListView.setAdapter(adapter);
        }
    }

}
