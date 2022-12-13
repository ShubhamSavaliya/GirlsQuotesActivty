package com.example.girlsquotesactivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> Category = new ArrayList<>();
    RecyclerView RecycleId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        intView();

    }

    private void intView() {
        RecycleId = findViewById(R.id.RecycleId);
        GirlsQuotesInterface girlsQuotesInterface = new GirlsQuotesInterface() {
            @Override
            public void onListClick(int position) {
                Intent intent = new Intent(MainActivity.this, QuotesActivity.class);
                intent.putExtra("Categoty", Category);
                intent.putExtra("position", position);

                startActivity(intent);

            }

            @Override
            public void galleryClicker(int position, String category) {

            }
        };


        GirlsQuotesAdapter girlsQuotesAdapter = new GirlsQuotesAdapter(this, Category, girlsQuotesInterface);
        RecycleId.setAdapter(girlsQuotesAdapter);


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecycleId.setLayoutManager(manager);
    }

    private void setData() {
        Category.add("Attitude");
        Category.add("Love");
        Category.add("Cool");
        Category.add("Friends");
        Category.add("Happiness");
        Category.add("Hurt");
        Category.add("Life");
        Category.add("Sad");
        Category.add("Success");
        Category.add("True");


    }
}