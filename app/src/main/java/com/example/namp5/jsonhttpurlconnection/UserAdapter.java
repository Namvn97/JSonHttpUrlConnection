package com.example.namp5.jsonhttpurlconnection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by namp5 on 12/13/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<Users> mUsers;
    private LayoutInflater mLayoutInflater;

    public UserAdapter(List<Users> mUsers) {
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = mLayoutInflater.inflate(R.layout.item_repo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolde, int position) {
        viewHolde.bindData(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mLanguage;
        private TextView mTextName;
        private TextView mId;
        private ImageView mAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLanguage = itemView.findViewById(R.id.text_repo_language);
            mTextName = itemView.findViewById(R.id.text_repo_name);
            mAvatar = itemView.findViewById(R.id.image_avatar);
            mId = itemView.findViewById(R.id.text_repo_id);

        }

        public void bindData(Users user) {
            if (user != null) {
                mLanguage.setText(user.getmLanguage());
                mTextName.setText(user.getmName());
                mId.setText(user.getmOwner().getmId());
                Glide.with(itemView.getContext())
                        .load(user.getmOwner().getmAvatar())
                        .into(mAvatar);
            }
        }
    }
}
