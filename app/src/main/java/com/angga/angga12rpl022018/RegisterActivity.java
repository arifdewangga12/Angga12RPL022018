package com.angga.angga12rpl022018;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    TextView Register;
    EditText username,password,email,noktp,notlp,alamat;
    private static String URL_REGISTER = "http://192.168.43.59/aksi/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register = findViewById(R.id.tvRegister);
        username = findViewById(R.id.txtusernameReg);
        password = findViewById(R.id.txtpasswordReg);
        email = findViewById(R.id.txtemailReg);
        noktp = findViewById(R.id.txtnoktpReg);
        notlp = findViewById(R.id.txtnotlpReg);
        alamat = findViewById(R.id.txtalamatReg);
        btnRegister = findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Register();
            }
        });

    }
    private void Register(){
        btnRegister.setVisibility(View.GONE);

        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String noktp = this.noktp.getText().toString().trim();
        final String notlp = this.notlp.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");

                    if (succes.equals("1")){
                        Toast.makeText(RegisterActivity.this, "Register succes!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                    btnRegister.setVisibility(View.VISIBLE);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError Error) {
                        Toast.makeText(RegisterActivity.this, "Register error!" + Error.toString(), Toast.LENGTH_SHORT).show();
                        btnRegister.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                params.put("email",email);
                params.put("noktp",noktp);
                params.put("notlp",notlp);
                params.put("alamat",alamat);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
