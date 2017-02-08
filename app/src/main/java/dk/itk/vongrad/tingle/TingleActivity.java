package dk.itk.vongrad.tingle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dk.itk.vongrad.tingle.database.ThingsDB;
import dk.itk.vongrad.tingle.models.Thing;

public class TingleActivity extends AppCompatActivity {

    private Button btn_new;
    private Button btn_list;

    private EditText edt_last;
    private EditText edt_what;
    private EditText edt_where;

    private ThingsDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        db = ThingsDB.getInstance(this);

        btn_new = (Button) findViewById(R.id.btn_new);
        btn_list = (Button) findViewById(R.id.btn_list);

        edt_last = (EditText) findViewById(R.id.edt_last);
        edt_what = (EditText) findViewById(R.id.edt_what);
        edt_where = (EditText) findViewById(R.id.edt_where);

        btn_new.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(edt_what.getText().length() > 0 && edt_where.getText().length() > 0){
                    db.add(new Thing(edt_what.getText().toString(), edt_where.getText().toString()));
                    updateUI();
                }
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TingleActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
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
