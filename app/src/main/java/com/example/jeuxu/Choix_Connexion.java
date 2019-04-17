package com.example.jeuxu;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeuxu.AdapterClass.PLaceAutoCompleteAdapter;
import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Classe.ExpandableListAdapter;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Inscription.Fragment_inscription.Inscription_three;
import com.example.jeuxu.Inscription.Inscription;
import com.example.jeuxu.Inscription.RegisterActivity;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;
import com.example.jeuxu.Utils.SharedPrefManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;

public class Choix_Connexion extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    Button btn_connexion, btn_inscription,btn_multilangue;
    private SignInButton signIn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 1000;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_choix__connexion);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
            return;
        }

        btn_connexion = (Button) findViewById(R.id.btn_connexion);
        btn_inscription = (Button) findViewById(R.id.btn_inscription);
        signIn = (SignInButton) findViewById(R.id.btn_login_google);
        btn_multilangue=(Button)findViewById(R.id.btn_multilangue);

        btn_multilangue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

        parseContent =new ParseContent(this);
        signIn.setOnClickListener(this);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Choix_Connexion.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Choix_Connexion.this, Inscription.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_google:
                signIn();
                break;
        }

    }

    private void signIn() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(i, REQ_CODE);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String name = account.getFamilyName();
            String email = account.getEmail();
            String Surname = account.getDisplayName();
            Client cl = new Client();
            Common.currentClient.setName(name);
            Common.currentClient.setEmail(email);
            Common.currentClient.setSurname(Surname);
            cl.setName(name);
            cl.setEmail(email);
            cl.setSurname(Surname);

            Common.currentClient = cl;
            updateUI(true);

        } else {
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin) {
        if (isLogin) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Choix_Connexion.this);
            builder.setTitle(R.string.info_supplementaire);

            LayoutInflater inflater = this.getLayoutInflater();
            View supplementaire = inflater.inflate(R.layout.supplementaire, null);
            final Spinner mySpinner=(Spinner)supplementaire.findViewById(R.id.spinnerList);
            ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(Choix_Connexion.this,
                    android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.list_spinner_sexe));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);

            final ShowHidePasswordEditText edt_passwd=(ShowHidePasswordEditText)supplementaire.findViewById(R.id.edt_passwd);
            final ShowHidePasswordEditText edt_conf_passwd=(ShowHidePasswordEditText)supplementaire.findViewById(R.id.edt_conf_passwd);


            final MaterialEditText edt_provenance = (MaterialEditText) supplementaire.findViewById(R.id.edt_provenance);
            final MaterialEditText edt_proven = (MaterialEditText) supplementaire.findViewById(R.id.edt_proven);
            final MaterialEditText edt_date = (MaterialEditText) supplementaire.findViewById(R.id.edt_date);

            Button btn_terminer = (Button) supplementaire.findViewById(R.id.btn_terminer);


            builder.setView(supplementaire);

            final AlertDialog dialog = builder.create();
            btn_terminer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (TextUtils.isEmpty(edt_proven.getText().toString())) {
                        Toast.makeText(Choix_Connexion.this, R.string.entrer_votre_nom, Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if (TextUtils.isEmpty(edt_provenance.getText().toString())) {

                        Toast.makeText(Choix_Connexion.this, R.string.entrer_provenance, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edt_passwd.getText().toString())) {

                        Toast.makeText(Choix_Connexion.this, R.string.entrer_passe, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(edt_conf_passwd.getText().toString())) {

                        Toast.makeText(Choix_Connexion.this, R.string.confirmer_passe, Toast.LENGTH_SHORT).show();
                        return;
                    }


                    final android.app.AlertDialog waitingDialog = new SpotsDialog(Choix_Connexion.this);
                    waitingDialog.show();
                    waitingDialog.setMessage(getString(R.string.verification));
                    if (!AndyUtils.isNetworkAvailable(Choix_Connexion.this)) {
                        Toast.makeText(Choix_Connexion.this, R.string.internet_is_required, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AndyUtils.showSimpleProgressDialog(Choix_Connexion.this);

                    final String sexe=mySpinner.getSelectedItem().toString();
                    final String password=edt_passwd.getText().toString();
                    final String ville=edt_proven.getText().toString();
                    final String universiter=edt_provenance.getText().toString();
                    final Intent intent = new Intent(Choix_Connexion.this, MainActivity.class);
                    Gson gson = new Gson();
                    String myJson = gson.toJson(client);
                    Common.currentClient.setSexe(sexe);
                    Common.currentClient.setPassword(password);
                    Common.currentClient.setNom_lieux(ville);
                    Common.currentClient.setID_univercity(universiter);
                    startActivity(intent);
                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... voids) {
                            String response = "";
                            HashMap<String, String> map = new HashMap<>();
                            map.put("name", Common.currentClient.getName());
                            map.put("surname", Common.currentClient.getSurname());
                            map.put("sexe", sexe);
                            map.put("date_naissance", Common.currentClient.getDate_naissance());
                            map.put("email", Common.currentClient.getEmail());
                            map.put("password",password);
                            map.put("nom_lieu",ville );
                            map.put("id_univercity", universiter);
                            try {
                                HttpRequest request = new HttpRequest(AndyConstant.ServiceType.REGISTER);
                                response = request.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                            } catch (IOException e) {
                                response = e.getMessage();
                            }
                            return response;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            Log.d("REGISTER_TWO", s);
                            onTaskCompleted(s,jsoncode);
                            SharedPrefManager.getInstance(Choix_Connexion.this).userlogin(Common.currentClient.getName(),Common.currentClient.getEmail(),Common.currentClient.getDate_naissance()
                                    ,ville,Common.currentClient.getSurname(),universiter);

                        }
                    }.execute();

                }
            });
            dialog.show();


        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }


    private void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    client = parseContent.getInfoClient(response);
                    final Intent intent = new Intent(Choix_Connexion.this, Inscription_three.class);
                    Gson gson = new Gson();
                    String myJson = gson.toJson(client);
                    intent.putExtra("user", myJson);

                    //onPostExecute(intent);
                    startActivity(intent);
                } else {
                    Toast.makeText(Choix_Connexion.this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();

                }

        }
    }
    private void showChangeLanguageDialog(){
        final String[] itemslist={"French","English"};
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(Choix_Connexion.this);
        mBuilder.setTitle("Choose Languague...");
        mBuilder.setSingleChoiceItems(itemslist, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    setLocal("fr");
                    recreate();
                }
                else if (which==1){
                    setLocal("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog=mBuilder.create();
        mDialog.show();

    }

    private void setLocal(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String Language=preferences.getString("My_Lang","");
        setLocal(Language);
    }

    private void onPostExecute(Intent intent) {
        Toast.makeText(Choix_Connexion.this, "Bienvenue " + Common.currentClient.getName(), Toast.LENGTH_LONG).show();
    }
}
