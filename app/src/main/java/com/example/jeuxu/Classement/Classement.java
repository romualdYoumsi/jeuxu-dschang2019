package com.example.jeuxu.Classement;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jeuxu.Classement.Adapter.ClassementAdapter;
import com.example.jeuxu.Classement.Classe.Classem;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;

import java.io.IOException;
import java.util.List;

public class Classement extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView lstdata;
    private SwipeRefreshLayout refresh;
    private ParseContent parseContent;
    private final int jsoncode = 1;
    private List<Classem> classemList;
    private  Exception exception;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        lstdata=(RecyclerView)findViewById(R.id.lst_class);
        refresh = (SwipeRefreshLayout) findViewById(R.id.recharger);
        refresh.setOnRefreshListener(this);
        parseContent = new ParseContent(this);
        parseJson();

    }

    public void display(List<Classem>classemList){
        ClassementAdapter classementAdapter=new ClassementAdapter(this,classemList);
        lstdata.setLayoutManager(new GridLayoutManager(this,1));
        lstdata.setAdapter(classementAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void parseJson(){
        if (!AndyUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }

        AndyUtils.showSimpleProgressDialog(this);
        new AsyncTask<Void,Void,String>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                exception=null;
            }

            @Override
            protected String doInBackground(Void... voids) {
                String response="";
                try{
                    HttpRequest req=new HttpRequest(AndyConstant.ServiceType.STATISTIQUEALL);
                    response=req.prepare(HttpRequest.Method.GET).sendAndReadString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return response;
            }

            @Override
            protected void onPostExecute(String result) {
                if (exception==null){
                    onTaskCompleted(result,jsoncode);
                }
                else {
                    Log.d("TAGONPOST",exception.getMessage());
                }
            }
        }.execute();
    }
    public void onTaskCompleted(String response,int serviceCode){
        switch (serviceCode){
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)){
                    classemList=parseContent.getInfosClass(response);
                    display(classemList);
                    Log.d("responsejsonclassemnt", response);
                }
                else {
                    Toast.makeText(this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }
    @Override
    public void onResume() {

        super.onResume();

    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                parseJson();
                refresh.setRefreshing(false);

            }
        }, 2000);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}
