package com.godD.appwidget.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDCardBitmap {

	public static Bitmap getBitmapFromSD(String key, Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Bitmap map = BitmapFactory.decodeFile(context.getExternalCacheDir()
					.getAbsolutePath()
					+ File.separator
					+ key.substring(key.lastIndexOf("/") + 1));
			return map;
		}
		return null;
	}

	public static void setBitmapToSD(String key, Bitmap map, Context context) {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = null;
			file = new File(context.getExternalCacheDir().getAbsolutePath()
					+ File.separator + key.substring(key.lastIndexOf("/") + 1));
			FileOutputStream outputStream = null;
			try {
				file.createNewFile();
				outputStream = new FileOutputStream(file);

				map.compress(CompressFormat.JPEG, 100, outputStream);
				outputStream.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}
}
