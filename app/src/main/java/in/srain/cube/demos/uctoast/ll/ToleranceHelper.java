package in.srain.cube.demos.uctoast.ll;

/**
 * Created by LL on 2018/1/4.
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
