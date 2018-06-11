package com.pawar.makrand.learncodeonline;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pawar.makrand.learncodeonline.adapters.MainActivityRecyclerViewAdapter;
import com.pawar.makrand.learncodeonline.retrofit.factory.RetrofitServiceFactory;
import com.pawar.makrand.learncodeonline.retrofit.services.DataStructuresService;
import com.pawar.makrand.learncodeonline.retrofit.utils.DataStructureResponse;

import java.util.LinkedHashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mainActivityRexyclerView;
    private ImageView mainActivityBanner;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar_cyclic);

        Toolbar myToolbar = findViewById(R.id.mainActivityAppBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Learn Code Online");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mainActivityBanner = findViewById(R.id.mainActivityBanner);
        mainActivityRexyclerView = findViewById(R.id.mainActivityRecyclerView);
        mainActivityRexyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainActivityRexyclerView.setHasFixedSize(false);

        mainActivityBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.learncodeonline.in"));
                if (null != intent.resolveActivity(getPackageManager()))
                    startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {
        Retrofit dsRetrofit = RetrofitServiceFactory.getInstanceforDS();

        DataStructuresService dataStructuresService = dsRetrofit.create(DataStructuresService.class);

        dataStructuresService.getDSData().enqueue(new Callback<DataStructureResponse>() {
            @Override
            public void onResponse(Call<DataStructureResponse> call, Response<DataStructureResponse> response) {
                mainActivityRexyclerView.setAdapter(new MainActivityRecyclerViewAdapter(MainActivity.this, removeDuplicates(response.body().questions)));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DataStructureResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error! Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<DataStructureResponse.QuestionAnswer> removeDuplicates(List<DataStructureResponse.QuestionAnswer> questions) {
        LinkedHashSet<DataStructureResponse.QuestionAnswer> hs = new LinkedHashSet<>(questions);
        questions.clear();
        questions.addAll(hs);
        return questions;
    }
}
