package io.github.luccastanan.bank;

import android.os.Parcel;

public class ErrorViewModel extends ErrorModel {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public ErrorViewModel() {
    }

    protected ErrorViewModel(Parcel in) {
        super(in);
    }

    public static final Creator<ErrorViewModel> CREATOR = new Creator<ErrorViewModel>() {
        @Override
        public ErrorViewModel createFromParcel(Parcel source) {
            return new ErrorViewModel(source);
        }

        @Override
        public ErrorViewModel[] newArray(int size) {
            return new ErrorViewModel[size];
        }
    };
}
