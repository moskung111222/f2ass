package f2.spw;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.JFrame;

public class MenuListener implements ActionListener{
    JMenuItem exit;
	JMenuItem play;
    JMenuItem restart;
    JMenuItem pause;

    GameEngine engine;
	public MenuListener(JMenuItem exit,JMenuItem restart,JMenuItem play,JMenuItem pause,GameEngine engine){
        
        this.exit = exit;
        this.restart = restart;
        this.pause = pause;
		this.play = play;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        clickMenu(e);
    }
    
    private void clickMenu(ActionEvent e){
        if(e.getSource() == exit){
            System.exit(0);
        }
       if(e.getSource() == restart){	    
		    engine.die();
			System.exit(0);
        }
		if(e.getSource() == play){
           engine.start();
        }
       
        if(e.getSource() == pause){
            engine.stop();
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "SCORE : " + engine.getScore());
        }
    }
}