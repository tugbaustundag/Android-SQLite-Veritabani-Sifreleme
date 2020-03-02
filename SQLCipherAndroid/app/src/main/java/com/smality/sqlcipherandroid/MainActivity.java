package com.smality.sqlcipherandroid;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import net.sqlcipher.Cursor;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private ArrayList personList;
    private Button insertBtn;
    private Context context;
    private EditText nametxt,agetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this;
        nametxt=(EditText) findViewById(R.id.names);
        agetxt=(EditText) findViewById(R.id.ages);
        insertBtn = (Button) findViewById(R.id.btn_insert);
        listView = (ListView)findViewById(R.id.person_list);
        dbHelper = new DBHelper(this);
        personList = new ArrayList();
        adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, personList);
        listView.setAdapter(adapter);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=nametxt.getText().toString();
                String age=agetxt.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                dbHelper.insert(values);
            }
        });
        queryData();

        adapter.notifyDataSetChanged();
    }
    private ArrayList queryData() {
        personList.clear();
        Cursor c = dbHelper.query();
        while (c.moveToNext()){
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String name_age=name +"  "+ age +"  yaşında";
            personList.add(name_age);
        }
        return personList;
    }
}

