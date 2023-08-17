package com.lio.mvc_generator;

import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MvcGeneratorFrame extends JFrame {
    private JPanel contentPanel;
    private JTextField textFieldPrefix;
    private JButton generateButton;

    public MvcGeneratorFrame() {
        setContentPane(contentPanel);
        setAlwaysOnTop(true);
        getRootPane().setDefaultButton(generateButton);

        initUI();
    }

    private void initUI() {
        generateButton.addActionListener(e -> onGenerate());
    }

    private void onGenerate() {
        String prefix = textFieldPrefix.getText();
        if (prefix.isEmpty()) {
            showDialog("Please input prefix!");
            return;
        }

        VirtualFile directory = MvcGeneratorAction.currentDirectory;
        if (directory == null) {
            showDialog("Can not get directory path!");
            return;
        }

        try {
            File destinyDirectory = new File(directory.getPath());
            if (destinyDirectory.isFile()) {
                File parentFile = new File(destinyDirectory.getParent());
                if (parentFile.isDirectory()) destinyDirectory = parentFile;
            }

            if (!destinyDirectory.isDirectory()) {
                showDialog("Please choose a directory to generate dart file.");
            }

            if (!prefix.contains("_")) {
                prefix = ExtensionKt.camelToUnderlineCase(prefix);
            }

            String viewCode = new ClassViewMaker(prefix).generateCode();
            new ClassFileGenerator(destinyDirectory, prefix + "_page.dart", viewCode).generate();
            String controllerCode = new ClassControllerMaker(prefix).generateCode();
            new ClassFileGenerator(destinyDirectory, prefix + "_controller.dart", controllerCode).generate();
            String modelCode = new ClassModelMaker(prefix).generateCode();
            new ClassFileGenerator(destinyDirectory, prefix + "_model.dart", modelCode).generate();
        } catch (IOException e) {
            showDialog("Generate dart file failed! \n stackTrack" + e);
        } finally {
            directory.refresh(false, true);
        }
        dispose();
    }

    private void showDialog(String text) {
        MessageDialog dialog = new MessageDialog(text);
        dialog.setSize(200, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
