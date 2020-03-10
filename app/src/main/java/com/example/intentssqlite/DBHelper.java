package com.example.intentssqlite ;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLInput;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    //We are supposing this database has one table. We define their attributes:
    public static final String TABLE_USERS = "users";
    public static final String USERS_ID ="id_user";
    public static final String USERS_NAME ="name";
    public static final String USERS_LAST_NAME ="last_name";
    public static final String USERS_DOB ="dob";
    public static final String USERS_ROLE ="role";
    public static final String USERS_SEMESTER ="semester";
    public static final String USERS_NICKNAME ="nickname";
    public static final String USERS_PASSWORD="password";


    //We name our database as follows:
    public static final String DATABASE_NAME = "mydatabase.db";

    //For this case, we put the CREATE sentences in Java Strings. We create two tables:

    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+TABLE_USERS+
            "("+USERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            USERS_NAME+" TEXT NOT NULL, "+
            USERS_LAST_NAME+" TEXT NOT NULL, "+
            USERS_DOB+" TEXT NOT NULL, "+
            USERS_ROLE+" TEXT NOT NULL, "+
            USERS_SEMESTER+" INTEGER NOT NULL, "+
            USERS_NICKNAME+" TEXT NOT NULL, "+
            USERS_PASSWORD+" TEXT NOT NULL);";


    //In constructor we create officially the database:
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //We create officially the table. If you had 10 tables, you will execute 10 SQL sentences.
        //Relationships between tables must be created here, with SQL Code.
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //For cleaning and updating processes, it is required to restart the database:

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }

    /*Now, it is important to define methods to modify and access the data in the database
    These operations will be useful to manipulate information saved in the database.
    It is recommended to define these methods for each table, or for each view report that must
    be obtained
    */

    //FIRST, we define INSERT sentences:

    public boolean insertUser(User user)
    {
        Integer id_user;
        String name;
        String last_name;
        String dob;
        String role;
        Integer semester;
        String nickname;
        String password;

        id_user = user.id_user;
        name = user.name;
        last_name = user.last_name;
        dob = user.dob;
        role = user.role;
        semester = user.semester;
        nickname = user.nickname;
        password = user.password;

        SQLiteDatabase database = this.getWritableDatabase(); // We obtain an instance of our database
        ContentValues contentValues = new ContentValues(); // We insert the data using one variable of type ContentValues
        contentValues.put("id_user", id_user);
        contentValues.put("name", name);
        contentValues.put("last_name", last_name);
        contentValues.put("dob", dob);
        contentValues.put("role", role);
        contentValues.put("semester", semester);
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);


        long ins = database.insert("users",null,contentValues); //We insert
        if(ins == -1){
            return false;
        }
        else{
            return true;
        }
    }



    //SECOND, we define SELECT sentences:

    public ArrayList<User> getAllUsers()
    {
        // This arraylist defines a personalized list containing objects of type "com.example.sqlite.User"
        // Managing the instance of Table as a Java Class allows to have control of
        // data from table.

        ArrayList<User> users_list = new ArrayList<User>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor result = database.rawQuery("SELECT * FROM "+TABLE_USERS, null);
        Log.e(null ,"SELECT * FROM "+TABLE_USERS);

        if(result.moveToFirst()) {
            Log.e(null, "We have data, OHH YEAAAH!!");
            while (result.moveToNext()) {
                User current_user = new User(
                        result.getInt(result.getColumnIndex(USERS_ID)),
                        result.getString(result.getColumnIndex(USERS_NAME)),
                        result.getString(result.getColumnIndex(USERS_LAST_NAME)),
                        result.getString(result.getColumnIndex(USERS_DOB)),
                        result.getString(result.getColumnIndex(USERS_ROLE)),
                        result.getInt(result.getColumnIndex(USERS_SEMESTER)),
                        result.getString(result.getColumnIndex(USERS_NICKNAME)),
                        result.getString(result.getColumnIndex(USERS_PASSWORD))
                );
                users_list.add(current_user);
            }
        }
        else
            Log.e(null, "Oh no, no data!!");

        return users_list;
    }


    public User getUserById(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_ID+" = "+id, null);
        User obtained_user = new User(result.getInt(result.getColumnIndex(USERS_ID)),
                result.getString(result.getColumnIndex(USERS_NAME)),
                result.getString(result.getColumnIndex(USERS_LAST_NAME)),
                result.getString(result.getColumnIndex(USERS_DOB)),
                result.getString(result.getColumnIndex(USERS_ROLE)),
                result.getInt(result.getColumnIndex(USERS_SEMESTER)),
                result.getString(result.getColumnIndex(USERS_NICKNAME)),
                result.getString(result.getColumnIndex(USERS_PASSWORD))
        );
        return obtained_user; //We return the Cursor with all data
    }



    //THIRD: We create functions to UPDATE the tables in our database:

    public boolean updateUser(Integer id, String name, String last_name, String dob, String role, Integer semester, String nickname, String password)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("last_name", last_name);
        contentValues.put("dob", dob);
        contentValues.put("role", role);
        contentValues.put("semester", semester);
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);
        database.update(TABLE_USERS, contentValues, "id = ?", new String[] {Integer.toString(id)});
        return true;
    }


    //Functions to count number of rows for each table. In our case, table primary keys are auto increment.

    public int numRowsUsers()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        int rows = (int) DatabaseUtils.queryNumEntries(database, TABLE_USERS);
        return rows;
    }

    //check if nickname exists

    public boolean checkNickname(String nickname){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where nickname=?", new String[]{nickname});
        if(cursor.getCount() > 0){
            return false;
        }
        else {
            return true;
        }
    }

    //checks the login within the database
    public boolean auth(String nickname, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where nickname=? and password=?", new String[]{nickname, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    //get user by nickname
    public User getUserByNickname(String nickname){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("Select * from users where nickname=?", new String[]{nickname});
        //
        result.moveToNext();
        User obtained_user = new User(
                result.getString(result.getColumnIndex(USERS_NAME)),
                result.getString(result.getColumnIndex(USERS_LAST_NAME)),
                result.getString(result.getColumnIndex(USERS_DOB)),
                result.getString(result.getColumnIndex(USERS_ROLE)),
                result.getInt(result.getColumnIndex(USERS_SEMESTER)),
                result.getString(result.getColumnIndex(USERS_NICKNAME)),
                result.getString(result.getColumnIndex(USERS_PASSWORD))
        );
        return obtained_user; //We return the Cursor with all data

    }


}
