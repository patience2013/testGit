package xiong.com.mvptest.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SqliteUtil {

	private final String DATABASE_PATH = FileUtil.getLocalStorePath("db");

	private String pathName;
	private String templateDB;
	private String dbName;

	public SqliteUtil(String templateDB) {
		this.pathName = "payyiydb";
		this.templateDB = templateDB;
		this.dbName = templateDB;
	}

	public SQLiteDatabase openDatabase(Context context, String db_name) {
		this.dbName = db_name;
		return openDatabase(context);
	}

	public SQLiteDatabase openDatabase(Context context) {
		try {
			String databasePath = DATABASE_PATH + "/" + pathName;

			String databaseFilename = databasePath + this.dbName;

			File dir = new File(databasePath);

			if (!dir.exists()) {
				dir.mkdirs();
			}

			File databaseFile = new File(databaseFilename);

			if (!databaseFile.exists()) {
				AssetManager am = context.getAssets();
				InputStream is = am.open(templateDB);
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.flush();
				fos.close();
				is.close();
			}

			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
					databaseFilename, null);

			return database;
		} catch (Exception e) {
			String s = e.getMessage();
			System.out.println(s);
		}

		return null;
	}
}
