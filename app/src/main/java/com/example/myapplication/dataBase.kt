package com.example.myapplication

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dataBase :SQLiteOpenHelper{
    constructor(context: Context,name:String,factory: SQLiteDatabase.CursorFactory?,version:Int):super(
        context,
        name,
        factory,
        version
    )

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE tablel (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
   fun insertData(name:String,age:Int){
    var db=writableDatabase
       db.execSQL("INSERT INTO tablel (name,age) VALUES ('${name}','${age}')")
   }
    fun getAllData():Cursor{
        return readableDatabase.rawQuery("SELECT * FROM tablel",null)
    }
}