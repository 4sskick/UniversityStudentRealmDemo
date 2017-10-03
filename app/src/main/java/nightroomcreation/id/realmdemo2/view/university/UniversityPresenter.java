package nightroomcreation.id.realmdemo2.view.university;

import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.database.model.University;
import nightroomcreation.id.realmdemo2.database.repository.university.IUniversityRepository;
import nightroomcreation.id.realmdemo2.database.repository.university.UniversityRepository;

/**
 * Created by iand on 29/09/17.
 */

public class UniversityPresenter implements IUniversityPresenter {

    private UniversityActivity view;

    private IUniversityRepository reposity;


    private IUniversityRepository.OnGetAllUniversityCallback onGetAllUniversityCallback;
    private IUniversityRepository.OnAddUniversityCallback onAddUniversityCallback;
    private IUniversityRepository.OnGetuniversityByIdCallback onGetuniversityByIdCallback;
    private IUniversityRepository.OnDeleteUniversityCallback onDeleteUniversityCallback;

    //constructor
    public UniversityPresenter(UniversityActivity view) {
        this.view = view;
        this.reposity = new UniversityRepository();
    }

    @Override
    public void subscribeCallback() {
        onGetAllUniversityCallback = new IUniversityRepository.OnGetAllUniversityCallback() {
            @Override
            public void onSuccess(RealmResults<University> universities) {
                view.showUniversities(universities);
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        };

        onAddUniversityCallback = new IUniversityRepository.OnAddUniversityCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Added");
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        };

        onGetuniversityByIdCallback = new IUniversityRepository.OnGetuniversityByIdCallback() {
            @Override
            public void onSuccess(University university) {
                //do nothing
            }

            @Override
            public void onFailute(String message) {
                view.showMessage(message);
            }
        };

        onDeleteUniversityCallback = new IUniversityRepository.OnDeleteUniversityCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Deleted");
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        };
    }

    @Override
    public void unsubscribeCallback() {
        onGetAllUniversityCallback = null;
        onDeleteUniversityCallback = null;
        onGetuniversityByIdCallback = null;
        onAddUniversityCallback = null;
    }

    @Override
    public void addUniversity(String universityName) {
        University university = new University(universityName);
        reposity.addUniversity(university, onAddUniversityCallback);
    }

    @Override
    public void deleteUniversity(int position) {
        reposity.deleteUniversityByPosition(position, onDeleteUniversityCallback);
    }

    @Override
    public void deleteUniversityById(String id) {
        reposity.deleteUniversityById(id, onDeleteUniversityCallback);
    }

    @Override
    public void getUniversityById(String id) {
        reposity.getUniversitybyId(id, onGetuniversityByIdCallback);
    }

    @Override
    public void getAllUniversities() {
        reposity.getAllUniversities(onGetAllUniversityCallback);
    }
}
