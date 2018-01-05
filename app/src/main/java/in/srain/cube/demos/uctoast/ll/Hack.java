package in.srain.cube.demos.uctoast.ll;

import android.graphics.Bitmap;
import android.util.Log;


/**
 * Created by LL on 2018/1/4.
 */

public class Hack {
    private String TAG=getClass().getSimpleName();
    /**
     * 弹跳系数，如果是720分辨率，请修改为2.05来试试。
     */
    static final double JUMP_RATIO = 1.385f;
    static final String ADB_PATH = "/Users/chenliang/Library/Android/sdk/platform-tools/adb";
    public void find(Bitmap image){

        MyPosFinder myPosFinder = new MyPosFinder();
        NextCenterFinder nextCenterFinder = new NextCenterFinder();
        WhitePointFinder whitePointFinder = new WhitePointFinder();
        double jumpRatio = 0;
        //i<2048
        for (int i = 0; i < 1; i++) {
            if (jumpRatio == 0) {
                jumpRatio = JUMP_RATIO * 1080 / image.getWidth();
            }
            int[] myPos = myPosFinder.find(image);
            if (myPos != null) {
                Log.e(TAG,"find myPos, succ, (" + myPos[0] + ", " + myPos[1] + ")");
                int[] excepted = {myPos[0] - 35, myPos[0] + 35};
                int[] nextCenter = nextCenterFinder.find(image, excepted, myPos[1]);
                if (nextCenter == null || nextCenter[0] == 0) {
                    Log.e(TAG,"find nextCenter, fail");
                    break;
                } else {
                    int centerX, centerY;
                    int[] whitePoint = whitePointFinder.find(image, nextCenter[0] - 120, nextCenter[1], nextCenter[0] + 120, nextCenter[1] + 180);
                    if (whitePoint != null) {
                        centerX = whitePoint[0];
                        centerY = whitePoint[1];
                        Log.e(TAG,"find whitePoint, succ, (" + centerX + ", " + centerY + ")");
                    } else {
                        if (nextCenter[2] != Integer.MAX_VALUE && nextCenter[4] != Integer.MIN_VALUE) {
                            centerX = (nextCenter[2] + nextCenter[4]) / 2;
                            centerY = (nextCenter[3] + nextCenter[5]) / 2;
                        } else {
                            centerX = nextCenter[0];
                            centerY = nextCenter[1] + 48;
                        }
                    }
                    Log.e(TAG,"find nextCenter, succ, (" + centerX + ", " + centerY + ")");
                    int distance = (int) (Math.sqrt((centerX - myPos[0]) * (centerX - myPos[0]) + (centerY - myPos[1]) * (centerY - myPos[1])) * jumpRatio);
                    Log.e(TAG,"distance: " + distance);
                    Log.e(TAG,ADB_PATH + " shell input swipe 400 400 400 400 " + distance);
//                    Runtime.getRuntime().exec(ADB_PATH + " shell input swipe 300 300 400 400 " + distance);
                }
            } else {
                Log.e(TAG,"find myPos, fail");
                break;
            }
        }

    }

}
