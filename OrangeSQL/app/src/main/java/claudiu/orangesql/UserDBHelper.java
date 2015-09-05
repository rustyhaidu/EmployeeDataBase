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
    private static final int DATABASE_VERSION = 2;
    private static final String CREATE_QUERY = "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME +
            " (" + UserContract.NewUserInfo.EMPLOYEE_NAME + " TEXT, "
            + UserContract.NewUserInfo.EMPLOYEE_SURNAME + " TEXT, "
            + UserContract.NewUserInfo.EMPLOYEE_GENDER + " TEXT,"
            + UserContract.NewUserInfo.EMPLOYEE_BIRTHDATE + " TEXT);";

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("Database opened", "database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("Database", "Table created!");
    }

    public void insertInfo(String name, String surname, String gender,String birthdate, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_NAME, name);
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_SURNAME, surname);
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_GENDER, gender);
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_BIRTHDATE, birthdate);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.i("Database operation", "one row inserted");
    }

    public Cursor selectInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.EMPLOYEE_NAME,
                                UserContract.NewUserInfo.EMPLOYEE_SURNAME,
                                UserContract.NewUserInfo.EMPLOYEE_GENDER,
                                UserContract.NewUserInfo.EMPLOYEE_BIRTHDATE
        };
        cursor = db.query(UserContract.NewUserInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }
    public Cursor getContact(String employeeName,SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {UserContract.NewUserInfo.EMPLOYEE_NAME,UserContract.NewUserInfo.EMPLOYEE_SURNAME,UserContract.NewUserInfo.EMPLOYEE_GENDER,UserContract.NewUserInfo.EMPLOYEE_BIRTHDATE};
        String selection = UserContract.NewUserInfo.EMPLOYEE_NAME +" LIKE ?";
        String[] selection_args = {employeeName};

        Cursor cursor = sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }
    public void deleteInformation(String employeeName,SQLiteDatabase sqLiteDatabase){
        String selection = UserContract.NewUserInfo.EMPLOYEE_NAME +" LIKE ?";
        String[] selection_args = {employeeName};

        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selection_args);

    }
    public int updateInformation(String old_name,String new_name, String new_surname, String new_gender,String new_birthdate,SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_NAME,new_name);
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_SURNAME,new_surname);
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_GENDER,new_gender);
        contentValues.put(UserContract.NewUserInfo.EMPLOYEE_BIRTHDATE,new_birthdate);

        String selection = UserContract.NewUserInfo.EMPLOYEE_NAME + " LIKE ?";
        String[] selection_args = {old_name};

       int count =  sqLiteDatabase.update(UserContract.NewUserInfo.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
