package com.example.retrodelsingleactivapplication.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrodelsingleactivapplication.R;
import com.example.retrodelsingleactivapplication.interfaces.OnClickListener;
import com.example.retrodelsingleactivapplication.interfaces.OnLongClick;
import com.example.retrodelsingleactivapplication.model.Post;

import java.util.ArrayList;
import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.PostViewHolder> {

    private List<Post> postList = new ArrayList<>();
    private OnLongClick onLongClick;
    private OnClickListener onClickListener;


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
            holder.bind(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setLongListener(OnLongClick onLongClick){
        this.onLongClick = onLongClick;
    }
    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }


    public void addList(List<Post> list) {
        postList.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteElement(Post post) {
        notifyItemRemoved((postList.indexOf(post)));
        postList.remove(post);
//        notifyDataSetChanged();
        Log.e("ololo", "deleteElement: " + post.getTitle());
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView titlePost,contentPost,userPostAndGroup;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titlePost = itemView.findViewById(R.id.title_post);
            contentPost = itemView.findViewById(R.id.content_post);
            userPostAndGroup = itemView.findViewById(R.id.user_post);
        }

        public void bind(Post post) {
                titlePost.setText(post.getTitle());
                contentPost.setText(post.getContent());
                userPostAndGroup.setText("User: " + post.getUser() + "\n"
                        + "Group: "+ post.getGroup());
                listeners();
        }

        private void listeners() {
            itemView.setOnLongClickListener(v -> {
                onLongClick.onLongClick(postList.get(getAdapterPosition()));
                return true;
            });
            itemView.setOnClickListener(v -> {
                onClickListener.onClickListener(postList.get(getAdapterPosition()));
            });
        }
    }
}
