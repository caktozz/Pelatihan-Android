package com.baristand_training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTotalVoltage,textViewLowestVoltage,textViewTempBattery;
    private DatabaseReference databaseReference;
    String text_state_connect = "Connect";
    String text_state_disconnect = "Disconnect";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTotalVoltage    = (TextView) findViewById(R.id.text_totalVoltage);
        textViewLowestVoltage   = (TextView) findViewById(R.id.text_lowestVoltage);
        textViewTempBattery     = (TextView) findViewById(R.id.temp_battery);

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("monitoring")
                .child("1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String total_voltage    = snapshot.child("total_voltage").getValue().toString();
                String lowest_voltage   = snapshot.child("lowest_voltage").getValue().toString();
                String temp_battery     = snapshot.child("temp_battery").getValue().toString();
                String state            = snapshot.child("state").getValue().toString();

                textViewTotalVoltage.setText(total_voltage);
                textViewLowestVoltage.setText(lowest_voltage);
                textViewTempBattery.setText(temp_battery);

                Log.d(TAG, "Total" + total_voltage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}