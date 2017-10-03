package nightroomcreation.id.realmdemo2.database.repository.university;

import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.database.model.University;

/**
 * Created by iand on 26/09/17.
 */

public interface IUniversityRepository {
    void addUniversity(University university, OnAddUniversityCallback callback);

    void deleteUniversityById(String id, OnDeleteUniversityCallback callback);

    void deleteUniversityByPosition(int position, OnDeleteUniversityCallback callback);

    void getUniversitybyId(String id, OnGetuniversityByIdCallback callback);

    void getAllUniversities(OnGetAllUniversityCallback callback);

    interface OnAddUniversityCallback {
        void onSuccess();

        void onFailure(String message);
    }

    interface OnDeleteUniversityCallback {
        void onSuccess();

        void onFailure(String message);
    }

    interface OnGetuniversityByIdCallback {
        void onSuccess(University university);

        void onFailute(String message);
    }

    interface OnGetAllUniversityCallback {
        void onSuccess(RealmResults<University> universities);

        void onFailure(String message);
    }
}
