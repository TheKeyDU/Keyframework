package com.maosong.tools.glide;

import android.content.res.Resources;
import android.graphics.*;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * 圆角
 */
public class RoundTransformation extends BitmapTransformation {
    private float radius = 0f;

    private static final int VERSION = 1;
    private static final String ID = "com.maosong.tools.roundgray." + VERSION;

    public RoundTransformation(int dp) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    public String toString() {
        return "GrayTransformation()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GrayTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID).getBytes(CHARSET));
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, bitmap, radius);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source, float radius) {
        if (source == null) {
            return null;
        }
        if (radius < 0.1f) {
            return source;
        }
        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
//        RectF rectRound = new RectF(0f, 100f, source.getWidth(), source.getHeight());
//        canvas.drawRect(rectRound, paint);
        return result;
    }
}
