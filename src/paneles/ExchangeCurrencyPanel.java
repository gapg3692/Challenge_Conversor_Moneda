package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import methods.ExchangeApiRequests;

public class ExchangeCurrencyPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfAmount;
	private JTextField tfResult;
	private JLabel lblTitle;
	private JComboBox<String> cbFrom, cbTo;
	private JButton btnExchangeCurrency, btnSwitch;
	private List<String[]> symbolsArray;
	private JLabel lblFrom, lblAmount, lblTo, lblResult;
	private String from, to;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ExchangeCurrencyPanel() {
		setBounds(100, 100, 536, 270);

		symbolsArray = ExchangeApiRequests.getsymbolRequest();
		setLayout(null);

		btnSwitch = new JButton("↑↓");
		btnSwitch.setToolTipText("Presiona para intercambiar monedas y cantidad");
		btnSwitch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSwitch.addActionListener(this);
		btnSwitch.setBounds(177, 121, 54, 32);
		add(btnSwitch);

		lblTitle = new JLabel("Cambiar Moneda");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setBounds(82, 10, 367, 37);
		add(lblTitle);

		tfAmount = new JTextField();
		tfAmount.setHorizontalAlignment(SwingConstants.CENTER);
		tfAmount.setBounds(340, 91, 170, 19);
		add(tfAmount);
		tfAmount.setColumns(10);

		tfResult = new JTextField();
		tfResult.setHorizontalAlignment(SwingConstants.CENTER);
		tfResult.setBackground(new Color(255, 255, 255));
		tfResult.setEditable(false);
		tfResult.setColumns(10);
		tfResult.setBounds(340, 164, 170, 19);
		add(tfResult);

		cbFrom = new JComboBox<String>();
		cbFrom.setFont(new Font("Tahoma", Font.BOLD, 10));
		cbFrom.setBounds(119, 90, 170, 21);
		cbFrom.setModel(new DefaultComboBoxModel<String>(symbolsArray.get(1)));
		cbFrom.setSelectedIndex(31);
		from = symbolsArray.get(0)[cbFrom.getSelectedIndex()];
		AutoCompleteDecorator.decorate(cbFrom);
		add(cbFrom);

		cbTo = new JComboBox<String>();
		cbTo.setFont(new Font("Tahoma", Font.BOLD, 10));
		cbTo.setBounds(119, 163, 170, 21);
		cbTo.setModel(new DefaultComboBoxModel<String>(symbolsArray.get(1)));
		cbTo.setSelectedIndex(150);
		to = symbolsArray.get(0)[cbTo.getSelectedIndex()];
		AutoCompleteDecorator.decorate(cbTo);
		add(cbTo);

		btnExchangeCurrency = new JButton("CAMBIAR");
		btnExchangeCurrency.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExchangeCurrency.setBounds(178, 220, 175, 32);
		btnExchangeCurrency.addActionListener(this);
		add(btnExchangeCurrency);

		lblFrom = new JLabel("Cambiar De:");
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrom.setBounds(10, 94, 111, 13);
		add(lblFrom);

		lblTo = new JLabel("Cambiar A:");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTo.setBounds(10, 167, 111, 13);
		add(lblTo);

		lblAmount = new JLabel("Cantidad a cambiar:");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmount.setBounds(340, 68, 170, 13);
		add(lblAmount);

		lblResult = new JLabel("Cantidad cambiada");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblResult.setBounds(340, 141, 170, 13);
		add(lblResult);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnExchangeCurrency) {
			double amount = 0.0;
			try {
				amount = Double.parseDouble(tfAmount.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Entrada no valida: " + tfAmount.getText() + ". Inserte un numero");
				tfAmount.setText("");
			}
			from = symbolsArray.get(0)[cbFrom.getSelectedIndex()];
			to = symbolsArray.get(0)[cbTo.getSelectedIndex()];

			if (from != to && amount >= 0) {
				amount = ExchangeApiRequests.getExchangedCurrencyRequest(from, to, amount);
				DecimalFormat df = new DecimalFormat("#0.00");
				tfResult.setText(df.format(amount));
			} else if (from == to) {
				JOptionPane.showMessageDialog(null, "Las unidades no pueden ser iguales");
			} else if (amount < 0) {
				JOptionPane.showMessageDialog(null, "La cantidad tiene que ser mayor o igual a 0");
			}
		}

		if (e.getSource() == btnSwitch) {
			int temp = cbTo.getSelectedIndex();
			cbTo.setSelectedIndex(cbFrom.getSelectedIndex());
			cbFrom.setSelectedIndex(temp);
			Double tempDouble;
			
			try {
				tempDouble = Double.parseDouble(tfResult.getText());
			} catch (NullPointerException e1) {
				tempDouble = 0.0;
			} catch (NumberFormatException e2) {
				tempDouble = 0.0;
			}
			tfResult.setText("");
			DecimalFormat df = new DecimalFormat("#0.00");
			tfAmount.setText(df.format(tempDouble));
		}

	}
}
