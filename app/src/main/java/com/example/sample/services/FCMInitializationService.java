package com.example.sample.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by manjula on 9/23/16.
 */
public class FCMInitializationService extends FirebaseInstanceIdService {
    private static final String TAG = "FCMInitializationService";

    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "FCM Device Token:" + fcmToken);
        //Save or send FCM registration token
    }
}