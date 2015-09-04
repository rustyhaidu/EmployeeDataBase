package claudiu.orangesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by claudiu.haidu on 7/16/2015.
 */
public class UserDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY = "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME +
            " (" + UserContract.NewUserInfo.USER_NAME + " TEXT, "
            + UserContract.NewUserInfo.USER_MOB + " TEXT, "
            + UserContract.NewUserInfo.USER_EMAIL + " TEXT);";

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Database opened", "database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("Database", "Table created!");
    }

    public void insertInfo(String name, String mob, String email, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB, mob);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL, email);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.i("Database operation", "one row inserted");
    }

    public Cursor selectInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.USER_NAME, UserContract.NewUserInfo.USER_MOB, UserContract.NewUserInfo.USER_EMAIL
        };
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }
    public Cursor getContact(String username,SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {UserContract.NewUserInfo.USER_MOB,UserContract.NewUserInfo.USER_EMAIL};
        String selection = UserContract.NewUserInfo.USER_NAME +" LIKE ?";
        String[] selection_args = {username};

        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }
    public void deleteInformation(String username,SQLiteDatabase sqLiteDatabase){
        String selection = UserContract.NewUserInfo.USER_NAME +" LIKE ?";
        String[] selection_args = {username};

        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selection_args);

    }
    public int updateInformation(String old_name,String new_name, String new_mobile, String new_email,SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,new_name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB,new_mobile);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,new_email);

        String selection = UserContract.NewUserInfo.USER_NAME + " LIKE ?";
        String[] selection_args = {old_name};

       int count =  sqLiteDatabase.update(UserContract.NewUserInfo.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
