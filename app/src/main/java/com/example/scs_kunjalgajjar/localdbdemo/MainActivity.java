package com.example.scs_kunjalgajjar.localdbdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
Button btn;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        createDB();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getData();
            }
        });
    }

    public void getData()
    {
        Cursor c=db.rawQuery("select * from customer where c_id=? or c_id=?",new String[]{"1","2"});
        c.moveToFirst();

        Toast.makeText(this,c.getString(c.getColumnIndex("c_name")).toString(),Toast.LENGTH_LONG).show();
        c.close();
    }
    public void createDB()
    {

        db=openOrCreateDatabase("myDB",MODE_ENABLE_WRITE_AHEAD_LOGGING,null);
        db.execSQL("create table if not exists customer(c_id integer primary key,c_name text)");
        db.execSQL("insert into customer values(5,'xyz')");
//        db.close();
        ContentValues cv= new ContentValues();
        cv.put("C_name","rohit");
        cv.put("c_id",5);
//        db.insert("customer",null,cv);
//        db.update("customer",cv,"c_id=?",new String[]{"5"});
//        db.delete("customer","c_id=? and c_name=?",new String[]{"5","rohit"});
//        db.query("customer",null,null,null,null,null,"asc");

        Toast.makeText(this,"Record inserted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }
}
