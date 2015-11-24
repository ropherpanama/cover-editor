package com.ropherpanama.mp3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

public class AudioFileUtils {
	/**
	 * Crea un archivo de audio a partir de la ruta indicada
	 * 
	 * @param fileName
	 *            ubicacion y nombre de archivo
	 * @return audio file
	 */
	public static AudioFile createAudio(String fileName) {
		try {
			File file = new File(fileName);
			return AudioFileIO.read(file);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Crea una lista de archivos de audio a partir de una lista de rutas de
	 * ficheros
	 * 
	 * @param files
	 *            lista de ubicacion y nombre de archivos
	 * @return lista de audio files
	 */
	public static List<AudioFile> createAudioList(List<String> files) {
		List<AudioFile> retorno = new ArrayList<AudioFile>(files.size());
		try {
			for (String s : files) {
				File tmp = new File(s);
				retorno.add(AudioFileIO.read(tmp));
			}
			return retorno;
		} catch (Exception e) {
			return null;
		} finally {
			retorno = null;
		}
	}
}
