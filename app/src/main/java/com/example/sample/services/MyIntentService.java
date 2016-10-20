package com.example.sample.services;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by manjula on 9/23/16.
 */
public class MyIntentService extends IntentService {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super(MyIntentService.class.getName());
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");

        Bundle bundle = new Bundle();

        if (!TextUtils.isEmpty(url)) {
            /* Update UI: Download Service is Running */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            try {
                String results = wsCall(url);

                /* Sending result back to activity */
                if (null != results && !results.equals("")) {
                    bundle.putString("result", results);
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e) {

                /* Sending error message back to activity */
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);
            }
        }
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String wsCall(String serviceURL)throws IOException {
        String responseString = "";
        HttpURLConnection connection = null;
        InputStream response = null;
        try {
            // defaultHttpClient

            URL url = new URL(serviceURL);
            connection = (HttpURLConnection) url.openConnection();

            //  byte[] outputInBytes = jsonInputData.getBytes("UTF-8");
            //   connection .setRequestProperty("Content-Length", Integer.toString(jsonInputData.length()));
            connection .setRequestProperty("charset", "utf-8");
            connection .setRequestProperty("Content-Type", "application/json");
            connection .setRequestMethod("POST");
            connection .setDoInput(true);
            connection .connect();

           /* try (DataOutputStream wr = new DataOutputStream(connection .getOutputStream())) {
                wr.write(outputInBytes);
            }*/
           /* if (connection .getResponseCode() != 200) {
                //throw new RuntimeException(""+connection .getResponseMessage());
                throw new RuntimeException(""+"isConnected failed: ETIMEDOUT");
            }*/

            try {
                response = connection .getInputStream();
            } catch (Exception e) {
                Log.e("WSClient - wsCall", "response : Exception : " + e.getMessage());
            }
            if(response != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                responseString = sb.toString();
                Log.e("responseString", "responseString : " + responseString);
                reader.close();
                response.close();
            } else {
                throw new DownloadException("Failed to fetch data!!");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            /*activity.runOnUiThread(new Runnable() {
                public void run() {
                    Util.alertDialog(activity, "Connection timed out", "Error");
                }
            });*/
        } catch (Exception e) {
            Log.e("WSClient - wsCall", "Exception : " + e.getMessage());
            //connection.getErrorStream().close();
        }finally{
            if(response != null)
                response.close();
            connection .disconnect();
        }

        return responseString;
    }

   /* private String[] parseResult(String result) {

        String[] blogTitles = null;
        try {
            JSONObject response = new JSONObject(result);

            JSONArray posts = response.optJSONArray("posts");

            blogTitles = new String[posts.length()];

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");

                blogTitles[i] = title;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return blogTitles;
    }*/

    public class DownloadException extends Exception {

        public DownloadException(String message) {
            super(message);
        }

        public DownloadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
