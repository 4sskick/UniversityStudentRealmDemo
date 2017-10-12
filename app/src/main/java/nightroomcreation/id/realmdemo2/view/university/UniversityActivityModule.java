package nightroomcreation.id.realmdemo2.view.university;

import dagger.Module;
import dagger.Provides;

/**
 * Created by iand on 12/10/17.
 */

@Module
public class UniversityActivityModule {
    @Provides
    IUniversityPresenter provideUniversityPresenter(UniversityActivity view) {
        return new UniversityPresenter(view);
    }
}
