package com.ropherpanama.mp3;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.jaudiotagger.audio.AudioFile;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ImageDownloader extends Thread {
	private List<String> urlArray;
	private List<String> namesArray;
	private List<AudioFile> audios;

	public ImageDownloader(List<String> urlArray, List<String> namesArray, List<AudioFile> audios) {
		super();
		this.urlArray = urlArray;
		this.namesArray = namesArray;
		this.audios = audios;
	}

	public void run() {
		final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));
		System.err.println("*** INICIANDO DESCARGA DE IMAGENES ....");
		ListenableFuture<String> futureTask = service.submit(new Callable<String>() {
			public String call() {
				try {
					for (int i = 0; i < urlArray.size(); i++) {
//						boolean status = CoverDownloader.saveImage(urlArray.get(i), namesArray.get(i));
						boolean status = CoverDownloader.saveBufferedImage(urlArray.get(i), namesArray.get(i));
						
						if(status) {
							System.err.println("DESCARGA EXITOSA ===>> " + namesArray.get(i));
						} else {
							String defaultImg = "http://static.dnaindia.com/sites/default/files/styles/square/public/2015/07/21/357624-creta.jpg";
							boolean recovery = CoverDownloader.saveBufferedImage(defaultImg, namesArray.get(i));
							
							if(recovery) System.err.println("DESCARGA EXITOSA ===>> DEFAULT IMG");
							else System.err.println("DESCARGA DEFAULT FALLO TAMBIEN");
						}
					}
					return "success";
				} catch (Exception e) {
					return "exception";
				}
			}
		});

		Futures.addCallback(futureTask, new FutureCallback<String>() {
			public void onFailure(Throwable arg0) {
				System.err.println("ERROR AL DESCARGAR LA IMAGEN");
				service.shutdown();
			}

			public void onSuccess(String result) {
				try {
					System.err.println("*** DESCARGA DE IMAGENES TERMINADA >> [" + result + "]");
					
					for(AudioFile a : audios) {
						String imgPath = "D:/cover-downloaded/" + a.getFile().getName() + ".jpg";
						imgPath = imgPath.replace(".mp3", "");
						FileArtworkEditor editor = new FileArtworkEditor(a, imgPath);
						editor.oneArtwork();
						editor = null;
					}
					service.shutdown();
				} catch (Exception e) {
					System.out.println(this.getClass().getCanonicalName() + " Booom! " + e.getMessage());
				}
			}
		});
	}
}
