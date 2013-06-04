package smn.snote;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

public class Main {
	public static boolean RIGHT_TO_LEFT = false;

	
	public static void addComponentsToPane(Container pane) {

		if (!(pane.getLayout() instanceof BorderLayout)) {
			pane.add(new JLabel("Container doesn't use BorderLayout!"));
			return;
		}

		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		}

		JToolBar toolBar = new JToolBar("Still draggable");
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
	
		
		addButtons(toolBar);
		pane.add(toolBar, BorderLayout.PAGE_START);
		
		//tree
		int newNodeSuffix = 1;
	    
	    DynamicTree treePanel;
	    
		
		
		
		
		
		
		// Make the center component big, since that's the
		// typical usage of BorderLayout.
		JButton button = new JButton("Button 2 (CENTER)");
		button.setPreferredSize(new Dimension(200, 100));
		pane.add(button, BorderLayout.CENTER);

		button = new JButton("Button 3 (LINE_START)");
		pane.add(button, BorderLayout.LINE_START);

		button = new JButton("Long-Named Button 4 (PAGE_END)");
		pane.add(button, BorderLayout.PAGE_END);

		button = new JButton("5 (LINE_END)");
		pane.add(button, BorderLayout.LINE_END);
	}
	
	public static void createDynamicTree() {
        //Create the components.
        DynamicTree treePanel = new DynamicTree();
        populateTree(treePanel);
    }

    public static void populateTree(DynamicTree treePanel) {
        String p1Name = new String("Parent 1");
        String p2Name = new String("Parent 2");
        String c1Name = new String("Child 1");
        String c2Name = new String("Child 2");

        DefaultMutableTreeNode p1, p2;

        p1 = treePanel.addObject(null, p1Name);
        p2 = treePanel.addObject(null, p2Name);

        treePanel.addObject(p1, c1Name);
        treePanel.addObject(p1, c2Name);

        treePanel.addObject(p2, c1Name);
        treePanel.addObject(p2, c2Name);
    }
    
	public static void addButtons(JToolBar bar){
		
		JButton button=new JButton();
		button.setText("Save");
		bar.add(button);
		
		bar.addSeparator();
		
		button=new JButton();
		button.setText("New Note");
		bar.add(button);
		
		button=new JButton();
		button.setText("New Category");
		bar.add(button);
		
		bar.addSeparator();
		
		button=new JButton();
		button.setText("New Category");
		bar.add(button);
		
		bar.addSeparator();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 * @throws IOException 
	 */
	private static void createAndShowGUI() throws IOException {

		// Create and set up the window.
		JFrame frame = new JFrame("BorderLayoutDemo");
		frame.setSize(800, 600);
		frame.setPreferredSize(new Dimension(800,600));
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(ImageIO.read(Main.class.getResource("/icon.png")));
		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());
		// Use the content pane's default BorderLayout. No need for
		// setLayout(new BorderLayout());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	
	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			 //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}

class Actions{
	final public static String CMD_ADD_NOTE="add_note";
	final public static String CMD_ADD_FOLDER="add_folder";
	final public static String CMD_DELETE_NOTE="delete_note";
	final public static String CMD_DELETE_FOLDER="delete_folder";
}

class TreeActionListener implements ActionListener{
	private JComponent com;
	public TreeActionListener(JComponent component){
		this.com=component;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DynamicTree treePanel = (DynamicTree)this.com;
        String command = e.getActionCommand();
        
        if (Actions.CMD_ADD_NOTE.equals(command)) {
            //Add button clicked
            treePanel.addObject("New Node ");
        } else if (Actions.CMD_ADD_FOLDER.equals(command)) {
            //Remove button clicked
        	treePanel.addObject("New Node ");
        } else if (Actions.CMD_DELETE_NOTE.equals(command)) {
            //Clear button clicked.
            treePanel.removeCurrentNode();
        }else if (Actions.CMD_DELETE_FOLDER.equals(command)) {
            //Clear button clicked.
            treePanel.removeCurrentNode();
        }
	}
}