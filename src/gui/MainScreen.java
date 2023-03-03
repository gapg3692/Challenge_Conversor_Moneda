package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import paneles.DistancePanel;
import paneles.ExchangeCurrencyPanel;
import paneles.TemperaturePanel;

import java.awt.Image;

public class MainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExchangeCurrencyPanel exchengeCurrencyPanel;
	private TemperaturePanel temperaturePanel;
	private DistancePanel distancePanel;
	private JTabbedPane tabsTypesConverter;
	private Image img, termometer, money, distance;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainScreen() {

		setTitle("Alura ChallengeONE - Conversor de unidades");
		setSize(560, 350);
		setLocationRelativeTo(null);	
		setResizable(false);
		img = new ImageIcon(this.getClass().getResource("/Vector.png")).getImage();
		termometer = new ImageIcon(this.getClass().getResource("/termometro_(1).png")).getImage();
		termometer = termometer.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		money = new ImageIcon(this.getClass().getResource("/monedas-de-un-dolar.png")).getImage();
		money = money.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		distance = new ImageIcon(this.getClass().getResource("/gobernante.png")).getImage();
		distance = distance.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setIconImage(img);
		loadComponets();
	}
	
	private void loadComponets () {
		tabsTypesConverter = new JTabbedPane();
		exchengeCurrencyPanel = new ExchangeCurrencyPanel();
		temperaturePanel = new TemperaturePanel();
		distancePanel = new DistancePanel();
		
		tabsTypesConverter.add("Cambio de Moneda", exchengeCurrencyPanel);
		tabsTypesConverter.setSize(getPreferredSize());
		tabsTypesConverter.setIconAt(0, new ImageIcon(money));
		tabsTypesConverter.add("Temperatura", temperaturePanel);
		tabsTypesConverter.setIconAt(1, new ImageIcon(termometer));
		tabsTypesConverter.add("Unidades de Medida", distancePanel);
		tabsTypesConverter.setIconAt(2, new ImageIcon(distance));
		
		getContentPane().add(tabsTypesConverter);
		
		}

}
