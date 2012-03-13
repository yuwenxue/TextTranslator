package textTranslator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;

import Translators.*;

/**
 * Project: Text Translator
 * @author YuwenXue
 * 
 */
public class TextTranslator extends JFrame implements ActionListener{

	//set menu
	JLabel jl;
	JMenuBar jmb;
	JMenu file, translate;
	JPanel jpNorth, jpSouth, jpCenter;
	JMenuItem load, saveAs, quit, detab, entab, 
	doubleSpace, singleSpace, fixIndentation, 
	flowText, wrapText, identity;
	JTextArea jt_from, jt_to;
	JScrollPane jsp_from, jsp_to;
	JButton jb;

	//current translator
	TranslatorInterface guiTranslator;
	public TranslatorInterface getGuiTranslator() {
		return guiTranslator;
	}

	/**
	 * remove previous listener from the "translate" button and 
	 * add new one
	 * @param Translator - particular translator to translate
	 */
	public void setGuiTranslator(TranslatorInterface Translator) {
		this.guiTranslator = Translator;
		if(jb.getActionListeners().length > 0) {
			for(int i = 0; i < jb.getActionListeners().length; i++) {
				jb.removeActionListener(jb.getActionListeners()[i]);
			}
		}
		jb.addActionListener(new TranslateListener(Translator));
	}

	/**main function
	 * @param args
	 */
	public static void main(String[] args) {
		TextTranslator tt = new TextTranslator();
		tt.run();

	}

	/**
	 * set the title of window
	 * @param translatorName current translator's name
	 */
	public void setFrameTitle(String translatorName) {
		this.setTitle("Yuwen's TextTranslator - "+translatorName);

	}

	/**
	 * set input text
	 * @param text - input text
	 */
	public void setInput(String text) {
		this.jt_from.setText(text);
	}
	/**
	 * set the result text, this method is called by listener to output result
	 * @param text - text to be displayed
	 */
	public void setText(String text) {
		this.jt_to.setText(text);
	}

	/**
	 * set the description of translator up on the label
	 * @param description - description
	 */
	public void setDescription(String description) {
		this.jl.setText(description);
	}



	/**
	 * to build GUI and add all components
	 */
	public void run() {
		this.setTitle("Yuwen's TextTranslator");
		this.setSize(Constants.window_length, Constants.window_width); 
		this.setLocation(Constants.window_start_loc_x, Constants.window_start_loc_y);

		jmb = new JMenuBar();

		//JMenus in the Bar
		file = new JMenu("File(F)");
		file.setMnemonic('F');

		translate = new JMenu("Translate(T)");
		translate.setMnemonic('T');

		//Menu items in each Menu;
		load = new JMenuItem("Load...(L)");
		load.setMnemonic('L');
		load.addActionListener(this);
		//load.addActionListener(TranslateListener);
		saveAs = new JMenuItem("Save As...(S)");
		saveAs.setMnemonic('S');
		saveAs.addActionListener(this);
		quit = new JMenuItem("Quit(Q)");
		quit.setMnemonic('Q');
		quit.addActionListener(this);


		//add MenuItem
		file.add(load);
		file.add(saveAs);
		file.addSeparator();
		file.add(quit);
		this.addTranslator(new IdentityTranslator());
		this.addTranslator(new DetabTranslator());
		this.addTranslator(new EntabTranslator());
		this.addTranslator(new SingleSpaceTranslator());
		this.addTranslator(new DoubleSpaceTranslator());
		this.addTranslator(new WrapTextTranslator());
		this.addTranslator(new FlowTextTranslator());
		this.addTranslator(new FixIndentationTranslator());
		//		translate.add(identity);
		//		translate.add(detab);
		//		translate.add(entab);
		//		translate.add(singleSpace);
		//		translate.add(doubleSpace);
		//		translate.add(wrapText);
		//		translate.add(flowText);
		//		translate.add(fixIndentation);

		//add Menu to Menubar
		jmb.add(file);
		jmb.add(translate);
		//add Menubar to the Jframe
		this.setJMenuBar(jmb);



		//set Jpanels, north, center and south
		//north panel just a sentence
		jpNorth = new JPanel();
		String instructions = "Welcom";
		jl = new JLabel(instructions, JLabel.CENTER);
		jpNorth.add(jl);

		//center panel-grid layout 2 text area
		jt_from = new JTextArea("");
		jsp_from = new JScrollPane(jt_from);
		jsp_from.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jt_to = new JTextArea("");
		jsp_to = new JScrollPane(jt_to);
		jsp_to.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jpCenter = new JPanel();
		jpCenter.setLayout(new GridLayout(2,1));
		jpCenter.add(jsp_from);
		jpCenter.add(jsp_to);

		//south panel just one button
		jb = new JButton("Translate");
		//jb.addActionListener(new TranslateListener(guiTranslator));
		jpSouth = new JPanel();
		jpSouth.add(jb);


		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpCenter, BorderLayout.CENTER);
		this.add(jpSouth, BorderLayout.SOUTH);
		//set close method
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}


	/**
	 * add a translator for particular button and add the button to menu
	 * @param translator - 
	 */
	private void addTranslator(TranslatorInterface translator) {
		JMenuItem jmi = new JMenuItem(translator.getName());
		jmi.addActionListener(new TranslateListener(translator));
		translate.add(jmi);
	}

	/**
	 * inner class of TextTranslator, so that listener could easily refer to 
	 * GUI's components and make changes
	 * @author YuwenXue
	 *
	 */
	private class TranslateListener implements ActionListener {

		TranslatorInterface translator;
		/**
		 * constructor
		 * @param translator
		 */
		public TranslateListener(TranslatorInterface translator) {
			this.translator = translator;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setFrameTitle(translator.getName());
			setDescription(translator.getDescription());
			setText(translator.translate(jt_from.getText()));
			setGuiTranslator(translator);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand(  );
		if (command.equals("Quit(Q)")) System.exit(0);
		else if (command.equals("Load...(L)")) loadFile(  );
		else if (command.equals("Save As...(S)")) saveFile(  );
	}

	/**
	 * load file
	 */
	private void loadFile (  ) {
		JFileChooser chooser = new JFileChooser(  );
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) return;
		try {
			File file = chooser.getSelectedFile(  );
			InputStream is = new FileInputStream(file); 
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer st = new StringBuffer();
			while(true) {
				line = r.readLine();
				if(line == null) {
					break;
				}
				st.append(line);
				st.append("\n");
			}
			st.deleteCharAt(st.length()-1);
			setInput(st.toString());
			
			//java.net.URL url = file.toURL(  );
			//textPane.setPage(url);
		}
		catch (Exception e) {
			e.printStackTrace();
			//textPane.setText("Could not load file: " + e);
		}
	}

	/**
	 * save file
	 */
	private void saveFile(  ) {
		JFileChooser chooser = new JFileChooser(  );
		chooser.showSaveDialog(this);
		FileWriter fw = null;
		File file = chooser.getSelectedFile();
		   try {
			fw = new FileWriter(file);
			System.out.print(this.jt_to.getText());
			   fw.write(this.jt_to.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		   finally {
			   try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		   }

	}
}
