package xiong.com.mvptest.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class VersionUtil {
	public static int getVersionCodeByFile(Context context,
			String archiveFilePath) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath,
					PackageManager.GET_ACTIVITIES);
			if (info != null) {
				// String appName = pm.getApplicationLabel(appInfo).toString();
				// String packageName = appInfo.packageName; // �õ���װ������
				// String version = info.versionName; // �õ��汾��Ϣ
				int versionCode = info.versionCode;
				return versionCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public static boolean checkAPP(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo(packageName,
							PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	public static boolean checkApkExist(Context context, String packageName) {
		if (packageName == null || "".equals(packageName)) {
			return false;
		}
		try {
			context.getPackageManager().getApplicationInfo(packageName,
					PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}
}
