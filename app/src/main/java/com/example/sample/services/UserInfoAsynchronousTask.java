package com.example.sample.services;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.sample.interfaces.UserInfoResponseCallback;

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
 * Created by manjula on 9/21/16.
 */
public class UserInfoAsynchronousTask extends AsyncTask<String, Void, String> {
    private UserInfoResponseCallback<String> callback;
    private Context context;

    public UserInfoAsynchronousTask(Context context, UserInfoResponseCallback<String> cb) {
        this.context = context;
        this.callback = cb;
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

    @Override
    protected String doInBackground(String... params) {

        try {
            return wsCall(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(String result) {
        System.out.println("on Post execute called");
        callback.onTaskComplete(result);
    }
}
