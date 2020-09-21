package com.angga.angga12rpl022018.Admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.angga.angga12rpl022018.AdminActivity;
import com.angga.angga12rpl022018.R;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.HashMap;

public class list_data_sepedaActivity extends AppCompatActivity {

    private ImageView gambarsepeda, ivBack;
    private TextView tvNamaSepeda,tvHargaSewa;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_sepeda);

        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }

            @Override
            public void onClick(View v) {
                finish();
            }
        });

            String url = "http://192.168.6.229/rentalsepeda/getdata.php";
        gambarsepeda = (ImageView)findViewById(R.id.gambarsepeda);
        tvNamaSepeda = (TextView)findViewById(R.id.tvNamaSepeda);
        tvHargaSewa = (TextView)findViewById(R.id.tvHargaSewa);

        requestQueue = Volley.newRequestQueue(list_data_sepedaActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("sepeda");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("namasepeda", json.getString("namasepeda"));
                        map.put("kodesepeda", json.getString("kodesepeda"));
                        map.put("merksepeda", json.getString("merksepeda"));
                        map.put("jenissepeda", json.getString("jenissepeda"));
                        map.put("hargasewa", json.getString("hargasewa"));
                        map.put("warnasepeda", json.getString("warnasepeda"));
                        map.put("gambarsepeda", json.getString("gambarsepeda"));
                        list_data.add(map);
                    }
//                    Glide.with(list_data_sepedaActivity.this)
//                            .("http://192.168.6.229/rentalsepeda/img/imgproduk1.jpg" + list_data.get(0).get("gambarsepeda"))
//                            .placeholder(R.drawable.ic_account)
//                            .into(gambarsepeda);
//                    tvNamaSepeda.setText(list_data.get(0).get("namasepeda"));
//                    tvHargaSewa.setText(list_data.get(0).get("hargasewa"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override            public void onErrorResponse(VolleyError error) {
                Toast.makeText(list_data_sepedaActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
