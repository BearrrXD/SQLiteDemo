package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Timmy on 2015/1/13.
 */
public class SQLiteHelper  extends SQLiteOpenHelper implements OnSQLiteOperation
{

    private static final String DATABASE_NAME = "TimmyWorks.db" ;
    private static final int DATABASE_VERSION = 1;

    final public static String WORKMENU  = "WorkMenu";
               				   				//資料庫版本
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(     "CREATE TABLE " + "WorkMenu" + " ("
                        + "ItemId"+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "ItemImage"+ " BLOB,"
                        + "ItemName"+ " VARCHER,"
                        + "ItemMemo" + " VARCHER);"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "WorkMenu");
        onCreate(db);
    }

    @Override
    public long Insert(String table, String nullColumnHack, ContentValues cv) {
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(table, nullColumnHack, cv);
        return result ;
    }

    @Override
    public int Delete(String table, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(table, whereClause , whereArgs);
        return result;
    }

    @Override
    public int Update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.update(table,values,whereClause,whereArgs);
        return result;
    }

    @Override
    public Cursor Query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor =
            db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy,limit);
        return  cursor;
    }
}
