package nightroomcreation.id.realmdemo2.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.R;
import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.util.DateFormater;
import nightroomcreation.id.realmdemo2.view.student.StudentActivity;

/**
 * Created by iand on 02/10/17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ItemViewHolder> {

    //variable
    private RealmList<Student> students;

    //view
    private OnItemListener listener;

    //constructor
    public StudentAdapter(RealmList<Student> students) {
        this.students = students;
    }

    @Override
    public StudentAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ItemViewHolder holder, int position) {
        holder.txtStudentName.setText(students.get(position).getName());

        String birthDay = DateFormater.convertDateToString(students.get(position).getBirthday());
        holder.txtBirthday.setText(birthDay);

        holder.txtEmail.setText(students.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    //subclass item view holder
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_student_name)
        TextView txtStudentName;

        @BindView(R.id.txt_email)
        TextView txtEmail;

        @BindView(R.id.txt_birthday)
        TextView txtBirthday;

        @BindView(R.id.layout_item)
        RelativeLayout layoutItem;

        public ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            layoutItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layout_item:
                    Student student = students.get(getAdapterPosition());
                    //trigger the interface class
                    listener.onItemClick(student.getId(), student);
                    break;
            }
        }
    }

    //custom method here
    public void setOnItemListener(OnItemListener listener) {
        this.listener = listener;
    }

    //interface class
    public interface OnItemListener {
        void onItemClick(String id, Student student);
    }
}
