package com.example.crud_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab_add;
    private ListView list_view;
    private ListViewAdapter adapter;
    private ArrayList<dataMahasiswa> listMahasiswa = new ArrayList<>();
    private SQLiteHelper helper;
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab_add = findViewById(R.id.fab_add);
        list_view = findViewById(R.id.list_view);
        refresh = findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                menampilkanData();
            }
        });

        helper = new SQLiteHelper(this);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, inputActivity.class));
            }
        });

        menampilkanData();
    }

    private void menampilkanData(){
        listMahasiswa.clear();
        Cursor res = helper.getDataAll();
        refresh.setRefreshing(true);
        while (res.moveToNext()){
            String id = res.getString(0);
            String nama = res.getString(1);
            String fakultas = res.getString(2);
            String jurusan = res.getString(3);
            String semester = res.getString(4);

            listMahasiswa.add(new dataMahasiswa(
                    id,
                    nama,
                    fakultas,
                    jurusan,
                    semester));
        }

        adapter = new ListViewAdapter(listMahasiswa,MainActivity.this);
        list_view.setAdapter(adapter);
        refresh.setRefreshing(false);
    }

}