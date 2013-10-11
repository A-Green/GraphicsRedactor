package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import lineGeneration.LineGenerator;
import model.*;
import view.*;
import parabola.*;
import line.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPopupMenu;

import curveGeneration.CurveGenerator;

import java.awt.Component;
import circle.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.image.MemoryImageSource;
public class MWind extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RadiusDialog dialog = null;
	private PDialog p_dialog = null;
	private int clickedX = 0;
	private int clickedY = 0;


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
		final GridView gridView = new GridView();
		final int ClickedX;
		final int ClickedY;
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
		
		
		final JButton stepBotton = new JButton("  STEP  ");
		stepBotton.setEnabled(false);
		// ��������� ���������� ����� ��� ������� �� ������, ���� ������ ����� "steply"
		stepBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridView.drawDotSteply();
				gridView.repaint();
			}

		});
		bottonPanel.add(stepBotton);
		
		JButton zoomOutBotton = new JButton("  -  ");
		// ���������� �������� �� ������� ������ '-'
		zoomOutBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridView.setStep(gridView.getStep() - 1);	
				gridView.repaint();
				
			}
		});
		
		bottonPanel.add(zoomOutBotton);
		
		JButton zoomInBotton = new JButton("  +  ");
		// ���������� �������� �� ������� ������ '+'
		bottonPanel.add(zoomInBotton);
		zoomInBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gridView.setStep(gridView.getStep() + 1);
				gridView.repaint();
			}
		});
		
		final Checkbox stepCheckBox = new Checkbox("Steply     ");
		stepCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				 if (arg0.getStateChange() == ItemEvent.DESELECTED)
					 stepBotton.setEnabled(false);
				 if (arg0.getStateChange() == ItemEvent.SELECTED)
					 stepBotton.setEnabled(true);	 
			}
		});
		bottonPanel.add(stepCheckBox);
		
		//������, ���� ��������, �� ��������� ���, ��� ����������
		final Checkbox clearCheckBox = new Checkbox("Eraser     ");
		clearCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				 if (arg0.getStateChange() == ItemEvent.DESELECTED)
					 gridView.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				 if (arg0.getStateChange() == ItemEvent.SELECTED)
				 {
					 Toolkit tk = Toolkit.getDefaultToolkit();
				     Dimension d = tk.getBestCursorSize(100, 100); // d � ������ �����������
				     int w = d.width, h = d.height, k = 0;
				     Point p = new Point(15, 15);                  // ����� ������� �����
				     int[] pix = new int[w * h];                 // ����� ����� ������� ����������� 
				     for(int i = 0; i < w; i++) 
				    	 for(int j = 0; j < h; j++)
				    		 pix[k++] = 0xFFFFFFF0; 
				     Image im = createImage(new MemoryImageSource(w, h, pix, 0, w)); 
				     Cursor curs = tk.createCustomCursor(im, p, null);  
				        
					 gridView.setCursor(curs);
				 }
			}
		});
		bottonPanel.add(clearCheckBox);
		
		
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
				
				DDALine DDA = new DDALine(gridView.getClickedEx(), gridView.getClickedEx());
				
				if (DDA.getColoredExes() == null)
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 2 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				else 
				{
						if (stepCheckBox.getState() == false)
						{
							gridView.addLine(DDA);
							gridView.repaint();	
						}
				}
			}
		});
		menuLines.add(itemDDA);
		
		//���������
		JMenuItem itemBrezenh = new JMenuItem("\u0411\u0440\u0435\u0437\u0435\u043D\u0445\u0435\u043C");
		itemBrezenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				BrezenhemLine  BLine= new BrezenhemLine(gridView.getClickedEx(), gridView.getClickedEx());
				
				if (BLine.getColoredExes() == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 2 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{					
					if(stepCheckBox.getState() == false)
					{
						gridView.addLine(BLine);
						gridView.repaint();

					}
					
				}
			}
		});
		menuLines.add(itemBrezenh);	
		
		//���������� �������������
		JMenuItem itemAntiAliasing = new JMenuItem("\u0423\u0441\u0442\u0440\u0430\u043D\u0435\u043D\u0438\u0435 \u0441\u0442\u0443\u043F\u0435\u043D\u0447\u0430\u0442\u043E\u0441\u0442\u0438");
		itemAntiAliasing.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				AntiAliasingLine AAL = new AntiAliasingLine(gridView.getClickedEx(), gridView.getClickedEx());
				
				if (AAL.getColoredExes() == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 2 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					if(stepCheckBox.getState() == false)
					{
						gridView.addLine(AAL);
						gridView.repaint();

					}

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
		menuCircle.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				int radius = -1;
				
				if(dialog == null)
				{
					dialog = new RadiusDialog();
				}
				dialog.setText("10");
				if(dialog.showDialog(MWind.this, "Radius"))
				{
					radius = dialog.getRadius();
				}
				
				if(radius != -1)
				{
					Circle circle = new Circle(gridView.getClickedEx(), radius);
					if(circle.getColoredExes() == null) 
						{
						JOptionPane.showMessageDialog(new JButton(),
							"������� ����� ����������!", "����������",
							JOptionPane.WARNING_MESSAGE);
						}
					else 
					{	
						if(stepCheckBox.getState() == false)
						{
							gridView.addLine(circle);
							gridView.repaint();
						}

					}
				}
				
			}
		});
		menu.add(menuCircle);
		
		// �������� (�������� �����)
				JMenuItem menuParabola = new JMenuItem("��������");
				menuParabola.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						double p_value = 0;

						if (p_dialog == null) {
							p_dialog = new PDialog("p_value :");
						}
						p_dialog.setText("1");
						if (p_dialog.showDialog(MWind.this, "p_value")) {
							p_value = p_dialog.getpValue();
						}

						if (p_value != 0) {
							Parabola parabola = new Parabola(gridView.getClickedEx(), p_value, gridView.getH());
							if (parabola.getColoredExes() == null) {
								JOptionPane.showMessageDialog(new JButton(),
										"������� ������� ��������!", "����������",
										JOptionPane.WARNING_MESSAGE);
							} else {
								if (stepCheckBox.getState() == false) {
									gridView.addLine(parabola);
									gridView.repaint();
								} 
							}
						}

					}
				});
				menu.add(menuParabola);

		
		
	//-------------------------------------------------------------------------------------------------------------------------------
		
	//---------------------------------��������������� ������-------------------------------------------------------------------------


		JMenu curveMenu = new JMenu("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u0438\u0447\u0435\u0441\u043A\u0438\u0435 \u043A\u0440\u0438\u0432\u044B\u0435");

		menuBar.add(curveMenu);
		
		//�����
		JMenuItem ermitaItem = new JMenuItem("����� ������");
		ermitaItem.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e)
			{
				ErmitForm ermit = new ErmitForm(
						gridView.getClickedEx(), gridView.getClickedEx(),gridView.getClickedEx(),gridView.getClickedEx());

				if (ermit.getColoredExes() == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"�������� 4 �����!", "����������",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{					
					if(stepCheckBox.getState() == false)
					{
						gridView.addLine(ermit);
						gridView.repaint();
					}
					
				}
			}
			
		});
		curveMenu.add(ermitaItem);
		
	//-----------------------------------------------------------------------------------------------------------------------------------
	
	//
	//-----------------------------------------------------------------------------------------------------------------------------------	
		//����������� �������
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(gridView, popupMenu);
		//��������
		JMenuItem popupClear = new JMenuItem("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");	
		popupClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gridView.clear();
				gridView.repaint();
			}
		});
		
		popupMenu.add(popupClear);
				
		// ��������������� �� �������� ����
		gridView.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			
				if(arg0.getWheelRotation() < 0)
					gridView.setStep(gridView.getStep() + 1);
				else 
					gridView.setStep(gridView.getStep() - 1);	
			gridView.repaint();

			}
		});
		
		// ������ ���� �� �����
		gridView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {				
				// ���� ����� �������� 
				if (SwingUtilities.isLeftMouseButton(event)){
					int x = event.getX() / gridView.getStep();
					int y = event.getY() / gridView.getStep();
					Excel ex = new Excel (x, y, Color.black);
					
					if (gridView.contains(ex)) 
						gridView.removeEx(ex);
					 else 
						gridView.addEx(ex);
						
					gridView.repaint();
					
				}
			}
			
			 public void mousePressed(MouseEvent e) {
				 	clickedX = e.getX() / gridView.getStep();
				 	clickedY = e.getY() / gridView.getStep();
			    }

		});
		
		//������������ ����
		 gridView.addMouseMotionListener(new MouseMotionListener(){
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					//x = 10;
					int x = e.getX() / gridView.getStep();
					int y = e.getY() / gridView.getStep();
					//if ( x != clickedX && y != clickedY )
					gridView.moveLine(clickedX, clickedY, x, y);
					clickedX = x; 
					clickedY = y;
					gridView.repaint();
					
				}
				public void mouseMoved(MouseEvent event) {
						int x = event.getX() / gridView.getStep();
						int y = event.getY() / gridView.getStep();
						Excel ex = new Excel(x,y,Color.black);
						if (gridView.contains(ex)) 
						{
							if(clearCheckBox.getState() == true)
							{
								gridView.removeEx(ex);
								gridView.repaint();
								System.out.println("CLEAR");
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
