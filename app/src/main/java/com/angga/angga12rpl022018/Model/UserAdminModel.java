package com.angga.angga12rpl022018.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAdminModel implements Parcelable {
    private String Id;
    private String Username;
    private String Email;
    private String RoleUser;
    private String NoKtp;
    private String NoTlp;
    private String Alamat;

    public UserAdminModel(Parcel in) {
        Id = in.readString();
        Username = in.readString();
        Email = in.readString();
        RoleUser = in.readString();
        NoKtp = in.readString();
        NoTlp = in.readString();
        Alamat = in.readString();
    }

    public UserAdminModel() {}


    public static Creator<UserAdminModel> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<UserAdminModel> CREATOR = new Creator<UserAdminModel>() {
        @Override
        public UserAdminModel createFromParcel(Parcel in) {
            return new UserAdminModel(in);
        }

        @Override
        public UserAdminModel[] newArray(int size) {
            return new UserAdminModel[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        Id= Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        Username = Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        Email = Email;
    }

    public String getRoleUser() {
        return RoleUser;
    }

    public void setRoleUser(String RoleUser) {
        RoleUser = RoleUser;
    }

    public String getNoTlp() {
        return NoTlp;
    }

    public void setNoTlp(String NoTlp) {
        NoTlp = NoTlp;
    }

    public String getNoKtp() {
        return NoKtp;
    }

    public void setNoKtp(String NoKtp) {
        NoKtp = NoKtp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        Alamat = Alamat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Username);
        dest.writeString(Email);
        dest.writeString(RoleUser);
        dest.writeString(NoKtp);
        dest.writeString(NoTlp);
        dest.writeString(Alamat);
    }

}