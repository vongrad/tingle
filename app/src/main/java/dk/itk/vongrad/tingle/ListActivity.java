package dk.itk.vongrad.tingle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class ListActivity extends FragmentActivity implements TingleListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_tindle_list);

        if(fragment == null) {
            fragment = new TingleListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_tindle_list, fragment)
                    .commit();
        }
    }
}
