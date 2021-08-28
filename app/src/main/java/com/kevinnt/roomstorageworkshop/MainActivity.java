package com.kevinnt.roomstorageworkshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private RecyclerView rv_cities;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        rv_cities = findViewById(R.id.rv_cities);
        db = AppDatabase.getInstance(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("flag", false);
                startActivity(intent);
            }
        });

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        adapter.setCities((ArrayList<CityEntity>) db.cityDao().getAllCities());
        rv_cities.setAdapter(adapter);
        rv_cities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}