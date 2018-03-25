package softwareengg.classroomvisualiser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DBImgHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Img.db";
    public static final String TABLE_NAME = "img_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "IMG";

    public DBImgHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY,IMG BLOB NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertImg(long id, Bitmap bm) {
        // Convert the image into byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] buffer=out.toByteArray();
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("ID", id);
            values.put("IMG", buffer);
            // Insert Row
            long result = db.insert(TABLE_NAME, null, values);
            Log.i("Insert", result + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();
            if(result == -1)
                return false;
            else
                return true;
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    public Bitmap getImg(long id) {
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();
        try
        {
            String selectQuery = "SELECT * FROM "+ TABLE_NAME+" WHERE id = " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Convert blob data to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex("IMG"));
                    // Convert the byte array to Bitmap
                    bitmap= BitmapFactory.decodeByteArray(blob, 0, blob.length);
                }
            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
        return bitmap;
    }

    public boolean updateImg(long id,Bitmap bm) {
        // Convert the image into byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] buffer=out.toByteArray();
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        try
        {
            ContentValues values = new ContentValues();
            values.put("IMG", buffer);
            // Insert Row
            long result = db.update(TABLE_NAME, values, "ID = " +id,null);
            Log.i("Insert", result + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();
            if(result == -1)
                return false;
            else
                return true;
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    public Integer deleteImg (long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = " +id,null);
    }
}