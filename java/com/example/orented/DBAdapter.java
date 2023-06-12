package com.example.orented;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "orentedDB.db";

    private static final String TABLE_VEHICLES = "vehicles";
    private static final String COLUMN_VID = "_vid";
    private static final String COLUMN_VNAME = "_vname";
    private static final String COLUMN_VMANUFACTURER = "_vmanufacturer";
    private static final String COLUMN_VCONTROL = "_vcontrol";
    private static final String COLUMN_VSEAT = "_vseat";
    private static final String COLUMN_VDOOR = "_vdoor";
    private static final String COLUMN_VRENT_PRICE = "_vrentprice";
    private static final String COLUMN_VOID = "_oid";
    private static final String COLUMN_VIS_AVAILABLE = "_visavailable";

    private static final String TABLE_USERS = "owners";
    private static final String COLUMN_UID = "_uid";
    private static final String COLUMN_UUSERNAME = "_uusername";
    private static final String COLUMN_UPASSWORD = "_upassword";
    private static final String COLUMN_UNAME = "_uname";
    private static final String COLUMN_UBIRTH_DATE = "_ubirthdate";
    private static final String COLUMN_UADDRESS = "_ucolumnaddress";
    private static final String COLUMN_UPHONE = "_uphone";
    private static final String COLUMN_UEMAIL = "_uemail";

    private static final String TABLE_SCHEDULES = "schedules";
    private static final String COLUMN_SID = "_sid";
    private static final String COLUMN_SPICK_UP_DATE = "_spickupdate";
    private static final String COLUMN_SPICK_UP_TIME = "_spickuptime";
    private static final String COLUMN_SPICK_UP_LOCATION = "_spickuplocation";
    private static final String COLUMN_SPAYMENT = "_spayment";
    private static final String COLUMN_SDURATION = "_sduration";
    private static final String COLUMN_SRID = "_srid";
    private static final String COLUMN_SVID = "_svid";
    private static final String COLUMN_SDRIVER_NAME = "_sdrivername";
    SQLiteDatabase db;

    public DBAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String[] query = new String[3];

        query[0] = "CREATE TABLE " + TABLE_VEHICLES + " (" +
                COLUMN_VID + " TEXT PRIMARY KEY, " +
                COLUMN_VNAME + " TEXT NOT NULL, " +
                COLUMN_VMANUFACTURER + " TEXT NOT NULL," +
                COLUMN_VCONTROL + " TEXT NOT NULL," +
                COLUMN_VSEAT + " INTEGER NOT NULL, " +
                COLUMN_VDOOR + " INTEGER NOT NULL, " +
                COLUMN_VRENT_PRICE + " INTEGER NOT NULL, " +
                COLUMN_VOID + " TEXT NOT NULL, " +
                COLUMN_VIS_AVAILABLE + " TEXT NOT NULL)";

        query[1] = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_UID + " TEXT PRIMARY KEY, " +
                COLUMN_UUSERNAME + " TEXT NOT NULL, " +
                COLUMN_UPASSWORD + " TEXT NOT NULL," +
                COLUMN_UNAME + " TEXT NOT NULL," +
                COLUMN_UBIRTH_DATE + " DATE NOT NULL, " +
                COLUMN_UADDRESS + " TEXT NOT NULL, " +
                COLUMN_UPHONE + " TEXT NOT NULL, " +
                COLUMN_UEMAIL + " TEXT NOT NULL)";

        query[2] = "CREATE TABLE " + TABLE_SCHEDULES + " (" +
                COLUMN_SID + " TEXT PRIMARY KEY, " +
                COLUMN_SPICK_UP_DATE + " DATE NOT NULL, " +
                COLUMN_SPICK_UP_TIME + " TEXT NOT NULL, " +
                COLUMN_SPICK_UP_LOCATION + " TEXT NOT NULL, " +
                COLUMN_SPAYMENT + " TEXT NOT NULL," +
                COLUMN_SDURATION + " INTEGER NOT NULL," +
                COLUMN_SRID + " DATE NOT NULL, " +
                COLUMN_SVID + " TEXT NOT NULL, " +
                COLUMN_SDRIVER_NAME + " TEXT NOT NULL)";

        for(int i = 0; i < query.length; i++){
            db.execSQL(query[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String[] query = new String[3];

        query[0] = "DROP TABLE IF EXISTS " + TABLE_VEHICLES;
        query[1] = "DROP TABLE IF EXISTS " + TABLE_USERS;
        query[2] = "DROP TABLE IF EXISTS " + TABLE_SCHEDULES;

        for(int i = 0; i < query.length; i++){
            db.execSQL(query[i]);
        }

        onCreate(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public void insertVehicles(Vehicle v){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_VID, v.getId());
        cv.put(COLUMN_VNAME, v.getName());
        cv.put(COLUMN_VMANUFACTURER, v.getManufacturer());
        cv.put(COLUMN_VCONTROL, v.getControl());
        cv.put(COLUMN_VSEAT, v.getSeat());
        cv.put(COLUMN_VDOOR, v.getDoor());
        cv.put(COLUMN_VRENT_PRICE, v.getRentPrice());
        cv.put(COLUMN_VOID, v.getOwner().getId());
        cv.put(COLUMN_VIS_AVAILABLE, v.checkAvailability());

        db.insert(TABLE_VEHICLES, null, cv);
    }

    public void insertUsers(User u){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_UID, u.getId());
        cv.put(COLUMN_UUSERNAME, u.getUsername());
        cv.put(COLUMN_UPASSWORD, u.getPassword());
        cv.put(COLUMN_UNAME, u.getName());
        cv.put(COLUMN_UBIRTH_DATE, u.getBirthDate());
        cv.put(COLUMN_UADDRESS, u.getAddress());
        cv.put(COLUMN_UPHONE, u.getPhone());
        cv.put(COLUMN_UEMAIL, u.getEmail());

        db.insert(TABLE_USERS, null, cv);
    }

    public void insertSchedule(Schedule s){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SID, s.getId());
        cv.put(COLUMN_SPICK_UP_DATE, s.getPickUpDate());
        cv.put(COLUMN_SPICK_UP_TIME, s.getPickUpTime());
        cv.put(COLUMN_SPICK_UP_LOCATION, s.getPickUpLocation());
        cv.put(COLUMN_SPAYMENT, s.getPayment());
        cv.put(COLUMN_SDURATION, s.getDuration());
        cv.put(COLUMN_SRID, s.getBorrower().getId());
        cv.put(COLUMN_SVID, s.getVehicle().getId());
        cv.put(COLUMN_SDRIVER_NAME, s.getDriver().getName());

        db.insert(TABLE_SCHEDULES, null, cv);
    }

    public Vehicle getVehicleById(String id){
        Vehicle vehicle = null;
        for(Vehicle v : getAllVehicle()){
            if(v.getId().equals(id)){
                vehicle = v;
            }
        }

        return vehicle;
    }

    public ArrayList<Vehicle> getAllVehicle(){
        String query = "SELECT * FROM " + TABLE_VEHICLES;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Vehicle> allVehicles = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                allVehicles.add(new Vehicle(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getString(3),
                        getUserById(cursor.getString(7)),
                        cursor.getInt(6)
                ));
            }
            while(cursor.moveToNext());

        }
        cursor.close();

        return allVehicles;
    }

    public User getUserById(String id){
        User user = null;
        for(User u : getAllUser()){
            if(u.getId().equals(id)){
                user = u;
                break;
            }
        }

        return user;
    }

    public ArrayList<User> getAllUser(){
        String query = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<User> allUsers = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                allUsers.add(new User(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            }
            while(cursor.moveToNext());

        }
        cursor.close();

        return allUsers;
    }

    public ArrayList<Schedule> getAllSchedule(){
        String query = "SELECT * FROM " + TABLE_SCHEDULES;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Schedule> allSchedules = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                allSchedules.add(new Schedule(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        getUserById(cursor.getString(6)),
                        getVehicleById(cursor.getString(7)),
                        new Driver("DDDDD", cursor.getString(8), 5.0f)
                ));
            }
            while(cursor.moveToNext());
        }

        return allSchedules;
    }

    /*
    public Cursor getAllContacts(){
        return db.query(TABLE_CONTACTS, new String[] {COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL}, null, null, null, null, null);
    }
    */

    /*
    public boolean updateContact(String id, String newName, String newEmail){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, newName);
        cv.put(COLUMN_EMAIL, newEmail);

        return db.update(TABLE_CONTACTS, cv, COLUMN_ID + " = \"" + id + "\"", null) > 0;
    }

    public boolean deleteContact(String name){
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_NAME + " = \"" + name + "\"";
        Cursor cursor = db.rawQuery(query, null);
        boolean result = false;

        if(cursor.moveToFirst()){
            String contactID = cursor.getString(0);
            db.delete(TABLE_CONTACTS, COLUMN_ID + " = ?", new String[] {contactID});
            result = true;
        }
        cursor.close();

        return result;
    }
    */
}