package io.github.luccastanan.bank.loginScreen;

import android.os.Parcel;

public class UserAccountViewModel extends UserAccountModel {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public UserAccountViewModel() {
    }

    protected UserAccountViewModel(Parcel in) {
        super(in);
    }

    public static final Creator<UserAccountViewModel> CREATOR = new Creator<UserAccountViewModel>() {
        @Override
        public UserAccountViewModel createFromParcel(Parcel source) {
            return new UserAccountViewModel(source);
        }

        @Override
        public UserAccountViewModel[] newArray(int size) {
            return new UserAccountViewModel[size];
        }
    };
}
