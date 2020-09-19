package com.angga.angga12rpl022018.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.angga.angga12rpl022018.Adapter.AdminUserAdapter;
import com.angga.angga12rpl022018.Helper.AppHelper;
import com.angga.angga12rpl022018.Helper.config;
import com.angga.angga12rpl022018.Model.UserAdminModel;
import com.angga.angga12rpl022018.R;
import com.angga.angga12rpl022018.initial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailAdminActivity extends AppCompatActivity {
    ImageView ivBack;
    private Button btnlogout;
    private TextView tvUsername,tvEmail,tvNoTlp,tvNoKtp,tvAlamat,tvRoleUser;

    private SwipeRefreshLayout swipeRefresh;
    private ArrayList<UserAdminModel> mList = new ArrayList<>();
    private AdminUserAdapter mAdapter;
    private RecyclerView rv;
    private UserAdminModel Amodel;

    private String mLoginToken = "";
    private String mUserId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);

        btnlogout = findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void logout() {
        new AlertDialog.Builder(DetailAdminActivity.this)
                .setTitle("Logout")
                .setMessage("Anda yakin akan logout ?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    private void doNothing() {

                    }

                    public void onClick(DialogInterface arg0, int arg1) {
                        config.forceLogout(DetailAdminActivity.this);
                    }
                }).create().show();
    }



}
