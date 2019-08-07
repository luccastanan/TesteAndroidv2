package io.github.luccastanan.bank.homeScreen;

import android.os.Parcel;

public class StatementViewModel extends StatementModel {


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public StatementViewModel() {
    }

    protected StatementViewModel(Parcel in) {
        super(in);
    }

    public static final Creator<StatementViewModel> CREATOR = new Creator<StatementViewModel>() {
        @Override
        public StatementViewModel createFromParcel(Parcel source) {
            return new StatementViewModel(source);
        }

        @Override
        public StatementViewModel[] newArray(int size) {
            return new StatementViewModel[size];
        }
    };
}
