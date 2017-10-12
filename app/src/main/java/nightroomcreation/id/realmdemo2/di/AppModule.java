package nightroomcreation.id.realmdemo2.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nightroomcreation.id.realmdemo2.view.student.StudentActivityComponent;
import nightroomcreation.id.realmdemo2.view.university.UniversityActivity;
import nightroomcreation.id.realmdemo2.view.university.UniversityActivityComponent;
import nightroomcreation.id.realmdemo2.view.university.UniversityActivityModule;

/**
 * Created by iand on 12/10/17.
 */

@Module(subcomponents = {
        UniversityActivityComponent.class,
        StudentActivityComponent.class
})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
