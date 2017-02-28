package dk.itk.vongrad.tingle.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dk.itk.vongrad.tingle.models.Thing;

public class ThingsDB {

    private static ThingsDB instance;

    private List<Thing> things;

    public static synchronized ThingsDB getInstance(Context context) {
        if (instance == null) {
            instance = new ThingsDB(context);
        }
        return instance;
    }

    public List<Thing> getAll() {
        return things;
    }

    public void add(Thing thing) {
        things.add(thing);
    }

    public int size() {
        return things.size();
    }

    public Thing get(int i){
        return things.get(i);
    }

    public void delete(int index) {
        things.remove(index);
    }

    private ThingsDB(Context context) {
        things= new ArrayList<>();
        things.add(new Thing("Android Pnone", "Desk"));
        things.add(new Thing("Big Nerd book", "Desk"));
    }

}
