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

import methods.TemperatureRequest;

public class TemperaturePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfTemperature;
	private JTextField tfResult;
	private JLabel lblTitle;
	private JComboBox<String> cbFrom, cbTo;
	private JButton btnTemperatureChange;
	private String[] symbolsArray;
	private JLabel lblFrom, lblTo, lblTemperature, lblResult;
	private String from, to;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TemperaturePanel() {		setBounds(100, 100, 536, 270);
		
		
		symbolsArray = TemperatureRequest.getUntitString();
		setLayout(null);
		
		lblTitle = new JLabel("Cambiar Escalas de Temperatura");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(28, 10, 480, 37);
		add(lblTitle);
		
		tfTemperature = new JTextField();
		tfTemperature.setToolTipText("Inserte Temperatura Aqui");
		tfTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		tfTemperature.setBounds(340, 91, 170, 19);
		add(tfTemperature);
		tfTemperature.setColumns(10);
		
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
		add(cbFrom);
		
		cbTo = new JComboBox<String>();
		cbTo.setBounds(119, 155, 170, 21);
		cbTo.setModel(new DefaultComboBoxModel<String>(symbolsArray));
		cbTo.setSelectedIndex(1);
		to = symbolsArray[cbTo.getSelectedIndex()];
		add(cbTo);
		
		btnTemperatureChange = new JButton("CAMBIAR");
		btnTemperatureChange.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTemperatureChange.setBounds(178, 220, 175, 32);
		btnTemperatureChange.addActionListener(this);
		add(btnTemperatureChange);
		
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
		
		lblTemperature = new JLabel("Temperatura a cambiar:");
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTemperature.setBounds(329, 58, 192, 23);
		add(lblTemperature);
		
		lblResult = new JLabel("Temperatura Cambiada:");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResult.setBounds(322, 121, 206, 25);
		add(lblResult);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnTemperatureChange) {
			double temperature = 0.0;
			try {
			    temperature = Double.parseDouble(tfTemperature.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null,"Entrada no valida: " + tfTemperature.getText() + ". Inserte un numero");
				tfTemperature.setText("");
			}
			from = symbolsArray[cbFrom.getSelectedIndex()];
			to = symbolsArray[cbTo.getSelectedIndex()];
			if(from != to) {
			temperature = TemperatureRequest.convertTemperature(temperature, from, to);
			DecimalFormat df = new DecimalFormat("#0.00");
			tfResult.setText(df.format(temperature));
			}else {
				JOptionPane.showMessageDialog(null,"Las unidades no pueden ser iguales");
			}
			
		}
		
	}

}
