package com.kevinnt.roomstorageworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private Button btn_add_new;
    private EditText et_city_name, et_image;
    private Intent intent;
    private AppDatabase db;
    private CityEntity city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = AppDatabase.getInstance(this);

        btn_add_new = findViewById(R.id.btn_add_new);
        et_city_name = findViewById(R.id.et_city_name);
        et_image = findViewById(R.id.et_image);
        intent = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(intent.getBooleanExtra("flag", false)) {
            city = intent.getParcelableExtra("city");
//            Toast.makeText(AddActivity.this, "MASUK UPDATE " + city.toString(), Toast.LENGTH_SHORT).show();
            et_city_name.setText(city.name);
            et_image.setText(city.image);
        }

        btn_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getBooleanExtra("flag", false)){
                    CityEntity newCity = city;
                    newCity.name = et_city_name.getText().toString();
                    newCity.image = et_image.getText().toString();
                    db.cityDao().updateCity(newCity);
                } else{
                    CityEntity newCity = new CityEntity();
                    newCity.id = 0;
                    newCity.name = et_city_name.getText().toString();
                    newCity.image = et_image.getText().toString();

                    db.cityDao().insertCity(newCity);
                }
                finish();
            }
        });


    }
}