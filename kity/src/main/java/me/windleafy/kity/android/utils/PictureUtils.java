package me.windleafy.kity.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureUtils {

    private PictureUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 保存Bitmap到手机本地
     *
     * @param bitmap   图片
     * @param dirName  文件夹名称
     * @param fileName 文件名称
     * @return 文件路径
     * @throws IOException
     */
    public static String saveBitmap(Bitmap bitmap, String dirName, String fileName) throws IOException {
        String dirPath = Environment.getExternalStorageDirectory().getCanonicalPath() + File.separator + dirName;
        File imgDir = new File(dirPath);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        String filePath = dirPath + File.separator + fileName;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
            return filePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
        return null;
    }

    /**
     * 保存Bitmap到手机本地
     *
     * @param bytes    图片流
     * @param dirName  文件夹名称
     * @param fileName 文件名称
     * @return 文件路径
     * @throws IOException
     */
    public static String saveBitmap(byte[] bytes, String dirName, String fileName) throws IOException {
        String dirPath = Environment.getExternalStorageDirectory().getCanonicalPath() + File.separator + dirName;
        File imgDir = new File(dirPath);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        String filePath = dirPath + File.separator + fileName;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            out.write(bytes);
            out.flush();
            out.close();
            return filePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
        return null;
    }

    /**
     * 更新图片到图库
     *
     * @param context
     * @param imageFilePath
     */
    public static void updateDCIM(Context context, String imageFilePath) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(imageFilePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }


    /**
     * View对象转Bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap view2Bitmap(final View view) {
        if (view == null) return null;
        Bitmap ret = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return ret;
    }


}
