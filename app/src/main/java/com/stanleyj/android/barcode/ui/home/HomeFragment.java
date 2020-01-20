package com.stanleyj.android.barcode.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.stanleyj.android.barcode.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    EditText text;
    ImageView image, image2;
    Button button;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        text = root.findViewById(R.id.code);
        image = root.findViewById(R.id.Bar_code);
        image2 = root.findViewById(R.id.QR_code);
        button = root.findViewById(R.id.generate);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qrcode();
                    barcode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    private void qrcode() throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(text.getText().toString(), BarcodeFormat.QR_CODE, 250, 200);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        image2.setImageBitmap(bitmap);

    }

    private void barcode() throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(text.getText().toString(), BarcodeFormat.CODE_128, 250, 200);
        BarcodeEncoder  barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        image.setImageBitmap(bitmap);

    }
}