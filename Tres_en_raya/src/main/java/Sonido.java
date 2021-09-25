
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author javi_
 */

public class Sonido {
    
    String ubicacionSonidoPosicionVacia;
    String ubicacionSonidoPosicionOcupada;
    String ubicacionSonidoVictoria;
    String ubicacionSonidoNuevaPantalla;
    String ubicacionSonidoExit;
        
    public Sonido(){
        
    }
            
    public void ReproducirSonidoPosicionVacia(){
        try {
            ubicacionSonidoPosicionVacia = "C://Users//javi_//Documents//NetBeansProjects//Tres_en_raya//src//main//resources//Sounds//correcto1.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ubicacionSonidoPosicionVacia).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }
     
    public void ReproducirSonidoPosicionOcupada(){
        try {
            ubicacionSonidoPosicionOcupada = "C:/Users/javi_/Documents/NetBeansProjects/Tres_en_raya/src/main/resources/Sounds/bump.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ubicacionSonidoPosicionOcupada).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           System.out.println("Error al reproducir el sonido.");
       }
    }
    
    public void ReproducirSonidoVictoria(){
        try {
            ubicacionSonidoVictoria = "C:/Users/javi_/Documents/NetBeansProjects/Tres_en_raya/src/main/resources/Sounds/1up.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ubicacionSonidoVictoria).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           System.out.println("Error al reproducir el sonido.");
       }
    }
    
    public void ReproducirSonidoNuevaPantalla(){
        try {
            ubicacionSonidoNuevaPantalla = "C:/Users/javi_/Documents/NetBeansProjects/Tres_en_raya/src/main/resources/Sounds/nsmb_hammer_throw.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ubicacionSonidoNuevaPantalla).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           System.out.println("Error al reproducir el sonido.");
       }
    }
    
    public void ReproducirSonidoExit(){
        try {
            ubicacionSonidoExit = "C:/Users/javi_/Documents/NetBeansProjects/Tres_en_raya/src/main/resources/Sounds/sm64_mario_okey-dokey.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ubicacionSonidoExit).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           System.out.println("Error al reproducir el sonido.");
       }
    }
}

