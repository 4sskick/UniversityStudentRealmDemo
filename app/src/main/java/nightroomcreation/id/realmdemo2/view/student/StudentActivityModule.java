package nightroomcreation.id.realmdemo2.view.student;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iand on 12/10/17.
 */

@Module
public class StudentActivityModule {
    @Provides
    IStudentPresenter provideStudentPresenter(StudentActivity view){
        return new StudentPresenter(view);
    }
}
