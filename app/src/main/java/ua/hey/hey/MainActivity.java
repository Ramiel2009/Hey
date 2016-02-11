package ua.hey.hey;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ContactsList.OnFragmentInteractionListener,
                    MyHeyFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //test notification
                Notifications not = new Notifications();
                synchronized (not){
                    not.notification(getBaseContext());}

            }
        });
        Config.context = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        phone();


        Contacts  c = new Contacts();
        c.contacts();

        SocketConnection sc = new SocketConnection();
        sc.setAction("get_messages");
        sc.execute();
        SocketConnection sc2 = new SocketConnection();
        sc2.setAction("get_new_messages");
        sc2.execute();

    }

    public void phone(){
    // get phone number test
    PhoneNumber pn = new PhoneNumber();
        String userPhone = pn.getPhoneNumber(Config.context);
    System.out.println("User's phone number: "+ userPhone);
        TextView phoneView = (TextView)findViewById(R.id.userPhone);
        phoneView.setText(userPhone);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_messages) {
            fragment = new ContactsList();

        } else if (id == R.id.nav_history) {
           fragment = new MyHeyFragment();
        } else if (id == R.id.nav_share) {
            Snackbar.make(this.findViewById(R.id.toolbar), "SHARE IT!", Snackbar.LENGTH_LONG ).show();
        }

        if (fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
