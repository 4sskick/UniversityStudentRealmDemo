package nightroomcreation.id.realmdemo2.view.university;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by iand on 12/10/17.
 */

@Subcomponent(modules = UniversityActivityModule.class)
public interface UniversityActivityComponent extends AndroidInjector<UniversityActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UniversityActivity>{}
}
