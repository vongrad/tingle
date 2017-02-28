package dk.itk.vongrad.tingle;

import android.content.DialogInterface;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import dk.itk.vongrad.tingle.adapters.ThingsAdapter;
import dk.itk.vongrad.tingle.database.ThingsDB;

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
    public void onListItemClick(ListView l, View v, final int position, long id) {

        final ThingsAdapter adapter = (ThingsAdapter) l.getAdapter();

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.dialog_delete_thing)
                .setMessage(R.string.dialog_delete_thing_desc)
                .setIcon(android.R.drawable.ic_delete)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        db.delete(position);
                        adapter.notifyDataSetChanged();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
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

    public interface OnListFragmentInteractionListener {}
}
