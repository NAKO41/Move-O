package com.ctrlz.prototype;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;


public class Controls extends AppCompatActivity {

    String t1; // string for bluetooth (not used, idk why we have this)
    String address = null, name = null;//same as above, don't know why we have it

    static BluetoothAdapter myBluetooth = null; //defines the bluetooth adapter (HC-06)
    static BluetoothSocket btSocket = null;     //defines the bluetooth socket
    static Set<BluetoothDevice> pairedDevices;  //this obtains the ip address of the device
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");  //TF?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);



        BottomNavigationView bottomNav = findViewById(R.id.bottomnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ControlsFragment()).commit();

        try {
            setw();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @SuppressLint("ClickableViewAccessibility")
    private void setw() throws IOException {
        bluetooth_connect_device();

    }


        private void bluetooth_connect_device () throws IOException {
            try {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();
                address = myBluetooth.getAddress();
                pairedDevices = myBluetooth.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    for (BluetoothDevice bt : pairedDevices) {
                        address = bt.getAddress().toString();
                        name = bt.getName().toString();
                        Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception we) {
            }
            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
            btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
            btSocket.connect();
            try {
                t1 = "BT Name: " + name + "\nBT Address: " + address;
            } catch (Exception e) {
            }
        }


        public static void move(String i){
            try {
                if (btSocket != null) {
                    btSocket.getOutputStream().write(i.toString().getBytes());
                }
            } catch (Exception e) {

            }

        }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_camera:
                            selectedFragment = new CameraFragment();
                            Log.d("STATE", "onNavigationItemSelected: YAY");
                            break;
                        case R.id.nav_controls:
                            selectedFragment = new ControlsFragment();
                            break;
                        case R.id.nav_library:
                            selectedFragment = new LibraryFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

}

