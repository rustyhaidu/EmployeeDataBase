package claudiu.orangesql;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by claudiu.haidu on 7/17/2015.
 */
public class RegistrationActivity extends Activity{

EditText EmployeetName,EmployeeSurname,EmployeeBirthdate;
    ToggleButton EmployeeGender;
Context context=this;
UserDBHelper userDBHelper;
SQLiteDatabase sqLiteDatabase;
TextView tvUIHelperObject;
    protected void onCreate(Bundle instance)
    {
        super.onCreate(instance);
        setContentView(R.layout.registration);
        EmployeetName = (EditText) findViewById(R.id.etEmployeeName);
        EmployeeSurname = (EditText) findViewById(R.id.etEmployeeSurname);
        //EmployeeGender = (EditText)findViewById(R.id.etEmployeeGender);

        EmployeeGender = (ToggleButton)findViewById(R.id.toggleSex);
        EmployeeBirthdate = (EditText)findViewById(R.id.etEmployeeBirthdate);
       tvUIHelperObject=(TextView) findViewById(R.id.tvUIHelper);


    }
    public void insertContact(View v)
    {
        String name = EmployeetName.getText().toString();
        String surname = EmployeeSurname.getText().toString();
        String gender= EmployeeGender.getText().toString();
        String birthdate= EmployeeBirthdate.getText().toString();

        userDBHelper = new UserDBHelper(context);
        sqLiteDatabase=userDBHelper.getWritableDatabase();
        userDBHelper.insertInfo(name, surname, gender,birthdate, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
       tvUIHelperObject.setText("Data Saved");
        userDBHelper.close();
    }


}
