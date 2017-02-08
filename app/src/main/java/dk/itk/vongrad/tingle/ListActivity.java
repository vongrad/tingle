package dk.itk.vongrad.tingle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import dk.itk.vongrad.tingle.adapters.ThingsAdapter;
import dk.itk.vongrad.tingle.database.ThingsDB;
import dk.itk.vongrad.tingle.models.Thing;

public class ListActivity extends AppCompatActivity {

    private ListView lst_things;

    private ThingsDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = ThingsDB.getInstance(this);

        lst_things = (ListView) findViewById(R.id.lst_things);

        lst_things.setAdapter(new ThingsAdapter(this, db));

        lst_things.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThingsAdapter adapter = (ThingsAdapter) parent.getAdapter();

                Thing thing = (Thing) adapter.getItem(position);
                Toast.makeText(ListActivity.this, thing.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
