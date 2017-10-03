package nightroomcreation.id.realmdemo2.view.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nightroomcreation.id.realmdemo2.R;
import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.database.repository.university.IUniversityRepository;
import nightroomcreation.id.realmdemo2.util.DateFormater;
import nightroomcreation.id.realmdemo2.view.base.BaseDialog;

/**
 * Created by iand on 02/10/17.
 */

public class StudentDialog extends BaseDialog implements View.OnClickListener, View.OnTouchListener {

    @NotEmpty
    @BindView(R.id.edit_name)
    EditText editName;

    @NotEmpty
    @Email
    @BindView(R.id.edit_email)
    EditText editEmail;

    @NotEmpty
    @BindView(R.id.edit_birthday)
    EditText editBirthday;

    @BindView(R.id.btn_hybrid)
    Button btnHybrid;

    //variables
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private Date birthDate;
    private String action;
    private String existingDataName;
    private String existingDataEmail;

    //listener
    private OnAddStudentClickListener listener;
    private OnUpdateStudentClickListener listenerUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;

        //get argument passed from fragment before
        Bundle bundle = getArguments();
        action = bundle.getString("action");

        if (bundle.getString("action").equals("add")) {
            view = inflater.inflate(R.layout.dialog_add_student, container);
        } else if (bundle.getString("action").equals("update")) {
            view = inflater.inflate(R.layout.dialog_update_student, container);

            existingDataName = bundle.getString("existing_name");
            existingDataEmail = bundle.getString("existing_email");
        }


        ButterKnife.bind(this, view);

        //set current day month year
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        editBirthday.setOnTouchListener(this);
        btnHybrid.setOnClickListener(this);

        if (bundle.getString("action").equals("update")) {
            editName.setText(existingDataName);
            editEmail.setText(existingDataEmail);
        }

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_hybrid:

                validateForm(new FormValidator() {
                    @Override
                    public void onValidationSucces() {
                        /*Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();*/
                        Student student = new Student();
                        student.setEmail(editEmail.getText().toString());
                        student.setName(editName.getText().toString());
                        student.setBirthday(birthDate);

                        if (action.equals("add"))
                            listener.onAddStudentClickListener(student);
                        else if (action.equals("update"))
                            listenerUpdate.onUpdateStudentClickListener(student);
                    }
                });
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.edit_birthday:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar selectedDate = Calendar.getInstance();
                            selectedDate.set(year, monthOfYear, dayOfMonth);
                            birthDate = selectedDate.getTime();

                            /*editBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);*/
                            editBirthday.setText(DateFormater.convertDateToString(birthDate));
                        }
                    }, currentYear, currentMonth, currentDay);
                    datePickerDialog.show();
                }
                break;
        }

        return false;
    }

    //custom method here
    public void setListener(OnAddStudentClickListener listener) {
        this.listener = listener;
    }

    public void setUpdateListener(OnUpdateStudentClickListener listenerUpdate) {
        this.listenerUpdate = listenerUpdate;
    }

    //interface class
    public interface OnAddStudentClickListener {
        void onAddStudentClickListener(Student student);

        void onDismiss();
    }

    public interface OnUpdateStudentClickListener {
        void onUpdateStudentClickListener(Student student);

        void onDismis();
    }
}
