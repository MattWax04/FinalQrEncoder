package com.example.finalqrencoder;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class QRScanner2 extends AppCompatActivity {
    Button scan;
    int Points = 73;
    String[] Codes = {"1", "2", "3", "4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner2);
        scan = findViewById(R.id.scan);
        scan.setOnClickListener(v ->
        {
            scanCode();
        });

        TextView v = findViewById(R.id.pointsText);
        v.setText(Points + " Points");
    }


    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if (result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QRScanner2.this);
            builder.setTitle("Scan Results");
            builder.setMessage("Scan Verified");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }

        for (String code : Codes) {
            if (code.equals(result.getContents())) {
                Points++;
            }
            TextView v = findViewById(R.id.pointsText);
            v.setText(Points + " Points");


        }
    });
}