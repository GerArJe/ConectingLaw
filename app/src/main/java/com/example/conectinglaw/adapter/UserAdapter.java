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

    ArrayList<String> nameReferenceChats;
    int layout;
    onItemClickListener listener;

    public UserAdapter(ArrayList<String> nameReferenceChats, int layout, onItemClickListener listener) {
        this.nameReferenceChats = nameReferenceChats;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        String nameReferenceChat = nameReferenceChats.get(position);
        holder.setData(nameReferenceChat, listener);
    }

    @Override
    public int getItemCount() {
        return nameReferenceChats.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNameReferenceChatCardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameReferenceChatCardview = itemView.findViewById(R.id.txt_nameReferenceChat_cardview);
        }

        public void setData(final String nameReferenceChat, final onItemClickListener listener){
            txtNameReferenceChatCardview.setText(nameReferenceChat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(nameReferenceChat, getAdapterPosition());
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(String nameReferenceChat, int position);
    }
}
