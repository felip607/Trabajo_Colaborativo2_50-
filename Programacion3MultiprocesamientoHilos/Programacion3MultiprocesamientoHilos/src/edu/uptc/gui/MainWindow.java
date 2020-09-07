package edu.uptc.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import edu.uptc.logic.Route;
import edu.uptc.logic.Target;
import edu.uptc.persistence.JSonHandler;

import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final String[] HEADERS =  {"Archivo", "Progreso"};
	public static final int MAXIMUMPB = 100;

	private JPanel contentPane;
	private JTable mainTable;
	private DefaultTableModel defaultModel;
	private SpringLayout sl_contentPane;
	private JScrollPane scrollPane;
	private JLabel lblTotal;
	private JProgressBar pgrsbarTotal;
	private JButton btnStart;
	private JComboBox<String> cmbSources;
	private JLabel lblNewLabel;
	private JLabel lblDestino;
	private JComboBox<String> cmbTargets;
	private JLabel lblBestFly;
	private JLabel lblResult;

	public MainWindow() {
		setTitle("Vuelos Hilos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		begin();

	}

	private void begin(){

		sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 40, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 249, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -38, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);

		lblTotal = new JLabel("Progreso Total");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblTotal, 16, SpringLayout.SOUTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTotal, 40, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTotal, -133, SpringLayout.SOUTH, contentPane);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblTotal);

		pgrsbarTotal = new JProgressBar();
		pgrsbarTotal.setStringPainted(true);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pgrsbarTotal, 16, SpringLayout.SOUTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pgrsbarTotal, -143, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblTotal, -24, SpringLayout.WEST, pgrsbarTotal);
		sl_contentPane.putConstraint(SpringLayout.WEST, pgrsbarTotal, 181, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pgrsbarTotal, 0, SpringLayout.EAST, scrollPane);
		contentPane.add(pgrsbarTotal);

		defaultModel = new DefaultTableModel(null, HEADERS);
		mainTable = new JTable(defaultModel);
		scrollPane.setViewportView(mainTable);
		mainTable.getColumn(HEADERS[1]).setCellRenderer(new ProgressRenderer(0, MAXIMUMPB));;
		mainTable.setPreferredScrollableViewportSize(mainTable.getPreferredSize());


		btnStart = new JButton("Iniciar Analisis");
		btnStart.addActionListener(this);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnStart, 258, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnStart, -10, SpringLayout.SOUTH, contentPane);
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(btnStart);

		cmbSources = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.NORTH, cmbSources, 14, SpringLayout.SOUTH, lblTotal);
		sl_contentPane.putConstraint(SpringLayout.EAST, cmbSources, -407, SpringLayout.EAST, contentPane);
		contentPane.add(cmbSources);

		lblNewLabel = new JLabel("Origen");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 15, SpringLayout.SOUTH, lblTotal);
		sl_contentPane.putConstraint(SpringLayout.WEST, cmbSources, 6, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -556, SpringLayout.EAST, contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel);

		lblDestino = new JLabel("Destino");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDestino, 1, SpringLayout.NORTH, cmbSources);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDestino, 61, SpringLayout.EAST, cmbSources);
		lblDestino.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDestino);

		cmbTargets = new JComboBox<String>();
		sl_contentPane.putConstraint(SpringLayout.WEST, cmbTargets, 410, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cmbTargets, -114, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblDestino, -6, SpringLayout.WEST, cmbTargets);
		sl_contentPane.putConstraint(SpringLayout.NORTH, cmbTargets, 0, SpringLayout.NORTH, cmbSources);
		contentPane.add(cmbTargets);

		lblBestFly = new JLabel("---");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBestFly, 0, SpringLayout.WEST, pgrsbarTotal);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblBestFly, 0, SpringLayout.EAST, cmbTargets);
		lblBestFly.setFont(new Font("Tahoma", Font.BOLD, 14));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblBestFly, -12, SpringLayout.NORTH, btnStart);
		lblBestFly.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBestFly);
		
		lblResult = new JLabel("Mejor Vuelo:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblResult, 24, SpringLayout.SOUTH, cmbSources);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBestFly, -6, SpringLayout.NORTH, lblResult);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblResult, 57, SpringLayout.WEST, contentPane);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblResult);

	}
	public DefaultTableModel getDefaultModel() {
		return defaultModel;
	}
	public JComboBox<String> getCmbSources() {
		return cmbSources;
	}
	public JComboBox<String> getCmbTargets() {
		return cmbTargets;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnStart) {

			String aux[] = JSonHandler.getPaths("./resources/");
			restartProgress(aux.length);
			for (int i = 0; i < aux.length; i++) {
				createThread(i, aux[i]);
			}
		}
	}

	private void restartProgress(int aux) {
		
		lblBestFly.setText("---");
		for (int i = 0; i < aux; i++) {
			defaultModel.setValueAt(0, i, 1);
		}
	}

	private void updateTotalProgress() {

		int contAux = defaultModel.getRowCount();
		int contProgress = 0;
		for(int i = 0; i < contAux; i++) {
			Integer value = (Integer) defaultModel.getValueAt(i, 1);
			contProgress+=value;
		}
		pgrsbarTotal.setValue((int) contProgress/contAux);
	}

	private void createThread(int row, String path) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<Route> auxRoutes = JSonHandler.readJson(path);
				int contTargets = 0;
				for (Route route : auxRoutes) {
					contTargets += route.getTargets().size();
				}
				for (Route route : auxRoutes) {			
					for (Target target : route.getTargets()) {
						if(cmbSources.getSelectedItem().equals(route.getSource())) {
							if(cmbTargets.getSelectedItem().equals(target.getTarget())){
								if(lblBestFly.getText().equals("---")) {
									lblBestFly.setText(cmbSources.getSelectedItem() + "-" + cmbTargets.getSelectedItem() + " = " + target.getValue());
								}else {
									if(Integer.parseInt(lblBestFly.getText().split("= ")[1]) > target.getValue()) {
										lblBestFly.setText(cmbSources.getSelectedItem() + "-" + cmbTargets.getSelectedItem() + " = " + target.getValue());
									}
								}
							}
						}
						
						Integer value = (Integer) defaultModel.getValueAt(row, 1);
						value =  value + (int) MAXIMUMPB / contTargets;
						System.out.println(Thread.currentThread() + " " + value);
						if (value <= MAXIMUMPB) {
							defaultModel.setValueAt(value, row, 1);
							updateTotalProgress();
							try {
								Thread.sleep((long)(Math.random() * 1000));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				defaultModel.setValueAt(MAXIMUMPB, row, 1);
				updateTotalProgress();
			}
		}).start();		
	}
}
