package generation.parabolaGeneration;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class PDialog extends JPanel {

	/**
	 *  Временно закомментировал проверку на ввод числа,
	 *  т.к она не пропускает вещественные и отрицательные числа
	 */

	private static final long serialVersionUID = 1L;
	//private final JPanel contentPanel = new JPanel();
	private JTextField JValueField;
	private boolean ok;
	private JDialog dialog;
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public PDialog(String name) {
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(new JLabel(name));
		JValueField = new JTextField();
		panel.add(JValueField);
		add(panel,BorderLayout.CENTER);
		
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) {
	
				//int check = checkString(JValueField.getText());
				ok = true;
				dialog.setVisible(false);
				/*if(check != -1)
				{
					ok = true;
					dialog.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(new JButton(),
							"Введите число", "Информация",
							JOptionPane.WARNING_MESSAGE);
					JValueField.setText("");
				}*/
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);		
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public int checkString(String string) {
        try {
        	Integer.parseInt(string);
        } catch (Exception e) {
            return -1;
        }
        return Integer.parseInt(string);
    }
	
	public int getRadius()
	{
		int result = Integer.parseInt(JValueField.getText());
		return result;
	}
	
	public double getpValue()
	{
		double result = Double.parseDouble(JValueField.getText());
		return result;
	}
	
	public void setText(String value)
	{
		JValueField.setText(value);
	}
	
	
	/**
	 * Launch the application.
	 */
	public boolean showDialog(Component parent,String title)
	{
		ok = false;
		
		//Обнаружение фрейма-владельца
		
		Frame owner = null;
		
		if(parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		
		//При  первом обращении или при изменении фрейма-владельца
		//создается новое диалоговое окно
		
		if(dialog == null || dialog.getOwner() != owner)
		{
			dialog = new JDialog(owner,true);
			dialog.add(this);
			dialog.setBounds(890, 50, 249, 123);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}
		
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}

}
