package nightroomcreation.id.realmdemo2.view.university;

import nightroomcreation.id.realmdemo2.view.base.IBasePresenter;

/**
 * Created by iand on 29/09/17.
 */

public interface IUniversityPresenter extends IBasePresenter{
    void addUniversity(String universityName);

    void deleteUniversity(int position);

    void deleteUniversityById(String id);

    void getUniversityById(String id);

    void getAllUniversities();
}
