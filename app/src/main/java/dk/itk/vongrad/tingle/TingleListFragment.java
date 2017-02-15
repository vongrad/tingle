package dk.itk.vongrad.tingle;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import dk.itk.vongrad.tingle.adapters.ThingsAdapter;
import dk.itk.vongrad.tingle.database.ThingsDB;
import dk.itk.vongrad.tingle.models.Thing;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TingleListFragment extends ListFragment {

    private OnListFragmentInteractionListener mListener;

    private ThingsDB db;

    public static TingleListFragment newInstance() {
        TingleListFragment fragment = new TingleListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = ThingsDB.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        setListAdapter(new ThingsAdapter(getContext(), db));

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ThingsAdapter adapter = (ThingsAdapter) l.getAdapter();

        Thing thing = (Thing) adapter.getItem(position);
        Toast.makeText(getContext(), thing.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void reloadList(){
        ((ThingsAdapter) getListAdapter()).notifyDataSetChanged();
    }

    public interface OnListFragmentInteractionListener { }
}
