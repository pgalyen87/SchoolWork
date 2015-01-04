import java.io.*;
import java.util.*;

import javax.swing.*;


public class MegamanGame {
	
	public static String highscore;
	
	public static void main(String args[]) {
		// create and set up the window.
		JFrame frame = new JFrame("Mega Man!");
		
		// make the program close when the window closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			FileReader fr = new FileReader("highscore.txt");
			highscore = new Scanner(new File("highscore.txt")).useDelimiter("\\Z").next();
			fr.close();
		} catch (Exception e) {
			highscore = "0";
		}

		JOptionPane.showMessageDialog(frame, "Controls are: \n" + "A & D to move left & right\n" + "W to jump\n" + "Space to shoot\n" + "High score is " + highscore);
		
		// add the GameWorld component
		GameWorld.bgMusic.gameMusic();
		GameWorld g = new GameWorld( );
		frame.add(g);
		frame.addKeyListener(g);
		
		// display the window.
		frame.setSize(1320, 480);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
