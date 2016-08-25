package com.example.sample.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${manjula} on ${4-6-2016}.
 */
public class PagePracelable implements Parcelable {
    private int page_id;
    private String page_name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page_id);
        dest.writeString(this.page_name);
    }

    public PagePracelable() {
    }

    protected PagePracelable(Parcel in) {
        this.page_id = in.readInt();
        this.page_name = in.readString();
    }

    public static final Parcelable.Creator<PagePracelable> CREATOR = new Parcelable.Creator<PagePracelable>() {
        @Override
        public PagePracelable createFromParcel(Parcel source) {
            return new PagePracelable(source);
        }

        @Override
        public PagePracelable[] newArray(int size) {
            return new PagePracelable[size];
        }
    };

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }
}
