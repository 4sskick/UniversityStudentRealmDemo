package nightroomcreation.id.realmdemo2.database.repository.university;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.CustomApplication;
import nightroomcreation.id.realmdemo2.database.DBConfig;
import nightroomcreation.id.realmdemo2.database.model.University;
import nightroomcreation.id.realmdemo2.database.table.RepositoryTable;

/**
 * Created by iand on 26/09/17.
 */

public class UniversityRepository implements IUniversityRepository {

    @Override
    public void addUniversity(University university, OnAddUniversityCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        //begin transaction
        realm.beginTransaction();
        //create tables based on Object class
        University u = realm.createObject(University.class);
        u.setId(UUID.randomUUID().toString());
        u.setName(university.getName());
        //commiting the transaction
        realm.commitTransaction();

        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void deleteUniversityById(String id, OnDeleteUniversityCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        realm.beginTransaction();
        //find the data on first list
        University university = realm.where(University.class).equalTo(RepositoryTable.ID, id).findFirst();
        //delete the data when found one
        university.removeFromRealm();
        realm.commitTransaction();

        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void deleteUniversityByPosition(int position, OnDeleteUniversityCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        realm.beginTransaction();
        //querying data on object class
        RealmQuery<University> query = realm.where(University.class);
        //hold the result query
        RealmResults<University> results = query.findAll();
        results.remove(position);
        realm.commitTransaction();

        if (callback != null) {
            callback.onSuccess();
        }
    }

    @Override
    public void getUniversitybyId(String id, OnGetuniversityByIdCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        University university = realm.where(University.class).equalTo(RepositoryTable.ID, id).findFirst();

        if (callback != null) {
            callback.onSuccess(university);
        }
    }

    @Override
    public void getAllUniversities(OnGetAllUniversityCallback callback) {
        Realm realm = DBConfig.getInstanceDB(CustomApplication.getInstance());
        /*Realm realm = Realm.getInstance(CustomApplication.getInstance());*/
        RealmQuery<University> query = realm.where(University.class);
        RealmResults<University> results = query.findAll();

        if (callback != null) {
            callback.onSuccess(results);
        }
    }
}
