package dk.itk.vongrad.tingle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class TingleActivity extends FragmentActivity implements TindleFragment.OnFragmentInteractionListener, TingleListFragment.OnListFragmentInteractionListener {

    private Boolean dualMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        View tingle_list = findViewById(R.id.fragment_tindle_list);
        dualMode = tingle_list != null && tingle_list.getVisibility() == View.VISIBLE;


        if(dualMode) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment_tingle_list = fm.findFragmentById(R.id.fragment_tindle_list);

            if(fragment_tingle_list == null) {
                fragment_tingle_list = TingleListFragment.newInstance();

                fm.beginTransaction()
                        .add(R.id.fragment_tindle_list, fragment_tingle_list)
                        .commit();
            }
        }
    }

    @Override
    public void onNewThingAdded() {
        if(dualMode) {
            Fragment fragment_tingle_list = getSupportFragmentManager().findFragmentById(R.id.fragment_tindle_list);
            if(fragment_tingle_list != null){
                ((TingleListFragment)fragment_tingle_list).reloadList();
            }
        }
    }
}
