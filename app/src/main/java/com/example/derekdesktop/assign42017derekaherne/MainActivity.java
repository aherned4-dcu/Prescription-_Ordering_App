package com.example.derekdesktop.assign42017derekaherne;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import static android.provider.CalendarContract.CalendarCache.URI;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static final int id = 110001;
    NotificationCompat.Builder notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        notify= new NotificationCompat.Builder(this);
        notify.setAutoCancel(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startTimer("I can set a timer",10,false);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                invalidateOptionsMenu();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void startTimer(String message, int seconds, boolean skipui) {

        /** Citation: Class contains code adapted from
         * URL: //https://developer.android.com/guide/components/intents-common.html
         * Permission: MIT Licence Retrieved on:21th January 2018  */

        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);

        intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);
        intent.putExtra(AlarmClock.EXTRA_LENGTH, seconds);
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, skipui);

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        /** Citation: Class contains code adapted from
         * URL: //https://stackoverflow.com/questions/28342181/show-menu-items-depending-viewpager-android
         * Permission: MIT Licence Retrieved on:21th January 2018  */

        getMenuInflater().inflate(R.menu.order_menu, menu);
        if (mViewPager.getCurrentItem()==0){
            menu.findItem(R.id.action_share).setVisible(false);
        } else if(mViewPager.getCurrentItem()==1){
            menu.findItem(R.id.action_share).setVisible(false);
        } else if(mViewPager.getCurrentItem()==2){
            menu.findItem(R.id.action_share).setVisible(true);
        } else if(mViewPager.getCurrentItem()==3){
            menu.findItem(R.id.action_share).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /** Citation: Class contains code adapted from
         * URL: //https://developer.android.com/guide/components/intents-common.html
         * Permission: MIT Licence Retrieved on:21th January 2018  */

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Send" icon...
                notify.setSmallIcon(R.drawable.lipstick);
                notify.setTicker("This is ticker");
                notify.setWhen(System.currentTimeMillis());
                notify.setContentTitle("DCU Chemist");
                notify.setContentText("Set your refill date!");
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notify.setSound(alarmSound);

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notify.setContentIntent(pendingIntent);

                //Build notification and issue
                NotificationManager nm=  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(id,notify.build());

                return true;

            case R.id.action_share:
                // User chose the "Share" action
                //extract text from EditText fields
                /** Citation: Class contains code adapted from
                   https://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
                 * Permission: MIT Licence Retrieved on:25th Ddecember 2017  */

                //Capture the text
                TextView textViewName = (TextView) findViewById(R.id.name);
                TextView textViewInfo = (TextView) findViewById(R.id.otherInfo);

                Spinner spinner = (Spinner) findViewById(R.id.spinner_time);
                String selected = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                String to = getString(R.string.email_recip);
                String subject1 = getString(R.string.name) + ": " + textViewName.getText().toString() + "\n" + getString(R.string.add_inst) + ": " + textViewInfo.getText().toString() + "\n" + getString(R.string.coll_t) + ": " + selected;
                //open an email app and set the fields using the text from the previous activity
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setType("message/rfc822"); // ensure only email apps
                i.setData(Uri.parse("mailto:" + to)); //recipient
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + " " + textViewName.getText().toString()); //subject
                i.putExtra(android.content.Intent.EXTRA_TEXT, subject1); //body
                if (URI != null) {
                    i.putExtra(Intent.EXTRA_STREAM, URI);
                }
                startActivity(Intent.createChooser(i, "Send mail..."));
                Log.i(TAG, " inside share click");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Info infoTab = new Info();
                    return infoTab;
                case 1:
                    List list = new List();
                    return list;
                case 2:
                    Order order = new Order();
                    return order;
                case 3:
                    Account account = new Account();
                    return account;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "INFO";
                case 1:
                    return "lIST";
                case 2:
                    return "ORDER";
                case 3:
                    return "ACCOUNT";
            }
            return null;
        }
    }
}
