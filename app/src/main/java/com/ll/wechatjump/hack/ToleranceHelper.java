package com.ll.wechatjump.hack;

/**
 * Created by lenovo on 2018/1/5.
 */

public class ToleranceHelper {
    public static boolean match(int r, int g, int b, int rt, int gt, int bt, int t) {
        return r > rt - t &&
                r < rt+ t &&
                g > gt - t &&
                g < gt + t &&
                b > bt - t &&
                b < bt + t;
    }
}
