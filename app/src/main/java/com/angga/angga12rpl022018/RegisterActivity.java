package com.angga.angga12rpl022018;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.StringRequest;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    TextView Register,Username,Password,email,noktp,notlp,alamat;
    private static String URL_REGIS="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = findViewById(R.id.txtusernameReg);
        Password = findViewById(R.id.txtpasswordReg);
        email = findViewById(R.id.txtemailReg);
        noktp = findViewById(R.id.txtnoktpReg);
        notlp = findViewById(R.id.txtnotlpReg);
        alamat = findViewById(R.id.txtalamatReg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void Register(){
        btnRegister.setVisibility(View.GONE);

        final String Username = this.Username.getText().toString().trim();
        final String Password = this.Password.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String noktp = this.noktp.getText().toString().trim();
        final String notlp = this.notlp.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();


    }
}
