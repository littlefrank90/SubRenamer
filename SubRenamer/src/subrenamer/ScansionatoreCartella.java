/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subrenamer;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Ciambellano
 */
public class ScansionatoreCartella {
    public ScansionatoreCartella(String percorsoFile){
        //il costruttore chiama smistaFile ogni volta che viene istanziato un oggetto ScansionatoreCartella
        File cartella = new File(percorsoFile);
        File[] tuttiFile = cartella.listFiles();
        this.smistaFile(tuttiFile);
    }
    private final ArrayList<String> nomiFileVideo = new ArrayList<>();
    private final ArrayList<String> nomiFileSottotitoli = new ArrayList<>();
    
    private void smistaFile(File[] tuttiFile){
        for (File file : tuttiFile){
            //se si desidera aggiungere altri formati di file video, aggiungerli nella clausola di questo if
            if (file.getName().endsWith(".mp4")|file.getName().endsWith(".mkv"))
                nomiFileVideo.add(file.getName());
            else if (file.getName().endsWith(".srt"))
                nomiFileSottotitoli.add(file.getName());
        }
    }
    
    public ArrayList<String> getNomiFileVideo (){
        //ordinamento in ordine alfabetico
        nomiFileVideo.sort(String::compareToIgnoreCase);
        return nomiFileVideo;
    }
    
    public ArrayList<String> getNomiFileSottotitoli (){
        nomiFileSottotitoli.sort(String::compareToIgnoreCase);
        return nomiFileSottotitoli;
    }
    
}
