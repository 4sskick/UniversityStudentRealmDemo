package nightroomcreation.id.realmdemo2;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerAppCompatActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import nightroomcreation.id.realmdemo2.database.module.DBModule;
import nightroomcreation.id.realmdemo2.di.DaggerAppComponent;

/**
 * Created by iand on 26/09/17.
 */

public class CustomApplication extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    //variables
    private static CustomApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        /*RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);*/

        RealmConfiguration configuration = new RealmConfiguration
                .Builder(getApplicationContext())
                .schemaVersion(0)
                /*.migration()*/
                .setModules(new DBModule()).build();
        Realm.setDefaultConfiguration(configuration);

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        if (BuildConfig.DEBUG) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        }
    }



    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public static CustomApplication getInstance() {
        return instance;
    }
}
