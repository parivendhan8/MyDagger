package com.example.mydagger.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.mydagger.R;
import com.example.mydagger.base.BaseActivity;
import com.example.mydagger.ui.main.profile.ProfileFragment;

import static com.chauthai.swipereveallayout.SwipeRevealLayout.DRAG_EDGE_TOP;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testFragment();

    }

    private void testFragment()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ProfileFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:
                sessionManager.logout();
                return true;

        }

        return super.onOptionsItemSelected(item);

    }
}
