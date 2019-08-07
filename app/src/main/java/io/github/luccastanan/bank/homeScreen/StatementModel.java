package io.github.luccastanan.bank.homeScreen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class StatementModel implements Parcelable {

    String title;
    String desc;
    String date;
    double value;

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"{ title: \"%s\", desc: \"%s\", date: \"%s\", value: %f }", title, desc, date, value);
    }

    public StatementModel() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.date);
        dest.writeDouble(this.value);
    }

    protected StatementModel(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.date = in.readString();
        this.value = in.readDouble();
    }

    public static final Creator<StatementModel> CREATOR = new Creator<StatementModel>() {
        @Override
        public StatementModel createFromParcel(Parcel source) {
            return new StatementModel(source);
        }

        @Override
        public StatementModel[] newArray(int size) {
            return new StatementModel[size];
        }
    };
}
