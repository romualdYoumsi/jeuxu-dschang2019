package com.example.jeuxu.Actualites;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import android.support.v4.widget.SwipeRefreshLayout;
import com.example.jeuxu.Actualites.Adapter.ArticleRevueAdapter;
import com.example.jeuxu.Actualites.Classes.Article_revue;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.AndyConstant;
import com.example.jeuxu.Utils.AndyUtils;
import com.example.jeuxu.Utils.HttpRequest;
import com.example.jeuxu.Utils.ParseContent;

import java.io.IOException;
import java.util.List;

public class ArticleRevue extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refresh;
    RecyclerView lstArticleRevue;
    private ParseContent parseContent;
    private final int jsoncode=1;
    private List<Article_revue>articleRevues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_revue);



        setTitle("Articles et revues");
        lstArticleRevue=(RecyclerView)findViewById(R.id.lstArticleRevue);
        parseContent=new ParseContent(this);
        refresh=(SwipeRefreshLayout)findViewById(R.id.recharger_article);
        refresh.setOnRefreshListener(this);
        parseJson();




    }
    private void display(List<Article_revue> article_revues){
        ArticleRevueAdapter articleRevueAdapter=new ArticleRevueAdapter(this,article_revues);
        lstArticleRevue.setLayoutManager(new GridLayoutManager(this,1));
        lstArticleRevue.setAdapter(articleRevueAdapter);
    }

    private void parseJson() {
        if (!AndyUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(this);
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {

                String response="";
                try {
                    HttpRequest request=new HttpRequest(AndyConstant.ServiceType.ARTICLE_REVUE);
                    response=request.prepare(HttpRequest.Method.GET).sendAndReadString();
                } catch (IOException e) {
                    response= e.getMessage();
                }

                return response;
            }

            @Override
            protected void onPostExecute(String s) {
                onTaskCompleted(s,jsoncode);
            }
        }.execute();

    }

    private void onTaskCompleted(String response, int serviceCode) {
        switch (serviceCode) {
            case jsoncode:
                AndyUtils.removeSimpleProgressDialog();
                if (parseContent.isSuccess(response)) {
                    articleRevues = parseContent.getInfoArticle(response);
                    display(articleRevues);
                    Log.d("responsejson", response);


                } else {
                    Toast.makeText(this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }

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


}
