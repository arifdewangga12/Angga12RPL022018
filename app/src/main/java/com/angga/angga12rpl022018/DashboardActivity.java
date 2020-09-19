package com.angga.angga12rpl022018;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.angga.angga12rpl022018.Admin.TambahUserActivity;
import com.angga.angga12rpl022018.Admin.list_data_customerActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
 private Button btnprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
            btnprofile = findViewById(R.id.btnprofile);
            btnprofile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(DashboardActivity.this, UserProfileActivity.class);
                    startActivity(i);
                }
            });
    }
}