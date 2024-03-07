package com.coderfaysal.textrepeater;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

public class MainActivity extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = "7c0abeee-d6b8-43c5-8299-40a6f30d955e";

    EditText it_inputText, it_repet;
    CheckBox it_line;
    Button it_generate, it_reset;
    ImageView it_copy, it_share, it_profile;
    TextView it_Results;
    public static String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);


        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            OneSignal.getNotifications().requestPermission(true, Continue.with(r -> {
                if (r.isSuccess()) {
                    if (r.getData()) {
                    }
                    else {
                    }
                }
                else {
                }
            }));
        }

        it_inputText = findViewById(R.id.it_inputText);
        it_repet = findViewById(R.id.it_repet);
        it_line = findViewById(R.id.it_line);
        it_generate = findViewById(R.id.it_generate);
        it_reset = findViewById(R.id.it_reset);
        it_copy = findViewById(R.id.it_copy);
        it_share = findViewById(R.id.it_share);
        it_Results = findViewById(R.id.it_Results);
        it_profile = findViewById(R.id.it_profile);


        it_profile.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Profile.class));
        });


        it_reset.setOnClickListener(view -> {
            it_inputText.setText("");
            it_repet.setText("");
            it_Results.setText("");
        });


        it_generate.setOnClickListener(view -> {

            if (it_inputText.getText().toString().trim().length() > 0 && it_repet.getText().toString().trim().length() > 0){

                // String Looping
                String input = it_inputText.getText().toString();
                int number = Integer.parseInt(it_repet.getText().toString());

                for (int i=0; i < number; i++){
                    text = text + input + " \n";
                }
                it_Results.setText(text);


            } else {
                it_inputText.setError("Please Enter Text");
                it_repet.setError("Please Enter Text");
            }
            text = "";


        });


        it_copy.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("TextView", it_Results.getText().toString());
            assert clipboardManager!= null;
            clipboardManager.setPrimaryClip(clipData);

            Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
        });

        it_share.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "share app");
                String shareMassage = it_Results.getText().toString();
                intent.putExtra(Intent.EXTRA_TEXT, shareMassage);
                startActivity(Intent.createChooser(intent, "Share by"));
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Don't Have any Text", Toast.LENGTH_SHORT).show();
            }
        });






    }
}