package nightroomcreation.id.realmdemo2.view.student;

import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.view.base.IBasePresenter;

/**
 * Created by iand on 02/10/17.
 */

public interface IStudentPresenter extends IBasePresenter {
    void addStudent(Student student);

    void addStudentByUniversityId(Student student, String universityId);

    void deleteStudentByPosition(int position);

    void deleteStudentById(String studentId);

    void getAllStudents();

    void getAllStudentByUniversityId(String universityId);

    void getStudentById(String id);

    void getUniversityById(String id);

    void updateStudentById(String id, Student student);
}
