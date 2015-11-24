package com.ropherpanama.mp3;

import java.io.File;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

/**
 * Esta clase modifica o agrega el tag Cover (imagen del album o artista) La
 * imagen sera agregada a partir de un unico fichero de imagen y se colocara a
 * un unico fichero o a varios, segun sea el metodo seleccionado
 * 
 * @author rospena
 *
 */
public class FileArtworkEditor {
	private AudioFile audio;
	private List<AudioFile> audios;
	private String artPath;

	/**
	 * Constructor para trabajar con un solo archivo de audio
	 * 
	 * @param audio
	 *            archivo de audio a modificar
	 * @param artPath
	 *            ruta del fichero de imagen
	 */
	public FileArtworkEditor(AudioFile audio, String artPath) {
		this.artPath = artPath;
		this.audio = audio;
	}

	/**
	 * Constructor para trabajar con varios archivos de audio
	 * 
	 * @param audios
	 *            arhcivos de audio a modificar
	 * @param artPath
	 *            ruta del fichero de imagen
	 */
	public FileArtworkEditor(List<AudioFile> audios, String artPath) {
		this.artPath = artPath;
		this.audios = audios;
	}

	/**
	 * Edita el cover de un solo fichero, con la imagen pasada como parametro
	 */
	public void oneArtwork() {
		try {
			Artwork art = getCoverFromFile(artPath);
			Tag tag = audio.getTag();

			if (tag != null) {
				tag.addField(art);
				tag.setField(art);
				audio.commit();
			}
			System.out.println("LISTO");
		} catch (Exception e) {
			System.out.println("Booom! " + e.getMessage());
		}
	}

	/**
	 * Edita el cover de varios ficheros, a cada archivo de audio le colocara
	 * una unica imagen (pasada por parametro)
	 */
	public void manyArtworks() {
		try {
			System.out.println("Editando covers ...");
			Artwork art = getCoverFromFile(artPath);

			for (AudioFile a : audios) {
				Tag tag = a.getTag();

				if (tag != null) {
					tag.addField(art);
					tag.setField(art);
					a.commit();
				}
			}
			System.out.println("Listo");
		} catch (Exception e) {
			System.out.println("Booom! " + e.getMessage());
		}
	}

	/**
	 * Crea un artwork a partir de un url de archivo
	 * 
	 * @param filePath
	 *            path de la imagen a colocar
	 * @return artwork listo para usar
	 */
	public Artwork getCoverFromFile(String filePath) {
		try {
			System.out.println("CREAR ARTWORK A PARTIR DE [" + filePath + "]");
			File f = new File(filePath);
			if (f.exists())
				return ArtworkFactory.createArtworkFromFile(new File(filePath));
			else {
				System.out.println("NO EXISTE RECURSO");
				return null;
			}

		} catch (Exception e) {
			System.out.println("Booom! " + e.getMessage());
			return null;
		}
	}
}
