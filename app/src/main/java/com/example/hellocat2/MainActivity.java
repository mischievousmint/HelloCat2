package com.example.hellocat2;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.hellocat2.fragments.ListFragment;
import com.example.hellocat2.fragments.MapFragment;
import com.example.hellocat2.fragments.PhotoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navMap:
                    launchFragment(new MapFragment());
                    return true;
                case R.id.navPho:
                    launchFragment(new PhotoFragment());
                    return true;
                case R.id.navList:
                    launchFragment(new ListFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pasamos el contexto
        MySharedPreferences.init(getApplicationContext());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void launchFragment(Fragment newfragment) {
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.flContainer,newfragment);
        FT.addToBackStack(null);
        FT.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
