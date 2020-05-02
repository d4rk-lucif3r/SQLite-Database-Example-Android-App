package com.lucifer.db_t_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Databasehelper mydb;
    EditText name,surname,marks,id;
    Button add,show,update,delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb =new Databasehelper(this);
        name =(EditText) findViewById(R.id.NAME_EDIT);
        surname =(EditText) findViewById(R.id.SUR_EDIT);
        marks =(EditText) findViewById(R.id.MARKS_EDIT);
        id =(EditText) findViewById(R.id.id_edit);
        add =(Button)findViewById(R.id.add);
        update =(Button)findViewById(R.id.update);
        show =(Button)findViewById(R.id.show);
        delete =(Button)findViewById(R.id.delete);

        adddata();
        viewall();
        updatedata();
        deletedata();

    }
    public void adddata(){
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
               boolean isInserted = mydb.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
            if(isInserted)
                Toast.makeText(MainActivity.this,"DATA INSERTED",Toast.LENGTH_SHORT);
            else
                Toast.makeText(MainActivity.this,"DATA Not INSERTED",Toast.LENGTH_SHORT);


            }
        });
    }
    public void updatedata(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdate = mydb.updatedata(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
               if(isupdate)
                   Toast.makeText(MainActivity.this,"DATA updated",Toast.LENGTH_SHORT);

               else
                   Toast.makeText(MainActivity.this,"DATA Not updated",Toast.LENGTH_SHORT);

            }
        });
    }
    public void deletedata(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isdelete = mydb.deletedata(id.getText().toString());
                if(isdelete>0)
                    Toast.makeText(MainActivity.this,"DATA deleted",Toast.LENGTH_SHORT);

                else
                    Toast.makeText(MainActivity.this,"DATA Not deleted",Toast.LENGTH_SHORT);

            }

        });
    }

    public void viewall(){
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res = mydb.getalldata();
               if(res.getCount() == 0){
                   showMessage("error","nothing found");
                   return;
               }
              StringBuffer buffer =new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("ID:"+res.getString(0)+"\n");
                   buffer.append("NAME:"+res.getString(1)+"\n");
                   buffer.append("SURNAME:"+res.getString(2)+"\n");
                   buffer.append("MARKS:"+res.getString(3)+"\n\n");

                   showMessage("Data",buffer.toString());



               }
            }
        });}
    public void showMessage(String title, String Message)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
builder.setCancelable(true);
builder.setTitle(title);
builder.setMessage(Message);
builder.show();
        }
    }

