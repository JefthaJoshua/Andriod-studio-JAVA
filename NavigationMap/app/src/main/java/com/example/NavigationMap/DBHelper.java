package com.example.NavigationMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(@Nullable Context context){
        super(context, "Userdata.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table UserHistory(" +
                "ID INTEGER primary key autoincrement," +
                "UserID Int," +
                "Rte TEXT, " +
                "TimTT TEXT, " +
                "FavouriteLcc TEXT, " +
                " FOREIGN KEY (UserID) REFERENCES User(ID))");

        db.execSQL("create Table UserLoggedIn(" +
                "ID INTEGER primary key autoincrement," +
                "UserID INTEGER," +
                " FOREIGN KEY (UserID) REFERENCES User(ID))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists User");
        db.execSQL("drop Table if exists UserHistory");
        db.execSQL("drop Table if exists UserLoggedIn");
    }


    public void logOut(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("UserLoggedIn", null, null);
    }

    public void addUserHistory(UserHistory userHistory){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM UserLoggedIn", null);
        int Id = 0;
        if (c.moveToFirst()){
            do {
                Id = c.getInt(1);
            } while(c.moveToNext());
        }
        if(Id >0){
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("TimTT", userHistory.TimTT);
            contentValues.put("Rte", userHistory.Rte);
            contentValues.put("FavouriteLcc", userHistory.FavouriteLcc);
            contentValues.put("UserID", Id);
            db.insert("UserHistory", null, contentValues);
        }
    }


    public void DeleteUserHistory ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete("UserHistory", null, null);
    }

    public List<UserHistory> getUserHistory()
    {
        List<UserHistory> userHistories = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM UserLoggedIn", null);
        int Id = 0;
        if (c.moveToFirst()){
            do {
                Id = c.getInt(1);
            } while(c.moveToNext());
        }
        if(Id >0){
            c = db.rawQuery("SELECT Rte,TimTT, FavouriteLcc  FROM UserHistory Where UserId = '" + Id + "'", null);
            while(c.moveToNext()) {
                c.getString(0);
                UserHistory userHistory = new UserHistory();
                // Passing values
                userHistory.Rte = c.getString(0);
                userHistory.TimTT = c.getString(1);
                userHistory.FavouriteLcc = c.getString(2);
                userHistories.add(userHistory);

            }
        }
        return userHistories;
    }

}
