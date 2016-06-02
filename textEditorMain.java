import javax.swing.*;

/**
 * Created by MuseMath on 6/2/2016.
 */
public class textEditorMain {
    public static void main(String[] args){
        //creating an instance of the class textEditorFrame
        textEditorFrame tef = new textEditorFrame();

        //setting size to frame
        tef.setSize(1000, 800);

        //setting location to be set to the middle of the screen
        tef.setLocationRelativeTo(null);

        //going to close application when the close button is clicked
        tef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //to actually see the editor
        tef.setVisible(true);
    }
}
