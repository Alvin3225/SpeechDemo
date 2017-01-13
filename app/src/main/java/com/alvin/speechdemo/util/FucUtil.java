package com.alvin.speechdemo.util;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.InputStream;

/**
 * 功能性函数扩展类
 */
public class FucUtil {

    public static String readFile(Context mContext, String file, String code) {
        int len = 0;
        byte[] buf = null;
        String result = "";
        try {
            InputStream in = mContext.getClass().getClassLoader().getResourceAsStream("assets/"+file);
            len = in.available();
            buf = new byte[len];
            in.read(buf, 0, len);

            result = new String(buf, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取asset目录下音频文件。
     *
     * @return 二进制文件数据
     */
    public static byte[] readAudioFile(Context context, String filename) {
        try {
            InputStream ins = context.getAssets().open(filename);
            byte[] data = new byte[ins.available()];

            ins.read(data);
            ins.close();

            return data;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
