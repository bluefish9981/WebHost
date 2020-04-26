package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.TextView;

public class NetInfo extends AppCompatActivity {

    TextView netInfo;
    DhcpInfo dhcpInfo;
    WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_info);
        netInfo = findViewById(R.id.netInfo);
        wifi= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        dhcpInfo=wifi.getDhcpInfo();

        @SuppressWarnings("depricated")
        String MAC = "MAC Address: " + wifi.getConnectionInfo().getMacAddress();
        String dns1 = "DNS 1: " + (Formatter.formatIpAddress(dhcpInfo.dns1));
        String dns2 = "DNS 2: " + (Formatter.formatIpAddress(dhcpInfo.dns2));
        String gateway = "Default Gateway: " + (Formatter.formatIpAddress(dhcpInfo.gateway));
        String ipAddress = "IP Address: " + (Formatter.formatIpAddress(dhcpInfo.ipAddress));
        String leaseDuration = "Lease Time: " + (dhcpInfo.leaseDuration);
        String netmask = "Subnet Mask: " + (Formatter.formatIpAddress(dhcpInfo.netmask));
        String serverAddress = "Server IP: " + (Formatter.formatIpAddress(dhcpInfo.serverAddress));

        netInfo.setText(ipAddress + "\n" + "\n" +
                MAC + "\n" + "\n" +
                gateway + "\n" + "\n" +
                netmask + "\n" + "\n" +
                dns1 +  "\n" + "\n" +
                dns2 + "\n" + "\n" +
                serverAddress + "\n" + "\n" +
                leaseDuration + "(s)");
    }
}