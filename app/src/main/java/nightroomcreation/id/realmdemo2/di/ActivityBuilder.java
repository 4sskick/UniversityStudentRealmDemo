package nightroomcreation.id.realmdemo2.di;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import nightroomcreation.id.realmdemo2.view.student.StudentActivity;
import nightroomcreation.id.realmdemo2.view.student.StudentActivityComponent;
import nightroomcreation.id.realmdemo2.view.university.UniversityActivity;
import nightroomcreation.id.realmdemo2.view.university.UniversityActivityComponent;

/**
 * Created by iand on 12/10/17.
 */

@Module
public abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(UniversityActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindUniversityActivity(UniversityActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(StudentActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindStudentActivity(StudentActivityComponent.Builder builder);
}
