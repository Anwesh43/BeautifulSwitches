package com.anwesome.ui.beautifulswitches;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 11/01/17.
 */
public class BeautifulSwitch {
    public interface SwitchListener {
        void onNo();
        void onYes();
    }
    private Activity activity;
    private int w=400,h=400;
    public BeautifulSwitch(Activity activity) {
        this.activity = activity;
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display!=null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void addSwitch(SwitchListener switchListener,float... coordinates) {
        BeautifulSwitchView beautifulSwitchView = new BeautifulSwitchView(activity.getApplicationContext());
        beautifulSwitchView.setSwitchListener(switchListener);
        if(coordinates.length == 2) {
            beautifulSwitchView.setX(coordinates[0]);
            beautifulSwitchView.setY(coordinates[1]);
        }
        activity.addContentView(beautifulSwitchView,new ViewGroup.LayoutParams(w/4,w/16));
    }
    public void addSwitch(float... coordinates) {
        BeautifulSwitchView beautifulSwitchView = new BeautifulSwitchView(activity.getApplicationContext());
        if(coordinates.length == 2) {
            beautifulSwitchView.setX(coordinates[0]);
            beautifulSwitchView.setY(coordinates[1]);
        }
        activity.addContentView(beautifulSwitchView,new ViewGroup.LayoutParams(w/4,w/16));
    }
}
