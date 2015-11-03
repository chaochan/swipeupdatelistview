package com.example.swipeupdatelistview;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 汎用メソッド群
 */
public class Utility {

    /**
     * rawリソースデータをロードする
     */
    public static byte[] loadRawFile(Context context, int rawResId) throws IOException {
        InputStream in = null;
        byte[] fileData = null;
        try {
            in = context.getResources().openRawResource(rawResId);
            fileData = dataFromInputStream(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return fileData;
    }


    /**
     * InputStreamからバイト配列を取得する
     */
    public static byte[] dataFromInputStream(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] byBuf = new byte[10240];
        int nSize;
        while ((nSize = in.read(byBuf)) > 0) {
            out.write(byBuf, 0, nSize);
        }

        byte[] data = out.toByteArray();
        out.close();
        return data;
    }
}
