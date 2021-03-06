package nightroomcreation.id.realmdemo2.view.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Validator;

/**
 * Created by iand on 29/09/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    abstract protected void initComponents();

    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
