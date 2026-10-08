import javax.swing.*;
import java.util.List;

public class BestScoresFrame extends  JDialog{

    private final JTextArea textArea = new JTextArea();



    public void showText(List<String> entries){
         StringBuilder text = new StringBuilder();
         int place = 1;
         for(String entry:entries){
             text.append(place).append(" .").append(entry).append("\n");
             place++;
         }
         textArea.setText(text.toString());
        setVisible(true);
    }

    public BestScoresFrame(){
        setTitle("Best players");
        setSize(200,150);
        setFocusableWindowState(false);
        setResizable(false);
        textArea.setEditable(false);
        add(textArea);
    }



}
