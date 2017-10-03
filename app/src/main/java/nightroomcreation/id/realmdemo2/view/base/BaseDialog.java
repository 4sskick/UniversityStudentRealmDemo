package nightroomcreation.id.realmdemo2.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

/**
 * Created by iand on 02/10/17.
 */

public class BaseDialog extends DialogFragment {

    public Validator validator;
    private FormValidator formValidator;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initValidator();
    }

    /**
     * CUSTOM METHOD HERE
     * ==================
     */
    private void initValidator() {
        validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                formValidator.onValidationSucces();
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view = error.getView();
                    String errorMessage = error.getCollatedErrorMessage(getContext());

                    //display error message
                    if (view instanceof EditText) {
                        ((EditText) view).setError(errorMessage);
                    }else{
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void validateForm(FormValidator formValidator){
        this.formValidator = formValidator;
        validator.validate();
    }

    //interface class
    public interface FormValidator {
        void onValidationSucces();
    }
}
