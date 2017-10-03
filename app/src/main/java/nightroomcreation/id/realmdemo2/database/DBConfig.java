package nightroomcreation.id.realmdemo2.database;

import android.content.Context;

import io.realm.Realm;
import nightroomcreation.id.realmdemo2.CustomApplication;

/**
 * Created by iand on 29/09/17.
 */

public class DBConfig {

    private static Realm realm;

    //custom method here
    public static Realm getInstanceDB(Context context) {
         realm = Realm.getInstance(context);
        return realm;
    }
}
