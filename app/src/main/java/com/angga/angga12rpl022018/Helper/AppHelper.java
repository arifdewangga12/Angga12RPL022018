package com.angga.angga12rpl022018.Helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.angga.angga12rpl022018.Admin.DetailUserActivity;
import com.angga.angga12rpl022018.Model.UserAdminModel;

import org.json.JSONObject;

public class AppHelper {
    public static UserAdminModel mapUserAdminModel(JSONObject rowData) {
        UserAdminModel item = new UserAdminModel();
        item.setId(rowData.optString("id"));
        item.setRoleUser(rowData.optString("roleuser"));
        item.setEmail(rowData.optString("email"));
        item.setUsername(rowData.optString("username"));
        item.setNoKtp(rowData.optString("noktp"));
        item.setNoTlp(rowData.optString("notlp"));
        item.setAlamat(rowData.optString("alamat"));



        return item;
    }

    public static void goToUserAdminDetail(Context context, UserAdminModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("id", String.valueOf(rowData.getId()));
        bundle.putString("roleuser", rowData.getRoleUser().toUpperCase());
        bundle.putString("email", rowData.getEmail().toUpperCase());
        bundle.putString("username", rowData.getUsername().toUpperCase());
        bundle.putString("noktp", rowData.getNoKtp());
        bundle.putString("notlp", rowData.getNoTlp());
        bundle.putString("alamat", rowData.getAlamat().toUpperCase());



        Intent i = new Intent(context, DetailUserActivity.class);
        i.putExtra("extra_user", rowData);
        context.startActivity(i);
    }

}
