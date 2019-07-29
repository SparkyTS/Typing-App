package typingApplication;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import static java.awt.event.KeyEvent.*;
import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
public class TypingApplication extends JFrame{

    private final JTextArea textArea = new JTextArea(10,90);
    private HashMap<Integer,JButton> map = new HashMap<>();
    private final static Dimension keyDimension = new Dimension(42,40);
    private final JButton caps = new JButton("Caps");


    public void addButton(String name,int keyCode){
        JButton jButton = new JButton(name);
        map.put(keyCode,jButton);
        jButton.setPreferredSize(keyDimension);
        add(jButton);
    }

    public void addButton(String name,int keyCode1,int keyCode2){
        JButton jButton = new JButton(name);
        map.put(keyCode1,jButton);
        map.put(keyCode2,jButton);
        jButton.setPreferredSize(keyDimension);
        add(jButton);
    }

    public void addButton(String name,int keyCode,Dimension dimension){
        JButton jButton = new JButton(name);
        map.put(keyCode,jButton);
        jButton.setPreferredSize(dimension);
        add(jButton);
    }

    public void addFirstRow(){
        addButton("~",VK_BACK_QUOTE);

        //adding numeric keys from 0-10 in order 1-10 and 0.
        for(int i = 1 ; i < 10 ; i++) addButton(String.valueOf(i),VK_NUMPAD0 + i, VK_0 + i);

        addButton("0",VK_NUMPAD0,VK_0);
        addButton("-",VK_MINUS,VK_SUBTRACT);
        addButton("+",VK_EQUALS,VK_ADD);
        addButton("Backspace",VK_BACK_SPACE,new Dimension(100,40));
    }

    private void addSecondRow() {
        addButton("Tab",VK_TAB,new Dimension(60,40));

        //Adding buttons for key QWERTYUIOP.
        int qToP[] = {VK_Q,VK_W,VK_E,VK_R,VK_T,VK_Y,VK_U,VK_I,VK_O,VK_P};
        for(int keyCode : qToP) addButton(KeyEvent.getKeyText(keyCode), keyCode);

        addButton("[",VK_OPEN_BRACKET);
        addButton("]",VK_CLOSE_BRACKET);
        addButton("\\",VK_BACK_SLASH);
    }

    private void addThirdRow() {
        //adding capsLock separately because can in only two state ON or OFF.
        caps.setPreferredSize(new Dimension(60,40));
        if(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK))//if caps on then set the color red.
            caps.setBackground(Color.RED);
        else
            caps.setBackground(Color.WHITE);
        add(caps);


        //adding buttons ASDFGHJKL
        int aToL[] = {VK_A,VK_S,VK_D,VK_F,VK_G,VK_H,VK_J,VK_K,VK_L};
        for(int keyCode : aToL) addButton(KeyEvent.getKeyText(keyCode), keyCode);

        addButton(";",VK_SEMICOLON);
        addButton("\"",VK_QUOTE);
        addButton("Enter",VK_ENTER,new Dimension(90,40));
    }

    private void addFourthRow() {
        addButton("Shift",VK_SHIFT,new Dimension(90,40));

        int ztoM[] = {VK_Z,VK_X,VK_C,VK_V,VK_B,VK_N,VK_M};
        for(int keyCode:ztoM) addButton(String.valueOf(KeyEvent.getKeyText(keyCode)),keyCode);

        addButton(",",VK_COMMA);
        addButton(".",VK_PERIOD);
        addButton("?",VK_SLASH);
        add(new JLabel("    "));
        addButton("↑",VK_UP);
    }

    private void addFifthRow() {
        add(new JLabel("                                              "));
        addButton(" ",VK_SPACE,new Dimension(330,40));
        add(new JLabel("                 "));
        addButton("←",VK_LEFT,new Dimension(43,40));
        addButton("↓",VK_DOWN);
        addButton("→",VK_RIGHT,new Dimension(43,40));
    }

    public TypingApplication(){
        //setting properties of TypingApplication.
        super("Typing Application");
        setSize(750,475);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setFocusTraversalKeysEnabled(false);
        //setting properties of textArea.
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("Welcome,"+System.getProperty("user.name")+"\n"
                        +"This application is here to help you increase your typing skill "
                        +"just press any key on the key board and the pressed key will be highlighted.\n"
                +"-----------------------------------------------------------------------------------------\n");
        textArea.setCaretPosition(textArea.getText().length());
        textArea.addKeyListener(new KeyAdapter() {
            JButton button=null;
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_CAPS_LOCK)
                    caps.setBackground(caps.getBackground()==Color.WHITE?Color.RED:Color.WHITE);
                else if(e.getKeyCode()==KeyEvent.VK_DELETE){}
                else{
                    for(JButton j : map.values())
                        j.setBackground(Color.WHITE);

                    if((button = map.get(e.getKeyCode()))!=null){
                        if(e.getKeyCode()!=KeyEvent.VK_DELETE && e.getKeyCode()!=KeyEvent.VK_CAPS_LOCK)
                            button.setBackground(Color.RED);
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(button!=null && e.getKeyCode()!=KeyEvent.VK_CAPS_LOCK)
                    button.setBackground(Color.WHITE);
            }
        });


        //adding all the elements to the typing application.
        add(new JScrollPane(textArea));
        textArea.requestFocus();
        addFirstRow();
        addSecondRow();
        addThirdRow();
        addFourthRow();
        addFifthRow();
        add(new JLabel("Developed by Tanay Shah"),BorderLayout.PAGE_END);
        validate();
    }

    public static void main(String[] args) {
        try {setLookAndFeel(getSystemLookAndFeelClassName());} catch (Exception e) { }
        new TypingApplication();
    }
}