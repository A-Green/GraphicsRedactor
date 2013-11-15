package main;

import generation.circleGeneration.RadiusDialog;
import generation.parabolaGeneration.PDialog;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import model.Excel;
import shape.dimensional.Cube;
import shape.dimensional.Pyramid4;
import shape.lines.AntiAliasingLine;
import shape.lines.BSplaynForm;
import shape.lines.BesieForm;
import shape.lines.BrezenhemLine;
import shape.lines.Circle;
import shape.lines.DDALine;
import shape.lines.ErmitForm;
import shape.lines.Parabola;
import view.GridView;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
public class MWind extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RadiusDialog dialog = null;
	private PDialog p_dialog = null;
	private int clickedX = 0;
	private int clickedY = 0;
	private JTextField rotateXAngle;
	private JTextField rotateYAngle;
	private JTextField rotateZAngle;
	private JTextField rotateAllAngle;


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
		// сетка
		final GridView gridView = new GridView();

		// верхнее меню
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(gridView);
		// нижняя панель внизу
		JPanel bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		
		
		final JButton stepBotton = new JButton("  STEP  ");
		stepBotton.setEnabled(false);
		// пошаговое построение фигур при нажатии на кнопку, если выбран режим "steply"
		stepBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridView.drawDotSteply();
				gridView.repaint();
			}

		});
		bottonPanel.add(stepBotton);
		
		JButton zoomOutBotton = new JButton("  -  ");
		// уменьшение масштаба по нажатию кнопки '-'
		zoomOutBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gridView.rotate(-5);
				gridView.repaint();
			}
		});
		
		bottonPanel.add(zoomOutBotton);
		
		JButton zoomInBotton = new JButton("  +  ");
		// увеличение масштаба по нажатию кнопки '+'
		bottonPanel.add(zoomInBotton);
		zoomInBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gridView.rotate(5);
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
		
		
//---------------------------------------ОТРЕЗКИ------------------------------------------------------------------
		//меню отрезки
		JMenu menuLines = new JMenu("\u041E\u0442\u0440\u0435\u0437\u043A\u0438");
		menuBar.add(menuLines);
		//п. меню ЦДА
		JMenuItem itemDDA = new JMenuItem("\u0426\u0414\u0410");
		
		//Алгоритм ЦДА по щелчку на кнопку из меню Отрезки	
		itemDDA.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				DDALine DDA = new DDALine(gridView.getClickedEx(), gridView.getClickedEx());
				
				if (DDA.getColoredExes() == null)
					{
					JOptionPane.showMessageDialog(new JButton(),
							"Выберите 2 точки!", "Информация",
							JOptionPane.WARNING_MESSAGE);
							gridView.repaint();
					}
				else 
				{
						if (stepCheckBox.getState() == false)						
							gridView.addFugure(DDA);
						else
							gridView.setSteplyArray(DDA);
						
						gridView.repaint();
				}
			}
		});
		menuLines.add(itemDDA);
		
		//Брезенхем
		JMenuItem itemBrezenh = new JMenuItem("\u0411\u0440\u0435\u0437\u0435\u043D\u0445\u0435\u043C");
		itemBrezenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				BrezenhemLine  BLine= new BrezenhemLine(gridView.getClickedEx(), gridView.getClickedEx());
				
				if (BLine.getColoredExes() == null)
				{
					JOptionPane.showMessageDialog(new JButton(),
							"Выберите 2 точки!", "Информация",
							JOptionPane.WARNING_MESSAGE);
					gridView.repaint();
				}
				else
				{					
					if(stepCheckBox.getState() == false)
						gridView.addFugure(BLine);
					else
						gridView.setSteplyArray(BLine);
					
						gridView.repaint();				
				}
			}
		});
		menuLines.add(itemBrezenh);	
		
		//Устранение ступенчатости
		JMenuItem itemAntiAliasing = new JMenuItem("\u0423\u0441\u0442\u0440\u0430\u043D\u0435\u043D\u0438\u0435 \u0441\u0442\u0443\u043F\u0435\u043D\u0447\u0430\u0442\u043E\u0441\u0442\u0438");
		itemAntiAliasing.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				AntiAliasingLine AAL = new AntiAliasingLine(gridView.getClickedEx(), gridView.getClickedEx());
				
				if (AAL.getColoredExes() == null)	
				{
					JOptionPane.showMessageDialog(new JButton(),
							"Выберите 2 точки!", "Информация",
							JOptionPane.WARNING_MESSAGE);
					gridView.repaint();
				}
				else
				{
					if(stepCheckBox.getState() == false)		
						gridView.addFugure(AAL);
					else
						gridView.setSteplyArray(AAL);
					
						gridView.repaint();
				}
			}
		});
		menuLines.add(itemAntiAliasing);
		
		JMenu menu = new JMenu("\u041A\u0440\u0438\u0432\u044B\u0435");
		menuBar.add(menu);
		
//-------------------------------------------------------------------------------------------------------------------------------
		
//---------------------------------Линии второго порядка-------------------------------------------------------------------------
		
		//Окружность
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
							"Укажите центр окружности!", "Информация",
							JOptionPane.WARNING_MESSAGE);
						gridView.repaint();
					}
						
					else 
					{	
						if(stepCheckBox.getState() == false)
							gridView.addFugure(circle);
						else
							gridView.setSteplyArray(circle);
							
						gridView.repaint();
					}
				}
				
			}
		});
		menu.add(menuCircle);
		
		// Парабола (тестовый режим)
				JMenuItem menuParabola = new JMenuItem("Парабола");
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
							if (parabola.getColoredExes() == null) 
							{
								JOptionPane.showMessageDialog(new JButton(),
										"Укажите вершину параболы!", "Информация",
										JOptionPane.WARNING_MESSAGE);
								gridView.repaint();
							}
							else {
								if (stepCheckBox.getState() == false)
									gridView.addFugure(parabola);
								else
									gridView.setSteplyArray(parabola);
									gridView.repaint();
								
							}
						}

					}
				});
				menu.add(menuParabola);
				
						
						
//-------------------------------------------------------------------------------------------------------------------------------
						
//---------------------------------Параметрические кривые-------------------------------------------------------------------------
				
				
						JMenu curveMenu = new JMenu("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440\u0438\u0447\u0435\u0441\u043A\u0438\u0435 \u043A\u0440\u0438\u0432\u044B\u0435");
						menu.add(curveMenu);
								
								//Эрмит
								JMenuItem ermitaItem = new JMenuItem("форма Эрмита");
								ermitaItem.addMouseListener(new MouseAdapter(){
									@Override
									public void mousePressed(MouseEvent e)
									{
										ArrayList<Excel> fourLastExceles = new ArrayList<Excel>();
										fourLastExceles.add(gridView.getClickedEx());
										fourLastExceles.add(gridView.getClickedEx());
										fourLastExceles.add(gridView.getClickedEx());
										fourLastExceles.add(gridView.getClickedEx());
										
										ErmitForm ermit = new ErmitForm(fourLastExceles);

										if (ermit.getColoredExes() == null)
										{
											JOptionPane.showMessageDialog(new JButton(),
													"Выберите 4 точки!", "Информация",
													JOptionPane.WARNING_MESSAGE);
											gridView.repaint();
										}
										
										else
										{					
											if(stepCheckBox.getState() == false)
												gridView.addFugure(ermit);
											else
												gridView.setSteplyArray(ermit);
											
											gridView.repaint();				
										}
									}
									
								});
								curveMenu.add(ermitaItem);
								
								
								//Безье
										JMenuItem besieItem = new JMenuItem("форма Безье");
										besieItem.addMouseListener(new MouseAdapter(){
											@Override
											public void mousePressed(MouseEvent e)
											{
												ArrayList<Excel> fourLastExceles = new ArrayList<Excel>();
												fourLastExceles.add(gridView.getClickedEx());
												fourLastExceles.add(gridView.getClickedEx());
												fourLastExceles.add(gridView.getClickedEx());
												fourLastExceles.add(gridView.getClickedEx());
												
												BesieForm besie = new BesieForm(fourLastExceles);
												
												if (besie.getColoredExes() == null)
												{
													JOptionPane.showMessageDialog(new JButton(),
															"Выберите 4 точки!", "Информация",
															JOptionPane.WARNING_MESSAGE);
													gridView.repaint();
												}
												
												else
												{					
													if(stepCheckBox.getState() == false)
														gridView.addFugure(besie);
													else
														gridView.setSteplyArray(besie);
													
													gridView.repaint();				
												}
											}
											
										});
										curveMenu.add(besieItem);
										
										
										//В-сплайн
										JMenuItem bSplaynItem = new JMenuItem("В-сплайн");
										bSplaynItem.addMouseListener(new MouseAdapter(){
											@Override
											public void mousePressed(MouseEvent e)
											{
												BSplaynForm bSplayn = new BSplaynForm(gridView.popAllClicked());

												if (bSplayn.getColoredExes() == null)
												{
													JOptionPane.showMessageDialog(new JButton(),
															"Выберите 4 и больше точки!", "Информация",
															JOptionPane.WARNING_MESSAGE);
													gridView.repaint();
												}
												
												else
												{					
													if(stepCheckBox.getState() == false)
														gridView.addFugure(bSplayn);
													else
														gridView.setSteplyArray(bSplayn);
													
													gridView.repaint();				
												}
											}
											
										});
										curveMenu.add(bSplaynItem);
										
										JMenu mnd = new JMenu("3D \u0424\u0438\u0440\u0443\u0440\u044B");
										menuBar.add(mnd);

//---------------------------------------------------------------------------------------------------------------------------
										
										
//--------------------------------- Объемные фигуры -------------------------------------------------------------------------
										
										JMenuItem cubeItem = new JMenuItem("\u041A\u0443\u0431");
										cubeItem.addMouseListener(new MouseAdapter() {
											@Override
											public void mousePressed(MouseEvent arg0) {
												Cube cube = new Cube(100);
												gridView.addFugure(cube);
												gridView.repaint();
											}
										});
										mnd.add(cubeItem);
										
										JMenuItem menuItem = new JMenuItem("\u041F\u0438\u0440\u0430\u043C\u0438\u0434\u0430");
										menuItem.addMouseListener(new MouseAdapter() {
											@Override
											public void mousePressed(MouseEvent arg0) {
												Pyramid4 piramid = new Pyramid4(100);
												gridView.addFugure(piramid);
												gridView.repaint();
											}
										});
										mnd.add(menuItem);
		gridView.setLayout(new BoxLayout(gridView, BoxLayout.X_AXIS));
		
	//-----------------------------------------------------------------------------------------------------------------------------------
	
	//
	//-----------------------------------------------------------------------------------------------------------------------------------	
		//всплывающая менюшка
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(gridView, popupMenu);
		//очистить
		JMenuItem popupClear = new JMenuItem("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");	
		popupClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gridView.clear();
				gridView.repaint();
			}
		});
		
		popupMenu.add(popupClear);
		
		JPanel toolsPanel = new JPanel();
		scrollPane.setRowHeaderView(toolsPanel);
		GridBagLayout gbl_toolsPanel = new GridBagLayout();
		gbl_toolsPanel.columnWidths = new int[]{89, 0};
		gbl_toolsPanel.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_toolsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_toolsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		toolsPanel.setLayout(gbl_toolsPanel);
		
		JButton rotateXBtn = new JButton("RotateX");
		rotateXBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 Pattern p = Pattern.compile("^-?[0-9]+$"); 
			     Matcher m = p.matcher(rotateXAngle.getText()); 
			     if (m.matches()){
			    	 gridView.rotateX(Integer.parseInt(rotateXAngle.getText()));
			    	 gridView.repaint();
			     }
			
			}
		});
		GridBagConstraints gbc_rotateXBtn = new GridBagConstraints();
		gbc_rotateXBtn.insets = new Insets(0, 0, 5, 0);
		gbc_rotateXBtn.gridx = 0;
		gbc_rotateXBtn.gridy = 6;
		toolsPanel.add(rotateXBtn, gbc_rotateXBtn);

		rotateXAngle = new JTextField();
		GridBagConstraints gbc_rotateXAngle = new GridBagConstraints();
		gbc_rotateXAngle.insets = new Insets(0, 0, 5, 0);
		gbc_rotateXAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotateXAngle.gridx = 0;
		gbc_rotateXAngle.gridy = 7;
		toolsPanel.add(rotateXAngle, gbc_rotateXAngle);
		rotateXAngle.setColumns(10);
		
		JButton rotateYBtn = new JButton("RotateY");
		rotateYBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Pattern p = Pattern.compile("^-?[0-9]+$"); 
			     Matcher m = p.matcher(rotateYAngle.getText()); 
			     if (m.matches()){
			    	 gridView.rotateY(Integer.parseInt(rotateYAngle.getText()));
			    	 gridView.repaint();
			     }
			}
		});
		GridBagConstraints gbc_rotateYBtn = new GridBagConstraints();
		gbc_rotateYBtn.insets = new Insets(0, 0, 5, 0);
		gbc_rotateYBtn.gridx = 0;
		gbc_rotateYBtn.gridy = 8;
		toolsPanel.add(rotateYBtn, gbc_rotateYBtn);
		
		rotateYAngle = new JTextField();
		GridBagConstraints gbc_rotateYAngle = new GridBagConstraints();
		gbc_rotateYAngle.insets = new Insets(0, 0, 5, 0);
		gbc_rotateYAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotateYAngle.gridx = 0;
		gbc_rotateYAngle.gridy = 9;
		toolsPanel.add(rotateYAngle, gbc_rotateYAngle);
		rotateYAngle.setColumns(10);
		
		JButton rotateZBtn = new JButton("RotateZ");
		rotateZBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Pattern p = Pattern.compile("^-?[0-9]+$"); 
			     Matcher m = p.matcher(rotateZAngle.getText()); 
			     if (m.matches()){
			    	 gridView.rotateZ(Integer.parseInt(rotateZAngle.getText()));
			    	 gridView.repaint();
			     }
			}
		});
		GridBagConstraints gbc_rotateZBtn = new GridBagConstraints();
		gbc_rotateZBtn.insets = new Insets(0, 0, 5, 0);
		gbc_rotateZBtn.gridx = 0;
		gbc_rotateZBtn.gridy = 10;
		toolsPanel.add(rotateZBtn, gbc_rotateZBtn);
		
		rotateZAngle = new JTextField();
		GridBagConstraints gbc_rotateZAngle = new GridBagConstraints();
		gbc_rotateZAngle.insets = new Insets(0, 0, 5, 0);
		gbc_rotateZAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotateZAngle.gridx = 0;
		gbc_rotateZAngle.gridy = 11;
		toolsPanel.add(rotateZAngle, gbc_rotateZAngle);
		rotateZAngle.setColumns(10);
		
		JButton rotateBnt = new JButton("Rotate");
		rotateBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 Pattern p = Pattern.compile("^-?[a-z0-9_-]+$"); 
			     Matcher m = p.matcher(rotateAllAngle.getText()); 
			     if (m.matches()){
			    	 gridView.rotate(Integer.parseInt(rotateAllAngle.getText()));
			    	 gridView.repaint();
			     }
			}
		});
		GridBagConstraints gbc_rotateBnt = new GridBagConstraints();
		gbc_rotateBnt.insets = new Insets(0, 0, 5, 0);
		gbc_rotateBnt.gridx = 0;
		gbc_rotateBnt.gridy = 12;
		toolsPanel.add(rotateBnt, gbc_rotateBnt);
		
		rotateAllAngle = new JTextField();
		GridBagConstraints gbc_rotateAllAngle = new GridBagConstraints();
		gbc_rotateAllAngle.fill = GridBagConstraints.HORIZONTAL;
		gbc_rotateAllAngle.gridx = 0;
		gbc_rotateAllAngle.gridy = 13;
		toolsPanel.add(rotateAllAngle, gbc_rotateAllAngle);
		rotateAllAngle.setColumns(10);
				
		// масштабирование по колесику мыши
		gridView.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			
				if(arg0.getWheelRotation() < 0)
					gridView.setStep(gridView.getStep() + 1);
				else 
					gridView.setStep(gridView.getStep() - 1);	
			gridView.repaint();

			}
		});
		
		// Щелчок мыши по сетке
		gridView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {				
				// если левой клавишей 
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
		
		//передвижение мыши
		 gridView.addMouseMotionListener(new MouseMotionListener(){
				public void mouseDragged(MouseEvent e) {

					int x = e.getX() / gridView.getStep();
					int y = e.getY() / gridView.getStep();
				
					if (clickedX != x || clickedY != y)
					{
					gridView.moveLine(clickedX, clickedY, x, y);
					clickedX = x; 
					clickedY = y;
					}
					gridView.repaint();
					
				}
				public void mouseMoved(MouseEvent event) {}
				});
		
		
		
	}
	
	//какая-то хрень сгенерилась сама, не трогайте
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
