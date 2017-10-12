package nightroomcreation.id.realmdemo2.view.student;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.database.model.University;
import nightroomcreation.id.realmdemo2.database.repository.student.IStudentRepository;
import nightroomcreation.id.realmdemo2.database.repository.student.StudentRepository;
import nightroomcreation.id.realmdemo2.database.repository.university.IUniversityRepository;
import nightroomcreation.id.realmdemo2.database.repository.university.UniversityRepository;
import nightroomcreation.id.realmdemo2.view.university.IUniversityPresenter;

/**
 * Created by iand on 02/10/17.
 */

public class StudentPresenter implements IStudentPresenter {

    //view
    private StudentActivity view;

    //action view callback
    //student
    private IStudentRepository.OnDeleteStudentCallback onDeleteStudentCallback;
    private IStudentRepository.OnGetAllStudentsCallback onGetAllStudentsCallback;
    private IStudentRepository.OnGetStudentByIdCallback onGetStudentByIdCallback;
    private IStudentRepository.OnGetStudentsCallback onGetStudentsCallback;
    private IStudentRepository.OnSaveStudentCallback onSaveStudentCallback;
    //university
    private IUniversityRepository.OnGetuniversityByIdCallback onGetuniversityByIdCallback;

    //repository
    private IStudentRepository studentRepository;
    private IUniversityRepository universityRepository;

    //constructor
    @Inject
    public StudentPresenter(StudentActivity view) {
        this.view = view;
        studentRepository = new StudentRepository();
        universityRepository = new UniversityRepository();
    }

    @Override
    public void subscribeCallback() {
        onDeleteStudentCallback = new IStudentRepository.OnDeleteStudentCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Deleted");
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

        onGetAllStudentsCallback = new IStudentRepository.OnGetAllStudentsCallback() {
            @Override
            public void onSuccess(RealmResults<Student> students) {
                //do nothing
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

        onGetStudentByIdCallback = new IStudentRepository.OnGetStudentByIdCallback() {
            @Override
            public void onSuccess(Student student) {

            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

        onGetStudentsCallback = new IStudentRepository.OnGetStudentsCallback() {
            @Override
            public void onSuccess(RealmList<Student> students) {
                view.showStudents(students);
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

        onSaveStudentCallback = new IStudentRepository.OnSaveStudentCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Saved");
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

        onGetuniversityByIdCallback = new IUniversityRepository.OnGetuniversityByIdCallback() {
            @Override
            public void onSuccess(University university) {
                view.updateToolbarTittle(university.getName());
            }

            @Override
            public void onFailute(String message) {
                view.showMessage(message);
            }
        };
    }

    @Override
    public void unsubscribeCallback() {
        onSaveStudentCallback = null;
        onGetStudentsCallback = null;
        onGetStudentByIdCallback = null;
        onGetAllStudentsCallback = null;
        onDeleteStudentCallback = null;
        onGetuniversityByIdCallback = null;
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.addStudent(student, onSaveStudentCallback);
    }

    @Override
    public void addStudentByUniversityId(Student student, String universityId) {
        studentRepository.addStudentByUniversityId(student, universityId, onSaveStudentCallback);
    }

    @Override
    public void deleteStudentByPosition(int position) {
        studentRepository.deleteStudentByPosition(position, onDeleteStudentCallback);
    }

    @Override
    public void deleteStudentById(String studentId) {
        studentRepository.deleteStudentById(studentId, onDeleteStudentCallback);
    }

    @Override
    public void getAllStudents() {
        studentRepository.getAllStudents(onGetAllStudentsCallback);
    }

    @Override
    public void getAllStudentByUniversityId(String universityId) {
        studentRepository.getAllStudentsByUniversityId(universityId, onGetStudentsCallback);
    }

    @Override
    public void getStudentById(String id) {
        studentRepository.getStudentById(id, onGetStudentByIdCallback);
    }

    @Override
    public void getUniversityById(String id) {
        universityRepository.getUniversitybyId(id, onGetuniversityByIdCallback);
    }

    @Override
    public void updateStudentById(String id, Student student) {
        studentRepository.updateStudentById(id, student, onSaveStudentCallback);
    }


}
