package ui.ButtonListeners;

import fileHelpers.Factory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseTypeListener implements ActionListener {

    private final Container container;

//    JMenuBar

    public ChooseTypeListener(Container container) {
        this.container = container;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //ToDo СДЕЛАТЬ  -> Этот листенер, который отвечает за выбор формата файла или типа БД и Executor.
    // Тут должны быть вызовны методы контейнера, на setExecutor и setfileName после того, кого как определили Executor и fileName

}
