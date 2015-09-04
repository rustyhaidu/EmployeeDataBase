package claudiu.orangesql;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by claudiu.haidu on 7/17/2015.
 */
public class RegistrationActivity extends Activity{

EditText ContactName,ContactMobile,ContactEmail;
Context context=this;
UserDBHelper userDBHelper;
SQLiteDatabase sqLiteDatabase;
TextView tvUIHelperObject;
    protected void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        setContentView(R.layout.registration);
        ContactName = (EditText) findViewById(R.id.etContactName);
        ContactMobile = (EditText) findViewById(R.id.etMobileNumber);
        ContactEmail = (EditText)findViewById(R.id.etEmailAddress);
       tvUIHelperObject=(TextView) findViewById(R.id.tvUIHelper);


    }
    public void insertContact(View v)
    {
        String name = ContactName.getText().toString();
        String mob = ContactMobile.getText().toString();
        String email= ContactEmail.getText().toString();
        userDBHelper = new UserDBHelper(context);
        sqLiteDatabase=userDBHelper.getWritableDatabase();
        userDBHelper.insertInfo(name, mob, email, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
       tvUIHelperObject.setText("Data Saved");
        userDBHelper.close();
    }


}
