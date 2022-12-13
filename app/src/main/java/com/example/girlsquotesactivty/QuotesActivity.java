package com.example.girlsquotesactivty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {
    TextView txtHeading;
    ImageView Backbtn;
    ArrayList<String> Category2;
    ArrayList<String> Quotes2;
    RecyclerView rcvQuotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        initView();
        listing();
    }

    private void listing() {
        rcvQuotes=findViewById(R.id.rcvQuotes);
        Category2 = (ArrayList<String>) getIntent().getSerializableExtra( "Categoty" );
        int position = getIntent().getIntExtra( "position", 0 );
        txtHeading.setText( Category2.get( position ) );
        DataSetModelClass quotesModelClass = new DataSetModelClass();
        Quotes2 = quotesModelClass.getList( position );
        String category=txtHeading.getText().toString();
        GirlsQuotesInterface girlsQuotesInterface=new GirlsQuotesInterface() {
            @Override
            public void onListClick(int position) {

            }

            @Override
            public void galleryClicker(int position, String Quotes2) {
                Intent i=new Intent(QuotesActivity.this,QuotesWithBeckground.class);
                i.putExtra("category",category);
                i.putExtra("quotes",Quotes2);
                startActivity(i);
            }
        };




        DataSetAdepter adepter=new DataSetAdepter(this,Quotes2,girlsQuotesInterface);
        rcvQuotes.setAdapter(adepter);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvQuotes.setLayoutManager(manager);


    }

    private void initView() {
            String name=getIntent().getStringExtra("name");
            txtHeading=findViewById(R.id.txtHeading);

        Backbtn=findViewById(R.id.Backbtn);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}