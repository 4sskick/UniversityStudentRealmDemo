package nightroomcreation.id.realmdemo2.database.repository.student;

import io.realm.RealmList;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.database.model.Student;

/**
 * Created by iand on 29/09/17.
 */

public interface IStudentRepository {
    interface OnSaveStudentCallback {
        void onSuccess();

        void onError(String message);
    }

    interface OnDeleteStudentCallback {
        void onSuccess();

        void onError(String message);
    }

    interface OnGetStudentByIdCallback {
        void onSuccess(Student student);

        void onError(String message);
    }

    interface OnGetAllStudentsCallback {
        void onSuccess(RealmResults<Student> students);

        void onError(String message);
    }

    interface OnGetStudentsCallback {
        void onSuccess(RealmList<Student> students);

        void onError(String message);
    }

    void addStudent(Student student, OnSaveStudentCallback callback);

    void addStudentByUniversityId(Student student, String universityId, OnSaveStudentCallback callback);

    void deleteStudentById(String id, OnDeleteStudentCallback callback);

    void deleteStudentByPosition(int position, OnDeleteStudentCallback callback);

    void getAllStudents(OnGetAllStudentsCallback callback);

    void getAllStudentsByUniversityId(String id, OnGetStudentsCallback callback);

    void getStudentById(String id, OnGetStudentByIdCallback callback);

    void updateStudentById(String id, Student student, OnSaveStudentCallback callback);
}
