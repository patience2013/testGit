package xiong.com.mvptest.util;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import xiong.com.mvptest.App;

public class FileUtil {

    public static final String TEMP_DIR = "temp/";

    /**
     * 删除系统资源。 相册里的相册， 联系人等等
     *
     * @param context
     * @param uri
     */
    public static void deleteUri(Activity context, Uri uri) {
        try {
            context.getContentResolver().delete(uri, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断SD卡是否存在
     *
     * @return boolean
     */
    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /**
     * 判断SD卡剩余容量，单位MB
     *
     * @return long
     */
    public static long getSDFreeSize() {
        // 取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        // 返回SD卡空闲大小
        return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
    }

    /**
     * sd卡是否有效
     *
     * @return
     */
    public static boolean isSDAvaliable() {
        if (FileUtil.ExistSDCard() && FileUtil.getSDFreeSize() >= 10) {
            return true;
        }
        return false;
    }

    /**
     * 获取临时目录
     *
     * @return
     */
    public static String getTmpFile() {
        String dir = FileUtil.getStorePath("img-tmp");
        String filename = dir + System.currentTimeMillis() + ".jpg";
        return filename;
    }

    public static String getLocalStorePath(String dir) {
        String path = "";

        if (ExistSDCard() && getSDFreeSize() >= 10) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/paiyiy/" + dir + "/";
        } else {
            path = App.getInstance().getFilesDir()
                    .getAbsolutePath()
                    + "/paiyiy/" + dir + "/";
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        return path;
    }

    public static String getPathFromUri(Activity context, Uri uri) {
        try {
            Cursor cursor = context.managedQuery(uri, null, null, null, null);
            cursor.moveToFirst();// 这个必须加，否则下面读取会报错
            String recordedVideoFilePath = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Video.Media.DATA));

            return recordedVideoFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getFileSize(String path) {
        try {
            File dF = new File(path);
            FileInputStream fis;
            fis = new FileInputStream(dF);
            return fis.available();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取可保存目录
     *
     * @param dir
     * @return
     */
    public static String getStorePath(String dir) {
        String path = "";

        if (FileUtil.isSDAvaliable()) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/paiyiy/";
            if (!StringUtil.isNullOrEmpty(dir)) {
                path += dir + "/";
            }
        } else {
            path = App.getInstance()
                    .getDir(dir, Context.MODE_WORLD_WRITEABLE)
                    .getAbsolutePath()
                    + "/";
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 复制文件
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.flush();
                fs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param b Bitmap <a
     *          href='\"http://www.eoeandroid.com/home.php?mod=space&uid=7300\"
     *          ' target='\"_blank\"'>@return</a> 图片存储的位置
     * @throws FileNotFoundException
     */
    public static File saveImg(Bitmap b, String outPath) {
        return FileUtil.saveImg(b, outPath, 100);
    }

    /**
     * @param b Bitmap <a
     *          href='\"http://www.eoeandroid.com/home.php?mod=space&uid=7300\"
     *          ' target='\"_blank\"'>@return</a> 图片存储的位置
     * @throws FileNotFoundException
     */
    public static File saveImg(Bitmap b, String outPath, int q) {
        File mediaFile = new File(outPath);
        try {
            if (mediaFile.exists()) {
                mediaFile.delete();
            }
            if (!mediaFile.getParentFile().exists()) {
                mediaFile.getParentFile().mkdirs();
            }
            mediaFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(mediaFile);
            b.compress(Bitmap.CompressFormat.JPEG, q, fos);
            fos.flush();
            fos.close();
            b.recycle();
            b = null;
            System.gc();
            return mediaFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取压缩图片，并保存在outfile中
     *
     * @param srcPic
     */
    public static File getZipPicture(String srcPic, String outPath) {
        File outFile = FileUtil.getThumbUploadFile(srcPic, outPath, 480);
        return outFile;
    }

    public static File getThumbUploadFile(String srcPic, String outPath,
                                          int bitmapMaxWidth) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inJustDecodeBounds = true;
            options.inPurgeable = true;
            // 在内存中创建bitmap对象，这个对象按照缩放大小创建的
            int sampleSize = 1;
            long byteSize = FileUtil.getFileSize(srcPic);

            if (byteSize < 102400) {
                File outFile = new File(outPath);
                FileUtil.copyFile(srcPic, outPath);
                return outFile;
            }

            sampleSize = options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(srcPic, options);
            FileUtil.saveImg(bitmap, outPath, 60);
            byteSize = FileUtil.getFileSize(outPath);

            if (byteSize < 102400) {
                File outFile = new File(outPath);
                return outFile;
            }
            int count = 0;
            while (byteSize > 102400 && count < 5) {
                sampleSize += 2;
                ++count;
                options.inSampleSize = sampleSize;
                bitmap = BitmapFactory.decodeFile(srcPic, options);
                FileUtil.saveImg(bitmap, outPath, 60);
                byteSize = FileUtil.getFileSize(outPath);
            }

            return new File(outPath);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                System.gc();
                bitmap = null;
            }
        }

        return null;
    }

    /**
     * 116      * 判断图片是否已经存在了
     * 117      * @param filePath
     * 118      * @return
     * 119
     */
    private static boolean fileIsExists(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
