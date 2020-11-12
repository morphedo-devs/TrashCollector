package com.example.trashcollector.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trashcollector.Model.BaseResponse;
import com.example.trashcollector.Network.Apimanager;
import com.example.trashcollector.R;
import com.example.trashcollector.Utils.Constant;
import com.example.trashcollector.Utils.UsbService;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatWouldYouLike extends AppCompatActivity {
TextView ecash,plastic,glass,tin,others;
EditText printnumbber;
Button one,two,three,four,five,six,seven,eight,nine,zero,del,plus,ok;
boolean hit;
String str1="",str2="",str3="",str4="",str5="",str6="",str7="",str8="",str9="",str0="",strplus="";

    private static final int targetVendorID = 9025;  //Arduino Uno
    private static final int targetProductID = 67; //Arduino Uno, not 0067
    UsbDevice deviceFound = null;
    UsbInterface usbInterfaceFound = null;
    UsbEndpoint endpointIn = null;
    UsbEndpoint endpointOut = null;

    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    PendingIntent mPermissionIntent;

    UsbInterface usbInterface;
    UsbDeviceConnection usbDeviceConnection;
   /* private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
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
    private EditText editText;
    private CheckBox box9600, box38400;
    private WhatWouldYouLike.MyHandler mHandler;
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
    };*/
    /*EditText textOut;
    Button buttonSend;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobileandchosetrashtypescreen);
        printnumbber=findViewById(R.id.printnumbber);
        one=findViewById(R.id.one);
        plastic=findViewById(R.id.Plastic);
        others=findViewById(R.id.others);
        glass=findViewById(R.id.glass);
        tin=findViewById(R.id.tin);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        zero=findViewById(R.id.zero);
        del=findViewById(R.id.del);
        plus=findViewById(R.id.plus);
        ok=findViewById(R.id.ok);


        //mHandler = new MyHandler(this);

     /*   mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(
                ACTION_USB_PERMISSION), 0);

        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        registerReceiver(mUsbReceiver, filter);

        registerReceiver(mUsbDeviceReceiver, new IntentFilter(
                UsbManager.ACTION_USB_DEVICE_ATTACHED));
        registerReceiver(mUsbDeviceReceiver, new IntentFilter(
                UsbManager.ACTION_USB_DEVICE_DETACHED));

        connectUsb();*/

plastic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if (!Constant.conatctnumber.equalsIgnoreCase("")){

    String data = "@";
    Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
    intent.putExtra("data",data);
    startActivity(intent);
    finish();
    /*if (usbService != null) { // if UsbService was correctly binded, Send data
        usbService.write(data.getBytes());

    }*/

}else {
    Toast.makeText(getApplicationContext(),"Please enter your mobile number first",Toast.LENGTH_SHORT).show();
}

           /* if (usbService != null) { // if UsbService was correctly binded, Send data
                usbService.write(data.getBytes());

            }*/

    }
});
glass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (!Constant.conatctnumber.equalsIgnoreCase("")){
            String data = "#";
            Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
            intent.putExtra("data",data);
            startActivity(intent);
            finish();
         /*   if (usbService != null) { // if UsbService was correctly binded, Send data
                usbService.write(data.getBytes());
                Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
                finish();
            }*/
        }else {
            Toast.makeText(getApplicationContext(),"Please enter your mobile number first",Toast.LENGTH_SHORT).show();
        }

       /* if (usbService != null) { // if UsbService was correctly binded, Send data
            usbService.write(data.getBytes());

        }*/
    }
});

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.conatctnumber.equalsIgnoreCase("")){
                    String data = "&";
                    Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
                    intent.putExtra("data",data);
                    startActivity(intent);
                    finish();
                    /*if (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write(data.getBytes());
                        Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                        finish();
                    }*/
                }else {
                    Toast.makeText(getApplicationContext(),"Please enter your mobile number first",Toast.LENGTH_SHORT).show();
                }

               /* if (usbService != null) { // if UsbService was correctly binded, Send data
                    usbService.write(data.getBytes());

                }*/
            }
        });
        tin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constant.conatctnumber.equalsIgnoreCase("")){
                    String data = "$";
                    Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
                    intent.putExtra("data",data);
                    startActivity(intent);
                    finish();
                   /* if (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write(data.getBytes());
                        Intent intent=new Intent(getApplicationContext(),StatusActivity.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                        finish();
                    }*/
                }else {
                    Toast.makeText(getApplicationContext(),"Please enter your mobile number first",Toast.LENGTH_SHORT).show();
                }

              /*  if (usbService != null) { // if UsbService was correctly binded, Send data
                    usbService.write(data.getBytes());

                }*/
            }
        });
        //ecash=findViewById(R.id.ecash);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(one,printnumbber,"1");
              /*  str1="1";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                //printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(two,printnumbber,"2");
             /*   str2="2";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();
// Test it out
              //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(three,printnumbber,"3");
                /*str3="3";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
              //  printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(four,printnumbber,"4");
              /*  str4="4";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                // printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(five,printnumbber,"5");
              /*  str5="5";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                //printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(six,printnumbber,"6");
             /*   str6="6";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                //printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(seven,printnumbber,"7");
               /* str7="7";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                // printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(eight,printnumbber,"8");
             /*   str8="8";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                //printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(nine,printnumbber,"9");
              //  str9="9";
               /* StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();

// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                // printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(zero,printnumbber,"0");
              /*  str0="0";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();
// Test it out
                //  System.out.println(buffer); // .toString());
                printnumbber.setText(buffer);*/
                // printnumbber.setText(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0);

            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Log.v("getvalue","0");
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();
                method(String.valueOf(buffer));*/
hit=true;
if (hit=true){
    try {
        printnumbber.setText(str1.substring(0, str1.length() - 1));
        str1=str1.substring(0, str1.length() - 1);
        hit=false;
    }catch (IndexOutOfBoundsException e){
        e.printStackTrace();
    }

}else {
    try {
        str1=str1.substring(0, str1.length() - 1);
        printnumbber.setText(str1.substring(0, str1.length() - 1));
    }catch (IndexOutOfBoundsException e){

    }


}

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnButtonClick(one,printnumbber,"+");
              /*  strplus="+";
                StringBuffer buffer = new StringBuffer(str0+str1+str2+str3+str4+str5+str6+str7+str8+str9+str0+strplus);
                buffer.reverse();
                printnumbber.setText(buffer);*/
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (printnumbber.getText().toString().length()==10){
                    Constant.conatctnumber=printnumbber.getText().toString();
                    Toast.makeText(getApplicationContext(),"New user added successfully",Toast.LENGTH_SHORT).show();
                    Apimanager.getApiservice().verfiymobile("698b0bb6b7ee4d1799615c9cd1c3ae52",printnumbber.getText().toString()).enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            if(response.isSuccessful()){

                                Toast.makeText(getApplicationContext(),"New user added successfully",Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                        }
                    });
                    printnumbber.getText().clear();
                }else {
                    Toast.makeText(getApplicationContext(),"Please enter valid mobile number",Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
 /*   public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);


            newnumberprint(str);
        }

        return str;

    }*/
public void OnButtonClick(Button button, EditText inpute, String number){
    String cache=printnumbber.getText().toString();
    inpute.setText(cache+number);
    str1=cache+number;

}


   /* public void Ecash(View view){
        Intent intent=new Intent(getApplicationContext(),TypeOfUser.class);
        startActivity(intent);
    }*/


   /* @Override
    protected void onDestroy() {
        releaseUsb();
        unregisterReceiver(mUsbReceiver);
        unregisterReceiver(mUsbDeviceReceiver);
        super.onDestroy();
    }

    private void connectUsb() {

        Toast.makeText(WhatWouldYouLike.this, "connectUsb()", Toast.LENGTH_LONG)
                .show();
       // textStatus.setText("connectUsb()");

        searchEndPoint();

        if (usbInterfaceFound != null) {
            setupUsbComm();
        }

    }

    private void releaseUsb() {

        Toast.makeText(WhatWouldYouLike.this, "releaseUsb()", Toast.LENGTH_LONG)
                .show();
       // textStatus.setText("releaseUsb()");

        if (usbDeviceConnection != null) {
            if (usbInterface != null) {
                usbDeviceConnection.releaseInterface(usbInterface);
                usbInterface = null;
            }
            usbDeviceConnection.close();
            usbDeviceConnection = null;
        }

        deviceFound = null;
        usbInterfaceFound = null;
        endpointIn = null;
        endpointOut = null;
    }*/

    /*private void searchEndPoint() {

       *//* textInfo.setText("");
        textSearchedEndpoint.setText("");*//*

        usbInterfaceFound = null;
        endpointOut = null;
        endpointIn = null;

        // Search device for targetVendorID and targetProductID
        if (deviceFound == null) {
            UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
            HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
            Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

            while (deviceIterator.hasNext()) {
                UsbDevice device = deviceIterator.next();

                if (device.getVendorId() == targetVendorID) {
                    if (device.getProductId() == targetProductID) {
                        deviceFound = device;
                    }
                }
            }
        }

        if (deviceFound == null) {
            Toast.makeText(WhatWouldYouLike.this, "device not found",
                    Toast.LENGTH_LONG).show();
          //  textStatus.setText("device not found");
        } else {
            String s = deviceFound.toString() + "\n" + "DeviceID: "
                    + deviceFound.getDeviceId() + "\n" + "DeviceName: "
                    + deviceFound.getDeviceName() + "\n" + "DeviceClass: "
                    + deviceFound.getDeviceClass() + "\n" + "DeviceSubClass: "
                    + deviceFound.getDeviceSubclass() + "\n" + "VendorID: "
                    + deviceFound.getVendorId() + "\n" + "ProductID: "
                    + deviceFound.getProductId() + "\n" + "InterfaceCount: "
                    + deviceFound.getInterfaceCount();
           // textInfo.setText(s);

            // Search for UsbInterface with Endpoint of USB_ENDPOINT_XFER_BULK,
            // and direction USB_DIR_OUT and USB_DIR_IN

            for (int i = 0; i < deviceFound.getInterfaceCount(); i++) {
                UsbInterface usbif = deviceFound.getInterface(i);

                UsbEndpoint tOut = null;
                UsbEndpoint tIn = null;

                int tEndpointCnt = usbif.getEndpointCount();
                if (tEndpointCnt >= 2) {
                    for (int j = 0; j < tEndpointCnt; j++) {
                        if (usbif.getEndpoint(j).getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                            if (usbif.getEndpoint(j).getDirection() == UsbConstants.USB_DIR_OUT) {
                                tOut = usbif.getEndpoint(j);
                            } else if (usbif.getEndpoint(j).getDirection() == UsbConstants.USB_DIR_IN) {
                                tIn = usbif.getEndpoint(j);
                            }
                        }
                    }

                    if (tOut != null && tIn != null) {
                        // This interface have both USB_DIR_OUT
                        // and USB_DIR_IN of USB_ENDPOINT_XFER_BULK
                        usbInterfaceFound = usbif;
                        endpointOut = tOut;
                        endpointIn = tIn;
                    }
                }

            }

            if (usbInterfaceFound == null) {
              //  textSearchedEndpoint.setText("No suitable interface found!");
            } else {
                *//*textSearchedEndpoint.setText("UsbInterface found: "
                        + usbInterfaceFound.toString() + "\n\n"
                        + "Endpoint OUT: " + endpointOut.toString() + "\n\n"
                        + "Endpoint IN: " + endpointIn.toString());*//*
            }
        }
    }*/

    /*private boolean setupUsbComm() {

        // for more info, search SET_LINE_CODING and
        // SET_CONTROL_LINE_STATE in the document:
        // "Universal Serial Bus Class Definitions for Communication Devices"
        // at http://adf.ly/dppFt
        final int RQSID_SET_LINE_CODING = 0x20;
        final int RQSID_SET_CONTROL_LINE_STATE = 0x22;

        boolean success = false;

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        Boolean permitToRead = manager.hasPermission(deviceFound);

        if (permitToRead) {
            usbDeviceConnection = manager.openDevice(deviceFound);
            if (usbDeviceConnection != null) {
                usbDeviceConnection.claimInterface(usbInterfaceFound, true);

                int usbResult;
                usbResult = usbDeviceConnection.controlTransfer(0x21, // requestType
                        RQSID_SET_CONTROL_LINE_STATE, // SET_CONTROL_LINE_STATE
                        0, // value
                        0, // index
                        null, // buffer
                        0, // length
                        0); // timeout

                Toast.makeText(
                        WhatWouldYouLike.this,
                        "controlTransfer(SET_CONTROL_LINE_STATE): " + usbResult,
                        Toast.LENGTH_LONG).show();

                // baud rate = 9600
                // 8 data bit
                // 1 stop bit
                byte[] encodingSetting = new byte[] { (byte) 0x80, 0x25, 0x00,
                        0x00, 0x00, 0x00, 0x08 };
                usbResult = usbDeviceConnection.controlTransfer(0x21, // requestType
                        RQSID_SET_LINE_CODING, // SET_LINE_CODING
                        0, // value
                        0, // index
                        encodingSetting, // buffer
                        7, // length
                        0); // timeout
               *//* Toast.makeText(MainActivity.this,
                        "controlTransfer(RQSID_SET_LINE_CODING): " + usbResult,
                        Toast.LENGTH_LONG).show();*//*

    *//*
    byte[] bytesHello = new byte[] { (byte) 'H', 'e', 'l', 'l',
      'o', ' ', 'f', 'r', 'o', 'm', ' ', 'A', 'n', 'd', 'r',
      'o', 'i', 'd' };
    usbResult = usbDeviceConnection.bulkTransfer(endpointOut,
      bytesHello, bytesHello.length, 0);
    Toast.makeText(MainActivity.this, "bulkTransfer: " + usbResult,
      Toast.LENGTH_LONG).show();
    *//*
            }

        } else {
            manager.requestPermission(deviceFound, mPermissionIntent);
            Toast.makeText(WhatWouldYouLike.this, "Permission: " + permitToRead,
                    Toast.LENGTH_LONG).show();
        //    textStatus.setText("Permission: " + permitToRead);
        }

        return success;
    }*/

   /* private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {

                Toast.makeText(WhatWouldYouLike.this, "ACTION_USB_PERMISSION",
                        Toast.LENGTH_LONG).show();
             //   textStatus.setText("ACTION_USB_PERMISSION");

                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent
                            .getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(
                            UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            connectUsb();
                        }
                    } else {
                        Toast.makeText(WhatWouldYouLike.this,
                                "permission denied for device " + device,
                                Toast.LENGTH_LONG).show();
                       *//* textStatus.setText("permission denied for device "
                                + device);*//*
                    }
                }
            }
        }
    };*/

  /*  private final BroadcastReceiver mUsbDeviceReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {

                deviceFound = (UsbDevice) intent
                        .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                Toast.makeText(
                        WhatWouldYouLike.this,
                        "ACTION_USB_DEVICE_ATTACHED: \n"
                                + deviceFound.toString(), Toast.LENGTH_LONG)
                        .show();
                *//*textStatus.setText("ACTION_USB_DEVICE_ATTACHED: \n"
                        + deviceFound.toString());*//*

                connectUsb();

            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {

                UsbDevice device = (UsbDevice) intent
                        .getParcelableExtra(UsbManager.EXTRA_DEVICE);

                Toast.makeText(WhatWouldYouLike.this,
                        "ACTION_USB_DEVICE_DETACHED: \n" + device.toString(),
                        Toast.LENGTH_LONG).show();
                *//*textStatus.setText("ACTION_USB_DEVICE_DETACHED: \n"
                        + device.toString());
*//*
                if (device != null) {
                    if (device == deviceFound) {
                        releaseUsb();
                    }else{
                        Toast.makeText(WhatWouldYouLike.this,
                                "device == deviceFound, no call releaseUsb()\n" +
                                        device.toString() + "\n" +
                                        deviceFound.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(WhatWouldYouLike.this,
                            "device == null, no call releaseUsb()", Toast.LENGTH_LONG).show();
                }


            }
        }

    };*/



    ///second

/*    @Override
    public void onResume() {
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(this, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            startService(startService);
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


 //    * This handler will be passed to UsbService. Data received from serial port is displayed through this handler

    static class MyHandler extends Handler {
        private final WeakReference<WhatWouldYouLike> mActivity;

        public MyHandler(WhatWouldYouLike activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    String data = (String) msg.obj;
                  //  mActivity.get().display.append(data);
                    //Toast.makeText(data,Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.CTS_CHANGE:
                    Toast.makeText(mActivity.get(), "CTS_CHANGE",Toast.LENGTH_LONG).show();
                    break;
                case UsbService.DSR_CHANGE:
                    Toast.makeText(mActivity.get(), "DSR_CHANGE",Toast.LENGTH_LONG).show();
                    break;
                case UsbService.SYNC_READ:
                    String buffer = (String) msg.obj;
                   // mActivity.get().display.append(buffer);
                    break;
            }
        }
    }*/
}