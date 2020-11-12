package com.example.trashcollector.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trashcollector.R;
import com.felhr.usbserial.UsbSerialDevice;

public class NumberConfirmationScreen extends AppCompatActivity {
TextView next;
    UsbManager usbManager;
    UsbDevice device;
    UsbSerialDevice serialPort;
    UsbDeviceConnection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_confirmation_screen);
        next=findViewById(R.id.next);

    }
    public void Next(View view){
        Intent intent=new Intent(getApplicationContext(),SelectTrashtype.class);
        startActivity(intent);
    }
}