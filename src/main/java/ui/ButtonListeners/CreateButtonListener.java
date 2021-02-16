package ui.ButtonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateButtonListener implements ActionListener {

    //ToDo получение fileName и Executor с Container получение с полей их значения и создание новой персоный с этими значениями и выполнение метода extcutor.create и container.getTable.redrawTable()
    private final Container container;
    private final JTextField txtId;
    private final JTextField txtFname;
    private final JTextField txtLname;
    private final JTextField txtAge;
    private final JTextField txtCity;

    public CreateButtonListener(Container container, JTextField txtId, JTextField txtFname, JTextField txtLname, JTextField txtAge, JTextField txtCity) {
        this.container = container;
        this.txtId = txtId;
        this.txtFname = txtFname;
        this.txtLname = txtLname;
        this.txtAge = txtAge;
        this.txtCity = txtCity;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
