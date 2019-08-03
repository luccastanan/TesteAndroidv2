package io.github.luccastanan.bank.loginScreen;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAccountModel implements Parcelable {

    public int userId;
    public String name;
    public String bankAccount;
    public String agency;
    public double balance;

    public UserAccountModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.name);
        dest.writeString(this.bankAccount);
        dest.writeString(this.agency);
        dest.writeDouble(this.balance);
    }

    protected UserAccountModel(Parcel in) {
        this.userId = in.readInt();
        this.name = in.readString();
        this.bankAccount = in.readString();
        this.agency = in.readString();
        this.balance = in.readDouble();
    }

    public static final Creator<UserAccountModel> CREATOR = new Creator<UserAccountModel>() {
        @Override
        public UserAccountModel createFromParcel(Parcel source) {
            return new UserAccountModel(source);
        }

        @Override
        public UserAccountModel[] newArray(int size) {
            return new UserAccountModel[size];
        }
    };
}
