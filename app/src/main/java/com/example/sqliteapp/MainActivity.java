package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        SQLiteDatabase db=getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null); //создаем базу данных
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER);"); //выполняем SQL запрос: создать таблицу в базе данных
        db.execSQL("INSERT INTO users VALUES ('Tom Smith', 23);"); //вставляем в таблицу запись 1
        db.execSQL("INSERT INTO users VALUES('Jonh Dow', 31);"); ////вставляем в таблицу запись 2

        Cursor query=db.rawQuery("SELECT * FROM users;", null); //SQL запрос, данные из БД помещаются в объект Cursor
        TextView textView=findViewById(R.id.textView);
        if (query.moveToFirst()){ //проверка - переходим к первой записи, если метод вернул тру, значит мы что то получили из БД
            do {
              String name=query.getString(0);
              int age=query.getInt(1);
              textView.append("Name: "+ name + "Age: " + age + "\n");

            }
            while (query.moveToNext());

        }
        query.close();
        db.close();


    }
}
