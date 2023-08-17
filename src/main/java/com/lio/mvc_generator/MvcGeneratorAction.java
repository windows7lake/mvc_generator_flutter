package com.lio.mvc_generator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

public class MvcGeneratorAction extends AnAction {

    public static VirtualFile currentDirectory;

    @Override
    public void actionPerformed(AnActionEvent e) {
        currentDirectory = e.getData(CommonDataKeys.VIRTUAL_FILE);

        MvcGeneratorFrame frame = new MvcGeneratorFrame();
        frame.setSize(600, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
