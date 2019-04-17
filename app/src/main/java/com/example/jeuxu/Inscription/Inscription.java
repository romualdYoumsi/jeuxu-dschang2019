package com.example.jeuxu.Inscription;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.MainActivity;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;
import com.example.jeuxu.Utils.SharedPrefManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class Inscription extends AppCompatActivity {

    private Button btn_connexion;
    EditText edt_name,edt_passwd;
    private ParseContent parseContent;
    private ProgressDialog pgd;
    private final int jsoncode = 1;
    private String passwd,name;
    private Client client;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        tvLogin     = (TextView) findViewById(R.id.tvLogin);

        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_passwd=(EditText)findViewById(R.id.edt_passwd);


        parseContent=new ParseContent(this);
        pgd=new ProgressDialog(this);
        pgd.setMessage("Chargement...");

        setTitle("Connexion");

        btn_connexion=(Button)findViewById(R.id.connexion);

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ConnectAccount", "Connection user");
                name=edt_name.getText().toString().trim();
                passwd=edt_passwd.getText().toString().trim();
                if (!name.isEmpty() && !passwd.isEmpty()){
                    parseJson();
                }
                else {
                    Toast.makeText(Inscription.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void parseJson() {
        if (!AndyUtils.isNetworkAvailable(Inscription.this)) {
            Toast.makeText(Inscription.this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(Inscription.this);
        final String email=edt_name.getText().toString();
        final String password=edt_passwd.getText().toString();

        new AsyncTask<Void,Void,String>(){
            //contient le code à executer en tache de fond
            @Override
            protected String doInBackground(Void... voids) {
                String response="";
                HashMap<String,String> map =new HashMap<>();
                map.put("email",email);
                map.put("password",password);
                try{
                    HttpRequest req=new HttpRequest(AndyConstant.ServiceType.LOGIN);
                    response=req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (IOException e) {
                    response= e.getMessage();
                }
                return response;
            }
            //ce qui dois etre fait apres ke doInBackground
            protected void onPostExecute(String result){
                Log.d("news",result);
                onTaskCompleted(result,jsoncode);
            }
        }.execute();
    }
    private void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson",response.toString());
        switch (serviceCode){
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)){
                    client=parseContent.getInfoClient(response);
                    final Intent intent =new Intent(this,MainActivity.class);
                    Gson gson=new Gson();
                    String myJson=gson.toJson(client);
                    intent.putExtra("user",myJson);
                    intent.putExtra("data",response);
                    Log.d("responsejsonn",response.toString());
                    Common.currentClient=null;
                    Common.currentClient=client;
                    onPostExecute(response,intent);
                    SharedPrefManager.getInstance(this).isLoggedIn();

                }
                else{
                    Toast.makeText(Inscription.this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }

        }
    }

    private void onPostExecute(String response, Intent intent) {
        try {
            JSONObject jo=new JSONObject(response);
            JSONObject data=jo.getJSONObject("data");
            name = data.getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(Inscription.this,"Bienvenue "+name.toString(),Toast.LENGTH_LONG).show();
        startActivity(intent);
    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onClick(View v) {
//        if (v==btRegister){
//            Intent intent   = new Intent(Inscription.this,RegisterActivity.class);
//            Pair[] pairs    = new Pair[1];
//            pairs[0] = new Pair<View,String>(tvLogin,"tvLogin");
//            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Inscription.this,pairs);
//            startActivity(intent,activityOptions.toBundle());
//        }
//    }
}
