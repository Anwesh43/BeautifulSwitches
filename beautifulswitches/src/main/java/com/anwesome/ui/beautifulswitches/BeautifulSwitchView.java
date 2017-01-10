package com.anwesome.ui.beautifulswitches;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 11/01/17.
 */
public class BeautifulSwitchView extends View{
    private boolean shouldInvalidate = false,isDown = false,yes = false;
    private BeautifulSwitch.SwitchListener switchListener;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float k = 0.0f,l = -1;
    private int w,h,time = 0;
    public BeautifulSwitchView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            k = h/2;
        }
        paint.setColor(Color.GRAY);
        canvas.drawRoundRect(new RectF(0,h/4,w,h-h/4),h/4,h/4,paint);
        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawRoundRect(new RectF(0,h/4,k,h-h/4),h/4,h/4,paint);
        canvas.drawCircle(k,h/2,h/2,paint);
        if(shouldInvalidate) {
            k+=l*w/4;
            if(k>=w-h/2) {
                k = w-h/2;
                shouldInvalidate = false;
                if(switchListener!=null) {
                    switchListener.onYes();
                    yes = true;
                }
            }
            else if(k<=h/2) {
                k = h/2;
                shouldInvalidate = false;
                if(switchListener!=null) {
                    switchListener.onNo();
                    yes = false;
                }
            }
            try {
                time++;
                Thread.sleep(100);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }

    public void setSwitchListener(BeautifulSwitch.SwitchListener switchListener) {
        this.switchListener = switchListener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(),y = event.getY();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!shouldInvalidate) {
                    if (!isDown && x >= k && x <= k + h && y >= 0 && y <= h) {
                        isDown = true;
                    } else {
                        shouldInvalidate = true;
                        l *=-1;
                        postInvalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDown) {
                    k = event.getX();
                    if(k>=w) {
                        k = w-h/2;
                        if(switchListener!=null) {
                            switchListener.onYes();
                        }
                        l = 1;
                        yes = true;
                    }
                    else if(k<=0) {
                        k = 0;
                        if(switchListener!=null) {
                            switchListener.onNo();
                        }
                        l = -1;

                        yes = false;
                    }
                    else {
                        yes = false;
                    }
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDown) {
                    if(!yes) {
                        k = h/2;
                        isDown = false;
                        postInvalidate();
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

}
