package nightroomcreation.id.realmdemo2.view.student;

import dagger.Subcomponent;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

/**
 * Created by iand on 12/10/17.
 */

@Subcomponent(modules = StudentActivityModule.class)
public interface StudentActivityComponent extends AndroidInjector<StudentActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<StudentActivity>{}
}
