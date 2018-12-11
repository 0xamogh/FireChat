package amoghjapps.com.snapchatclone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FeedItemAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    MainActivity main;
    List<ModelImage> list;

    public FeedItemAdapter(MainActivity main, List<ModelImage> list) {
        this.main = main;
        this.list = list;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(main.getBaseContext());
        View view =inflater.inflate(R.layout.listitem,parent,false);

        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

            }
        });
        holder.captionSubtext.setText(list.get(position).getCaption());
        holder.logintext.setText(list.get(position).getLoginid());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface ItemClickListener{
        void onClick(View view,int position,boolean isLongClick);
    }
}
