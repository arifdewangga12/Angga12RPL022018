package com.angga.angga12rpl022018.Helper;

import android.content.Context;
import android.os.Bundle;

import com.angga.angga12rpl022018.Model.UserAdminModel;

import org.json.JSONObject;

public class AppHelper {
    public static UserAdminModel mapUserAdminModel(JSONObject rowData) {
        UserAdminModel item = new UserAdminModel();
        item.setId(rowData.optString("id"));
        item.setUsername(rowData.optString("username"));
        item.setAlamat(rowData.optString("alamat"));
        item.setEmail(rowData.optString("email"));
        item.setNoKtp(rowData.optString("noktp"));
        item.setNoTlp(rowData.optString("notlp"));
        item.setRoleUser(rowData.optString("roleuser"));


        return item;
    }

    public static void goToUserAdminDetail(Context context, UserAdminModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("Id", String.valueOf(rowData.getId()));
        bundle.putString("Email", rowData.getEmail().toUpperCase());
        bundle.putString("Alamat", rowData.getAlamat().toUpperCase());
        bundle.putString("NoKtp", rowData.getNoKtp());
        bundle.putString("NoTlp", rowData.getNoTlp());
        bundle.putString("Username", rowData.getUsername().toUpperCase());
        bundle.putString("RoleUser", rowData.getRoleUser().toUpperCase());

//        Intent i = new Intent(context, CustomerDetailActivity.class);
//        i.putExtra("extra_customer", rowData);
//        context.startActivity(i);
    }

}
