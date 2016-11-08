package webservices.sd.tosin.com.br.clientwebservicesd.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import webservices.sd.tosin.com.br.clientwebservicesd.R;
import webservices.sd.tosin.com.br.clientwebservicesd.models.User;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.fragments.BookFragment;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.fragments.MyBookFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String host;
    private User user;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Todos os livros");
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("user"))
            user = (User) getIntent().getExtras().getSerializable("user");
        if (getIntent().hasExtra("host"))
            host = getIntent().getExtras().getString("host");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        // config header do navigation
        View navHeader = navigationView.getHeaderView(0);
        TextView username = (TextView) navHeader.findViewById(R.id.textView);
        username.setText(user.username);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int container = R.id.content_main;

        if (id == R.id.store) {
            // Handle the camera action
            toolbar.setTitle("Todos os livros");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, BookFragment.newInstance(1, user))
                    .commit();
        } else if (id == R.id.library) {
            toolbar.setTitle("Meus livros");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, MyBookFragment.newInstance(1, user))
                    .commit();

        } else if (id == R.id.logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
