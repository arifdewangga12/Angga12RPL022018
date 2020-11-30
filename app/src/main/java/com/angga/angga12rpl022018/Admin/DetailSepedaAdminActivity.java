package com.angga.angga12rpl022018.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.angga.angga12rpl022018.Adapter.AdminUserAdapter;
import com.angga.angga12rpl022018.Adapter.SepedaAdapter;
import com.angga.angga12rpl022018.Helper.config;
import com.angga.angga12rpl022018.Model.SepedaModel;
import com.angga.angga12rpl022018.Model.UserAdminModel;
import com.angga.angga12rpl022018.R;
import com.angga.angga12rpl022018.initial;

import org.json.JSONObject;

import java.util.HashMap;

public class DetailSepedaAdminActivity extends AppCompatActivity {
    ImageView ivBack;
    private Button btneditsepeda;
    private TextView tvNamaSepeda,tvKodeSepeda,tvMerkSepeda,tvJenisSepeda,tvWarnaSepeda,tvHargaSewa;

    private SwipeRefreshLayout swipeRefresh;
    private SepedaModel model;
    private String U_ID;
    private SepedaAdapter mAdapter;
    private RecyclerView rv;

    private String mLoginToken = "";
    private String mUserId = "";
    private String mNamaSepeda, mKodeSepeda, mJenisSepeda, mMerkSepeda, mWarnaSepeda,mHargaSewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sepeda_admin);

        btneditsepeda = findViewById(R.id.btneditsepeda);
        btneditsepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailSepedaAdminActivity.this,EditSepedaAdminActivity.class);
                startActivity(i);
            }
        });

        binding();
        model = getIntent().getExtras().getParcelable("extra_data");
        if(/*bundle*/ model != null) {
            U_ID = model.getId();

            tvNamaSepeda.setText(model.getNamaSepeda());
            tvKodeSepeda.setText(model.getKodeSepeda());
            tvJenisSepeda.setText(model.getJenisSepeda());
            tvMerkSepeda.setText(model.getMerkSepeda());
            tvWarnaSepeda.setText(model.getWarnasepeda());
            tvHargaSewa.setText(model.getHargaSewa());
        }

    }

    private void binding() {
        tvNamaSepeda = findViewById(R.id.tvNamaSepeda);
        tvKodeSepeda = findViewById(R.id.tvKodeSepeda);
        tvJenisSepeda = findViewById(R.id.tvJenisSepeda);
        tvMerkSepeda = findViewById(R.id.tvMerkSepeda);
        tvWarnaSepeda = findViewById(R.id.tvWarnaSepeda);
        tvHargaSewa = findViewById(R.id.tvHargaSewa);
        btneditsepeda = findViewById(R.id.btneditsepeda);
        btneditsepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailSepedaAdminActivity.this,EditSepedaAdminActivity.class);
                startActivity(i);
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
}