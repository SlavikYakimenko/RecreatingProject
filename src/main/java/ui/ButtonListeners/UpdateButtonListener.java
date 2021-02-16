package ui.ButtonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateButtonListener implements ActionListener {
    private final Container container;
    private final JTextField txtId;
    private final JTextField txtFname;
    private final JTextField txtLname;
    private final JTextField txtAge;
    private final JTextField txtCity;

    public UpdateButtonListener(Container container, JTextField txtId, JTextField txtFname, JTextField txtLname, JTextField txtAge, JTextField txtCity) {
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
    //ToDo получение fileName и Executor с Container получение с полей значения , запись в стринг массив  и выполнение метода extcutor.update и container.getTable.redrawTable();
    // link github.com/AntonShymbryk/KAVO/.... для получения более подробной информации и документации

}
