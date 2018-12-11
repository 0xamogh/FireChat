package amoghjapps.com.snapchatclone;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView logintext,captionSubtext;
    Uri imagelink;
    FeedItemAdapter.ItemClickListener itemClickListener;



    public FeedViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        logintext=itemView.findViewById(R.id.postid);
        captionSubtext=itemView.findViewById(R.id.caption);

            }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(FeedItemAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}

