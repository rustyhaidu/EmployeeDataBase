package claudiu.orangesql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by claudiu.haidu on 7/24/2015.
 */
public class SelectInfoActivity extends AppCompatActivity{
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDBHelper userDBHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    protected void onCreate(Bundle stateIntance){
        super.onCreate(stateIntance);
        setContentView(R.layout.selectinfoactivity);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Employee ListView");
        actionBar.setSubtitle("CRUD Application");
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);



        listView = (ListView)findViewById(R.id.lvListView);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.rowlayout);
        listView.setAdapter(listDataAdapter);

        userDBHelper = new UserDBHelper(getApplicationContext());
        sqLiteDatabase = userDBHelper.getReadableDatabase();

        cursor = userDBHelper.selectInfo(sqLiteDatabase);// TODO - CODE REVIEW - andrei | 9/7/15 - The cursor is never closed! You should always close cursor when you've finished working with it.

        if (cursor.moveToFirst()){

            do {
                String name,surname,gender,birthdate;
                name = cursor.getString(0);
                surname = cursor.getString(1);
                gender = cursor.getString(2);
                birthdate = cursor.getString(3);
                DataProvider dataProvider = new DataProvider(name,surname,gender,birthdate);
                listDataAdapter.add(dataProvider);
            }
                while (cursor.moveToNext());
        }
        registerClickCallback();
        listDataAdapter.notifyDataSetChanged();
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.lvListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

             DataProvider provider = (DataProvider) listDataAdapter.getItem(position);
             String selectedName = provider.getName();


                String message = position+" "+selectedName;
                Toast.makeText(SelectInfoActivity.this, message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SelectInfoActivity.this,SearchContactActivity.class);
                //intent.putExtra(provider.toString(),selectedName);
              //  intent.putExtra(clickedCar.toString(),clickedName);
                intent.putExtra("name",selectedName);

                startActivity(new Intent(intent));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:// handle click of the app icon in the action bar

            case
                    R.id.action_Delete:
                Intent intent2 = new Intent();
                intent2.setClass(this,SearchContactActivity.class);
                startActivity(intent2);
                return true;

            case R.id.action_add:
               Intent intent = new Intent();
                intent.setClass(this,RegistrationActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
