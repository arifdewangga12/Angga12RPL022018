package com.angga.angga12rpl022018;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.angga.angga12rpl022018.Adapter.AdminUserAdapter;
import com.angga.angga12rpl022018.Admin.DetailAdminActivity;
import com.angga.angga12rpl022018.Admin.list_data_customerActivity;
import com.angga.angga12rpl022018.Admin.list_data_sepedaActivity;

public class AdminActivity extends AppCompatActivity {

   private CardView daftarsepeda, daftarcustomer;
   private ImageView ivProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        daftarsepeda = findViewById(R.id.daftarsepeda);
        daftarsepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, list_data_sepedaActivity.class);
                startActivity(i);
            }
        });
        daftarcustomer = findViewById(R.id.daftarcustomer);
        daftarcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, list_data_customerActivity.class);
                startActivity(i);
            }
        });
        ivProfile = findViewById(R.id.ivProfile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this, DetailAdminActivity.class);
                startActivity(i);
            }
        });
    }
}