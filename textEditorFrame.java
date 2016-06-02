import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by MuseMath on 6/2/2016.
 */
public class textEditorFrame extends JFrame {

    //Creating objects for the GUI application
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu fDec;
    private JMenu fSize;

    private JMenuItem newFile;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem saveas;
    private JMenuItem exit;

    private JMenuItem arial;
    private JMenuItem arialBlack;
    private JMenuItem serif;
    private JMenuItem roman;

    private JMenuItem changeSize;

    private String fontF = "Arial";
    private int fontD = Font.PLAIN;
    private int fontS = 12;

    private JTextArea textArea;
    private JLabel status;

    private JScrollPane scroller;
    private File fileName;

    public textEditorFrame(){
        //title of the frame
        super("MuseMath Text Editor");

        //creating objects with names for the menu bar and menu
        menuBar = new JMenuBar();
        file = new JMenu("File");
        fDec = new JMenu("Font Style");
        fSize = new JMenu("Font Size");

        ////creating objects with names for the menu items
        newFile = new JMenuItem("New File");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveas = new JMenuItem("Save As");
        exit = new JMenuItem("Exit");

        arial = new JMenuItem("Arial");
        arialBlack = new JMenuItem("Arial Black");
        serif = new JMenuItem("Serif");
        roman = new JMenuItem("Times New Roman");

        changeSize = new JMenuItem("Change Size");

        //created a label to display what has changed and our text area to write in
        status = new JLabel("Welcome to MuseMath editor!");
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scroller = new JScrollPane(textArea);
        add(scroller, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        //adding all objects to its respectful place
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(exit);

        fDec.add(arial);
        fDec.add(arialBlack);
        fDec.add(serif);
        fDec.add(roman);

        fSize.add(changeSize);

        menuBar.add(file);
        menuBar.add(fDec);
        menuBar.add(fSize);

        //adding the menu bar to the frame
        setJMenuBar(menuBar);

        //adding text area to frame
        add(textArea);

        //Action Listener to every menu item
        newFile.addActionListener(new newF());
        open.addActionListener(new openFile());
        save.addActionListener(new saveFile());
        saveas.addActionListener(new saveAsFile());
        exit.addActionListener(new exitEditor());

        arial.addActionListener(new arialFont());
        arialBlack.addActionListener(new arialBlackFont());
        serif.addActionListener(new serifFont());
        roman.addActionListener(new romanFont());

        changeSize.addActionListener(new changeSizeFont());
    }
    //going to create a new file and save current file if you want to
    private class newF implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int value = JOptionPane.showConfirmDialog(null, "Would you like to save file?", "Please Confirm", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                save.doClick();
                fileName = null;
                textArea.setText(null);
                status.setText("Generated new file");
            } else {
                fileName = null;
                textArea.setText(null);
                status.setText("Generated new file");
            }
        }
    }

    //going to open a file
    private class openFile implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            int r = chooser.showOpenDialog(new JFrame());
            fileName = chooser.getSelectedFile();
            String fname = fileName.getAbsolutePath();
            System.out.println(fname);

            try {
                FileReader fr = new FileReader(fname);
                BufferedReader br = new BufferedReader(fr);
                textArea.read(br, null);
                br.close();
                textArea.requestFocus(true);
                status.setText("Opened file: " + fileName.getName());
            } catch (Exception exce) {
                JOptionPane.showMessageDialog(null, exce);
            }
        }
    }

    //save a file to chooser location, also saves existing files to the same file.
    private class saveFile implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String content = textArea.getText();

            if (fileName == null) {
                saveas.doClick();
            } else {
                try {
                    FileWriter fw = new FileWriter(fileName.getPath());
                    fw.write(content);
                    fw.flush();
                    fw.close();
                    JOptionPane.showMessageDialog(null, "successfully saved file as \"" + fileName.getName() + "");
                    status.setText("Saved file as: " + fileName.getName());
                } catch (Exception exce) {
                    JOptionPane.showMessageDialog(null, exce.getMessage());
                }
            }
        }
    }

    //saves file with different name
    private class saveAsFile implements ActionListener {
        public void actionPerformed(ActionEvent e){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Save File");
            int result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String content = textArea.getText();
                File fi = chooser.getSelectedFile();
                if (fi.exists()) {
                    try {
                        FileWriter fw = new FileWriter(fi.getPath());
                        fw.write(content);
                        fw.flush();
                        fw.close();
                    } catch (Exception exce) {
                        JOptionPane.showMessageDialog(null, exce.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "successfully saved file as \"" + fi.getName() + "\"");
                } else {
                    try {
                        FileWriter fw = new FileWriter(fi.getPath() + ".txt");
                        fw.write(content);
                        fw.flush();
                        fw.close();
                    } catch (Exception exce) {
                        JOptionPane.showMessageDialog(null, exce.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "successfully saved file as \"" + fi.getName() + "\"");
                }
            }
        }
    }

    //closes out of the editor
    private class exitEditor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int value = JOptionPane.showConfirmDialog(null, "Want to save file before exiting?", "EXIT", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                save.doClick();
                System.exit(0);
            } else
                System.exit(0);
        }
    }

    //changes font to Arial
    private class arialFont implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fontF = "Arial";
            textArea.setFont(new Font(fontF, fontD, fontS));
            status.setText("Font changed to Arial");
        }
    }

    //changes font to Arial Black
    private class arialBlackFont implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fontF = "Arial Black";
            textArea.setFont(new Font(fontF, fontD, fontS));
            status.setText("Font changed to Arial Black");
        }
    }

    //changes font to Serif
    private class serifFont implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fontF = "Serif";
            textArea.setFont(new Font(fontF, fontD, fontS));
            status.setText("Font changed to Serif");
        }
    }

    //changes font to Time New Roman
    private class romanFont implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fontF = "Times New Roman";
            textArea.setFont(new Font(fontF, fontD, fontS));
            status.setText("Font changed to Times New Roman");
        }
    }

    //changes font size to what ever user wants it to be
    private class changeSizeFont implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fontS = Integer.parseInt(JOptionPane.showInputDialog(null, "What size would you like your text to be?", "Font Size", JOptionPane.QUESTION_MESSAGE));
            textArea.setFont(new Font(fontF, fontD, fontS));
            status.setText("Font size changed to " + fontS);
        }
    }
}
