package com.example.sample.view;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.sample.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Button recycler_view_button, complex_recycler_view_button, text_speech_button, coordinate_layout, emergancy_call_button, bottom_menu_bar_button, sample_layouts_button, tab_layout_button;
    private CalendarView cal_view;
    boolean isPhoneCalling = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  Toolbar tool_bar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setElevation(26F);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);*/
        recycler_view_button = (Button) findViewById(R.id.recycler_view_button);
        recycler_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerSample.class);
                startActivity(intent);
            }
        });
        complex_recycler_view_button = (Button) findViewById(R.id.complex_recycler_view_button);
        complex_recycler_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComplexRecyclerViewSample.class);
                startActivity(intent);
            }
        });
        text_speech_button = (Button) findViewById(R.id.text_speech_button);
        text_speech_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TextSpeechActivity.class);
                startActivity(intent);
            }
        });
        coordinate_layout = (Button)findViewById(R.id.coordinate_layout);
        coordinate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoordinateLayoutSampleActivity.class);
                startActivity(intent);
            }
        });
        emergancy_call_button = (Button) findViewById(R.id.emergancy_call_button);
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        emergancy_call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(MainActivity.this, EmergencyCallActivity.class);
                startActivity(intent);*/
                isPhoneCalling = true;
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8951206025"));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(callIntent);
            }
        });
        bottom_menu_bar_button = (Button) findViewById(R.id.bottom_menu_bar_button);
        bottom_menu_bar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BottomSheetDialogActivity.class);
                startActivity(intent);
            }
        });
        sample_layouts_button = (Button) findViewById(R.id.sample_layouts_button);
        sample_layouts_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleLayoutViewActivity.class);
                startActivity(intent);
            }
        });
        tab_layout_button = (Button) findViewById(R.id.tab_layout_button);
        tab_layout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabFragmentActivity.class);
                startActivity(intent);
            }
        });
        /*TelephonyManager tm = (TelephonyManager) MainActivity.this.getSystemService(TELEPHONY_SERVICE);
        GsmCellLocation loc = (GsmCellLocation) tm.getCellLocation();


        int cellid = loc.getCid();
        int lac = loc.getLac();
        //"http://www.google.com/glm/mmap"
try {
    Log.e("", "cellid:" + "Try");
   // System.exit(0);
    Log.e("", "cellid:" + cellid + ":lac" + lac + ": ");//  cellid:27848083:lac34243  //cellid:27803695:lac34243 - wifi or gps on
}catch (Exception e){
    Log.e("", "cellid:" + "Exception");
}finally {
   // System.exit(0);
    Log.e("", "cellid:" + "Finally");
}
        Log.e("", "cellid:" + "out of finally");*/
       /* this.registerReceiver(this.mConnReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));
        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                WifiManager.WIFI_STATE_CHANGED_ACTION));
        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                BluetoothAdapter.ACTION_STATE_CHANGED));*/
    }
    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent
                    .getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_IS_FAILOVER, false);
            boolean noConnectivity1 = intent.getBooleanExtra(
                    BluetoothAdapter.ACTION_STATE_CHANGED, false);
            boolean isfailover = intent.getBooleanExtra(
                    BluetoothAdapter.EXTRA_STATE, false);
            NetworkInfo currentNetworkInfo = (NetworkInfo) intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);
            NetworkInfo currentwifiInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherwifiInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            NetworkInfo currentbluetoothinfo = (NetworkInfo) intent
                    .getParcelableExtra(BluetoothAdapter.EXTRA_STATE);
            NetworkInfo otherbluetoothinfo = (NetworkInfo) intent
                    .getParcelableExtra(BluetoothAdapter.ACTION_STATE_CHANGED);
            if (currentNetworkInfo.isConnected()) {
                Toast.makeText(getApplicationContext(),
                        "Internet Connected", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        " Internet Not Connected", Toast.LENGTH_LONG).show();
            }
            if (currentwifiInfo.isConnected()) {
                Toast.makeText(getApplicationContext(), "wifi Connected",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        " WIFI Not Connected", Toast.LENGTH_LONG).show();
            }
            if (currentbluetoothinfo.isAvailable()) {
                Toast.makeText(getApplicationContext(),
                        "Bluetooth Connected", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Bluetooth Not Connected", Toast.LENGTH_LONG).show();
            }
        }
    };
    /**
     * Class : PhoneCallListener
     * To get the current status of the call and after call disconnected application restores to previous status.
     *
     */
    private class PhoneCallListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                if (isPhoneCalling) {
                    Intent intant = MainActivity.this.getIntent();
                    intant.getFlags();
                    startActivity(intant);
                    isPhoneCalling = false;
                }
            }
        }
    }
}
