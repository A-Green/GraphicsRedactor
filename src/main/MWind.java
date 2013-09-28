package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Point;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import lineGeneration.LineGenerator;
import model.*;
import view.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import circle.*;
public class MWind extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grid grid = new Grid(300);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MWind frame = new MWind();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MWind() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 485);
		// �����
		final JPanel gridView = new GridView(grid);
		// ������� ����
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(gridView);
		// ������ ������ �����
		JPanel bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		
		
		JButton zoomOutBotton = new JButton("  -  ");
		// ���������� �������� �� ������� ������ '-'
		zoomOutBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grid.getStep() > 2) {
					grid.setStep(grid.getStep() - 2);
					gridView.repaint();
				}
			}
		});
		
		bottonPanel.add(zoomOutBotton);
		
		JButton zoomInBotton = new JButton("  +  ");
		// ���������� �������� �� ������� ������ '+'
		bottonPanel.add(zoomInBotton);
		zoomInBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grid.setStep(grid.getStep() + 3);
				gridView.repaint();
			}
		});
		
		final Checkbox stepCheckBox = new Checkbox("Steply     ");

		bottonPanel.add(stepCheckBox);
		
		
	//---------------------------------------�������------------------------------------------------------------------
		//���� �������
		JMenu menuLines = new JMenu("\u041E\u0442\u0440\u0435\u0437\u043A\u0438");
		menuBar.add(menuLines);
		//�. ���� ���
		JMenuItem itemDDA = new JMenuItem("\u0426\u0414\u0410");
		
		//�������� ��� �� ������ �� ������ �� ���� �������	
		itemDDA.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				ArrayList<Point> coloredEx = LineGenerator.DDA(grid);	
				if (coloredEx == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 2 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{					
					for (int i = 0; i < coloredEx.size(); i++)
					grid.getExcel(coloredEx.get(i).x, coloredEx.get(i).y).setColored(true);			
					gridView.repaint();
					
				}
			}
		});
		menuLines.add(itemDDA);
		
		//���������
		JMenuItem itemBrezenh = new JMenuItem("\u0411\u0440\u0435\u0437\u0435\u043D\u0445\u0435\u043C");
		itemBrezenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList<Point> coloredEx = LineGenerator.Brezenhem(grid);
				
				if (coloredEx == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 2 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
				for (int i = 0; i < coloredEx.size(); i++)
					grid.getExcel(coloredEx.get(i).x, coloredEx.get(i).y).setColored(true);
				gridView.repaint();
				}
			}
		});
		menuLines.add(itemBrezenh);	
		
		//���������� �������������
		JMenuItem itemAntiAliasing = new JMenuItem("\u0423\u0441\u0442\u0440\u0430\u043D\u0435\u043D\u0438\u0435 \u0441\u0442\u0443\u043F\u0435\u043D\u0447\u0430\u0442\u043E\u0441\u0442\u0438");
		itemAntiAliasing.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Map<Point,Color> coloredEx= LineGenerator.WuAlgorithm(grid);
				
				if (coloredEx == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 2 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					for (Map.Entry<Point, Color> entry : coloredEx.entrySet())
					{
						Color color = entry.getValue();
						if (color != null)
					    grid.getExcel(entry.getKey().x, entry.getKey().y).setColor(color);
					}
					
				gridView.repaint();
				}
			}
		});
		menuLines.add(itemAntiAliasing);
		
		JMenu menu = new JMenu("\u041A\u0440\u0438\u0432\u044B\u0435");
		menuBar.add(menu);
		

	//-------------------------------------------------------------------------------------------------------------------------------
		
	//---------------------------------����� ������� �������-------------------------------------------------------------------------
		
		//����������
		JMenuItem menuCircle = new JMenuItem("\u041E\u043A\u0440\u0443\u0436\u043D\u043E\u0441\u0442\u044C");
		menuCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				ArrayList<Point> coloredEx = CircleGenerator.circle(grid, 20);
				
				if(coloredEx == null) 
					{
					JOptionPane.showMessageDialog(new JButton(),
						"������� ����� ����������!", "����������",
						JOptionPane.WARNING_MESSAGE);
					}
				else {	
					
				for (int i = 0; i < coloredEx.size(); i++)
					grid.getExcel(coloredEx.get(i).x, coloredEx.get(i).y).setColored(true);
				gridView.repaint();
				}
				
			}
		});
		menu.add(menuCircle);
		
	//-----------------------------------------------------------------------------------------------------------------------------------	
		//����������� �������
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(gridView, popupMenu);
		//��������
		JMenuItem popupClear = new JMenuItem("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");	
		popupClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < grid.getSize(); i++)
					for (int j = 0; j < grid.getSize(); j++)
						grid.getExcel(i, j).setColored(false);
				repaint();
			}
		});
		
		popupMenu.add(popupClear);
				
		// ��������������� �� �������� ����
		gridView.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				
				if(arg0.getWheelRotation() < 0)
					grid.setStep(grid.getStep() + 2);
				else if (grid.getStep() > 1) 
					grid.setStep(grid.getStep() - 1);
				gridView.repaint();
			}
		});
		
		// ������ ���� �� �����
		gridView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				
				// ���� ����� �������� - ������ �������
				if (SwingUtilities.isLeftMouseButton(event)) {
					int x = event.getX() / grid.getStep();
					int y = event.getY() / grid.getStep();
					if (grid.getExcel(x, y).isColored() == false) {
						grid.getExcel(x, y).setColored(true);
						repaint();
					} else {
						grid.getExcel(x, y).setColored(false);
						gridView.repaint();
					}
				}
			}
		});
		
		
		
	}
	
	//�����-�� ����� ����������� ����, �� ��������
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
