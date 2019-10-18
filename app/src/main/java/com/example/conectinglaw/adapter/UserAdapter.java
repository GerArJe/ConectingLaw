package com.example.conectinglaw.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conectinglaw.R;
import com.example.conectinglaw.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    ArrayList<User> users;
    int layout;
    onItemClickListener listener;

    public UserAdapter(ArrayList<User> users, int layout, onItemClickListener listener) {
        this.users = users;
        this.layout = layout;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.setData(user, listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPhotoProfile;
        TextView txtUsernameCardview, txtTypeCaseCardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhotoProfile = itemView.findViewById(R.id.iv_photoProfile);
            txtUsernameCardview = itemView.findViewById(R.id.txt_username_cardview);
            txtTypeCaseCardview = itemView.findViewById(R.id.txt_typeCase_cardview);
        }

        public void setData(final User user, final onItemClickListener listener){
            txtUsernameCardview.setText(user.getName());
//            Picasso.get()
//                    .load(user.getPhotoUrl())
//                    .error(R.drawable.user_icon)
//                    .centerCrop()
//                    .into(ivPhotoProfile);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user, getAdapterPosition());
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(User user, int position);
    }
}
