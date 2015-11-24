package com.ropherpanama.mp3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class CoverDownloader {

	/**
	 * Descar la imagen de acuerdo al url indicado
	 * 
	 * @param imageUrl
	 *            url de internet en donde esta la imagen
	 * @param destinationFile
	 *            archivo destino
	 * @throws IOException
	 */
	public static boolean saveImageXXX(String imageUrl, String destinationFile) {
		try {
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
			return true;
		} catch (Exception e) {
			System.err.println("Boom! " + e.getMessage());
			return false;
		}
	}
	
	public static boolean saveBufferedImage(String imageUrl, String destinationFile) {
		try {
			BufferedImage image = ImageIO.read(new URL(imageUrl));
			ImageIO.write(image, "jpg", new File(destinationFile));
			return true;
		}catch(Exception e) {
			System.err.println("saveBufferedImage Boom! " + e.getMessage());
			return false;
		}
	}

	// http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=king+of+leon+piro&as_filetype=jpg

	public static InputStream googleSearch(String filter) {
		try {
			String href = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + filter + "&as_filetype=jpg";
			href = href.trim();
			href = href.replace(" ", "+");
			System.out.println("*** Searching [" + href + "]");
			URL url = new URL(href);
			URLConnection urlConnection = url.openConnection();
			return urlConnection.getInputStream();
		} catch (Exception e) {
			System.err.println("Boom! " + e.getMessage());
			return null;
		}
	}
}
