package test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Play0 extends Thread{
    Player player;
    public Play0() {
    }
     public void run() {
        try {
            play();     
        } catch (FileNotFoundException | JavaLayerException e) {
             e.printStackTrace();
        }catch (ThreadDeath e) {
			// TODO: handle exception
		}
     }
    public void play() throws FileNotFoundException, JavaLayerException { 
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("meta\\5.mp3")); 
        player = new Player(buffer); 
        player.play(); 
    } 
} 
