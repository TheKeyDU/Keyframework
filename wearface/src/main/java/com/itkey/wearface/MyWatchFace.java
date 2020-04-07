package com.itkey.wearface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.core.content.ContextCompat;

import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowInsets;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Digital watch face with seconds. In ambient mode, the seconds aren't displayed. On devices with
 * low-bit ambient mode, the text is drawn without anti-aliasing in ambient mode.
 * <p>
 * Important Note: Because watch face apps do not have a default Activity in
 * their project, you will need to set your Configurations to
 * "Do not launch Activity" for both the Wear and/or Application modules. If you
 * are unsure how to do this, please review the "Run Starter project" section
 * in the Google Watch Face Code Lab:
 * https://codelabs.developers.google.com/codelabs/watchface/index.html#0
 */
public class MyWatchFace extends CanvasWatchFaceService {
    private static final Typeface NORMAL_TYPEFACE =
            Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);

    /**
     * Update rate in milliseconds for interactive mode. Defaults to one second
     * because the watch face needs to update seconds in interactive mode.
     */
    private static final long INTERACTIVE_UPDATE_RATE_MS = TimeUnit.SECONDS.toMillis(1);

    /**
     * Handler message id for updating the time periodically in interactive mode.
     */
    private static final int MSG_UPDATE_TIME = 0;

    @Override
    public Engine onCreateEngine() {
        return new Engine();
    }

    private static class EngineHandler extends Handler {
        private final WeakReference<MyWatchFace.Engine> mWeakReference;

        public EngineHandler(MyWatchFace.Engine reference) {
            mWeakReference = new WeakReference<>(reference);
        }

        @Override
        public void handleMessage(Message msg) {
            MyWatchFace.Engine engine = mWeakReference.get();
            if (engine != null) {
                switch (msg.what) {
                    case MSG_UPDATE_TIME:
                        engine.handleUpdateTimeMessage();
                        break;
                }
            }
        }
    }

    private class Engine extends CanvasWatchFaceService.Engine {

        private final Handler mUpdateTimeHandler = new EngineHandler(this);
        private Calendar mCalendar;
        private final BroadcastReceiver mTimeZoneReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mCalendar.setTimeZone(TimeZone.getDefault());
                invalidate();
            }
        };
        private boolean mRegisteredTimeZoneReceiver = false;
        private float mXOffset;
        private float mYOffset;
        private Paint mBackgroundPaint;
        private Paint mTextPaint;
        /**
         * Whether the display supports fewer bits for each color in ambient mode. When true, we
         * disable anti-aliasing in ambient mode.
         */
        private boolean mLowBitAmbient;
        private boolean mBurnInProtection;
        private boolean mAmbient;
        private int SrceenWidth = 400;
        private int SrceenHeigh = 400;

        //--------------------------------------------------------------------

        Paint paint = null;
        Random random = null;
        int width = 0;
        int height = 0;
        int centerX = 200;
        int centerY = 200;
        int lineMaxNumber = 100;
        int MaxCircleradius = 150;
        int fpsNum = 59;
        int MoveX;
        int MoveY;
        boolean forward = true;
        boolean puase = false;
        int arratSize[] = {10, 33, 30, 20, 15, 20, 30, 33, 20, 10, 15, 31};
        Paint paint2 = new Paint();
        sendMessage sendMessage = null;
        StartsPonitBean DrawmLineBean = null;
        ArrayList<StartsPonitBean> StartsLinesBeanlist = null;
        Paint CirclePaint = new Paint();

        Handler mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {


                    case 1: {
                        if (!puase) {
                            for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
                                StartsPonitBean Bean = StartsLinesBeanlist.get(i);
                                //StartsLinesBeanlist.get(i).offsetAddOrSub(r,r/1.5f,true);
                                if (Bean.outOfscreen) {
                                    Bean = null;
                                    StartsLinesBeanlist.remove(i);
                                    StartsLinesBeanlist.add(initALineObject(false));
                                    Log.e("-------删除了", StartsLinesBeanlist.size() + "");
                                } else {

                                    Bean.startAndEndTransform(Bean.speedFloat, forward);

                                }

                                invalidate();

                            }
                        }
                        break;
                    }
                    case 2: {
                        //puase = true;
                        for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
                            StartsPonitBean Bean = StartsLinesBeanlist.get(i);
                            Bean.calculateNow();

                        }
                        invalidate();
                        break;
                    }
                    case 3: {
                        puase = false;
                        invalidate();
                        break;
                    }

                }

         /*   for (int i = 0; i <= 2; i++) {
            }*/

                //    Log.e("------- ", StartsLinesBeanlist.size() + "");


            }


        };


        //-------------------------------------------


        synchronized private StartsPonitBean initALineObject(Boolean from0) {
            int randomSize = random.nextInt(arratSize.length);
            int RandomMaxCircleradius = arratSize[randomSize];

            // int RandomMaxCircleradius = MaxCircleradius;
            int CirclerOutOFScreenX = 0 - 2 * RandomMaxCircleradius;
            int CirclerOutOFScreenY = 0 - 2 * RandomMaxCircleradius;
            int CircleradiusD = 2 * RandomMaxCircleradius;

            int startx = 0;
            int starty = 0;
            int ramodNum = random.nextInt(4);
            switch (ramodNum) {
                case 0: {

                    startx = random.nextInt(SrceenWidth + 2 * CircleradiusD) - CircleradiusD;
                    starty = CirclerOutOFScreenY;
                    break;
                }
                case 1: {
                    startx = SrceenWidth + CircleradiusD;
                    starty = random.nextInt(SrceenHeigh);
                    break;

                }
                case 2: {
                    startx = random.nextInt(SrceenWidth + 2 * CircleradiusD) - CircleradiusD;
                    starty = SrceenHeigh + CircleradiusD;
                    break;

                }
                case 3: {
                    startx = CirclerOutOFScreenX;
                    starty = random.nextInt(SrceenHeigh);
                    break;

                }

            }
            float sl;
            if (from0) {
                sl = 0;
            } else {
                sl = random.nextInt(100);
            }
            //  Log.e("---" + ramodNum + "   ", "x:" + startx + " y:" + starty);
            StartsPonitBean startsLinesBean = new StartsPonitBean(startx, starty, centerX, centerY, sl, RandomMaxCircleradius, randomSize);

            return startsLinesBean;
        }


        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);

            setWatchFaceStyle(new WatchFaceStyle.Builder(MyWatchFace.this)
                    .setAcceptsTapEvents(true)
                    .build());

            mCalendar = Calendar.getInstance();

            Resources resources = MyWatchFace.this.getResources();
            mYOffset = resources.getDimension(R.dimen.digital_y_offset);

            // Initializes background.
            mBackgroundPaint = new Paint();
            mBackgroundPaint.setColor(
                    ContextCompat.getColor(getApplicationContext(), R.color.background));


            // Initializes Watch Face.
            mTextPaint = new Paint();
            mTextPaint.setTypeface(NORMAL_TYPEFACE);
            mTextPaint.setAntiAlias(true);
            mTextPaint.setColor(
                    ContextCompat.getColor(getApplicationContext(), R.color.digital_text));

            StartsLinesBeanlist = new ArrayList<>();
            random = new Random();
            initPaint();
            initLines();
            sendMessage = new sendMessage();

        }

        class sendMessage extends Thread {
            @Override
            public void run() {
                super.run();
                super.run();
                try {
                    Thread.sleep(1000 / fpsNum);
                    Message message = new Message();
                    message.what = 1;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private void initLines() {

            for (int i = 0; i < lineMaxNumber; i++) {
                StartsLinesBeanlist.add(initALineObject(false));

            }


        }

        private void initPaint() {
            paint = new Paint();
            paint.setStrokeWidth(5);
            Paint paint2 = new Paint();
            paint2.setARGB(80, 255, 255, 255);
            paint2.setTextSize(10);
        }


        @Override
        public void onDestroy() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);
            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (visible) {
                registerReceiver();

                // Update time zone in case it changed while we weren't visible.
                mCalendar.setTimeZone(TimeZone.getDefault());
                invalidate();
            } else {
                unregisterReceiver();
            }

            // Whether the timer should be running depends on whether we're visible (as well as
            // whether we're in ambient mode), so we may need to start or stop the timer.
            updateTimer();
        }

        private void registerReceiver() {
            if (mRegisteredTimeZoneReceiver) {
                return;
            }
            mRegisteredTimeZoneReceiver = true;
            IntentFilter filter = new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED);
            MyWatchFace.this.registerReceiver(mTimeZoneReceiver, filter);
        }

        private void unregisterReceiver() {
            if (!mRegisteredTimeZoneReceiver) {
                return;
            }
            mRegisteredTimeZoneReceiver = false;
            MyWatchFace.this.unregisterReceiver(mTimeZoneReceiver);
        }

        @Override
        public void onApplyWindowInsets(WindowInsets insets) {
            super.onApplyWindowInsets(insets);

            // Load resources that have alternate values for round watches.
            Resources resources = MyWatchFace.this.getResources();
            boolean isRound = insets.isRound();
            mXOffset = resources.getDimension(isRound
                    ? R.dimen.digital_x_offset_round : R.dimen.digital_x_offset);
            float textSize = resources.getDimension(isRound
                    ? R.dimen.digital_text_size_round : R.dimen.digital_text_size);

            mTextPaint.setTextSize(textSize);
        }

        @Override
        public void onPropertiesChanged(Bundle properties) {
            super.onPropertiesChanged(properties);
            mLowBitAmbient = properties.getBoolean(PROPERTY_LOW_BIT_AMBIENT, false);
            mBurnInProtection = properties.getBoolean(PROPERTY_BURN_IN_PROTECTION, false);
        }

        @Override
        public void onTimeTick() {
            super.onTimeTick();
            invalidate();
        }

        @Override
        public void onAmbientModeChanged(boolean inAmbientMode) {
            super.onAmbientModeChanged(inAmbientMode);
/*

            mAmbient = inAmbientMode;
            if (mLowBitAmbient) {
                mTextPaint.setAntiAlias(!inAmbientMode);
            }
*/

            // Whether the timer should be running depends on whether we're visible (as well as
            // whether we're in ambient mode), so we may need to start or stop the timer.
            updateTimer();
        }

        /**
         * Captures tap event (and tap type) and toggles the background color if the user finishes
         * a tap.
         */
        @Override
        public void onTapCommand(int tapType, int x, int y, long eventTime) {
            switch (tapType) {
                case TAP_TYPE_TOUCH:
                    // The user has started touching the screen.
                    mXOffset = x - 100;
                    mYOffset = y;
                    centerX = x;
                    centerY = y;
                    break;
                case TAP_TYPE_TOUCH_CANCEL:
                    // The user has started a different gesture or otherwise cancelled the tap.
                    break;
                case TAP_TYPE_TAP:
                    // The user has completed the tap gesture.
                    mXOffset = x - 100;
                    mYOffset = y;
                    centerX = x;
                    centerY = y;
                    break;
            }
            invalidate();
        }

        @Override
        public void onDraw(Canvas canvas, Rect bounds) {
            // Draw the background.
            canvas.drawRect(0, 0, bounds.width(), bounds.height(), mBackgroundPaint);

            // Draw H:MM in ambient mode or H:MM:SS in interactive mode.
            long now = System.currentTimeMillis();
            mCalendar.setTimeInMillis(now);

            String text = String.format("%d:%02d:%02d", mCalendar.get(Calendar.HOUR),
                    mCalendar.get(Calendar.MINUTE), mCalendar.get(Calendar.SECOND));
            canvas.drawText(text, mXOffset, mYOffset, mTextPaint);

            drawStartsPonit(canvas, StartsLinesBeanlist);
            sendMessage.run();

        }

        private void drawStartsPonit(Canvas canvas, ArrayList<StartsPonitBean> StartsLinesBean) {
            for (int i = 0; i < StartsLinesBean.size(); i++) {
                DrawmLineBean = StartsLinesBean.get(i);
                paint.setAntiAlias(true);
                CirclePaint.setARGB(DrawmLineBean.color[0], DrawmLineBean.color[1], DrawmLineBean.color[2], DrawmLineBean.color[3]);
                canvas.drawCircle(DrawmLineBean.lineStartX,
                        DrawmLineBean.linestartY,
                        (100 - DrawmLineBean.Startlength) * DrawmLineBean.raduis / 100,
                        CirclePaint);
            }
        }


        /**
         * Starts the {@link #mUpdateTimeHandler} timer if it should be running and isn't currently
         * or stops it if it shouldn't be running but currently is.
         */
        private void updateTimer() {
            mUpdateTimeHandler.removeMessages(MSG_UPDATE_TIME);

            mUpdateTimeHandler.sendEmptyMessage(MSG_UPDATE_TIME);

        }

        /**
         * Returns whether the {@link #mUpdateTimeHandler} timer should be running. The timer should
         * only run when we're visible and in interactive mode.
         */
        private boolean shouldTimerBeRunning() {
            return isVisible() && !isInAmbientMode();
        }

        /**
         * Handle updating the time periodically in interactive mode.
         */
        private void handleUpdateTimeMessage() {
            invalidate();

            long timeMs = System.currentTimeMillis();
            long delayMs = INTERACTIVE_UPDATE_RATE_MS
                    - (timeMs % INTERACTIVE_UPDATE_RATE_MS);
            mUpdateTimeHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, delayMs);

        }
    }
}
