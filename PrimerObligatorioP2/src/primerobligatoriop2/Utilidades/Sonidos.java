package primerobligatoriop2.Utilidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joaquinmerida
 */
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sonidos {

    public static void reproducirSonido() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String rutaArchivo = Sonidos.class.getResource("maullido.wav").getPath();
        File file = new File(rutaArchivo);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
