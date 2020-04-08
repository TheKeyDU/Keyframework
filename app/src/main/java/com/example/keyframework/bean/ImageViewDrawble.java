package com.example.keyframework.bean;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * Created by Administrator on 2020/4/8 15:06
 */
public class ImageViewDrawble implements Parcelable {
    public ImageViewDrawble(Drawable drawable) {
        this.drawable = drawable;
    }

    public ImageViewDrawble() {
    }

    protected ImageViewDrawble(Parcel in) {
    }

    public static final Creator<ImageViewDrawble> CREATOR = new Creator<ImageViewDrawble>() {
        @Override
        public ImageViewDrawble createFromParcel(Parcel in) {
            return new ImageViewDrawble(in);
        }

        @Override
        public ImageViewDrawble[] newArray(int size) {
            return new ImageViewDrawble[size];
        }
    };

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable drawable;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
