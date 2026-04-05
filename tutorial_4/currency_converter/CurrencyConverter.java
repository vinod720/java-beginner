package tutorial_4.currency_converter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class CurrencyConverter extends JFrame {

	private JTextField textField1;
	private JTextField textField2;

	/**
	 * Create the frame.
	 */
	private void createFrame() {

		Reader r = new Reader();
		r.openFile();
		r.readFile();

		setTitle("Currency Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel contentPane = new JPanel(new GridLayout(2, 2, 5, 5));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		// create comboBoxes
		JComboBox<String> comboBox1 = new JComboBox<>();
		JComboBox<String> comboBox2 = new JComboBox<>();

		// adds currency names to comboBox 1 & 2
		for (int i = 0; i < r.sizeOf(); i++) {
			comboBox1.addItem(r.getName(i));
			comboBox2.addItem(r.getName(i));
		}
		comboBox2.setSelectedItem(comboBox2.getItemAt(1));

		// create textFields
		textField1 = new JTextField(10);
		textField2 = new JTextField(10);

		contentPane.add(comboBox1);
		contentPane.add(textField1);
		contentPane.add(comboBox2);
		contentPane.add(textField2);

		// convert from textField1 to textField2
		textField1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try {
					double input = Double.parseDouble(textField1.getText());
					double rate1 = r.getRate(comboBox1.getSelectedIndex());
					double rate2 = r.getRate(comboBox2.getSelectedIndex());
					textField2.setText(String.format("%.6f", (input * rate1) / rate2));
				} catch (NumberFormatException ex) {
					textField2.setText("");
				}
			}
		});

		// convert from textField2 to textField1
		textField2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try {
					double input = Double.parseDouble(textField2.getText());
					double rate1 = r.getRate(comboBox2.getSelectedIndex());
					double rate2 = r.getRate(comboBox1.getSelectedIndex());
					textField1.setText(String.format("%.6f", (input * rate1) / rate2));
				} catch (NumberFormatException ex) {
					textField1.setText("");
				}
			}
		});

		// update conversion when selected currency changes
		ActionListener comboListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField1.getText().isEmpty()) {
					try {
						double input = Double.parseDouble(textField1.getText());
						double rate1 = r.getRate(comboBox1.getSelectedIndex());
						double rate2 = r.getRate(comboBox2.getSelectedIndex());
						textField2.setText(String.format("%.6f", (input * rate1) / rate2));
					} catch (NumberFormatException ex) {
						textField2.setText("");
					}
				}
			}
		};
		comboBox1.addActionListener(comboListener);
		comboBox2.addActionListener(comboListener);

		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CurrencyConverter frame = new CurrencyConverter();
				frame.createFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
