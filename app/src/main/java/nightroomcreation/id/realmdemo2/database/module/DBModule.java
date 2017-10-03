package nightroomcreation.id.realmdemo2.database.module;

import io.realm.annotations.RealmModule;
import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.database.model.University;

/**
 * Created by iand on 26/09/17.
 */

@RealmModule(classes = {University.class, Student.class})
public class DBModule {
}
