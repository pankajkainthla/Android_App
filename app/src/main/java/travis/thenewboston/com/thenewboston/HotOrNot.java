package travis.thenewboston.com.thenewboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by pankaj on 22-Mar-15.
 */
public class HotOrNot {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";

    private static final String DATABASE_NAME = "HotorNotdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    public HotOrNot(Context ourContext) {
        this.ourContext = ourContext;
    }

    public HotOrNot open() throws SQLException {

        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();


        return this;

    }

    public void close() {

        ourHelper.close();

    }

    public long createEntry(String name, String hotness) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_HOTNESS, hotness);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);

    }

    public String getData() {

        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c;
        c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";


        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iHotness) + "\n";

        }


        return result;
    }

    public String getName(long l) {

        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c;
        c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        String result = "";
        if (c != null) {
            c.moveToFirst();
            result = c.getString(1);
        }


        return result;
    }

    public String getHotness(long l) {

        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};

        Cursor c;
        c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
        String result = "";
        if (c != null) {
            c.moveToFirst();
            result = c.getString(2);
        }


        return result;

    }

    public long updateEntry(long lRow, String mName, String mHotness) {

        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_HOTNESS, mHotness);
        return ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);


    }

    public void deleteEntry(long lRowDelete) {


        ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRowDelete, null);
    }


    private static class DbHelper extends SQLiteOpenHelper {


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);


        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL
                    ("CREATE TABLE " +
                            DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + KEY_NAME + " TEXT NOT NULL, "
                            + KEY_HOTNESS + " TEXT NOT NULL);");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
            onCreate(db);
        }
    }


}
