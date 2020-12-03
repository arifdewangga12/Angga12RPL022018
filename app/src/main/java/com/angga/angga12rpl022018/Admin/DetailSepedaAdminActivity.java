package com.angga.angga12rpl022018.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
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
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

public class DetailSepedaAdminActivity extends AppCompatActivity implements IPickResult {
    private ImageView ivBack, ivGambarSepeda;
    private Button btneditsepeda, divDelete;
    private TextView tvNamaSepeda,tvKodeSepeda,tvMerkSepeda,tvJenisSepeda,tvWarnaSepeda,tvHargaSewa;

    private SwipeRefreshLayout swipeRefresh;
    private SepedaModel model;
    private String U_ID;
    private SepedaAdapter mAdapter;
    private RecyclerView rv;

    private Bitmap mSelectedImage;
    private String mSelectedImagePath;
    File mSelectedFileBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sepeda_admin);

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
            Picasso.get()
                    .load(config.BASE_URL+"img/"+ model.getGambarSepeda())
                    .into(ivGambarSepeda);
        }

    }

    private void binding() {
        ivGambarSepeda = findViewById(R.id.ivSepeda);
        ivGambarSepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(DetailSepedaAdminActivity.this);
                new PickSetup().setCameraButtonText("Gallery");
            }
        });
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
                boolean mIsFormFilled = true;
                final String namasepeda = tvNamaSepeda.getText().toString();
                final String kodesepeda = tvKodeSepeda.getText().toString().trim();
                final String jenissepeda = tvJenisSepeda.getText().toString();
                final String merksepeda = tvMerkSepeda.getText().toString();
                final String warnasepeda = tvWarnaSepeda.getText().toString();
                final String hargasewa = tvHargaSewa.getText().toString();


                if (namasepeda.isEmpty() || kodesepeda.isEmpty() || jenissepeda.isEmpty() || merksepeda.isEmpty() || warnasepeda.isEmpty() || hargasewa.isEmpty()){
                    Toast.makeText(DetailSepedaAdminActivity.this, "Harap lengkapi kolom Data Sepeda!", Toast.LENGTH_SHORT).show();
                    mIsFormFilled = false;
                }


                if (mIsFormFilled) {
                    HashMap<String, String> body = new HashMap<>();
                    body.put("id", U_ID);
                    body.put("namasepeda", namasepeda);
                    body.put("kodesepeda", kodesepeda);
                    body.put("jenissepeda", jenissepeda);
                    body.put("merksepeda", merksepeda);
                    body.put("warnasepeda", warnasepeda);
                    body.put("hargasewa", hargasewa);

                    AndroidNetworking.upload(config.BASE_URL+"updatedata.php")
                            .addMultipartFile("gambarsepeda",mSelectedFileBanner)
                            .addMultipartParameter(body)
                            .setPriority(Priority.MEDIUM)
                            .setOkHttpClient(((initial) getApplication()).getOkHttpClient())
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

//                                            String status = response.getString(config.RESPONSE_STATUS_FIELD);
                                        String message = response.getString(config.RESPONSE_MESSAGE_FIELD);

                                        Toast.makeText(DetailSepedaAdminActivity.this, message, Toast.LENGTH_LONG).show();
                                        Log.d("f", "response: "+message);
                                        if (message.equalsIgnoreCase(config.RESPONSE_STATUS_VALUE_SUCCESS)) {

                                            Intent intent = new Intent(DetailSepedaAdminActivity.this, list_data_sepedaActivity.class);
                                            startActivity(intent);
                                            finishAffinity();
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.d("b", "JSONException: " + e.getMessage());
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
//                                        mProgress.dismiss();
                                    Toast.makeText(DetailSepedaAdminActivity.this, config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
                                    Log.d("ab", "onError: " + anError.getErrorBody());
                                    Log.d("ab", "onError: " + anError.getLocalizedMessage());
                                    Log.d("ab", "onError: " + anError.getErrorDetail());
                                    Log.d("ab", "onError: " + anError.getResponse());
                                    Log.d("ab", "onError: " + anError.getErrorCode());
                                }
                            });
                }
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

    @Override
    public void onPickResult(PickResult r) {
        if(r.getError() == null){
            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));
                mSelectedImagePath = fileku.getAbsolutePath();
                mSelectedFileBanner = new File(mSelectedImagePath);
                mSelectedImage=r.getBitmap();
                ivGambarSepeda.setImageBitmap(mSelectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(DetailSepedaAdminActivity.this, r.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}