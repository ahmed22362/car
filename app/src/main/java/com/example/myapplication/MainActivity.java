 package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public  static final String CAE_KEY= "car_code";
    private RecyclerView rv;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private CarRVAdapter myAdapter;
    private DatabaseAccess db;
    private final  int ADD_REQ_CODE = 1;
    private final  int EDIT_REQ_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            fab = findViewById(R.id.floating_main_btn);
            rv = findViewById(R.id.main_recycler);
            toolbar = findViewById(R.id.main_toolBar);
            setSupportActionBar(toolbar);

                db = DatabaseAccess.getInstance(getBaseContext());
                db.open();
                ArrayList<Car> cars = db.getAllCars();
                db.close();
            myAdapter = new CarRVAdapter(cars, new OnRecyclerViewClickListener() {
                @Override
                public void onItemClickListener(int carID) {
                    Intent i = new Intent(getBaseContext() , ActivityCarLayout.class);
                    i.putExtra(CAE_KEY,carID);
                    startActivityForResult(i,EDIT_REQ_CODE);
                }
            });
                rv.setAdapter(myAdapter);
                RecyclerView.LayoutManager lm = new GridLayoutManager(this,2);
                rv.setLayoutManager(lm);
                rv.setHasFixedSize(true);

            fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ActivityCarLayout.class);
                startActivityForResult(i,ADD_REQ_CODE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_test,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(),"search submitted",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(),"text changed",Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(getBaseContext(),"search closed",Toast.LENGTH_SHORT).show();

                return false;
            }
        });
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_REQ_CODE && requestCode==RESULT_OK){
            db.open();
            ArrayList<Car> cars = db.getAllCars();
            db.close();
            myAdapter.setCars(cars);
            myAdapter.notifyDataSetChanged();
        }
    }
}
