package ui.ButtonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {
    //ToDo получение fileName и Executor с Container получение с поля id  персоный с и выполнение метода extcutor.delete и container.getTable.redrawTable()

    private final Container container;
    private final JTextField txtId;

    public DeleteButtonListener(Container container, JTextField txtId) {
        this.container = container;
        this.txtId = txtId;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
