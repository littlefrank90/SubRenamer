/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subrenamer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Ciambellano
 */
public class Rinominatore{
    public Rinominatore(String percorsoFile){
        this.eseguiRinomina(percorsoFile);
    }
    
    public void eseguiRinomina(String percorsoFile){
        ScansionatoreCartella scansionatoreCartella = new ScansionatoreCartella(percorsoFile);
        ArrayList<String> nomiFileSottotitoli = scansionatoreCartella.getNomiFileSottotitoli();
        ArrayList<String> nomiFileVideo = scansionatoreCartella.getNomiFileVideo();
        
        for(String nomeSottotitolo : nomiFileSottotitoli){
            String numeroEpisodio = getNumeroEpisodio(nomeSottotitolo);
            
            for(String nomeFileVideo : nomiFileVideo){
                //se c'è un file video che contiene il numero episodio
                if (nomeFileVideo.toLowerCase().contains(numeroEpisodio.toLowerCase())){ 
                    Path percorsoFileSottotitoloRinominato = Paths.get(percorsoFile+"\\"+nomeFileVideo+".srt");
                    Path percorsoFileSottotitolo = Paths.get(percorsoFile+"\\"+nomeSottotitolo);

                    try {
                        //rinomina finalmente il sottotitolo
                        Files.move(percorsoFileSottotitolo, percorsoFileSottotitoloRinominato, StandardCopyOption.REPLACE_EXISTING);
                    } catch (NoSuchFileException fileNonTrovato) {
                        JOptionPane.showMessageDialog(null, "Errore.\nOperazione non eseguita. Cartella non valida.");
                        System.exit(0);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Errore.\nOperazione non eseguita.");
                        System.exit(0);
                    }    
                }
            }  
        }
        JOptionPane.showMessageDialog(null, "Sottotitoli rinominati correttamente.");
    }
    
    private String getNumeroEpisodio(String nomeSottotitolo){
        String numeroEpisodio = "e00"; //sono costretto a assegnare un nome di default

        Pattern patternCreazioneEpisodio = Pattern.compile("e\\d\\d",Pattern.CASE_INSENSITIVE);
        Matcher matcherCreazioneEpisodio = patternCreazioneEpisodio.matcher(nomeSottotitolo);
        
        //magia nera, crea una sottostringa a partire dalla sequenza regex trovata nella stringa "nomeSottotitolo"
        if(matcherCreazioneEpisodio.find()){
            numeroEpisodio = nomeSottotitolo.substring(matcherCreazioneEpisodio.start(), matcherCreazioneEpisodio.end());
        }
        return numeroEpisodio;
    }
    
}
