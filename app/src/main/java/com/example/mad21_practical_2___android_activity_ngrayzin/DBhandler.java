package com.example.mad21_practical_2___android_activity_ngrayzin;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhandler extends SQLiteOpenHelper {

    public String name;
    public Integer id;
    public String description;
    public Boolean followed;
    private static DBhandler dbInstance;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "user";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public DBhandler(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    public static DBhandler getInstance() {
        return dbInstance == null ? dbInstance = new DBhandler(ListActivity.getInstance()) : dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME +
                " TEXT, " + COLUMN_DESC + " TEXT, "
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    boolean ahhhh;

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        ArrayList<User> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getInt(3) == 1) {
                    ahhhh = false;
                } else if (cursor.getInt(3) == 0) {
                    ahhhh = true;
                }
                list.add(new User(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(0),
                        ahhhh));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void updateUser(User user) {
        boolean x = user.followed;
        Integer num = x ? 0 : 1;
        String y = user.name;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " UPDATE " + TABLE_USERS + " SET " + COLUMN_FOLLOWED + " = " + num + " WHERE " + COLUMN_NAME + " = \""
                + y + "\"";
        db.execSQL(query);
    }
    Boolean x;
    public User findUser(String name) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_NAME
                + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                if(cursor.getInt(3) == 0){
                    x = true;
                }
                else if(cursor.getInt(3) == 1){
                    x = false;
                }
                User john = new User(cursor.getString(1), cursor.getString(2), cursor.getInt(0), x);
                cursor.close();
                return john;
            }
        return null;
    }
    /*public void updateUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, user.id);
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_DESC, user.description);
        values.put(COLUMN_FOLLOWED, user.followed? 1:0);

        String follow = user.followed? "1":"0";

        db.update(TABLE_USERS, values, " id = ?", new String[]{ follow } );


    }*/

        public void addUsers(String Name, String Description, Integer ID, Integer Followed){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, ID);
            values.put(COLUMN_NAME, Name);
            values.put(COLUMN_DESC, Description);
            values.put(COLUMN_FOLLOWED, Followed);
            db.insert(TABLE_USERS, null, values);
        }

}

