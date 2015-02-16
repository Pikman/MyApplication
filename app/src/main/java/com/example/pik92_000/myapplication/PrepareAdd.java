package com.example.pik92_000.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class PrepareAdd extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_add);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        String Receiver = getIntent().getExtras().getString("Receiver");
        String Theme = getIntent().getExtras().getString("Theme");
        String OutNum = getIntent().getExtras().getString("OutNum");
        String InpNum = getIntent().getExtras().getString("InpNum");


        TextView PreparedTextView = (TextView)findViewById(R.id.textView3);
        PreparedTextView.setText(""+Receiver);

        TextView ThemeTextView = (TextView)findViewById(R.id.textView10);
        ThemeTextView.setText(""+Theme);


        TextView NumTextView = (TextView)findViewById(R.id.textView4);
        NumTextView.setText(""+OutNum + " " + InpNum);

        TextView MapsCord = (TextView)findViewById(R.id.textView5);
        MapsCord.setText("Координаты объекта: " + MapsActivity.MarkLat + " " + MapsActivity.MarkLong);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prepare_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

      @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_prepare_add, container, false);
            return rootView;
        }
    }
}
