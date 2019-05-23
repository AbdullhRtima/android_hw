package com.recyclerview.example;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private ArrayList<Product> poductList = new ArrayList<>();
    private ArrayList<Person> personList = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerviewAdapterPerson adapter;
    Context context = this;
    ImageView add;
    DbHelper dbHelper = new DbHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
//        adapter = new RecyclerviewAdapter(context, poductList);
        adapter = new RecyclerviewAdapterPerson(context, personList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                // Do not draw the divider
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        add = findViewById(R.id.add);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                long longNumber = dbHelper.insertProduct("New"
//                        , 0);
//
//                if (longNumber != -1) {
//                    Product product = new Product((int) longNumber
//                            , "New"
//                            , false);
//                    poductList.add(product);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long longNumber = dbHelper.insertPerson("ِabdullah_120150781"
                        );

                if (longNumber != -1) {
                    Person  person= new Person((int) longNumber
                            , "abdullah_120150781"
                           );
                    personList.add(person);
                    adapter.notifyDataSetChanged();
                }
            }
        });


//        praperData();

//        readFormDatabase();
        readFormDatabasePerson();


    }



    private void readFormDatabasePerson() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] project = {DbHelper.PersonId, DbHelper.PersonName };

        Cursor cursor = db.query(DbHelper.PersonTable, project, null, null, null, null, null);


        try {
            int idColumnIndex = cursor.getColumnIndex(DbHelper. PersonId);
            int nameColumnIndex = cursor.getColumnIndex(DbHelper. PersonName);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                 boolean temp = false;

                Person  person = new Person(cursor.getInt(idColumnIndex)
                        , cursor.getString(nameColumnIndex)
                       );
                 personList.add(person );
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
            adapter.notifyDataSetChanged();
        }
    }
//    private void praperData() {
//        poductList.clear();
//        String image = "https://images.pexels.com/photos/120049/pexels-photo-120049.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940";
//        poductList.add(new Product(0, "product#1", image, true));
//        poductList.add(new Product(1, "product#2", image, false));
//        poductList.add(new Product(2, "product#3", image, true));
//        poductList.add(new Product(3, "product#4", image, false));
//        poductList.add(new Product(4, "product#1", image, true));
//        poductList.add(new Product(5, "product#2", image, false));
//        poductList.add(new Product(6, "product#3", image, true));
//        poductList.add(new Product(7, "product#4", image, false));
//
//        adapter.notifyDataSetChanged();
//    }
}
