import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


public class Fenetre extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private Cube cube;
	
	public Fenetre() {
		this.setSize(600,600);
		this.setTitle("Rubik's Cube solver");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void run(){
		this.cube = new Cube(this, 102);
		this.setContentPane(cube);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		cube.revalidate();
		
//		try {
//			cube.melanger();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Solver.solve(cube);
	}
	
	public static void main(String args[]) {
		Fenetre f = new Fenetre();
		f.run();
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
