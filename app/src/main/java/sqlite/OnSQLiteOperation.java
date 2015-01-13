package sqlite;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Timmy on 2015/1/13.
 */
public interface OnSQLiteOperation {

    long Insert(String table ,String nullColumnHack, ContentValues cv);
    int Delete(String table, String whereClause, String[] whereArgs);
    int Update(String table, ContentValues values, String whereClause, String[] whereArgs);
    Cursor Query(String table, String[] columns, String selection,
                 String[] selectionArgs, String groupBy, String having,
                 String orderBy, String limit);
}

