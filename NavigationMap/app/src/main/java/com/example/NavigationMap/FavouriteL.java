package com.example.NavigationMap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class FavouriteL extends AppCompatActivity {


    EditText FavouriteLoc, TimeT, Rating;
    Button insert, delete, view;
    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_l);
        FavouriteLoc = findViewById(R.id.FavLoc);
        TimeT = findViewById(R.id.Travt);
        Rating = findViewById(R.id.LRat);

        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = FavouriteLoc.getText().toString();
                String contactTXT = TimeT.getText().toString();
                String dobTXT = Rating.getText().toString();
                UserHistory userHistory = new UserHistory();
                userHistory.TimTT = contactTXT;
                userHistory.Rte = dobTXT;
                userHistory.FavouriteLcc = nameTXT;
                DB.addUserHistory(userHistory);
                Toast.makeText(FavouriteL.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();

            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.DeleteUserHistory();
                Toast.makeText(FavouriteL.this, "History Deleted", Toast.LENGTH_SHORT).show();

            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserHistory> userHistories = DB.getUserHistory();
                if(userHistories.size()==0){
                    Toast.makeText(FavouriteL.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i <userHistories.size(); i++){
                    UserHistory userHistory = userHistories.get(i);

                    buffer.append("Favourite Location :"+ userHistory.FavouriteLcc +"\n");
                    buffer.append("Time Traveled H = :"+userHistory.TimTT+"\n");
                    buffer.append("Rating :"+userHistory.Rte+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(FavouriteL.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}