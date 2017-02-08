package dk.itk.vongrad.tingle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import dk.itk.vongrad.tingle.R;
import dk.itk.vongrad.tingle.database.ThingsDB;
import dk.itk.vongrad.tingle.models.Thing;

/**
 * Created by vongrad on 2/8/17.
 */

public class ThingsAdapter extends BaseAdapter {

    private Context context;
    private ThingsDB db;
    private LayoutInflater inflater;

    public ThingsAdapter(Context context, ThingsDB db) {
        this.context = context;
        this.db = db;

        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return db.size();
    }

    @Override
    public Object getItem(int position) {
        return db.get(position);
    }

    @Override
    public long getItemId(int position) {
        Thing thing = (Thing) getItem(position);
        return thing.hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_list_row, parent, false);
        }

        Thing thing = (Thing) getItem(position);

        TextView txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        txt_name.setText(thing.toString());

        return convertView;
    }
}
