package com.lio.mvc_generator;

import javax.swing.*;
import java.awt.event.*;

public class MessageDialog extends JDialog {
    private JPanel contentPanel;
    private JLabel label;
    private JButton buttonOK;

    public MessageDialog(String text) {
        setContentPane(contentPanel);
        setModal(true);
        setAlwaysOnTop(true);
        getRootPane().setDefaultButton(buttonOK);

        initUI(text);
    }

    private void initUI(String text) {
        label.setText(text);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        // call onCancel() on ESCAPE
        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }
}
