package com.example.pik92_000.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    db sqh;
    SQLiteDatabase sqdb;

    EditText txtReceiver, txtTheme, OutNum, InpNum;
    public final int CAMERA_RESULT=0;



    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtReceiver = (EditText) findViewById(R.id.txtReceiver);
        txtTheme = (EditText) findViewById(R.id.txtTheme);
        OutNum = (EditText) findViewById(R.id.OutNum);
        InpNum = (EditText) findViewById(R.id.InpNum);

        db sqh = new db(this);
        SQLiteDatabase sqdb = sqh.getWritableDatabase();
        sqdb.close();
        sqh.close();



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                //setContentView(R.layout.activity_main);
                // Тут надо менять вью.
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                // setContentView(R.layout.kalendar);
                startActivity(new Intent (MainActivity.this, ViewActivity.class));

                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main_activity2);

                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    public void onClickPhoto(View view){
        //Toast.makeText(this, "sdfsdxcvcxv", Toast.LENGTH_SHORT).show();
        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAMERA_RESULT){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ImageButton PhotoBut;
            PhotoBut=(ImageButton)findViewById(R.id.PhotoBut);
            PhotoBut.setImageBitmap(thumbnail);
        }
    }

    public void onClickMaps(View view) {
        Intent RunMaps = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(RunMaps);
    }


    public void onClickAdd(View view){

        Intent intent = new Intent(MainActivity.this, PrepareAdd.class);
        intent.putExtra("Receiver", txtReceiver.getText().toString());
        intent.putExtra("Theme", txtTheme.getText().toString());
        intent.putExtra("OutNum", OutNum.getText().toString());
        intent.putExtra("InpNum", InpNum.getText().toString());
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Marker v "+MapsActivity.MarkLat, Toast.LENGTH_LONG).show();
        //ContentValues cv = new ContentValues();
        //cv.put(db.RECEIVER, txtReceiver.getText().toString());

        //sqdb.insert(db.TABLE_NAME, db.RECEIVER,cv);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
