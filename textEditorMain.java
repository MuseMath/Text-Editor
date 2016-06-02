import javax.swing.*;

/**
 * Created by MuseMath on 6/2/2016.
 */
public class textEditorMain {
    public static void main(String[] args){
        textEditorFrame tef = new textEditorFrame();

        tef.setSize(1000, 800);
        tef.setLocationRelativeTo(null);
        tef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tef.setVisible(true);
    }
}
