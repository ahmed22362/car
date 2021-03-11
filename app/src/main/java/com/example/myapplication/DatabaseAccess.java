package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteDatabase myDB;
    private SQLiteOpenHelper openHelper ;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new MyDatabase(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.myDB = this.openHelper.getWritableDatabase();
    }
    public void close(){
        if(this.myDB!=null){
            this.myDB.close();
        }
    }
    public boolean insertCar(Car car){
        ContentValues values = new ContentValues();
        values.put(MyDatabase.CARS_CLN_MODEL, car.getModel());
        values.put(MyDatabase.CARS_CLN_COLOR, car.getColor());
        values.put(MyDatabase.CARS_CLN_IMAGE, car.getImg());
        values.put(MyDatabase.CARS_CLN_DESCRIPTION, car.getDescription());
        values.put(MyDatabase.CARS_CLN_DPL, car.getDPL());
        long result = myDB.insert(MyDatabase.DB_NAME , null,values);

        return result != -1;
    }

    public  boolean updateCar(Car car){
        ContentValues values = new ContentValues();
        values.put(MyDatabase.CARS_CLN_MODEL, car.getModel());
        values.put(MyDatabase.CARS_CLN_COLOR, car.getColor());
        values.put(MyDatabase.CARS_CLN_IMAGE, car.getImg());
        values.put(MyDatabase.CARS_CLN_DESCRIPTION, car.getDescription());
        values.put(MyDatabase.CARS_CLN_DPL, car.getDPL());
        String arg[] = {String.valueOf(car.getId())} ;

        long result = myDB.update(MyDatabase.DB_NAME,values,"id=?",arg);
        return  result > 0;
    }

    public  long getCatCount(Car car){
        return DatabaseUtils.queryNumEntries(myDB,MyDatabase.DB_NAME);
    }

    public boolean deleteCar(Car car){
        String arg[] = {String.valueOf(car.getId())};
        int result = myDB.delete(MyDatabase.DB_NAME,"id=?", arg);
        return result > 0 ;
    }

    public ArrayList<Car> getAllCars(){
        ArrayList<Car> cars = new ArrayList<>();

        Cursor c = myDB.rawQuery("SELECT * FROM "+MyDatabase.DB_NAME+" ",null );

        if (c !=null && c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(MyDatabase.CARS_CLN_ID));
                double dpl = c.getDouble((c.getColumnIndex(MyDatabase.CARS_CLN_DPL)));
                String color = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_COLOR));
                String model = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_MODEL));
                String description = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_DESCRIPTION));
                String img = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_IMAGE));

                Car car = new Car(id,model,color,description,img,dpl);
                cars.add(car);
            }while (c.moveToNext());
            c.close();
        }
        return cars;
    }

    public Car getCar(int carID){

        Cursor c = myDB.rawQuery("SELECT * FROM "+MyDatabase.DB_NAME+" WHERE "+MyDatabase.CARS_CLN_ID+"=?",new String[]{String.valueOf(carID)} );
        Car car;
        if (c !=null && c.moveToFirst()){
                int id = c.getInt(c.getColumnIndex(MyDatabase.CARS_CLN_ID));
                double dpl = c.getDouble((c.getColumnIndex(MyDatabase.CARS_CLN_DPL)));
                String color = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_COLOR));
                String model = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_MODEL));
                String description = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_DESCRIPTION));
                String img = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_IMAGE));
                car = new Car(id,model,color,description,img,dpl);
            c.close();

            Log.e("error","cool");

            return car;

        }
        Log.e("error",carID+"");
        Log.e("error","here");

        return null;
    }

    public ArrayList<Car> getSearchCar(String carSearch){
        ArrayList<Car> cars = new ArrayList<>();
        Cursor c =myDB.rawQuery("SELECT * FROM "+MyDatabase.DB_NAME+" WHERE "+MyDatabase.CARS_CLN_COLOR+" LIKE ? ",new String[]{carSearch+"%"});
        if (c!=null && c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(MyDatabase.CARS_CLN_ID));
                double dpl = c.getDouble((c.getColumnIndex(MyDatabase.CARS_CLN_DPL)));
                String color = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_COLOR));
                String model = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_MODEL));
                String description = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_DESCRIPTION));
                String img = c.getString(c.getColumnIndex(MyDatabase.CARS_CLN_IMAGE));

                Car car = new Car(id,color,img,description,model,dpl);
                cars.add(car);
            }while (c.moveToNext());
            c.close();
        }
        return cars;
    }
}
