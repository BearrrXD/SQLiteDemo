package com.example.timmy.sample;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import sqlite.SQLiteHelper;


public class MainActivity extends ActionBarActivity {
    SQLiteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new SQLiteHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if      (id == R.id.action_settings)
        {
            return true;
        }
        else if (id == R.id.action_insert)
        {
            ContentValues cv = new ContentValues();
            cv.put("ItemName","DB_Insert");
            long dataCount = helper.Insert(SQLiteHelper.WORKMENU, null, cv);
        }
        else if (id == R.id.action_update)
        {
            String whereClause = "ItemName = ?";
            String[] whereArgs = {"DB_Insert"};
            ContentValues cv = new ContentValues();
            cv.put("ItemName","DB_Update");
            cv.put("ItemMemo","IsEdit");
            long dataCount = helper.Update(SQLiteHelper.WORKMENU,cv,whereClause,whereArgs);
        }
        else if (id == R.id.action_delete)
        {
            String whereClause = "ItemName = ?";
            String[] whereArgs = {"DB_Update"};
            int result = helper.Delete(SQLiteHelper.WORKMENU,whereClause,whereArgs);
        }
        else if (id == R.id.action_query)
        {
            String[] columns = {"ItemId" , "ItemName" , "ItemMemo"};
            String selection = "ItemName = ?";
            String[] selectionArgs = {"DB_Insert"};
            Cursor cursor = helper.Query(SQLiteHelper.WORKMENU,columns,
                    selection,selectionArgs,null,null,null,null);
            if (cursor!=null && cursor.getCount()>0)
            {
                cursor.moveToFirst();
                do
                {
                    String itemName = cursor.getString(cursor.getColumnIndex("ItemName"));
                    Log.d("Query",itemName);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }


        return super.onOptionsItemSelected(item);
    }
}
