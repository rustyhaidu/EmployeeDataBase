package claudiu.orangesql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Claudiu on 9/1/2015.
 */
public class SearchContactActivity extends ActionBarActivity {

    // TODO - CODE REVIEW - andrei | 9/7/15 - You have unused members. Delete them!
    TextView display_name, display_surname;
    EditText search_name, updateName, updateSurname, updateGender,updateBirthdate;
    ToggleButton updateGenderToggle;
    UserDBHelper userDBHelper;
    SQLiteDatabase sqLiteDatabase;
    String searchName;
    String newName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchcontact);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Employee Details");
        actionBar.setSubtitle("Edit Section");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //display_name = (TextView) findViewById(R.id.tvNameFound);
        //display_surname = (TextView) findViewById(R.id.tvSurnameFound);
        search_name = (EditText) findViewById(R.id.etSearchName);

        updateName = (EditText) findViewById(R.id.etNewName);
        updateSurname = (EditText) findViewById(R.id.etNewSurname);
        //updateGender = (EditText) findViewById(R.id.etNewGender);
        updateBirthdate = (EditText) findViewById(R.id.etNewBirthdate);
        updateGenderToggle = (ToggleButton) findViewById(R.id.NewToggleSex);

       // display_name.setVisibility(View.GONE);
       // display_surname.setVisibility(View.GONE);


        Intent intent = getIntent();
        String message = intent.getStringExtra("name");
        search_name.setText(message);

        if (message != null) {
            Button pressButton = (Button) findViewById(R.id.bSearchContact);
            // pressButton.performClick();

            searchContact(pressButton);
        }
    }

    // TODO - CODE REVIEW - andrei | 9/7/15 - Delete commented code. If you'll need it later, you can find it in GIT.
    /*public void searchContact(View view)
    {
        searchName = search_name.getText().toString();
        userDBHelper= new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getContact(searchName,sqLiteDatabase);

        if (cursor.moveToFirst()) {
            String MOBILE = cursor.getString(0);
            String EMAIL = cursor.getString(1);

            display_mobile.setText(MOBILE);
            display_email.setText(EMAIL);

            display_email.setVisibility(View.VISIBLE);
            display_mobile.setVisibility(View.VISIBLE);
        }
    } */

    public void searchContact(View view) {
        searchName = search_name.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getContact(searchName, sqLiteDatabase);

        if (cursor.moveToFirst()) {

            String NAME = cursor.getString(0);
            String SURNAME = cursor.getString(1);
            String GENDER = cursor.getString(2);
            String BIRTHDATE = cursor.getString(3);
           // newName = searchName;

           // updateGender.setText(GENDER);
            updateName.setText(NAME);
            updateSurname.setText(SURNAME);
            updateBirthdate.setText(BIRTHDATE);

            updateGenderToggle.toggle();
            if(GENDER=="Male"){
               updateGenderToggle.setSelected(false);

            }
            else if(GENDER=="Female"){
                updateGenderToggle.setSelected(true);
            }



           // display_name.setText(NAME);
           // display_surname.setText(SURNAME);

           // display_name.setVisibility(View.VISIBLE);
           // display_surname.setVisibility(View.VISIBLE);
        }
        if (searchContact())
            Toast.makeText(getBaseContext(), "Contact Deleted: " + searchName, Toast.LENGTH_LONG).show();
        else Toast.makeText(getBaseContext(), "Contact Not Found: " + searchName, Toast.LENGTH_LONG).show();
    }
    public boolean searchContact() {
        boolean found = false;
        searchName = search_name.getText().toString();
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();
        Cursor cursor = userDBHelper.getContact(searchName, sqLiteDatabase);

        if (cursor.moveToFirst()) {

            found =true;
        }
        return found;
    }

    public void deleteContact(View view) {
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();

        if (searchName==null)
        {
            searchName = search_name.getText().toString();
        }

        userDBHelper.deleteInformation(searchName, sqLiteDatabase);
        if (searchContact())
        Toast.makeText(getBaseContext(), "Contact Deleted: " + searchName, Toast.LENGTH_LONG).show();
        else Toast.makeText(getBaseContext(), "Contact Not Found: " + searchName, Toast.LENGTH_LONG).show();
    }
    public void deleteContact() {
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();

        userDBHelper.deleteInformation(searchName, sqLiteDatabase);

        Toast.makeText(getBaseContext(), "Contact Deleted: " + searchName, Toast.LENGTH_LONG).show();
    }

    public void updateContact(View view) {
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        String name, surname,gender,birthdate;

        name = updateName.getText().toString();
        surname = updateSurname.getText().toString();
        gender = updateGenderToggle.getText().toString();
        birthdate = updateBirthdate.getText().toString();

        int count = userDBHelper.updateInformation(searchName, name, surname, gender,birthdate, sqLiteDatabase);

        if (count>0) {
            Toast.makeText(getApplicationContext(), count + " contact updated ", Toast.LENGTH_LONG).show();
            finish();
        }
        else Toast.makeText(getApplicationContext(),  "contact does not exist ", Toast.LENGTH_LONG).show();
    }

    public void updateContact() {
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        String name, surname,gender,birthdate;

        name = updateName.getText().toString();
        surname = updateSurname.getText().toString();
        gender = updateGenderToggle.getText().toString();
        birthdate= updateBirthdate.getText().toString();

        int count = userDBHelper.updateInformation(searchName, name, surname, gender,birthdate, sqLiteDatabase);
        if (count>0) {
            Toast.makeText(getApplicationContext(), count + " contact updated ", Toast.LENGTH_LONG).show();
            finish();
        }
        else Toast.makeText(getApplicationContext(),"contact does not exist ", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:// handle click of the app icon in the action bar

            case R.id.action_Edit:
                updateContact();
                return true;
            case R.id.action_Delete:
                deleteContact();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}
