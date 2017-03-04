package xiong.com.mvptest.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import xiong.com.mvptest.App;
import xiong.com.mvptest.R;

public class ImageUtil {
	public static Bitmap loadImage(Context context, int id) {
		try {
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inPreferredConfig = Config.RGB_565;// 表示16位位图
															// 565代表对应三原色占的位数
			opt.inInputShareable = true;
			opt.inPurgeable = true;// 设置图片可以被回收
			InputStream is = context.getResources().openRawResource(id);
			return BitmapFactory.decodeStream(is, null, opt);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return BitmapFactory.decodeResource(App.getInstance()
				.getResources(), R.mipmap.ic_launcher);
	}

	public static Bitmap loadBitmap(int id) {
		return BitmapFactory.decodeResource(App.getInstance()
				.getResources(), id);
	}

	/**
	 * 根据原图转为圆形图片，按最小边并居中转换
	 *
	 * @param source
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap source) {
		if (source == null) {
			return null;
		}

		Bitmap output = null;
		try {
			int size = Math.min(source.getWidth(), source.getHeight());
			Rect srcRect = new Rect();
			srcRect.left = (source.getWidth() - size) / 2;
			srcRect.top = (source.getHeight() - size) / 2;
			srcRect.bottom = srcRect.top + size;
			srcRect.right = srcRect.left + size;
			output = Bitmap.createBitmap(size, size, Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, size, size);
			final RectF rectF = new RectF(rect);
			final float roundPx = size / 2;

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(source, srcRect, rect, paint);
		} catch (OutOfMemoryError e) {
			Log.d("gaga", "内存溢出");
		}
		return output;
	}

	/**
	 * 换成圆角bitmap
	 *
	 * @param bitmap
	 * @param cornerPX
	 *            圆角半径
	 * @return
	 */
	public static Bitmap toCornerBitmap(Bitmap bitmap, int cornerPX) {
		if (bitmap == null) {
			return null;
		}
		Bitmap output = null;
		try {
			output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
					Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = cornerPX;
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
		} catch (OutOfMemoryError e) {
			Log.d("gaga", "内存溢出");
		}
		return output;
	}

	/**
	 * 保存bitmap到文件
	 *
	 * @param source
	 * @return
	 */
	public static String saveBitmapToTemp(Bitmap source) {
		String dir = FileUtil.getStorePath("img-tmp");
		String filename = dir + System.currentTimeMillis() + ".jpg";
		if (saveBitmap(source, filename)) {
			return filename;
		}
		return null;
	}

	/**
	 * 保存图片到指定路径
	 *
	 * @param source
	 * @param filePath
	 * @return
	 */
	public static boolean saveBitmap(Bitmap source, String filePath) {
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			source.compress(CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i("exception11", "saveBitmap: "+e.toString());
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("exception11", "IoException: "+e.toString());
			return false;
		}
		return true;
	}

	public static void recycleBitmap(Bitmap bitmap) {
		if (null != bitmap && !bitmap.isRecycled())
			bitmap.recycle();
	}

	/**
	 * 获取图片保存的临时路径
	 *
	 * @return
	 */
	public static String getTempPath() {
		String dir = FileUtil.getStorePath("img-tmp");
		return dir + System.currentTimeMillis() + ".jpg";
	}

	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Bitmap extractThumbNail(Bitmap bm, final int height,
			final int width, final boolean crop) {
		if (bm == null)
			return null;

		final double beY = bm.getHeight() * 1.0 / height;
		final double beX = bm.getWidth() * 1.0 / width;

		int newHeight = height;
		int newWidth = width;
		if (crop) {
			if (beY > beX) {
				newHeight = (int) (newWidth * 1.0 * bm.getHeight() / bm
						.getWidth());
			} else {
				newWidth = (int) (newHeight * 1.0 * bm.getWidth() / bm
						.getHeight());
			}
		} else {
			if (beY < beX) {
				newHeight = (int) (newWidth * 1.0 * bm.getHeight() / bm
						.getWidth());
			} else {
				newWidth = (int) (newHeight * 1.0 * bm.getWidth() / bm
						.getHeight());
			}
		}

		final Bitmap scale = Bitmap.createScaledBitmap(bm, newWidth, newHeight,
				true);
		if (crop) {
			int x = 0;
			if (newWidth > width) {
				x = (newWidth - width) / 2;
			}
			int y = 0;
			if (newHeight > height) {
				y = (newHeight = height) / 2;
			}
			int w = Math.min(newWidth, width);
			int h = Math.min(newHeight, height);
			final Bitmap cropped = Bitmap.createBitmap(scale, x, y, w, h);
			if (cropped == null) {
				return scale;
			}
			return cropped;
		}
		return scale;
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromFile(String filename,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filename, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filename, options);
	}

	// 缩放图片
	public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
	   // 获得图片的宽高
	   int width = bm.getWidth();
	   int height = bm.getHeight();
	   // 计算缩放比例
	   float scaleH = 1.0f;
	   float scaleW = 1.0f;
	   if (height > newHeight) {
		   scaleH = (float) newHeight /  (float) height;
	   }
	   if (width > newWidth) {
		   scaleW = (float) newWidth / (float) width;
	   }
	   float scale = Math.min(scaleH,  scaleW);
	   // 取得想要缩放的matrix参数
	   Matrix matrix = new Matrix();
	   matrix.postScale(scale, scale);
	   // 得到新的图片
	   Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
	    return newbm;
	}
}
