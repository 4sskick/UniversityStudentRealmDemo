package nightroomcreation.id.realmdemo2.di;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import nightroomcreation.id.realmdemo2.CustomApplication;

/**
 * Created by iand on 12/10/17.
 */

@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(CustomApplication app);
}
