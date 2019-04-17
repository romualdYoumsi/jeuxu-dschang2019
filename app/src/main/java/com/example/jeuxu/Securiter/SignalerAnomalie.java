package com.example.jeuxu.Securiter;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.jeuxu.R;

public class SignalerAnomalie extends AppCompatActivity {

    private static final int SELECT_PICTURE =1 ;
    private String selectedImagePath;
    Spinner Spsexe;
    Button btn_send,btn_prendre_img;
    ImageView img_signaler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signaler_anomalie);

        btn_prendre_img=(Button)findViewById(R.id.btn_prendre_img);
        btn_send=(Button)findViewById(R.id.btn_send);
        img_signaler=(ImageView) findViewById(R.id.img_signaler);


        btn_prendre_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });



//        Spsexe = (Spinner) findViewById(R.id.spsexe);
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list_spinner_anomalie));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spsexe.setAdapter(myAdapter);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                 selectedImagePath = getPath(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
