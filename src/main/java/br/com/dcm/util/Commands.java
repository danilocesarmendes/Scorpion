package br.com.dcm.util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
/**
 *
 * @author Danilo Mendes
 */
import java.io.*;

import javax.swing.JFrame;

public class Commands {

    static final Runtime run = Runtime.getRuntime();
    static BufferedReader bufferedReader;
    static DisplayMode originalDM;
    

    public static void main(String[] args) {
    }
    
    /**
     * @param commands
     * @see executa lista de comando no prompt de comando
     */
    public static void execute(String... commands){

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                    String.join("& ", commands));

            builder.redirectErrorStream(true);

            Process p = builder.start();

            bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream())); 

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void iniciaResolucao(GraphicsDevice device) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		originalDM = device.getDisplayMode();

		device.setFullScreenWindow(frame);
		device.setDisplayMode(new DisplayMode(1280, 800, 32, 60));
		frame.setVisible(false);
	}
    
    public static void retornaResolucao(GraphicsDevice device) {
    	device.setDisplayMode(originalDM);
    }
}
