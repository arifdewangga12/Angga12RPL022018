package com.angga.angga12rpl022018.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.angga.angga12rpl022018.AdminActivity;
import com.angga.angga12rpl022018.Model.UserAdminModel;
import com.angga.angga12rpl022018.R;

import java.util.ArrayList;
import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.ItemViewHolder> {
    private Context context;
    private List<UserAdminModel> mList;  private String mLoginToken = "";
    private boolean mBusy = false;
    private AdminActivity mAdminUserActivity;


    public AdminUserAdapter(Context context, List<UserAdminModel> mList, String loginToken, Activity AdminUserActivity) {
        this.context = context;
        this.mList = mList;
        this.mLoginToken = loginToken;
        this.mAdminUserActivity = (AdminActivity) AdminUserActivity;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_list_data_customer, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUserAdapter.ItemViewHolder itemViewHolder, int i) {
        final UserAdminModel Amodel = mList.get(i);
        itemViewHolder.tv_username.setText(Amodel.getUsername());
        itemViewHolder.tv_notlp.setText(Amodel.getNoTlp());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username, tv_notlp;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tvUsername);
            tv_notlp = itemView.findViewById(R.id.tvNotlp);
        }
    }
}