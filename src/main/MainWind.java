package main;


//----------------------------------------------------------------------------------------------------------//
//																											//
//							СТАРАЯ ВЕРСИЯ!!																	//
//																											//
//----------------------------------------------------------------------------------------------------------//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.applet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

import lineGeneration.*;
public class MainWind extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWind frame = new MainWind();
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
	public MainWind() {
		super("Graphic Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 450, 400);
		
		// Create the grid
		final Grid g = new Grid(100);
		contentPane = new GridView(g);
	
		contentPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				
				if(arg0.getWheelRotation() < 0)
					g.setStep(g.getStep() + 2);
				else if (g.getStep() > 2) 
					g.setStep(g.getStep() - 2);
				
					repaint();
			}
		});
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setAlignmentX(CENTER_ALIGNMENT);

		//
		System.out.println("Hello");
		// Fill the massive
		//
		// System.out.println(g.getExcel(0, 0).getT());
		
		JScrollPane s = new JScrollPane(contentPane);
		//
		JScrollBar scrollHor = new JScrollBar(JScrollBar.HORIZONTAL);
		s.setHorizontalScrollBar(scrollHor);
		s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollBar scrollVer = new JScrollBar(JScrollBar.VERTICAL);
		s.setVerticalScrollBar(scrollVer);
		s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(s);
		// setContentPane(s);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("\u041E\u0442\u0440\u0435\u0437\u043A\u0438");
		menuBar.add(menu);

		JMenu functions = new JMenu("Функции");
		menuBar.add(functions);

		JMenuItem checkBoxMenuItem = new JMenuItem("\u0426\u0414\u0410");
		checkBoxMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) { // Алгоритм ЦДА
				
				ArrayList<Point> coloredEx = LineGenerator.DDA(g);				
				for (int i = 0; i < coloredEx.size(); i++)
					g.getExcel(coloredEx.get(i).x, coloredEx.get(i).y).setColored(true);
				// добавить условие в цикле для пошагового выполнения. ну и лог можно сделать				
				//contentPane.repaint();
				repaint();
			
				
				
			}
		}
		);
		
		JMenuItem BrezenhemAlg = new JMenuItem("\u0411\u0440\u0435\u0437\u0435\u043D\u0445\u0435\u043C");
		BrezenhemAlg.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ArrayList<Point> coloredEx = LineGenerator.Brezenhem(g);
				for (int i = 0; i < coloredEx.size(); i++)
					g.getExcel(coloredEx.get(i).x, coloredEx.get(i).y).setColored(true);
				repaint();
			}
		});
		
		menu.add(BrezenhemAlg);
		
		menu.add(checkBoxMenuItem);

		JMenuItem clearAll = new JMenuItem("Очистить");
		clearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < g.getSize(); i++)
					for (int j = 0; j < g.getSize(); j++)
						g.getExcel(i, j).setColored(false);
				repaint();
			}
		});
		
		functions.add(clearAll);

		JMenuItem zoomIn = new JMenuItem("ZoomIn");
		zoomIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				g.setStep(g.getStep() + 3);
				contentPane.updateUI();
			}
		});

		JMenuItem zoomOut = new JMenuItem("ZoomOut");
		zoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (g.getStep() > 2) {
					g.setStep(g.getStep() - 2);
					contentPane.updateUI();
				}
			}
		});

		JMenuItem paint = new JMenuItem("Paint");
		paint.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (SwingUtilities.isRightMouseButton(event))
					System.out.println(event.getX());
					System.out.println(event.getY());
			}
		});

		final JPopupMenu popup = new JPopupMenu();
		popup.add(zoomIn);
		// popup.add(paint);
		popup.add(zoomOut);
		//
		contentPane.updateUI();
		contentPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (SwingUtilities.isRightMouseButton(event))
					popup.show(contentPane, event.getX(), event.getY());
				if (SwingUtilities.isLeftMouseButton(event)) {
					int x = event.getX() / g.getStep();
					int y = event.getY() / g.getStep();
					if (g.getExcel(x, y).isColored() == false) {
						g.getExcel(x, y).setColored(true);
						repaint();
					} else {
						g.getExcel(x, y).setColored(false);
						repaint();
					}
					//
					System.out.println(event.getX());
					System.out.println(event.getY());
				}

			}
		});

		// JScrollPane scrollPane = new JScrollPane(contentPane);
		// setPreferredSize(new Dimension(450, 110));
		// getContentPane().add(scrollPane, BorderLayout.CENTER);
		// getContentPane().add(new JScrollPane(contentPane));
		// contentPane.setAutoscrolls(true);
		// setContentPane(contentPane);

		/*
		 * GridBagLayout gbl_contentPane = new GridBagLayout();
		 * gbl_contentPane.columnWidths = new int[]{0};
		 * gbl_contentPane.rowHeights = new int[]{0};
		 * gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		 * gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		 * contentPane.setLayout(gbl_contentPane);
		 */
	}

}
