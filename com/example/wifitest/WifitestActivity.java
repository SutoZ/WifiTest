package com.example.wifitest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WifitestActivity extends Activity {
    private TextView tvStatus;
    private BroadcastReceiver wifibr;
    private WifiManager wifimanager;

    /* renamed from: com.example.wifitest.WifitestActivity.1 */
    class C00931 extends BroadcastReceiver {
        C00931() {
        }

        public void onReceive(Context context, Intent intent) {
            for (ScanResult r : WifitestActivity.this.wifimanager.getScanResults()) {
                WifitestActivity.this.tvStatus.append("\n" + r.SSID);
            }
        }
    }

    /* renamed from: com.example.wifitest.WifitestActivity.2 */
    class C00942 implements OnClickListener {
        C00942() {
        }

        public void onClick(View v) {
            WifitestActivity.this.wifimanager.startScan();
        }
    }

    public WifitestActivity() {
        this.wifibr = new C00931();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0092R.layout.wifitest);
        this.tvStatus = (TextView) findViewById(C0092R.id.tvStatus);
        Button btnGetWifi = (Button) findViewById(C0092R.id.btnGetWifi);
        this.wifimanager = (WifiManager) getSystemService("wifi");
        btnGetWifi.setOnClickListener(new C00942());
        registerReceiver(this.wifibr, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
    }

    protected void onStop() {
        super.onStop();
        unregisterReceiver(this.wifibr);
    }
}
