import java.awt.event.*;                                                                                               
import javax.swing.*;

import java.io.*;

public class FileMenu {

	
	
  // adds the file menu to the menu bar	
  public JMenuBar addFileMenu(JMenuBar menubar) {
    

    String text ="hello";

    // create the file menu
    JMenu file = new JMenu("File");
    file.setMnemonic(KeyEvent.VK_F);

    // add menu items and mnemonics/ tool tips
    JMenuItem exit = new JMenuItem("Exit", null);
    exit.setMnemonic(KeyEvent.VK_E);
    exit.setToolTipText("Exit the program");
    
    JMenuItem save = new JMenuItem("Save", null);
    save.setMnemonic(KeyEvent.VK_S);
    save.setToolTipText("Save the file with a default name");
    
    JMenuItem saveAs = new JMenuItem("Save as...", null);
    saveAs.setMnemonic(KeyEvent.VK_S);
    saveAs.setToolTipText("Save the file with a custom name");
    
    JMenuItem load = new JMenuItem("Load", null);
    load.setMnemonic(KeyEvent.VK_L);
    load.setToolTipText("Loads a file");
    
    JMenuItem search = new JMenuItem("Search",null);
    search.setMnemonic(KeyEvent.VK_X);
    search.setToolTipText("Searches for given text");
    
    
    //action listeners for each function 
    search.addActionListener(new ActionListener()
    {
            public void actionPerformed(ActionEvent e)
            {
                    Hunter hunter = new Hunter();
                    String name = JOptionPane.showInputDialog("Enter text to be searched.");
                    hunter.search(name);
            }
    });
    
    
   
    exit.addActionListener(new ActionListener( ) 
    {
      public void actionPerformed(ActionEvent e) 
      {
        System.exit(0);
      }   
    }); 
    
   
    
    save.addActionListener(new ActionListener( ) 
    {
    	
    	
    	int count = 1;
    	
        public void actionPerformed(ActionEvent e) 
        {
         
        	try{
        	PrintWriter out = new PrintWriter("filename"+count+".txt");	
        	JTextArea t = TextBox.getInstance();
       
		t.write(out);	
        	out.close();
        	count++;
        
        } catch(IOException a){
        	
        }
        }
        
           
      }); 
    
    
    saveAs.addActionListener(new ActionListener( ) 
    {
        public void actionPerformed(ActionEvent e) 
        {
          
        	String name = JOptionPane.showInputDialog("Enter the name of your file:");
        	
        	try{
                PrintWriter out = new PrintWriter(name);	
                JTextArea t = TextBox.getInstance();
                       
                t.write(out);
                	
                out.close();
             
                
                } catch(IOException a){
                	
                }
             
         }	
        	
        	
    
      }); 
    
    load.addActionListener(new ActionListener( ) 
    {
        public void actionPerformed(ActionEvent e) 
        {


        	String name = JOptionPane.showInputDialog("Enter the name of the file to load:");
        	
        	
        	try {
        		   
        		   FileReader fr = new FileReader(name);
        		   BufferedReader reader = new BufferedReader(fr);
        		   JTextArea t = TextBox.getInstance();
        		   
        		     t.read(reader,"t");
        		    
        		}
        		   catch (IOException ioe) {
        		  
        			   
        		}
        	
        	
        	
        	
        }   
      }); 

    
    // add menu items to the menu
    file.add(load);
    file.add(save);
    file.add(saveAs);
    file.addSeparator();
    file.add(search);
    file.addSeparator();
    file.add(exit);

    
    
    // add file menu to the menubar
    menubar.add(file);
    
    return menubar;

   
  }
}
