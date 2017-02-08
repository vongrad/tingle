package dk.itk.vongrad.tingle.models;

/**
 * Created by vongrad on 2/1/17.
 */

public class Thing {

    private String what;
    private String where;

    public Thing(String what, String where) {
        this.what = what;
        this.where = where;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    @Override
    public String toString() {
        return this.what + " " + this.where;
    }
}
