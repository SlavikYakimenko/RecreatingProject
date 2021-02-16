package ui.ButtonListeners;

import filehelpers.FileExecutor;
import ui.Table;

import javax.swing.*;

public class Container {
    private final Table table;
    private String fileName = "";
    private FileExecutor fileExecutor;

    public Container(JFrame jFrame) {
        table = new Table(jFrame, this);
    }

    public Table getTable() {
        return table;
    }

    public FileExecutor getFileExecutor() {
        return fileExecutor;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileExecutor(FileExecutor fileExecutor) {
        this.fileExecutor = fileExecutor;
    }
}
