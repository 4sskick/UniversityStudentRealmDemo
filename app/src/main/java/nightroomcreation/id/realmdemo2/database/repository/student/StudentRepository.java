package nightroomcreation.id.realmdemo2.database.repository.student;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.CustomApplication;
import nightroomcreation.id.realmdemo2.database.DBConfig;
import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.database.model.University;
import nightroomcreation.id.realmdemo2.database.table.RepositoryTable;

/**
 * Created by iand on 26/09/17.
 */

public class StudentRepository implements IStudentRepository {
    @Override
    public void addStudent(Student student, OnSaveStudentCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        realm.beginTransaction();
        Student s = realm.createObject(Student.class);
        s.setId(UUID.randomUUID().toString());
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        s.setBirthday(student.getBirthday());
        realm.commitTransaction();
    }

    @Override
    public void addStudentByUniversityId(Student student, String universityId, OnSaveStudentCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        realm.beginTransaction();

        Student s = realm.createObject(Student.class);
        s.setId(UUID.randomUUID().toString());
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        s.setBirthday(student.getBirthday());

        //get data university by id
        University university = realm.where(University.class).equalTo(RepositoryTable.ID, universityId).findFirst();
        university.getStudents().add(s);

        realm.commitTransaction();

        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void deleteStudentById(String id, OnDeleteStudentCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        realm.beginTransaction();
        Student student = realm.where(Student.class).equalTo(RepositoryTable.ID, id).findFirst();
        student.removeFromRealm();
        realm.commitTransaction();

        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void deleteStudentByPosition(int position, OnDeleteStudentCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        realm.beginTransaction();
        RealmQuery<Student> query = realm.where(Student.class);
        RealmResults<Student> results = query.findAll();
        results.remove(position);
        realm.commitTransaction();
    }

    @Override
    public void getAllStudents(OnGetAllStudentsCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        RealmResults<Student> results = realm.where(Student.class).findAll();

        if (callback != null) {
            callback.onSuccess(results);
        }
    }

    @Override
    public void getAllStudentsByUniversityId(String id, OnGetStudentsCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        University university = realm.where(University.class).equalTo(RepositoryTable.ID, id).findFirst();
        RealmList<Student> students = university.getStudents();

        if (callback != null) {
            callback.onSuccess(students);
        }
    }

    @Override
    public void getStudentById(String id, OnGetStudentByIdCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        Student student = realm.where(Student.class).equalTo(RepositoryTable.ID, id).findFirst();

        if (callback != null) {
            callback.onSuccess(student);
        }
    }

    @Override
    public void updateStudentById(String id, Student student, OnSaveStudentCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        //begin transaction
        realm.beginTransaction();

        //holding existing data
        Student s = realm.where(Student.class).equalTo(RepositoryTable.ID, id).findFirst();
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        s.setBirthday(student.getBirthday());

        //committing changes
        realm.commitTransaction();

        if (callback != null) {
            callback.onSuccess();
        }
    }
}
