package nightroomcreation.id.realmdemo2.view.university;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.R;
import nightroomcreation.id.realmdemo2.database.model.University;
import nightroomcreation.id.realmdemo2.database.table.RepositoryTable;
import nightroomcreation.id.realmdemo2.view.adapter.UniversityAdapter;
import nightroomcreation.id.realmdemo2.view.base.BaseActivity;
import nightroomcreation.id.realmdemo2.view.dialog.UniversityDialog;
import nightroomcreation.id.realmdemo2.view.student.StudentActivity;

public class UniversityActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton btnFab;

    @BindView(R.id.rv_universities)
    RecyclerView recyclerView;

    //view
    private UniversityAdapter adapter;
    @Inject
    /*private */IUniversityPresenter presenter;

    //variable
    private RealmResults<University> universities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        ButterKnife.bind(this);

        /*this.presenter = new UniversityPresenter(this);*/
        AndroidInjection.inject(this);

        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.presenter.subscribeCallback();
        this.presenter.getAllUniversities();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.presenter.unsubscribeCallback();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                showAddUniversityDialog();
                break;
        }
    }

    //base activity
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
                presenter.deleteUniversityById(universities.get(viewHolder.getAdapterPosition()).getId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        //attaching the item touch helper to recyclerview
        swipeTouch.attachToRecyclerView(recyclerView);
    }

    /**
     * CUSTOM METHOD HERE
     * ==================
     */
    private void showAddUniversityDialog() {
        //showing dialog for add new data university
        /*Toast.makeText(this, "dialog add university clicked", Toast.LENGTH_SHORT).show();*/
        final UniversityDialog dialog = new UniversityDialog();
        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
        dialog.setListener(new UniversityDialog.OnAddUniversityClickListener() {
            @Override
            public void onAddUniversityClickListener(String universityName) {
                presenter.addUniversity(universityName);
                adapter.notifyDataSetChanged();
                onDismiss();
            }

            @Override
            public void onDismiss() {
                dialog.dismiss();
            }
        });
    }

    public void showUniversities(RealmResults<University> universities) {
        this.universities = universities;
        adapter = new UniversityAdapter(universities);

        //all from interface and class that's been defined
        adapter.setOnItemClickListener(new UniversityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                /*Toast.makeText(UniversityActivity.this, "adapter clicked", Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                intent.putExtra(RepositoryTable.ID, id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
