package nightroomcreation.id.realmdemo2.database.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by iand on 26/09/17.
 */

public class University extends RealmObject {

    @PrimaryKey
    private String id;
    @Required
    private String name;
    private RealmList<Student> students;

    public University() {

    }

    public University(String universityName) {
        this.name = universityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Student> getStudents() {
        return students;
    }

    public void setStudents(RealmList<Student> students) {
        this.students = students;
    }
}
