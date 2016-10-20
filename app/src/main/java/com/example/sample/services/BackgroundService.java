package com.example.sample.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.eddystone.Eddystone;

import java.util.List;

public class BackgroundService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    private BeaconManager beaconManager;
    private static final int REQUEST_ENABLE_BT = 1234;
    private static int scanCount = 0;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();
//Initialize BeaconInfo Manager
        beaconManager = new BeaconManager(this);
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Toast.makeText(context, "Service is still running", Toast.LENGTH_LONG).show();
                //connectToService();
                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
        beaconManager.disconnect();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        scanCount = 0;
        // Check if device supports Bluetooth Low Energy.
        if (!beaconManager.hasBluetooth()) {
            Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
            return;
        }

        // If Bluetooth is not enabled, let user enable it.
        if (!beaconManager.isBluetoothEnabled()) {
          /*  Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);*/
            Toast.makeText(this, "Device does not enable Bluetooth", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "scan start.", Toast.LENGTH_LONG).show();
            connectToService();
        }
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
    }


    public void connectToService() {
        //toolbar.setSubtitle("Scanning...");
        //adapter.replaceWith(Collections.<Eddystone>emptyList());

        beaconManager.setEddystoneListener(new BeaconManager.EddystoneListener() {
            @Override
            public void onEddystonesFound(List<Eddystone> eddystones) {
                Toast.makeText(BackgroundService.this, "scan running.", Toast.LENGTH_LONG).show();
                Log.i("eddystones.get(0):", "" + "scan running");
                scanCount++;
                int minDist = -200;
                String namespace = "";
                String instance = "";
                String mac = "";
                if (eddystones.size() == 1) {
                    Log.i("eddystones.get(0):", "" + eddystones.get(0).toString());
                    minDist = eddystones.get(0).rssi;
                    namespace = eddystones.get(0).namespace;
                    instance = eddystones.get(0).instance;
                    mac = eddystones.get(0).macAddress;
                }
                if (eddystones.size() > 1) {
                    for (int i = 0; i < eddystones.size(); i++) {
                        int rssi = eddystones.get(i).rssi;
                        if (rssi > minDist) {
                            minDist = rssi;
                            namespace = eddystones.get(i).namespace;
                            instance = eddystones.get(i).instance;
                            mac = eddystones.get(i).macAddress;
                        }
                    }
                }
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startEddystoneScanning();
            }
        });
    }

}