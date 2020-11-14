package com.angga.angga12rpl022018.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.angga.angga12rpl022018.Admin.DetailUserActivity;
import com.angga.angga12rpl022018.Admin.list_data_customerActivity;
import com.angga.angga12rpl022018.Admin.list_data_sepedaActivity;
import com.angga.angga12rpl022018.Helper.AppHelper;
import com.angga.angga12rpl022018.Helper.config;
import com.angga.angga12rpl022018.Model.SepedaModel;
import com.angga.angga12rpl022018.Model.UserAdminModel;
import com.angga.angga12rpl022018.R;

import org.json.JSONObject;

import java.util.List;

public class SepedaAdapter extends RecyclerView.Adapter<SepedaAdapter.ItemViewHolder> {
    private Context context;
    private List<SepedaModel> mList;
    private boolean mBusy = false;
    private String mLoginToken = "";
    private ProgressDialog mProgressDialog;
    private list_data_sepedaActivity mAdminActivity;
    private TextView tvNamaSepeda;

    public SepedaAdapter(Context context, List<SepedaModel> mList, Activity AdminDataActivity) {

        this.context = context;
        this.mList = mList;
        this.mAdminActivity = (list_data_sepedaActivity) AdminDataActivity;

    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_list_data_sepeda, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        final SepedaModel Amodel = mList.get(i);
        itemViewHolder.bind(Amodel);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamasepeda, tvJenissepeda, tvHargaSewa;
        private ImageView ivDelete;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamasepeda = itemView.findViewById(R.id.tvNamaSepeda);
            tvJenissepeda = itemView.findViewById(R.id.tvJenisSepeda);
            tvHargaSewa = itemView.findViewById(R.id.tvHargaSewa);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }

        private void bind(final SepedaModel Amodel) {
            tvNamasepeda.setText(Amodel.getNamaSepeda());
            tvJenissepeda.setText(Amodel.getJenisSepeda());
            tvHargaSewa.setText(Amodel.getHargaSewa());
            ivDelete.setOnClickListener(new View.OnClickListener() {
                private void doNothing() {

                }

                @Override
                public void onClick(View view) {
                    if (mBusy) {
                        Toast.makeText(context, "Harap tunggu", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("Hapus data sepeda ?");
                    alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        private void doNothing() {

                        }

                        public void onClick(DialogInterface arg0, int arg1) {
                            deleteData(String.valueOf(Amodel.getId()));
                        }
                    });
                    alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        private void doNothing() {

                        }

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                        }
                    });

                    //Showing the alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }

        private void deleteData(String id) {
            if (mBusy) {
                Toast.makeText(context, "Harap tunggu", Toast.LENGTH_SHORT).show();
                return;
            }

            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setMessage("Proses ...");
            mProgressDialog.setCancelable(true);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Batal", new DialogInterface.OnClickListener() {
                private void doNothing() {

                }

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            if (!mProgressDialog.isShowing()) mProgressDialog.show();

            Log.d("A", "act:deletedata\n" +
                    "loginToken:" + mLoginToken + "\n" +
                    "uId:" + id);

            AndroidNetworking.post(config.BASE_URL + "deletedata.php")
                    .addBodyParameter("id", id)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject jsonResponse) {
                            mBusy = false;
                            if (mProgressDialog != null) mProgressDialog.dismiss();

                            String message = jsonResponse.optString("message");

                            if (message.equalsIgnoreCase("success")) {
                                mAdminActivity.getUserList();
                            } else {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            mBusy = false;
                            if (mProgressDialog != null) mProgressDialog.dismiss();

                            Toast.makeText(context, config.TOAST_AN_EROR, Toast.LENGTH_SHORT).show();
                            Log.d("A", "onError: " + anError.getErrorBody());
                            Log.d("A", "onError: " + anError.getLocalizedMessage());
                            Log.d("A", "onError: " + anError.getErrorDetail());
                            Log.d("A", "onError: " + anError.getResponse());
                            Log.d("A", "onError: " + anError.getErrorCode());
                        }
                    });

        }
    }

}
