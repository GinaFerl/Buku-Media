package com.adityaputrak.bukumedia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaputrak.bukumedia.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.BukuAdapter;
import Adapter.BukuUserAdapter;
import Api.ApiClient;
import Model.DataBuku;
import Model.ListBukuResponse;
import Model.TotalSeluruhnya;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    BukuAdapter adapter;
    private List<DataBuku> mItems = new ArrayList<>();
    Button btrefresh;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
        recyclerView = findViewById(R.id.rvBuku);
        btrefresh = findViewById(R.id.btRefresh);
        tvTotal = findViewById(R.id.tvTotalQty);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BukuAdapter(mItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTotal.setText(String.valueOf("Rp. " +TotalSeluruhnya.TOTALAKHIR));
            }
        });

        readData();
    }

    private void readData() {
        ApiClient.getServices().getListBuku().enqueue(new Callback<ListBukuResponse>() {
            @Override
            public void onResponse(Call<ListBukuResponse> call, Response<ListBukuResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()==1) {
                        mItems = response. body().getDataBuku();
                        recyclerView.setAdapter(new BukuUserAdapter(mItems, getApplicationContext()));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListBukuResponse> call, Throwable t) {
                Toast.makeText(context, "gagal akses, cek koneksi", Toast.LENGTH_SHORT).show();

                Log.d("On Failure", t.getMessage());
            }
        });
    }
}