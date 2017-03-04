package xiong.com.mvptest.util;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownload extends AsyncTask<String, Integer, Integer> {

	DownloadListener progressListener = null;
	String outFilePath = null;
	Object userObject;

	public FileDownload(DownloadListener listener) {
		this.progressListener = listener;
	}

	public FileDownload() {

	}

	public void setObject(Object obj) {
		userObject = obj;
	}

	/**
	 * params[0] fileURL params[1] outfileName
	 */
	@Override
	protected Integer doInBackground(String... params) {
		int result = -1;
		try {
			URL url = new URL(params[0]);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// con.setDoOutput(true);
			// con.setUseCaches(false);
			con.setConnectTimeout(3000);
			con.setReadTimeout(3000);

			con.setRequestMethod("GET");
			con.connect();

			int fileLength = con.getContentLength();
			outFilePath = params[1];
			FileOutputStream out = new FileOutputStream(new File(params[1]));
			InputStream in = con.getInputStream();

			byte[] buffer = new byte[1024];
			int length = 0;
			long total = 0;
			while ((length = in.read(buffer)) > 0 && !isCancelled()) {
				total += length;
				publishProgress((int) total, (int) fileLength);
				out.write(buffer, 0, length);
			}
			out.close();

			result = 0;
		} catch (MalformedURLException e) {
			result = -1;
		} catch (IOException e) {
			result = -2;
		}

		if (result != 0 && !StringUtil.isNullOrEmpty(outFilePath)) {
			File temp = new File(outFilePath);
			if (temp != null && temp.exists()) {
				temp.delete();
			}
		}

		return result;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		if (progressListener != null) {
			progressListener.onProgressUpdate(userObject, values[0], values[1]);
		}
	}

	@Override
	protected void onPostExecute(Integer result) {
		if (progressListener != null) {
			progressListener.onPostExecute(userObject, result, outFilePath);
		}
	}

	public static interface DownloadListener {

		/**
		 * 下载进度回调
		 * 
		 * @param taskID
		 * @param progress
		 *            值为0-100
		 */
		void onProgressUpdate(Object obj, int cur, int total);

		/**
		 * 下载结果返回
		 * 
		 * @param taskID
		 * @param result
		 */
		void onPostExecute(Object obj, int resultCode, String filePath);

	}
}
