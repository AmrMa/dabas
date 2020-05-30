package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dataArr = ArrayList<String>()
    var db:dataBase ?=null
    var dataAdapter:ArrayAdapter<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= dataBase(this,"myDataBase.db",null,1)
        dataAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,dataArr)
        listView.adapter=dataAdapter
        readData()
        addData.setOnClickListener(){
            var name = nameEt.text.toString()
            var age = ageEt.text.toString().toInt()
            db!!.insertData(name,age)
            readData()
        }
    }
    fun readData(){
        var cursor = db!!.getAllData()

        if(cursor.moveToFirst()) {
            dataArr.clear()
            do {
                var str =
                    "id: ${cursor.getInt((cursor.getColumnIndex("id")))} | name: ${cursor.getString(
                        (cursor.getColumnIndex("name"))
                    )}  | age: ${cursor.getInt((cursor.getColumnIndex("age")))} "
                dataArr.add(str)
            } while (cursor.moveToNext())
            dataAdapter!!.notifyDataSetChanged()
        }
    }
}
