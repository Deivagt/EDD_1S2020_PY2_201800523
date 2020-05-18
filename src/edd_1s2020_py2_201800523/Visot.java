/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_1s2020_py2_201800523;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Visot extends JFrame {

    public Visot(String ruta) {
	super("Visor");
	ImageIcon ii = new ImageIcon(ruta);

	// ImageIcon car = new ImageIcon(ruta);
	Icon icono = new ImageIcon(ii.getImage().getScaledInstance(ii.getIconWidth(), ii.getIconHeight(), Image.SCALE_DEFAULT));
	JScrollPane jsp = new JScrollPane(new JLabel(icono));
	getContentPane().add(jsp);
	setSize(1366, 758);
	setLocationRelativeTo(null);
	setVisible(true);
	
    }

}
