package com.example.trashcollector.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trashcollector.R;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SelectTrashtype extends AppCompatActivity {
TextView Plastic;
    public final String ACTION_USB_PERMISSION = "com.example.trashcollector.USB_PERMISSION";
    UsbManager usbManager;
    UsbDevice device;
    UsbSerialDevice serialPort;
    UsbDeviceConnection connection;
    TextView droptrash,trashtype,more,trashvalidate,sorry;
    private LinearLayout donemain,continuewithother;
    static String datastr="";
    private Button done;
    private String deviceconnectd="";
    UsbSerialInterface.UsbReadCallback mCallback = new UsbSerialInterface.UsbReadCallback() { //Defining a Callback which triggers whenever data is read.
        @Override
        public void onReceivedData(byte[] arg0) {
            Toast.makeText(SelectTrashtype.this, "Callback Received"+arg0, Toast.LENGTH_SHORT).show();
            String data = null;
            try {
                data = new String(arg0, "UTF-8");
                data.concat("/n");
                if (data.equalsIgnoreCase("TRASH_MATCHED")){
                    sorry.setVisibility(View.GONE);
                    droptrash.setVisibility(View.GONE);
                    trashtype.setVisibility(View.GONE);
                    more.setVisibility(View.VISIBLE);
                    trashvalidate.setVisibility(View.VISIBLE);
                    donemain.setVisibility(View.VISIBLE);
                    continuewithother.setVisibility(View.VISIBLE);
                }
               // tvAppend(textView, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Toast.makeText(SelectTrashtype.this, "Exception:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
    };
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { //Broadcast Receiver to automatically start and stop the Serial connection.
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_USB_PERMISSION)) {
                boolean granted = intent.getExtras().getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED);
                if (granted) {
                    connection = usbManager.openDevice(device);
                    serialPort = UsbSerialDevice.createUsbSerialDevice(device, connection);
                    if (serialPort != null) {
                        if (serialPort.open()) { //Set Serial Connection Parameters.
                            setUiEnabled(true);
                            serialPort.setBaudRate(9600);
                            serialPort.setDataBits(UsbSerialInterface.DATA_BITS_8);
                            serialPort.setStopBits(UsbSerialInterface.STOP_BITS_1);
                            serialPort.setParity(UsbSerialInterface.PARITY_NONE);
                            serialPort.setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF);
                            try {
                                serialPort.read(mCallback);
                                Toast.makeText(SelectTrashtype.this, "Read"+serialPort.read(mCallback), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(SelectTrashtype.this, "Exception in read:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                           // tvAppend(textView, "Serial Connection Opened!\n");
                            Toast.makeText(SelectTrashtype.this, "Serial port connected", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("SERIAL", "PORT NOT OPEN");
                        }
                    } else {
                        Log.d("SERIAL", "PORT IS NULL");
                    }
                } else {
                    Log.d("SERIAL", "PERM NOT GRANTED");
                }
            } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)) {
                //onClickStart(startButton);
                startservice();
            } else if (intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
                //onClickStop(stopButton);

            }
        }

        ;
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trashtype);
        Intent intent = getIntent();
        datastr = intent.getStringExtra("data");
        Log.v("getdata",datastr);
       // Plastic=findViewById(R.id.Plastic);
        setUiEnabled(false);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        registerReceiver(broadcastReceiver, filter);
        droptrash=findViewById(R.id.droptrash);
        trashtype=findViewById(R.id.trashtype);
        more=findViewById(R.id.more);
        done=findViewById(R.id.done);
        //display=(TextView) findViewById(R.id.display);
        trashvalidate=findViewById(R.id.trashvalidate);
        sorry=findViewById(R.id.sorry);
        continuewithother=findViewById(R.id.continuewithother);
        donemain=findViewById(R.id.donemain);


        droptrash.setVisibility(View.VISIBLE);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               senddata();
              /* if (usbService != null) {
                   Log.v("getdata1",datastr);
                   // if UsbService was correctly binded, Send data
                   usbService.write(datastr.getBytes());
               }*/
            }
        });
    }

    private void senddata() {
        serialPort.write(datastr.getBytes());

    }

    public void setUiEnabled(boolean bool) {
       // startButton.setEnabled(!bool);
        //sendButton.setEnabled(bool);
        //stopButton.setEnabled(bool);
        //textView.setEnabled(bool);

    }

    public void onClickStart(View view) {




    }

    /*public void onClickSend(View view) {
      //  String string = editText.getText().toString();
        serialPort.write(string.getBytes());
       // tvAppend(textView, "\nData Sent : " + string + "\n");

    }*/

    public void onClickStop(View view) {
        setUiEnabled(false);
        serialPort.close();
      //  tvAppend(textView,"\nSerial Connection Closed! \n");

    }

    public void onClickClear(View view) {
     //   textView.setText(" ");
    }

    private void tvAppend(TextView tv, CharSequence text) {
        final TextView ftv = tv;
        final CharSequence ftext = text;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ftv.append(ftext);
            }
        });
    }
  /*  public void PLasticButton(View view){
        Intent intent =new Intent(getApplicationContext(),StatusActivity.class);
        startActivity(intent);
    }*/
    private void startservice() {
        HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {
            boolean keep = true;
            for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                device = entry.getValue();
                int deviceVID = device.getVendorId();
                if (deviceVID == 1659)//Arduino Vendor ID
                {
                    PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
                    usbManager.requestPermission(device, pi);
                    keep = false;
                } else {
                    connection = null;
                    device = null;
                }

                if (!keep)
                    break;
            }
        }
    }
}