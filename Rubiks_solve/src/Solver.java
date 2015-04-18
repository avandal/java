import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Solver {
	private static final int MAX = 10000;
	private static Cube cube;
	
	private static String[] head = {"num","Mouvement"};
	private static String[][] instructions = new String[MAX][2];
	
	private static int cpt = -1;
	
	public static void solve(Cube cube) {
		Solver.cube = cube;
		
		JOptionPane.showMessageDialog(cube, "Recherche du côté blanc...");
		searchWhite();
		
		
		JOptionPane.showMessageDialog(cube, new JScrollPane(new JTable(reformater(), head)));
	}
	
	private static String[][] reformater() {
		boolean trouve = false;
		int k = -1;
		String[][] body;
		while (!trouve && k < MAX) {
			k++;
//			System.out.println(k);
			trouve = (instructions[k][1] == null);
		}
		body = new String[2][k];
		for (int i = 0; i < k; i++) {
			body[i][0] = instructions[i][0];
			body[i][1] = instructions[i][1];
		}
		return body;
	}
	
	private static void add(String message) {
		cpt++;
		instructions[cpt][0] = Integer.toString(cpt+1);
		instructions[cpt][1] = message;
	}
	
	private static void searchWhite() {
		if (cube.getUp()[1][1].getColor() == Color.white){
			add("Face blanche en haut");
		} else {
			if (cube.getDown()[1][1].getColor() == Color.white) {
				add("Tournez deux fois le cube vers l'arrière");
				for (int i = 0; i < 2; i++) {
					cube.basculerHaut();
					cube.repaint();
				}
				add("Face blanche en haut");
			} else if (cube.getFront()[1][1].getColor() == Color.white) {
				add("Tournez le cube vers l'arrière");
				cube.basculerHaut();
				cube.repaint();
				add("Face blanche en haut");
			} else if (cube.getBack()[1][1].getColor() == Color.white) {
				add("Tournez le cube vers l'avant");
				cube.basculerBas();
				cube.repaint();
				add("Face blanche en haut");
			} else if (cube.getLeft()[1][1].getColor() == Color.white) {
				add("Basculez le cube vers la droite");
				cube.basculerDroite();
				cube.repaint();
				add("Face blanche en haut");
			} else if (cube.getRight()[1][1].getColor() == Color.white) {
				add("Basculez le cube vers la gauche");
				cube.basculerHaut();
				cube.repaint();
				add("Face blanche en haut");
			}
		}
	}
}
