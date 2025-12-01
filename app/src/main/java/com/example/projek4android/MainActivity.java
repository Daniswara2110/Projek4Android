package com.example.projek4android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    Button btnOpenUrl, btnDial, btnSendEmail, btnOpenMap, btnShare;
    EditText etUrl, etPhone, etLocation, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hubungkan dengan komponen di layout XML
        btnOpenUrl = findViewById(R.id.btnOpenUrl);
        btnDial = findViewById(R.id.btnDial);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        btnOpenMap = findViewById(R.id.btnOpenMap);
        btnShare = findViewById(R.id.btnShare);
        etUrl = findViewById(R.id.editTextUrl);
        etPhone = findViewById(R.id.editTextDial);
        etLocation = findViewById(R.id.editTextMap);
        etEmail = findViewById(R.id.editTextEmail);

        // Buka Website
        btnOpenUrl.setOnClickListener(v -> {
            String url = etUrl.getText().toString().trim();
            if (url.isEmpty()) {
                Toast.makeText(this, "Masukkan URL terlebih dahulu!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Tidak ada aplikasi browser!", Toast.LENGTH_SHORT).show();
            }
        });

        // Dial Nomor
        btnDial.setOnClickListener(v -> {
            String nomor = etPhone.getText().toString().trim();
            if (nomor.isEmpty()) {
                Toast.makeText(this, "Masukkan nomor telepon!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + nomor));
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Tidak ada aplikasi telepon!", Toast.LENGTH_SHORT).show();
            }
        });

        // Kirim Email
        btnSendEmail.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Masukkan alamat email!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + email));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Halo dari aplikasi saya");
            intent.putExtra(Intent.EXTRA_TEXT, "Pesan ini dikirim dari aplikasi Project5!");

            try {
                startActivity(Intent.createChooser(intent, "Kirim email menggunakan..."));
            } catch (Exception e) {
                Toast.makeText(this, "Tidak ada aplikasi email!", Toast.LENGTH_SHORT).show();
            }
        });

        // Lokasi
        btnOpenMap.setOnClickListener(v -> {
            String lokasi = etLocation.getText().toString().trim();
            if (lokasi.isEmpty()) {
                Toast.makeText(this, "Masukkan nama lokasi!", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(lokasi));
            Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Tidak ada aplikasi Maps!", Toast.LENGTH_SHORT).show();
            }
        });

        // Share Teks
        btnShare.setOnClickListener(v -> {
            String teks = "Coba aplikasi ini: https://example.com";
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, teks);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Bagikan melalui...");
            try {
                startActivity(shareIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Tidak ada aplikasi untuk share!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




