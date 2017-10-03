package nightroomcreation.id.realmdemo2.view.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nightroomcreation.id.realmdemo2.R;
import nightroomcreation.id.realmdemo2.view.base.BaseDialog;

/**
 * Created by iand on 02/10/17.
 */

public class UniversityDialog extends BaseDialog implements View.OnClickListener {

    @BindView(R.id.layout_item)
    RelativeLayout layoutItem;

    @BindView(R.id.btn_add_university)
    Button btnAddUniversity;

    @NotEmpty
    @BindView(R.id.edit_university_name)
    EditText editUniversityName;

    //variable
    private OnAddUniversityClickListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_add_university, container);
        ButterKnife.bind(this, view);

        //set the listener click
        layoutItem.setOnClickListener(this);
        btnAddUniversity.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        //get the id of clicked item layout
        switch (view.getId()) {
            case R.id.layout_item:
                //triggering of layout which clicked by user
                //with interface
                listener.onDismiss();
                break;
            case R.id.btn_add_university:
                validateForm(new FormValidator() {
                    @Override
                    public void onValidationSucces() {
                        listener.onAddUniversityClickListener(editUniversityName.getText().toString());
//                        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }

    //custom method here
    public void setListener(OnAddUniversityClickListener listener) {
        this.listener = listener;
    }

    //interface class
    public interface OnAddUniversityClickListener {
        void onAddUniversityClickListener(String universityName);

        void onDismiss();
    }
}
