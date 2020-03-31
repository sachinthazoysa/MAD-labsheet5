package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.btnStartBroadcast);
        txt = findViewById(R.id.txtViewMsg);
    }
    protected void onStart() {
        super.onStart();
        localLisetner = new Reciever();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener,intentFilter);
    }

    protected void onStop(){
        super.onStop();
        this.unregisterReceiver(localListener);
    }

    public void onClick(View view){
        if(view.getId()==R.id.btnStartBroadcast){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }
    
    
     public class Reciever extends BroadcastReceiver{
         @Override
         public void onReceive(Context context, Intent intent) {
             String currentText=txtViewMsg.getText().toString();
             String message = intent.getStringExtra("value");
             currentText += "\nRecieved " +message;
             txtViewMsg.setText(currentText);
         }
     }
    

}
