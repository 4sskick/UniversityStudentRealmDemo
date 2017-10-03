package nightroomcreation.id.realmdemo2.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import nightroomcreation.id.realmdemo2.R;
import nightroomcreation.id.realmdemo2.database.model.University;

/**
 * Created by iand on 02/10/17.
 */

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ItemViewHolder> {

    //view
    private OnItemClickListener listener;

    //variable
    RealmResults<University> universities;

    //constructor
    public UniversityAdapter(RealmResults<University> universities){
        this.universities = universities;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_university, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txtUniversity.setText(universities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return universities.size();
    }

    //subclass holder view
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_university)
        TextView txtUniversity;

        @BindView(R.id.layout_item)
        RelativeLayout layoutItem;

        ItemViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            layoutItem.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            University university = universities.get(getAdapterPosition());
            //trigger the interface class
            listener.onItemClick(university.getId());
        }
    }

    //interface class
    public interface OnItemClickListener {
        void onItemClick(String id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
