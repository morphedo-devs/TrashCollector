package com.example.trashcollector.Activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.trashcollector.MainActivity;
import com.example.trashcollector.Model.Transactionmain;
import com.example.trashcollector.Network.Apimanager;
import com.example.trashcollector.R;
import com.example.trashcollector.Utils.Constant;
import com.example.trashcollector.Utils.UsbService;
import com.squareup.leakcanary.LeakCanary;

import java.lang.ref.WeakReference;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Response;


public class StatusActivity extends AppCompatActivity {
    /*
     * Notifications from UsbService will be received here.
     */
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private UsbService usbService;
    private TextView display;
    private TextView droptrash;
    private TextView trashtype;
    private TextView more;
    private  TextView sorry;
    private  TextView trashvalidate;

    private EditText editText;
    private String datasrt="";
    private CheckBox box9600, box38400;
    private MyHandler mHandler;
    private LinearLayout donemain;
    private LinearLayout continuewithother;
    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        mHandler = new MyHandler(this);
        Intent intent = getIntent();
        datasrt = intent.getStringExtra("data");
        Constant.datatype=datasrt;
        Log.v("getvalue",datasrt);
        display = (TextView) findViewById(R.id.textView1);
        droptrash=(TextView)findViewById(R.id.droptrash);
        trashtype=(TextView)findViewById(R.id.trashtype);
        more=(TextView)findViewById(R.id.more);
        trashvalidate=(TextView)findViewById(R.id.trashvalidate);
        sorry=(TextView)findViewById(R.id.sorry);
        continuewithother=(LinearLayout)findViewById(R.id.continuewithother);
        donemain=(LinearLayout) findViewById(R.id.donemain);

        Button sendButton = (Button) findViewById(R.id.buttonSend);
        droptrash.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //trashtype.setVisibility(View.VISIBLE);
                //droptrash.setVisibility(View.GONE);
                if (!datasrt.equalsIgnoreCase("")) {
                    String data = datasrt;
                    if (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write(data.getBytes());
                    }
                }
            }
        }, 5000);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!datasrt.equalsIgnoreCase("")) {
                    String data = datasrt;
                    if (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write(data.getBytes());
                    }
                }
            }
        });

        box9600 = (CheckBox) findViewById(R.id.checkBox);
        box9600.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (box9600.isChecked())
                    box38400.setChecked(false);
                else
                    box38400.setChecked(true);
            }
        });

        box38400 = (CheckBox) findViewById(R.id.checkBox2);
        box38400.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (box38400.isChecked())
                    box9600.setChecked(false);
                else
                    box9600.setChecked(true);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
        Toast.makeText(StatusActivity.this, "onResume", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
        Intent stopService = new Intent(this, UsbService.class);
        stopService(stopService);
    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        Toast.makeText(StatusActivity.this, "startService", Toast.LENGTH_LONG).show();
        if (!UsbService.SERVICE_CONNECTED) {
            Toast.makeText(StatusActivity.this, "SERVICE_not_CONNECTED", Toast.LENGTH_LONG).show();
            Intent startService = new Intent(this, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
           // serviceIntent.putExtra("inputExtra", input);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Toast.makeText(StatusActivity.this, "VERSION_CODES", Toast.LENGTH_LONG).show();
                ContextCompat.startForegroundService(this, startService);
            }
            else {
                ContextCompat.startForegroundService(this, startService);
            }
        }
        Intent bindingIntent = new Intent(this, service);
        bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);
    }

    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */
    private static class MyHandler extends Handler {
        private final WeakReference<StatusActivity> mActivity;

        public MyHandler(StatusActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    String data = (String) msg.obj;
                    //   mActivity.get().display.append(data);

                    break;
           /*     case UsbService.CTS_CHANGE:
                    Toast.makeText(mActivity.get(), "CTS_CHANGE", Toast.LENGTH_LONG).show();
                    break;
                case UsbService.DSR_CHANGE:
                    Toast.makeText(mActivity.get(), "DSR_CHANGE", Toast.LENGTH_LONG).show();
                    break;*/
                case UsbService.SYNC_READ:
                    String buffer = (String) msg.obj;
                    mActivity.get().display.append(buffer);
                    if (buffer.equalsIgnoreCase("a")){
                        mActivity.get().more.setVisibility(View.VISIBLE);
                        mActivity.get().trashvalidate.setVisibility(View.VISIBLE);
                        mActivity.get().donemain.setVisibility(View.VISIBLE);
                        mActivity.get().continuewithother.setVisibility(View.VISIBLE);
                        mActivity.get().sorry.setVisibility(View.GONE);
                        mActivity.get().droptrash.setVisibility(View.GONE);
                        mActivity.get().trashtype.setVisibility(View.GONE);
                        Toast.makeText(mActivity.get(),"Trash Matched Successfully",Toast.LENGTH_SHORT).show();
                        Apimanager.getApiservice().initPayment("698b0bb6b7ee4d1799615c9cd1c3ae52", Constant.conatctnumber, Constant.datatype).enqueue(new retrofit2.Callback<Transactionmain>() {
                            @Override
                            public void onResponse(Call<Transactionmain> call, Response<Transactionmain> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(mActivity.get(),"Transaction completed",Toast.LENGTH_SHORT).show();
                                Constant.conatctnumber="";
                                   /* new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                           Intent intent=new Intent(mActivity.get(),MainActivity.class);
                                           mActivity.get().startActivity(intent);
                                        }
                                    }, 15000);*/

                                }
                            }

                            @Override
                            public void onFailure(Call<Transactionmain> call, Throwable t) {
                                Toast.makeText(mActivity.get(),"fail",Toast.LENGTH_SHORT).show();

                            }
                        });

                        mActivity.get().continuewithother.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent2=new Intent(mActivity.get(), WhatWouldYouLike.class);
                                mActivity.get().startActivity(intent2);
                                mActivity.get().finish();
                            }
                        });
                        mActivity.get().donemain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent1=new Intent(mActivity.get(), MainActivity.class);
                                mActivity.get().startActivity(intent1);
                                mActivity.get().finish();

                            }
                        });

                    }else if (buffer.equalsIgnoreCase("b")){
                        mActivity.get().more.setVisibility(View.GONE);
                        mActivity.get().trashvalidate.setVisibility(View.GONE);
                        mActivity.get().donemain.setVisibility(View.VISIBLE);
                        mActivity.get().continuewithother.setVisibility(View.GONE);
                        mActivity.get().sorry.setVisibility(View.VISIBLE);
                        mActivity.get().droptrash.setVisibility(View.GONE);
                        mActivity.get().trashtype.setVisibility(View.GONE);

                        mActivity.get().donemain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent1=new Intent(mActivity.get(), MainActivity.class);
                                mActivity.get().startActivity(intent1);
                                mActivity.get().finish();

                            }
                        });
                        Toast.makeText(mActivity.get(),"Trash Mismatched",Toast.LENGTH_SHORT).show();
/*
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(mActivity.get(),MainActivity.class);
                                mActivity.get().startActivity(intent);
                            }
                        }, 15000);*/

                    }else if (buffer.equalsIgnoreCase("c")){
                        mActivity.get().more.setVisibility(View.GONE);
                        mActivity.get().trashvalidate.setVisibility(View.GONE);
                        mActivity.get().donemain.setVisibility(View.GONE);
                        mActivity.get().continuewithother.setVisibility(View.GONE);
                        mActivity.get().sorry.setVisibility(View.GONE);
                        mActivity.get().droptrash.setVisibility(View.GONE);
                        mActivity.get().trashtype.setVisibility(View.VISIBLE);
                        Constant.conatctnumber="";
                    }
                    Toast.makeText(mActivity.get(),buffer,Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    }
    private void payment() {

    }
}
