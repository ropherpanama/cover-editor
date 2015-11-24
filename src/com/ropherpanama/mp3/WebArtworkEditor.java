package com.ropherpanama.mp3;

import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class WebArtworkEditor {

	private List<String> urls = new ArrayList<String>();
	private List<String> files = new ArrayList<String>();;

	public boolean searchAndDownloadArtworks(List<AudioFile> audios) {
		try {
			for (AudioFile af : audios) {
				Tag tag = af.getTag();

				if (tag != null) {
					String artist = tag.hasField(FieldKey.ARTIST) ? tag.getFirst(FieldKey.ARTIST) : "";
					String songTitle = tag.hasField(FieldKey.TITLE) ? tag.getFirst(FieldKey.TITLE) : "";
					String filter = "";

					if (artist.equals("") && songTitle.equals(""))
						filter = af.getFile().getName();
					else
						filter = artist + " " + songTitle;

					// TODO la ubicacion de la imagen debe ser dinamica, igual
					// la extension del archivo
					String imagenDestino = "D:/cover-downloaded/" + af.getFile().getName() + ".jpg";
					imagenDestino = imagenDestino.replace(".mp3", "");

					urls.add(filter);
					files.add(imagenDestino);
				}
			}

			System.out.println(">>>>>>>>>>>>>>>>>>>> TERMINO PREPARACION DE DATOS PARA GOOGLE API");

			if (urls.size() > 0 && files.size() > 0) {
				GoogleAPIDownloader api = new GoogleAPIDownloader(urls, files, audios);
				api.start();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
