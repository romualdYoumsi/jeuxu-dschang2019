package com.example.jeuxu.Profile;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Classe.Preference;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.MainActivity;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.DatabaseHelper;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;
import com.example.jeuxu.Utils.SharedPrefManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Profil extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_name, edt_email, edt_phoneNumber, edt_ville, edt_localiter;
    Button profilSave;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private Client client;
    private List<Client> clients=new ArrayList<>();
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_phoneNumber = (EditText) findViewById(R.id.edt_phoneNumber);
        edt_ville = (EditText) findViewById(R.id.edt_ville);
        edt_localiter = (EditText) findViewById(R.id.edt_localiter);
        profilSave = (Button) findViewById(R.id.profilSave);
        parseContent = new ParseContent(this);

        databaseHelper=new DatabaseHelper(this);
        chargedata();
        profilSave.setOnClickListener(this);

    }

    public void chargedata() {
            clients=databaseHelper.getData();
            Common.currentClient=clients.get(0);
        if (Common.currentClient != null) {
            edt_name.setText(Common.currentClient.getName());
            edt_email.setText(Common.currentClient.getEmail());
            edt_phoneNumber.setText(Common.currentClient.getDate_naissance());
            edt_ville.setText(Common.currentClient.getID_univercity());
            edt_localiter.setText(Common.currentClient.getLocalite());

        }
    }

    @Override
    public void onClick(View v) {
        if (v == profilSave) {

        }
    }

    private void parsejson() {
        if (!AndyUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(this);

        final String noms = edt_name.getText().toString();
        final String telephone = edt_phoneNumber.getText().toString();
        final String email = edt_email.getText().toString();
        final String localiter = edt_localiter.getText().toString();
        final String ville = edt_ville.getText().toString();

        new AsyncTask<Void, Void, String>() {


            @Override
            protected String doInBackground(Void... voids) {
                String response = "";
                HashMap<String, String> map = new HashMap<>();
                map.put("nom", noms);
                map.put("prenom", Common.currentClient.getSurname());
                map.put("sexe", Common.currentClient.getSexe());
                map.put("telephone", telephone);
                map.put("email", email);
                map.put("password", Common.currentClient.getPassword());
                map.put("ville", localiter);
                map.put("univercite", ville);

                try {
                    HttpRequest request = new HttpRequest(AndyConstant.ServiceType.CHANGE_PROFILS);
                    response = request.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();


                } catch (IOException e) {
                    response = e.getMessage();
                }
                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                onTaskCompleted(s, jsoncode);

            }
        }.execute();
    }

    private void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    client = parseContent.getInfoClient(response);
                    final Intent intent = new Intent(this, MainActivity.class);

                    Common.currentClient = client;

                    onPostExecute(intent);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();


                }

        }
    }

    private void onPostExecute(Intent intent) {
        Toast.makeText(this, "Changer ", Toast.LENGTH_LONG).show();

        SharedPrefManager.getInstance(this).userlogin(Common.currentClient.getName(), Common.currentClient.getEmail(), Common.currentClient.getDate_naissance()
                , Common.currentClient.getNom_lieux(), Common.currentClient.getSurname(), Common.currentClient.getID_univercity());
        startActivity(intent);
    }
}
