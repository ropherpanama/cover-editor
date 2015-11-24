package com.ropherpanama.mp3;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFile;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.ropherpanama.domain.GoogleResponse;
import com.ropherpanama.domain.Parser;

public class GoogleAPIDownloader extends Thread {

	private List<String> links;
	private List<String> files;
	private List<String> imageLinks;
	private List<AudioFile> audios;

	public GoogleAPIDownloader(List<String> links, List<String> files, List<AudioFile> audios) {
		super();
		this.links = links;
		this.files = files;
		this.audios = audios;
		imageLinks = new ArrayList<String>();
	}

	public void run() {
		final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));

		ListenableFuture<String> futureTask = service.submit(new Callable<String>() {
			public String call() {
				try {
					for (int i = 0; i < links.size(); i++) {
						 System.err.println("*** INICIANDO BUSQUEDA PARA FILTRO >> " + links.get(i));
						InputStream stream = CoverDownloader.googleSearch(links.get(i));

						if (stream != null) {
							StringWriter writer = new StringWriter();
							IOUtils.copy(stream, writer, Charset.defaultCharset());
							String data = writer.toString();

							GoogleResponse response = (GoogleResponse) Parser.jsonAObjeto(data, new GoogleResponse());

							if (response != null) {
								if (response.getResponseData().getResults().size() > 0) {
									// TODO Aqui se puede filtrar la imagen a
									// buscar (por tamanio, etc), antes de
									// retornar su url
									imageLinks.add(response.getResponseData().getResults().get(0).getUrl());
								}
							}
						} else {
							System.out.println("GOOGLE NO RETORNO DATOS PARA >> " + links.get(i));
							imageLinks.add("http://static.dnaindia.com/sites/default/files/styles/square/public/2015/07/21/357624-creta.jpg");//Default img
						}
					}
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
					return "exception";
				}
			}
		});

		Futures.addCallback(futureTask, new FutureCallback<String>() {
			public void onFailure(Throwable arg0) {
				System.err.println("ERROR EN LA CONSULTA AL API DE GOOGLE");
				service.shutdown();
			}

			public void onSuccess(String imageUrl) {
				try {
					System.err.println("ESTADO DEL PROCESO DE BUSQUEDA EN GOOGLE >> " + imageUrl);
					// Paso los links encontrados para cada imagen y el nombre
					// del archivo fisico resultado de la descarga
					//y los ficheros a modificar
					ImageDownloader i = new ImageDownloader(imageLinks, files, audios);
					i.start();
					service.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
