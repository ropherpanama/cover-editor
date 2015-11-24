package com.ropherpanama.mp3.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;

import com.ropherpanama.mp3.AudioFileUtils;
import com.ropherpanama.mp3.WebArtworkEditor;

public class Runit {
	public static void main(String[] args){		
//		=================== PRUEBA PARA UN SOLO ARCHIVO DE AUDIO =================== 
//		String image = "D:/cover-downloaded/Bob Marley - Is This Love.jpg";
//		String file = "D:/cover-songs/Café Tacuba - Como Te Extraño Mi Amor.mp3";
//		AudioFile audio = AudioFileUtils.createAudio(file);
		
		//FileArtworkEditor editor = new FileArtworkEditor(audio, image);
		//editor.oneArtwork();
//		=================== PRUEBA PARA UN SOLO ARCHIVO DE AUDIO ===================
		
//		=================== PRUEBA PARA VARIOS ARCHIVOS DE AUDIO ===================
//		String commonImage = "D:/cover-downloaded/Bob Marley - Is This Love.png";
//		
//		List<String> archivos = new ArrayList<String>();
//		archivos.add("D:/cover-songs/Bacilos - Caraluna.mp3");
//		archivos.add("D:/cover-songs/Charlie Zaa Donde Esta El Amor.mp3");
//		archivos.add("D:/cover-songs/Deep Purple-Burn.mp3");
//		
//		List<AudioFile> audios = AudioFileUtils.createAudioList(archivos);
//		FileArtworkEditor editor = new FileArtworkEditor(audios, commonImage);
//		editor.manyArtworks();
//		=================== PRUEBA PARA VARIOS ARCHIVOS DE AUDIO ===================
		
//		=================== PRUEBA PARA WEB COVERS ===================
		List<String> archivos = new ArrayList<String>();
		File directory = new File("D:/Dropbox/Music Download/");
		File[] songs = directory.listFiles();
		
		for(File s : songs) {
			archivos.add(s.getPath());
		}
		
		List<AudioFile> audios = AudioFileUtils.createAudioList(archivos);
		WebArtworkEditor editor = new WebArtworkEditor();
		editor.searchAndDownloadArtworks(audios);
		
//		=================== PRUEBA PARA WEB COVERS ===================
	}
}
