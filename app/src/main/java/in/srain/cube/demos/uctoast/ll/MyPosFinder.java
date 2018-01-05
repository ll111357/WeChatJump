package in.srain.cube.demos.uctoast.ll;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by LL on 2018/1/4.
 * 查找图像中我的位置
 */

public class MyPosFinder {
    private String TAG=this.getClass().getSimpleName();

    //我的位置rgb颜色
    public static final int R_TARGET = 40;
    public static final int G_TARGET = 43;
    public static final int B_TARGET = 86;

    public int[] find(Bitmap image) {
        if (image == null) {
            return null;
        }
        int width = image.getWidth();
        int height = image.getHeight();

        int[] ret = {0, 0};
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int i = 0; i < width; i++) {
            //遍历中间二分之一的区域 在此区域内查找
            for (int j = height / 4; j < height * 3 / 4; j++) {
                //取当前位置的RGB颜色值
                int pixel = image.getPixel(i, j);
//                如果要把R、G、B、A转为16进制（比如转为16进制命令发送等等）
//                RGB转为16进制代码如下：
//                String r1=Integer.toHexString(r);
//                String g1=Integer.toHexString(g);
//                String b1=Integer.toHexString(b);
//                String colorStr=r1+g1+b1;    //十六进制的颜色字符串。
                //得到RGB 三原色的值
                int r = (pixel & 0xff0000) >> 16;
                int g = (pixel & 0xff00) >> 8;
                int b = (pixel & 0xff);
                //是否在图像中找到与我的位置相同颜色
                if (ToleranceHelper.match(r, g, b, R_TARGET, G_TARGET, B_TARGET, 16) && j > ret[1]) {
//                    maxX = Integer.max(maxX, i);
//                    minX = Integer.min(minX, i);
//                    maxY = Integer.max(maxY, j);
//                    minY = Integer.min(minY, j);
                    maxX = Math.max(maxX, i);
                    minX = Math.min(minX, i);
                    maxY = Math.max(maxY, j);
                    minY = Math.min(minY, j);
                }
            }
        }
        ret[0] = (maxX + minX) / 2 +3;
        ret[1] = maxY;
        Log.e(TAG,maxX + ", " + minX);
        Log.e(TAG,"pos, x: " + ret[0] + ", y: " + ret[1]);
        return ret;
    }
}
