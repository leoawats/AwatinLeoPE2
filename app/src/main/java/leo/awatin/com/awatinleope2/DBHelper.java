package leo.awatin.com.awatinleope2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String db_name = "Person.db";
    static final int ver = 1;
    static final String TABLE = "Info";

    public DBHelper(Context context) {
        super( context, db_name, null, ver );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE grade (ID INTERGER PRIMARY KEY AUTOINCREMENT, FullName TEXT, Age INTEGER, Gender TEXT)";
        db.execSQL( createTable );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable = "DROP TABLE IF EXISTS FullName";
        db.execSQL( deleteTable );
        onCreate( db );
    }

    public boolean save (String eFullName, int age, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( "FullName", eFullName );
        cv.put( "Age", age );
        cv.put( "Gender", gender );
        long insterted = db.insert(TABLE, null, cv);
        if ((insterted) == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Cursor populateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery( "SELECT * FROM Grade", null );
    }
}
