package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NetInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private int STORAGE_RPERMISSION_CODE = 1;
    private int STORAGE_WPERMISSION_CODE = 2;
    TextView netInfo;
    DhcpInfo dhcpInfo;
    WifiManager wifi;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_info);
        netInfo = findViewById(R.id.netInfo);
        wifi= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        dhcpInfo=wifi.getDhcpInfo();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.nav_netInfo);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Context context = NetInfo.this;
        switch (item.getItemId()){
            case R.id.nav_home:
                openHomePage(null);
                break;
            case R.id.nav_netInfo:
                Toast.makeText(context, "Already on this page.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_savePer:
                if(ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(context, "Permission for external storage already granted.", Toast.LENGTH_LONG).show();
                }
                else if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
                    Toast.makeText(context, "Permission for external storage currently denied.", Toast.LENGTH_LONG).show();
                    requestWPermission();
                }
                break;
            case R.id.nav_about:
                openHelpPage(null);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openHomePage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openHelpPage(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    private void requestWPermission()
    {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_WPERMISSION_CODE);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_RPERMISSION_CODE);
    }
}