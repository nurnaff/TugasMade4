package com.example.andinurnaf.tugas4.Loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsynTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    private String link;

    public MyAsynTaskLoader(final Context context, String link) {
        super(context);

        onContentChanged();
        this.link = link;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }


    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItems> filmItemses = new ArrayList<>();
        String url = link;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0 ; i < list.length() ; i++){
                        JSONObject film = list.getJSONObject(i);
                        MovieItems filmItems = new MovieItems(film);
                        filmItemses.add(filmItems);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });

        return filmItemses;
    }

    protected void onReleaseResources(ArrayList<MovieItems> data) {
        //nothing to do.
    }

}