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

public class DetailUserActivity extends AppCompatActivity {
    ImageView ivBack;
    private Button btnlogout;
    private TextView tvUsername,tvEmail,tvNoTlp,tvNoKtp,tvAlamat,tvRoleUser;

    private SwipeRefreshLayout swipeRefresh;
    private ArrayList<UserAdminModel> mList = new ArrayList<>();
    private AdminUserAdapter mAdapter;
    private RecyclerView rv;

    private String mLoginToken = "";
    private String mUserId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        btnlogout = findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }
            @Override
            public void onClick(View v) {
                finish();
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
        new AlertDialog.Builder(DetailUserActivity.this)
                .setTitle("Logout")
                .setMessage("Anda yakin akan logout ?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    private void doNothing() {

                    }

                    public void onClick(DialogInterface arg0, int arg1) {
                        config.forceLogout(DetailUserActivity.this);
                    }
                }).create().show();
    }
//    public void getUserList() {
//        swipeRefresh.setRefreshing(true);
//        AndroidNetworking.get(config.BASE_URL + "getdatauser.php")
//                .setPriority(Priority.LOW)
//                .setOkHttpClient(((initial) getApplication()).getOkHttpClient())
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        swipeRefresh.setRefreshing(false);
//                        if (mAdapter != null) {
//                            mAdapter.clearData();
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        if (mList != null) mList.clear();
//                        Log.d("RBA", "res" + response);
//                        try {
//                            Log.i("AB", "respo: "+response);
//                            //Loop the Array
//                            for(int i=0;i < response.length();i++) {
//                                JSONObject data = response.getJSONObject(i);
//                                Log.e("ADF", "ponse: "+data );
//                                UserAdminModel item = AppHelper.mapUserAdminModel(data);
//                                mList.add(item);
//                            }
//                            mAdapter = new AdminUserAdapter(DetailUserActivity.this, mList, DetailUserActivity.this);
//                            rv.setAdapter(mAdapter);
//                        } catch(JSONException e) {
//                            Log.e("log_tag", "Error parsing data "+e.toString());
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        swipeRefresh.setRefreshing(false);
//                        Toast.makeText(DetailUserActivity.this, config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
//                        Log.d("A", "onError1: " + anError.getErrorBody());
//                        Log.d("A", "onError: " + anError.getLocalizedMessage());
//                        Log.d("A", "onError: " + anError.getErrorDetail());
//                        Log.d("A", "onError: " + anError.getResponse());
//                        Log.d("A", "onError: " + anError.getErrorCode());
//                    }
//                });
//
//    }
    }
