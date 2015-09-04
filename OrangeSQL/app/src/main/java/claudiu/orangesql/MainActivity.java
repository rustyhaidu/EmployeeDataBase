package claudiu.orangesql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    public void goToRegistrationActivity(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this,RegistrationActivity.class);
        startActivity(intent);
    }
    public void goToSelectInfoActivity(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this,SelectInfoActivity.class);
        startActivity(intent);
    }
    public void goToSearchContactActivity(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this,SearchContactActivity.class);
        startActivity(intent);
    }
}
