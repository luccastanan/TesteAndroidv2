package io.github.luccastanan.bank;

import android.os.Parcel;
import android.os.Parcelable;

public class ErrorModel implements Parcelable {
    int code;
    String message;

    @Override
    public String toString() {
        return "{ code: " + code + ", message: \"message\": " + message + " }";
    }

    public ErrorModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.message);
    }

    protected ErrorModel(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
    }

    public static final Creator<ErrorModel> CREATOR = new Creator<ErrorModel>() {
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
