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

/**
 * Created by Claudiu on 9/1/2015.
 */
public class SearchContactActivity extends ActionBarActivity {
    TextView display_email, display_mobile;
    EditText search_name, updateName, updateMobile, updateEmail;
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

        display_email = (TextView) findViewById(R.id.tvEmailFound);
        display_mobile = (TextView) findViewById(R.id.tvMobileNumberFound);
        search_name = (EditText) findViewById(R.id.etSearchName);

        updateName = (EditText) findViewById(R.id.etNewName);
        updateMobile = (EditText) findViewById(R.id.etNewMobile);
        updateEmail = (EditText) findViewById(R.id.etNewEmail);

        display_email.setVisibility(View.GONE);
        display_mobile.setVisibility(View.GONE);


        Intent intent = getIntent();
        String message = intent.getStringExtra("name");
        search_name.setText(message);

        if (message != null) {
            Button pressButton = (Button) findViewById(R.id.bSearchContact);
            // pressButton.performClick();

            searchContact(pressButton);
        }
    }

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

            String MOBILE = cursor.getString(0);
            String EMAIL = cursor.getString(1);
            newName = searchName;

            updateEmail.setText(EMAIL);
            updateName.setText(newName);
            updateMobile.setText(MOBILE);


            display_mobile.setText(MOBILE);
            display_email.setText(EMAIL);

            display_email.setVisibility(View.VISIBLE);
            display_mobile.setVisibility(View.VISIBLE);
        }
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
        String name, email, mobile;

        name = updateName.getText().toString();
        mobile = updateMobile.getText().toString();
        email = updateEmail.getText().toString();

        int count = userDBHelper.updateInformation(searchName, name, mobile, email, sqLiteDatabase);

        Toast.makeText(getApplicationContext(), count + " contact updated ", Toast.LENGTH_LONG).show();
        finish();
    }

    public void updateContact() {
        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getWritableDatabase();
        String name, email, mobile;

        name = updateName.getText().toString();
        mobile = updateMobile.getText().toString();
        email = updateEmail.getText().toString();

        int count = userDBHelper.updateInformation(searchName, name, mobile, email, sqLiteDatabase);

        Toast.makeText(getApplicationContext(), count + " contact updated ", Toast.LENGTH_LONG).show();
        finish();
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
