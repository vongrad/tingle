package dk.itk.vongrad.tingle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import dk.itk.vongrad.tingle.database.ThingsDB;
import dk.itk.vongrad.tingle.models.Thing;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TindleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TindleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TindleFragment extends Fragment {

    private Button btn_new;
    private Button btn_list;

    private EditText edt_last;
    private EditText edt_what;
    private EditText edt_where;

    private ThingsDB db;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TindleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TindleFragment newInstance() {
        TindleFragment fragment = new TindleFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = ThingsDB.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tindle, container, false);

        btn_new = (Button) view.findViewById(R.id.btn_new);
        btn_list = (Button) view.findViewById(R.id.btn_list);

        edt_last = (EditText) view.findViewById(R.id.edt_last);
        edt_what = (EditText) view.findViewById(R.id.edt_what);
        edt_where = (EditText) view.findViewById(R.id.edt_where);

        btn_new.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(edt_what.getText().length() > 0 && edt_where.getText().length() > 0){

                    Thing thing = new Thing(edt_what.getText().toString(), edt_where.getText().toString());

                    db.add(thing);

                    updateUI();
                    addNewThing();
                }
            }
        });

        // Portrait mode
        if(btn_list != null) {
            btn_list.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), ListActivity.class);
                    startActivity(i);
                }
            });
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void addNewThing() {
        if (mListener != null) {
            mListener.onNewThingAdded();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onNewThingAdded();
    }

    /**
     * Update UI
     */
    private void updateUI(){
        if(db.size() > 0){
            edt_last.setText(db.get(db.size() - 1).toString());
        }
    }
}
