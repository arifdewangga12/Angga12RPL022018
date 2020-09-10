package com.angga.angga12rpl022018.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.angga.angga12rpl022018.Adapter.AdminUserAdapter;
import com.angga.angga12rpl022018.AdminActivity;
import com.angga.angga12rpl022018.Helper.AppHelper;
import com.angga.angga12rpl022018.Model.UserAdminModel;
import com.angga.angga12rpl022018.R;
import com.angga.angga12rpl022018.Helper.config;
import com.angga.angga12rpl022018.RS;
import com.angga.angga12rpl022018.initial;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class list_data_customerActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView ivAdd, ivBack;

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rv;

    private ArrayList<UserAdminModel> mList = new ArrayList<>();
    private AdminUserAdapter mAdapter;

    private String mLoginToken = "";
    private String mUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mLoginToken = sp.getString(config.LOGIN_TOKEN_SHARED_PREF,"");
        mUserId = sp.getString(config.LOGIN_ID_SHARED_PREF, "");

        if(mLoginToken.equalsIgnoreCase("") || mUserId.equalsIgnoreCase("")) {
            finish();
            config.forceLogout(list_data_customerActivity.this);
        }

        setContentView(R.layout.activity_list_data_customer);
        binding();
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.post(new Runnable() {
            private void doNothing() {

            }

            @Override
            public void run() {
                getUserList();
            }
        });

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
    private void binding() {
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rv = findViewById(R.id.rvUserManage);
        swipeRefresh = findViewById(R.id.swipeRefresh);

    }

    @Override
    public void onRefresh() {
        getUserList();
    }
    public void show(){
        mAdapter = new AdminUserAdapter (list_data_customerActivity.this, mList, mLoginToken, list_data_customerActivity.this);

        rv.setAdapter(mAdapter);
    }

    public void getUserList() {
        swipeRefresh.setRefreshing(true);
        HashMap<String, String> body = new HashMap<>();
        body.put("act", "get_konsumen");
        body.put("loginToken", mLoginToken);
        AndroidNetworking.post(config.BASE_URL + "getdatauser.php")
                .addBodyParameter(body)
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(((RS) getApplication()).getOkHttpClient())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        swipeRefresh.setRefreshing(false);
                        if (mAdapter != null) {
                            mAdapter.clearData();
                            mAdapter.notifyDataSetChanged();
                        }
                        if (mList != null) mList.clear();
                        Log.d("A", "res" + response);

                        String status = response.optString(config.RESPONSE_STATUS_FIELD);
                        String message = response.optString(config.RESPONSE_MESSAGE_FIELD);
                        if (status.trim().equalsIgnoreCase(config.RESPONSE_STATUS_VALUE_SUCCESS)) {
                            JSONArray payload = response.optJSONArray(config.RESPONSE_PAYLOAD_FIELD);

                            if (payload == null) {
                                Toast.makeText(list_data_customerActivity.this,"Tidak ada user",Toast.LENGTH_SHORT).show();                                return;
                            }

                            for (int i = 0; i < payload.length(); i++) {
                                JSONObject dataUser = payload.optJSONObject(i);
                                UserAdminModel item = AppHelper.mapUserAdminModel(dataUser);
                                mList.add(item);
                            }
                            show();
                        } else {
                            Toast.makeText(list_data_customerActivity.this, message, Toast.LENGTH_SHORT).show();
                            JSONObject payload = response.optJSONObject(config.RESPONSE_PAYLOAD_FIELD);
                            if (payload != null && payload.optString("API_ACTION").equalsIgnoreCase("LOGOUT"))
                                config.forceLogout(list_data_customerActivity.this);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        swipeRefresh.setRefreshing(false);
                        Toast.makeText(list_data_customerActivity.this, config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
                        Log.d("A", "onError: " + anError.getErrorBody());
                        Log.d("A", "onError: " + anError.getLocalizedMessage());
                        Log.d("A", "onError: " + anError.getErrorDetail());
                        Log.d("A", "onError: " + anError.getResponse());
                        Log.d("A", "onError: " + anError.getErrorCode());
                    }
                });

    }
}