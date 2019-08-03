package io.github.luccastanan.bank;

import android.os.Parcel;
import android.os.Parcelable;

public class ErrorModel implements Parcelable {
    int code;
    String message;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.message);
    }

    public ErrorModel() {
    }

    protected ErrorModel(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ErrorModel> CREATOR = new Parcelable.Creator<ErrorModel>() {
        @Override
        public ErrorModel createFromParcel(Parcel source) {
            return new ErrorModel(source);
        }

        @Override
        public ErrorModel[] newArray(int size) {
            return new ErrorModel[size];
        }
    };
}
