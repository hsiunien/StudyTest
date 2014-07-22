package com.ahsiu.studytest.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hsiunien on 2014/7/12.
 */
public class ImageUtil {
    /**
     * @param bitmap
     * @param fileName
     */
    public static void bitm2png(Bitmap bitmap, String fileName) {
        String saveFilePath = Environment.getExternalStorageDirectory()
                + File.separator + fileName;
        System.out.println(saveFilePath);

        File bitmapFile = new File(saveFilePath);
        if (bitmapFile.getParentFile() != null
                && !bitmapFile.getParentFile().exists()) {
            bitmapFile.getParentFile().mkdirs();
        }
        FileOutputStream bitmapWtriter = null;
        try {
            bitmapWtriter = new FileOutputStream(bitmapFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, bitmapWtriter);
    }

    public static void bitm2jpg(Bitmap bitmap, String fileName) {
        String saveFilePath = Environment.getExternalStorageDirectory()
                + File.separator + fileName;
        System.out.println(saveFilePath);

        File bitmapFile = new File(saveFilePath);
        if (bitmapFile.getParentFile() != null
                && !bitmapFile.getParentFile().exists()) {
            bitmapFile.getParentFile().mkdirs();
        }
        FileOutputStream bitmapWtriter = null;
        try {
            bitmapWtriter = new FileOutputStream(bitmapFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bitmapWtriter);
    }

    /**
     * byte->bitmap
     *
     * @param bytes
     * @param opts
     * @return
     */
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    /**
     * inputStream to byte;
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    /**
     * 压缩文件后存放到SD卡中
     *
     * @param rawBitmap
     * @param fileName
     * @param quality
     * @return
     */
    public static String compressAndSaveBitmapToSDCard(Bitmap rawBitmap,
                                                       String fileName, int quality) {
        String saveFilePath = Environment.getExternalStorageDirectory()
                + File.separator + fileName;
        File saveFile = new File(saveFilePath);
        if (saveFile.exists()) {
            saveFile.delete();
        }

        try {
            saveFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            if (fileOutputStream != null) {
                // imageBitmap.compress(format, quality, stream);
                // 把位图的压缩信息写入到一个指定的输出流中
                // 第一个参数format为压缩的格式
                // 第二个参数quality为图像压缩比的值,0-100.0 意味着小尺寸压缩,100意味着高质量压缩
                // 第三个参数stream为输出流
                rawBitmap.compress(Bitmap.CompressFormat.JPEG, quality,
                        fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFilePath;
    }


    /**
     * 加载一张图片 按照压缩比例
     *
     * @return
     */
    public static Bitmap compressLoadBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        int reqHeight = 800;
        int reqWidth = 480;
        /*
         * heightRatio是图片原始高度与压缩后高度的倍数，widthRatio是图片原始宽度与压缩后宽度的倍数
        */
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 从sd卡中加载图片 /mnt/sdcard/ 目录下开始算起
     *
     * @return
     */
    public static Bitmap loadBitmapFromSdCard(String fileName) {
        String filePath = Environment.getExternalStorageDirectory()
                + File.separator + fileName;
        Bitmap bmp = BitmapFactory.decodeFile(filePath);
        return bmp;
    }

    // 获取指定Activity的截屏，保存到png文件
    public static Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("TAG", "" + statusBarHeight);

        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 去掉标题栏
        // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }
    //Bitmap->string Base64
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream bos = null;
        try {
            if (null != bitmap) {
                bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bos);//将bitmap放入字节数组流中
                bos.flush();//将bos流缓存在内存中的数据全部输出，清空缓存
                bos.close();

                byte[] bitmapByte = bos.toByteArray();
                result = Base64.encodeToString(bitmapByte, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 加载大图片工具类：解决android加载大图片时报OOM异常
     * 解决原理：先设置缩放选项，再读取缩放的图片数据到内存，规避了内存引起的OOM
     *
     * @author: 张进
     * @time:2011/7/28
     */

    public static final int UNCONSTRAINED = -1;

    /*
  * 获得设置信息
  */
    public static BitmapFactory.Options getOptions(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只描边，不读取数据
        BitmapFactory.decodeFile(path, options);
        return options;
    }


    /**
     * 获得图像
     *
     * @param path
     * @param options
     * @return
     * @throws FileNotFoundException
     */
    public static Bitmap getBitmapByPath(String path, BitmapFactory.Options options, int screenWidth, int screenHeight) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        FileInputStream in = null;
        in = new FileInputStream(file);
        if (options != null) {
            Rect r = getScreenRegion(screenWidth, screenHeight);
            int w = r.width();
            int h = r.height();
            int maxSize = w > h ? w : h;
            int inSimpleSize = computeSampleSize(options, maxSize, w * h);
            options.inSampleSize = inSimpleSize; //设置缩放比例
            options.inJustDecodeBounds = false;
        }
        Bitmap b = BitmapFactory.decodeStream(in, null, options);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }


    private static Rect getScreenRegion(int width, int height) {
        return new Rect(0, 0, width, height);
    }


    /**
     * 获取需要进行缩放的比例，即options.inSampleSize
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == UNCONSTRAINED) ? 1 :
                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == UNCONSTRAINED) ? 128 :
                (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == UNCONSTRAINED) &&
                (minSideLength == UNCONSTRAINED)) {
            return 1;
        } else if (minSideLength == UNCONSTRAINED) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
