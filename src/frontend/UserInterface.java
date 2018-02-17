package frontend;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.script.ScriptException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UserInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Labels and textfields for the GUI.
	 */

	private JLabel lblOutput;
	private JLabel lblDA;
	private JLabel lblDB;
	private JLabel lblDC;
	private JLabel lblTA;
	private JLabel lblTB;
	private JLabel lblTC;
	private JLabel lblSA;
	private JLabel lblSB;
	private JLabel lblSC;
	private JLabel lblRA;
	private JLabel lblRB;
	private JLabel lblRC;
	private JLabel lblJA;
	private JLabel lblJB;
	private JLabel lblJC;
	private JLabel lblKA;
	private JLabel lblKB;
	private JLabel lblKC;
	private JLabel lblDFlipflops;
	private JLabel lblTFlipflops;
	private JLabel lblJKFlipflops;
	private JLabel lblRSFlipflops;
	private JTextField textFieldOutput;
	private JTextField textFieldDA;
	private JTextField textFieldDB;
	private JTextField textFieldDC;
	private JTextField textFieldTA;
	private JTextField textFieldTB;
	private JTextField textFieldTC;
	private JTextField textFieldSA;
	private JTextField textFieldSB;
	private JTextField textFieldSC;
	private JTextField textFieldRA;
	private JTextField textFieldRB;
	private JTextField textFieldRC;
	private JTextField textFieldJA;
	private JTextField textFieldJB;
	private JTextField textFieldJC;
	private JTextField textFieldKA;
	private JTextField textFieldKB;
	private JTextField textFieldKC;
	private int FFType, noOfInput, noOfOutput, noOfFF;

	/**
	 * Clears the labels and textfields.
	 */
	private void clearInputs() {
		if (frame.getContentPane().getComponentCount() > 6) {
			for (int i = frame.getContentPane().getComponentCount() - 1; i > 9; i--) {
				frame.getContentPane().remove(i); // remove 7 up
			}
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame.setTitle("Flip-flop State Table Generator");
		lblOutput = new JLabel("Output");
		lblDA = new JLabel("DA");
		lblDB = new JLabel("DB");
		lblDC = new JLabel("DC");
		lblTA = new JLabel("TA");
		lblTB = new JLabel("TB");
		lblTC = new JLabel("TC");
		lblSA = new JLabel("SA");
		lblSB = new JLabel("SB");
		lblSC = new JLabel("SC");
		lblRA = new JLabel("RA");
		lblRB = new JLabel("RB");
		lblRC = new JLabel("RC");
		lblJA = new JLabel("JA");
		lblJB = new JLabel("JB");
		lblJC = new JLabel("JC");
		lblKA = new JLabel("KA");
		lblKB = new JLabel("KB");
		lblKC = new JLabel("KC");
		lblDFlipflops = new JLabel("D Flip-FLops");
		lblTFlipflops = new JLabel("T Flip-FLops");
		lblJKFlipflops = new JLabel("J-K Flip-FLops");
		lblRSFlipflops = new JLabel("R-S Flip-FLops");
		textFieldOutput = new JTextField();
		textFieldDA = new JTextField();
		textFieldDB = new JTextField();
		textFieldDC = new JTextField();
		textFieldTA = new JTextField();
		textFieldTB = new JTextField();
		textFieldTC = new JTextField();
		textFieldSA = new JTextField();
		textFieldSB = new JTextField();
		textFieldSC = new JTextField();
		textFieldRA = new JTextField();
		textFieldRB = new JTextField();
		textFieldRC = new JTextField();
		textFieldJA = new JTextField();
		textFieldJB = new JTextField();
		textFieldJC = new JTextField();
		textFieldKA = new JTextField();
		textFieldKB = new JTextField();
		textFieldKC = new JTextField();

		frame = new JFrame();
		frame.setBounds(100, 100, 474, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JComboBox<String> dropDown_flipFlop = new JComboBox<String>();
		dropDown_flipFlop.setModel(new DefaultComboBoxModel<String>(
				new String[] { "D Flip-Flop", "T Flip-Flop", "J-K Flip-Flop", "S-R Flip-Flop" }));
		dropDown_flipFlop.setBounds(12, 32, 101, 22);
		frame.getContentPane().add(dropDown_flipFlop);

		JLabel lblTypeOfFlipflop = new JLabel("Type of Flip-Flop");
		lblTypeOfFlipflop.setBounds(12, 13, 114, 16);
		frame.getContentPane().add(lblTypeOfFlipflop);

		JLabel lblNoOfInput = new JLabel("No. of Input");
		lblNoOfInput.setBounds(118, 13, 98, 16);
		frame.getContentPane().add(lblNoOfInput);

		JLabel lblNoOfFF = new JLabel("No. of Flip-flop");
		lblNoOfFF.setBounds(272, 13, 98, 16);
		frame.getContentPane().add(lblNoOfFF);

		JLabel lblNoOfOutput = new JLabel("No. of Output");
		lblNoOfOutput.setBounds(193, 13, 76, 16);
		frame.getContentPane().add(lblNoOfOutput);

		JComboBox<Integer> dropDown_noOfInput = new JComboBox<Integer>();
		dropDown_noOfInput.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		dropDown_noOfInput.setBounds(118, 32, 70, 22);
		frame.getContentPane().add(dropDown_noOfInput);

		JComboBox<Integer> dropDown_noOfOutput = new JComboBox<Integer>();
		dropDown_noOfOutput.setModel(new DefaultComboBoxModel(new String[] { "0", "1" }));
		dropDown_noOfOutput.setBounds(193, 32, 76, 22);
		frame.getContentPane().add(dropDown_noOfOutput);

		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(374, 31, 70, 25);
		frame.getContentPane().add(btnSelect);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setVisible(false);
		JComboBox<Integer> dropDown_noOfFF = new JComboBox<Integer>();
		dropDown_noOfFF.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3" }));
		dropDown_noOfFF.setBounds(272, 32, 90, 22);
		frame.getContentPane().add(dropDown_noOfFF);

		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FFType = dropDown_flipFlop.getSelectedIndex();
				noOfInput = dropDown_noOfInput.getSelectedIndex() + 1;
				noOfOutput = dropDown_noOfOutput.getSelectedIndex();
				noOfFF = dropDown_noOfFF.getSelectedIndex() + 1;
				clearInputs();

				if (dropDown_noOfOutput.getSelectedIndex() == 1) { // 1 output
					lblOutput.setBounds(12, 62, 211, 16);
					frame.getContentPane().add(lblOutput);
					textFieldOutput.setBounds(10, 79, 211, 22);
					frame.getContentPane().add(textFieldOutput);
					textFieldOutput.setColumns(10);
				}
				if (dropDown_flipFlop.getSelectedIndex() == 0) { // D flip flop

					lblDFlipflops.setFont(new Font("Arial Black", Font.PLAIN, 16));
					lblDFlipflops.setBounds(12, 117, 200, 16);
					frame.getContentPane().add(lblDFlipflops);

					lblDA.setBounds(12, 146, 16, 16);
					frame.getContentPane().add(lblDA);

					textFieldDA.setBounds(34, 143, 189, 22);
					frame.getContentPane().add(textFieldDA);
					textFieldDA.setColumns(10);

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 1) {
						lblDB.setBounds(12, 181, 16, 16);
						frame.getContentPane().add(lblDB);

						textFieldDB.setBounds(34, 178, 189, 22);
						frame.getContentPane().add(textFieldDB);
						textFieldDB.setColumns(10);
					}

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 2) {
						lblDC.setBounds(12, 216, 16, 16);
						frame.getContentPane().add(lblDC);

						textFieldDC.setBounds(34, 213, 189, 22);
						frame.getContentPane().add(textFieldDC);
						textFieldDC.setColumns(10);
					}
				}

				if (dropDown_flipFlop.getSelectedIndex() == 1) { // T flip flop
					lblTFlipflops.setFont(new Font("Arial Black", Font.PLAIN, 16));
					lblTFlipflops.setBounds(12, 117, 200, 16);
					frame.getContentPane().add(lblTFlipflops);

					lblTA.setBounds(12, 146, 16, 16);
					frame.getContentPane().add(lblTA);

					textFieldTA.setBounds(34, 143, 189, 22);
					frame.getContentPane().add(textFieldTA);
					textFieldTA.setColumns(10);

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 1) {
						lblTB.setBounds(12, 181, 16, 16);
						frame.getContentPane().add(lblTB);

						textFieldTB.setBounds(34, 178, 189, 22);
						frame.getContentPane().add(textFieldTB);
						textFieldTB.setColumns(10);
					}

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 2) {
						lblTC.setBounds(12, 216, 16, 16);
						frame.getContentPane().add(lblTC);

						textFieldTC.setBounds(34, 213, 189, 22);
						frame.getContentPane().add(textFieldTC);
						textFieldTC.setColumns(10);
					}
				}

				if (dropDown_flipFlop.getSelectedIndex() == 2) { // JK flip flop
					lblJKFlipflops.setFont(new Font("Arial Black", Font.PLAIN, 16));
					lblJKFlipflops.setBounds(12, 117, 200, 16);
					frame.getContentPane().add(lblJKFlipflops);

					lblJA.setBounds(12, 146, 16, 16);
					frame.getContentPane().add(lblJA);

					textFieldJA.setBounds(34, 143, 189, 22);
					frame.getContentPane().add(textFieldJA);
					textFieldJA.setColumns(10);

					lblKA.setBounds(233, 146, 16, 16);
					frame.getContentPane().add(lblKA);

					textFieldKA.setBounds(255, 143, 189, 22);
					frame.getContentPane().add(textFieldKA);
					textFieldKA.setColumns(10);

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 1) {
						lblJB.setBounds(12, 181, 16, 16);
						frame.getContentPane().add(lblJB);

						textFieldJB.setBounds(34, 178, 189, 22);
						frame.getContentPane().add(textFieldJB);
						textFieldJB.setColumns(10);

						lblKB.setBounds(233, 181, 16, 16);
						frame.getContentPane().add(lblKB);

						textFieldKB.setBounds(255, 178, 189, 22);
						frame.getContentPane().add(textFieldKB);
						textFieldKB.setColumns(10);
					}

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 2) {
						lblJC.setBounds(12, 216, 16, 16);
						frame.getContentPane().add(lblJC);

						textFieldJC.setBounds(34, 213, 189, 22);
						frame.getContentPane().add(textFieldJC);
						textFieldJC.setColumns(10);

						lblKC.setBounds(233, 216, 16, 16);
						frame.getContentPane().add(lblKC);

						textFieldKC.setBounds(255, 213, 189, 22);
						frame.getContentPane().add(textFieldKC);
						textFieldKC.setColumns(10);
					}
				}

				if (dropDown_flipFlop.getSelectedIndex() == 3) { // RS flip flop
					lblRSFlipflops.setFont(new Font("Arial Black", Font.PLAIN, 16));
					lblRSFlipflops.setBounds(12, 117, 200, 16);
					frame.getContentPane().add(lblRSFlipflops);

					lblSA.setBounds(12, 146, 16, 16);
					frame.getContentPane().add(lblSA);

					textFieldSA.setBounds(34, 143, 189, 22);
					frame.getContentPane().add(textFieldSA);
					textFieldSA.setColumns(10);

					lblRA.setBounds(233, 146, 16, 16);
					frame.getContentPane().add(lblRA);

					textFieldRA.setBounds(255, 143, 189, 22);
					frame.getContentPane().add(textFieldRA);
					textFieldRA.setColumns(10);

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 1) {
						lblSB.setBounds(12, 181, 16, 16);
						frame.getContentPane().add(lblSB);

						textFieldSB.setBounds(34, 178, 189, 22);
						frame.getContentPane().add(textFieldSB);
						textFieldSB.setColumns(10);

						lblRB.setBounds(233, 181, 16, 16);
						frame.getContentPane().add(lblRB);

						textFieldRB.setBounds(255, 178, 189, 22);
						frame.getContentPane().add(textFieldRB);
						textFieldRB.setColumns(10);
					}

					if (dropDown_noOfFF.getSelectedIndex() + 1 > 2) {
						lblSC.setBounds(12, 216, 16, 16);
						frame.getContentPane().add(lblSC);

						textFieldSC.setBounds(34, 213, 189, 22);
						frame.getContentPane().add(textFieldSC);
						textFieldSC.setColumns(10);

						lblRC.setBounds(233, 216, 16, 16);
						frame.getContentPane().add(lblRC);

						textFieldRC.setBounds(255, 213, 189, 22);
						frame.getContentPane().add(textFieldRC);
						textFieldRC.setColumns(10);
					}
				}
				btnSubmit.setVisible(true);
				frame.repaint();

			}

		});

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Driver d = new Driver();
				try {
					if (FFType == 0) {
						d.makeDStateTable(noOfInput, noOfOutput, noOfFF, textFieldOutput.getText(),
								textFieldDA.getText(), textFieldDB.getText(), textFieldDC.getText());
					} else if (FFType == 1) {
						d.makeTStateTable(noOfInput, noOfOutput, noOfFF, textFieldOutput.getText(),
								textFieldTA.getText(), textFieldTB.getText(), textFieldTC.getText());
					} else if (FFType == 2) {
						d.makeJKStateTable(noOfInput, noOfOutput, noOfFF, textFieldOutput.getText(),
								textFieldJA.getText(), textFieldJB.getText(), textFieldJC.getText(),
								textFieldKA.getText(), textFieldKB.getText(), textFieldKC.getText());
					} else if (FFType == 3) {
						d.makeJKStateTable(noOfInput, noOfOutput, noOfFF, textFieldOutput.getText(),
								textFieldRA.getText(), textFieldRB.getText(), textFieldRC.getText(),
								textFieldSA.getText(), textFieldSB.getText(), textFieldSC.getText());
					}
					JOptionPane.showMessageDialog(frame, "State table created.");
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(frame, "Invalid input.");
				} catch (ScriptException e1) {
					JOptionPane.showMessageDialog(frame, "Invalid input.");
				}

			}
		});
		btnSubmit.setBounds(172, 274, 97, 25);
		frame.getContentPane().add(btnSubmit);
	}
}
