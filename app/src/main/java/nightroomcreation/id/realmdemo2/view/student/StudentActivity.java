package nightroomcreation.id.realmdemo2.view.student;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.R;
import nightroomcreation.id.realmdemo2.database.model.Student;
import nightroomcreation.id.realmdemo2.database.table.RepositoryTable;
import nightroomcreation.id.realmdemo2.util.DateFormater;
import nightroomcreation.id.realmdemo2.view.adapter.StudentAdapter;
import nightroomcreation.id.realmdemo2.view.base.BaseActivity;
import nightroomcreation.id.realmdemo2.view.dialog.StudentDialog;

public class StudentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fab)
    FloatingActionButton btnFab;

    @BindView(R.id.rv_student)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //view
    private StudentPresenter presenter;
    private StudentAdapter adapter;

    //variable
    private String universityId;
    RealmList<Student> students;

    @Override
    protected void onStart() {
        super.onStart();

        presenter.subscribeCallback();
        presenter.getUniversityById(universityId);
        presenter.getAllStudentByUniversityId(universityId);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.unsubscribeCallback();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ButterKnife.bind(this);

        presenter = new StudentPresenter(this);

        //catch the intent values from previous activity
        universityId = getIntent().getStringExtra(RepositoryTable.ID);

        //need to be triggered
        initComponents();
    }

    @Override
    protected void initComponents() {
        //toolbar
        toolbar.setTitle(R.string.str_universities);
        setSupportActionBar(toolbar);

        //floating action button
        btnFab.setOnClickListener(this);

        //recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //action button of item on recycler view
        ItemTouchHelper swipeTouch = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //notify adapter & setting up action presenter
                presenter.deleteStudentById(students.get(viewHolder.getAdapterPosition()).getId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        //attaching the item touch helper to recyclerview
        swipeTouch.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                showAddStudentDialog();
//                break;
        }
    }

    /**
     * CUSTOM METHOD HERE
     * ==================
     */
    private void showAddStudentDialog() {
        /*Toast.makeText(this, "add student clicked", Toast.LENGTH_SHORT).show();*/
        final StudentDialog dialog = new StudentDialog();

        //set bundle data for arguments
        Bundle bundle = new Bundle();
        bundle.putString("action", "add");
        dialog.setArguments(bundle);

        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
        dialog.setListener(new StudentDialog.OnAddStudentClickListener() {
            @Override
            public void onAddStudentClickListener(Student student) {
                presenter.addStudentByUniversityId(student, universityId);//add student to specific university
                presenter.getAllStudentByUniversityId(universityId);
                adapter.notifyDataSetChanged();
                onDismiss();
            }

            @Override
            public void onDismiss() {
                dialog.dismiss();
            }
        });
    }

    private void showUpdateStudentDialog(final String id, Student student) {
        /*Toast.makeText(StudentActivity.this, id + " Clicked", Toast.LENGTH_SHORT).show();*/
        final StudentDialog dialog = new StudentDialog();

        //set bundle data for arguments
        Bundle bundle = new Bundle();
        bundle.putString("action", "update");
        bundle.putString("existing_name", student.getName());
        bundle.putString("existing_email", student.getEmail());
        dialog.setArguments(bundle);

        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
        dialog.setUpdateListener(new StudentDialog.OnUpdateStudentClickListener() {
            @Override
            public void onUpdateStudentClickListener(Student student) {
                presenter.updateStudentById(id, student);
                presenter.getAllStudentByUniversityId(universityId);
                adapter.notifyDataSetChanged();
                onDismis();
            }

            @Override
            public void onDismis() {
                dialog.dismiss();
            }
        });
    }

    public void showStudents(RealmList<Student> students) {
        this.students = students;
        adapter = new StudentAdapter(students);

        adapter.setOnItemListener(new StudentAdapter.OnItemListener() {
            @Override
            public void onItemClick(String id, Student student) {
                showUpdateStudentDialog(id, student);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void updateToolbarTittle(String tittle) {
        toolbar.setTitle(tittle);
    }

}
