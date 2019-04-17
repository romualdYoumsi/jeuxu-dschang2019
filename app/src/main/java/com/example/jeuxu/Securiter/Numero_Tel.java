package com.example.jeuxu.Securiter;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jeuxu.R;
import com.example.jeuxu.Securiter.Adapter.SecuriterAdapter;
import com.example.jeuxu.Securiter.Classe.numero;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;

import java.io.IOException;
import java.util.List;

public class Numero_Tel extends AppCompatActivity {


    RecyclerView lst_numero;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private List<numero> numeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero__tel);
        setTitle("Numéros Utiles");
        lst_numero = (RecyclerView) findViewById(R.id.lst_numero);
        parseContent = new ParseContent(this);

       // parseJson();

    }


    private void display(List<numero> numeros) {
        SecuriterAdapter articleRevueAdapter = new SecuriterAdapter(this, numeros);
        lst_numero.setLayoutManager(new GridLayoutManager(this, 1));
        lst_numero.setAdapter(articleRevueAdapter);
    }

    private void parseJson() {
        if (!AndyUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(this);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                String response = "";
                try {
                    HttpRequest request = new HttpRequest(AndyConstant.ServiceType.SECURITER_NUMERO);
                    response = request.prepare(HttpRequest.Method.GET).sendAndReadString();
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
                    numeros = parseContent.getInfoNumero(response);
                    display(numeros);
                    Log.d("responsejson", response);


                } else {
                    Toast.makeText(this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }


    }
}
