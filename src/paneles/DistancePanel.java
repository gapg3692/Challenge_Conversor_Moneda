package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import methods.MeasurementUnitsConverter;

public class DistancePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfMeasure;
	private JTextField tfResult;
	private JLabel lblTitle;
	private JComboBox<String> cbFrom, cbTo;
	private JButton btnMeasureChange;
	private JLabel lblFrom, lblTo, lblMeasure, lblResult;
	private String[] symbolsArray;
	private String from, to;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DistancePanel() {
		setBounds(100, 100, 536, 270);

		symbolsArray = MeasurementUnitsConverter.getUnitString();
		setLayout(null);

		lblTitle = new JLabel("Cambiar Unidades de Medida");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(28, 10, 480, 37);
		add(lblTitle);

		tfMeasure = new JTextField();
		tfMeasure.setToolTipText("Inserte Temperatura Aqui");
		tfMeasure.setHorizontalAlignment(SwingConstants.CENTER);
		tfMeasure.setBounds(340, 91, 170, 19);
		add(tfMeasure);
		tfMeasure.setColumns(10);

		tfResult = new JTextField();
		tfResult.setHorizontalAlignment(SwingConstants.CENTER);
		tfResult.setBackground(new Color(255, 255, 255));
		tfResult.setEditable(false);
		tfResult.setColumns(10);
		tfResult.setBounds(340, 156, 170, 19);
		add(tfResult);

		cbFrom = new JComboBox<String>();
		cbFrom.setBounds(119, 90, 170, 21);
		cbFrom.setModel(new DefaultComboBoxModel<String>(symbolsArray));
		cbFrom.setSelectedIndex(0);
		from = symbolsArray[cbFrom.getSelectedIndex()];
		AutoCompleteDecorator.decorate(cbFrom);
		add(cbFrom);

		cbTo = new JComboBox<String>();
		cbTo.setBounds(119, 155, 170, 21);
		cbTo.setModel(new DefaultComboBoxModel<String>(symbolsArray));
		cbTo.setSelectedIndex(1);
		to = symbolsArray[cbTo.getSelectedIndex()];
		AutoCompleteDecorator.decorate(cbTo);
		add(cbTo);

		btnMeasureChange = new JButton("CAMBIAR");
		btnMeasureChange.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMeasureChange.setBounds(178, 220, 175, 32);
		btnMeasureChange.addActionListener(this);
		add(btnMeasureChange);

		lblFrom = new JLabel("Cambiar De:");
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrom.setBounds(10, 94, 111, 13);
		add(lblFrom);

		lblTo = new JLabel("Cambiar A:");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTo.setBounds(10, 159, 111, 13);
		add(lblTo);

		lblMeasure = new JLabel("Medida a cambiar:");
		lblMeasure.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeasure.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMeasure.setBounds(329, 58, 192, 23);
		add(lblMeasure);

		lblResult = new JLabel("Unidad Cambiada:");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResult.setBounds(322, 121, 206, 25);
		add(lblResult);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnMeasureChange) {
			double measure = 0.0;
			try {
				measure = Double.parseDouble(tfMeasure.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null,
						"Entrada no valida: " + tfMeasure.getText() + ". Inserte un numero");
				tfMeasure.setText("");
			}
			from = symbolsArray[cbFrom.getSelectedIndex()];
			to = symbolsArray[cbTo.getSelectedIndex()];
			if (from != to && measure >= 0) {
				measure = MeasurementUnitsConverter.convert(measure, from, to);
				DecimalFormat df = new DecimalFormat("#0.00");
				tfResult.setText(df.format(measure));
			} else if(from == to) {
				JOptionPane.showMessageDialog(null, "Las unidades no pueden ser iguales");
			} else if(measure < 0) {
				JOptionPane.showMessageDialog(null, "La distancia tiene que ser mayor o igual a 0");
			}
		}

	}

}
