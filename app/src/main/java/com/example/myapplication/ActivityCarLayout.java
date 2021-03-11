package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityCarLayout extends AppCompatActivity {
    private ImageView car_imv;
    private TextInputEditText et_model, et_color , et_Dpl , et_description;
    private Toolbar car_toolbar;
    private FloatingActionButton car_fab;
    private final int PIC_IMG_REQ_CODE = 1;
    public int carID;
    private  Uri imageUri;
    DatabaseAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_layout);
        car_fab = findViewById(R.id.car_fBTN);

        car_toolbar = findViewById(R.id.carView_toolBar);
        setSupportActionBar(car_toolbar);

        car_imv = findViewById(R.id.car_img);
        et_color= findViewById(R.id.et_car_color);
        et_model= findViewById(R.id.et_car_model);
        et_Dpl= findViewById(R.id.et_car_dpl);
        et_description= findViewById(R.id.et_car_description);
        db = DatabaseAccess.getInstance(getBaseContext());

        Intent in = getIntent();
        carID = in.getIntExtra(MainActivity.CAE_KEY , -1);
        if (carID == -1){
            //add process
            enaAbleFields();
            clearFields();

        }else { //edit process
            disAbleFields();
            db.open();
            Car c = db.getCar(carID);
            db.close();
            if(c!=null){
                fillCarToField(c);
            }


        }
        car_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,PIC_IMG_REQ_CODE);
            }
        });
    }



    private void disAbleFields(){
        car_imv.setEnabled(false);
        et_color.setEnabled(false);
        et_model.setEnabled(false);
        et_description.setEnabled(false);
        et_Dpl.setEnabled(false);
    }
    private void enaAbleFields(){
        car_imv.setEnabled(true);
        et_color.setEnabled(true);
        et_model.setEnabled(true);
        et_description.setEnabled(true);
        et_Dpl.setEnabled(true);
    }
    private void clearFields(){
        car_imv.setImageURI(null);
        et_Dpl.setText("");
        et_color.setText("");
        et_model.setText("");
        et_description.setText("");
    }

    private void fillCarToField(Car c){
        et_description.setText(c.getDescription());
        et_Dpl.setText(String.valueOf(c.getDPL()));
        et_model.setText(c.getModel());
        et_color.setText(c.getColor());
        if(c.getImg()!= null && !c.getImg().equals(""))
            car_imv.setImageURI(Uri.parse(c.getImg()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_car_menu,menu);
        MenuItem save = menu.findItem(R.id.details_menu_save);
        MenuItem edit = menu.findItem(R.id.details_menu_edit);
        MenuItem delete = menu.findItem(R.id.details_menu_delete);
        if(carID == -1){
            //save process
            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);
        }else{
            //delete process
            save.setVisible(false);
            edit.setVisible(true);
            delete.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String model,description , color  ,image;
        double dpl;
        switch (item.getItemId()){
            case R.id.details_menu_save:
                    model = et_model.getText().toString();
                    description = et_description.getText().toString();
                    color = et_color.getText().toString();
                    dpl = Double.parseDouble( et_Dpl.getText().toString());
                    image = imageUri.toString();
                    Car c = new Car(model,color,description,image,dpl);
                    db.open();
                    boolean res = db.insertCar(c);
                    db.close();
                    if(res) {
                        Toast.makeText(getBaseContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                        setResult(4);
                    }
                return true;
            case R.id.details_menu_delete:
                return true;
            case R.id.details_menu_edit:
                return true;
        }
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PIC_IMG_REQ_CODE && requestCode== RESULT_OK){
            if(data.getData()!=null){
                imageUri = data.getData();
                car_imv.setImageURI(imageUri);}
        }
    }
}