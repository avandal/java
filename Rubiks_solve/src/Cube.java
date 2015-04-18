import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class Cube extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Fenetre frame;
	
	private int height;
	
	private JButton bFaceHaut = new JButton("Face du haut");
	private JButton bFaceDevant = new JButton("Face de devant");
	private JButton bFaceGauche = new JButton("Face de gauche");
	private JButton bFaceBas = new JButton("Face du bas");
	private JButton bFaceDerriere = new JButton("Face de derriere");
	private JButton bFaceDroite = new JButton("Face de droite");
	private JButton bMilieuH = new JButton("Milieu horizontal");
	private JButton bMilieuVFace = new JButton("Milieu face");
	private JButton bMilieuVTranche = new JButton("Milieu tranche");
	
	private boolean e1up, e1front, e1left, e1down, e1back, e1right, e1milh, e1milvtranche, e1milvface = false;
	
	private JButton bTournerGauche = new JButton("Tourner vers la gauche");
	private JButton bTournerDroite = new JButton("Tourner vers la droite");
	private JButton bTournerHaut = new JButton("Tourner vers le haut");
	private JButton bTournerBas = new JButton("Tourner vers le bas");
	
	private JButton bRetour = new JButton("Selectionner une autre face");
	
	private JButton bZGauche = new JButton("Basculer vers la gauche");
	private JButton bZDroite = new JButton("Basculer vers la droite");
	private JButton bYGauche = new JButton("Tourner vers la gauche");
	private JButton bYDroite = new JButton("Tourner vers la droite");
	private JButton bXHaut = new JButton("Basculer vers le haut");
	private JButton bXBas = new JButton("Basculer vers le bas");
	
	private int cote;
	
	private PolygonColor[][] front = new PolygonColor[3][3];
	private PolygonColor[][] left = new PolygonColor[3][3];
	private PolygonColor[][] up = new PolygonColor[3][3];
	private PolygonColor[][] down = new PolygonColor[3][3];
	private PolygonColor[][] right = new PolygonColor[3][3];
	private PolygonColor[][] back = new PolygonColor[3][3];
	
	private Color blue = Color.blue;
	private Color red = Color.red;
	private Color yellow = Color.yellow;
	private Color green = new Color(0,128,0);
	private Color orange = new Color(255,100,0);
	private Color white = Color.white;
	
	public Cube(Fenetre frame, int cote) {
		this.height = frame.getHeight();
		this.setBackground(new Color(50,50,50));
		this.frame = frame;
		this.cote = cote;
		
		this.initialize();
	}
	
	private void initCube() { // TODO initCube
		// Faces
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						// Devant
						int[] x1 = {cote*2+1 + j*cote/3, cote*2+cote/3 + j*cote/3, cote*2+cote/3 + j*cote/3, cote*2+1 + j*cote/3};
						int[] y1 = {cote*2+1 + i*cote/3+50, cote*2+1 + i*cote/3+50, cote*2+cote/3 + i*cote/3+50, cote*2+cote/3 + i*cote/3+50};
						int nb1 = 4;
						front[i][j] = new PolygonColor(x1, y1, nb1, green);
						
						// Derriere
						int[] x4 = {cote*2+1 + j*cote/3 - cote/2, cote*2+cote/3 + j*cote/3 - cote/2, cote*2+cote/3 + j*cote/3 - cote/2, cote*2+1 + j*cote/3 - cote/2};
						int[] y4 = {cote/2+1 + i*cote/3+50, cote/2+1 + i*cote/3+50, cote/2+cote/3 + i*cote/3+50, cote/2+cote/3 + i*cote/3+50};
						int nb4 = 4;
						back[i][j] = new PolygonColor(x4, y4, nb4, blue);
						
						// Gauche
						int[] x2 = {cote*2 - j*cote/6, cote*2-cote/6+1 - j*cote/6, cote*2-cote/6+1 - j*cote/6, cote*2 - j*cote/6};
						int[] y2 = {cote*2 + i*cote/3 - j*cote/6+1+50, cote*2 - (cote*2 - (cote*2-cote/2))/3 + i*cote/3 - j*cote/6+1+50, cote*2 - (cote*2 - (cote*2-cote/2))/3+cote/3 + i*cote/3 - j*cote/6+50, cote*2+cote/3 + i*cote/3 - j*cote/6+50};
						int nb2 = 4;
						left[i][j] = new PolygonColor(x2, y2, nb2, red);
						
						// Droite
						int[] x5 = {cote*3+1 + j*cote/3, cote*3+cote/3 + j*cote/3, cote*3+cote/3 + j*cote/3, cote*3+1 + j*cote/3};
						int[] y5 = {cote*2+1 + i*cote/3+50, cote*2+1 + i*cote/3+50, cote*2+cote/3 + i*cote/3+50, cote*2+cote/3 + i*cote/3+50};
						int nb5 = 4;
						right[i][j] = new PolygonColor(x5, y5, nb5, orange);
						
						// Haut
						int[] x3 = {cote*2 + i*cote/3 - j*cote/6+1, cote*2 - (cote*2 - (cote*2-cote/2))/3 + i*cote/3 - j*cote/6+1, cote*2 - (cote*2 - (cote*2-cote/2))/3+cote/3 + i*cote/3 - j*cote/6, cote*2+cote/3 + i*cote/3 - j*cote/6};
						int[] y3 = {cote*2 - j*cote/6+50, cote*2-cote/6+1 - j*cote/6+50, cote*2-cote/6+1 - j*cote/6+50, cote*2 - j*cote/6+50};
						int nb3 = 4;
						up[i][j] = new PolygonColor(x3, y3, nb3, yellow);
						
						// Bas
						int[] x6 = {cote*2+1 + j*cote/3, cote*2+cote/3 + j*cote/3, cote*2+cote/3 + j*cote/3, cote*2+1 + j*cote/3};
						int[] y6 = {cote*3+1 + i*cote/3+50, cote*3+1 + i*cote/3+50, cote*3+cote/3 + i*cote/3+50, cote*3+cote/3 + i*cote/3+50};
						int nb6 = 4;
						down[i][j] = new PolygonColor(x6, y6, nb6, white);
					}
				}
	}
	
	public void initialize() {
		initCube();
		initButtons();
		
		toggleHautBas();
		toggleGaucheDroite();
		bRetour.setVisible(false);
	}
	
	public void toggleFaces() {
		bFaceHaut.setVisible(!bFaceHaut.isVisible());
		bMilieuH.setVisible(!bMilieuH.isVisible());
		bFaceBas.setVisible(!bFaceBas.isVisible());
		bFaceGauche.setVisible(!bFaceGauche.isVisible());
		bMilieuVTranche.setVisible(!bMilieuVTranche.isVisible());
		bFaceDroite.setVisible(!bFaceDroite.isVisible());
		bFaceDevant.setVisible(!bFaceDevant.isVisible());
		bMilieuVFace.setVisible(!bMilieuVFace.isVisible());
		bFaceDerriere.setVisible(!bFaceDerriere.isVisible());
	}
	
	public void toggleGaucheDroite() {
		bTournerGauche.setVisible(!bTournerGauche.isVisible());
		bTournerDroite.setVisible(!bTournerDroite.isVisible());
		bRetour.setVisible(!bRetour.isVisible());
	}
	
	public void toggleHautBas() {
		bTournerHaut.setVisible(!bTournerHaut.isVisible());
		bTournerBas.setVisible(!bTournerBas.isVisible());
		bRetour.setVisible(!bRetour.isVisible());
	}
	
	private void initButtons() { // TODO initButtons
		this.setLayout(null);
		
		
		
		// Etape 1 : Choisir sa face
		
		bFaceHaut.setBounds(0,0,200,25);
		this.add(bFaceHaut);
		bFaceHaut.addActionListener(this);
		
		bMilieuH.setBounds(0,25,200,25);
		this.add(bMilieuH);
		bMilieuH.addActionListener(this);
		
		bFaceBas.setBounds(0,50,200,25);
		this.add(bFaceBas);
		bFaceBas.addActionListener(this);
		
		bFaceGauche.setBounds(200,0,200,25);
		this.add(bFaceGauche);
		bFaceGauche.addActionListener(this);
		
		bMilieuVTranche.setBounds(200,25,200,25);
		this.add(bMilieuVTranche);
		bMilieuVTranche.addActionListener(this);
		
		bFaceDroite.setBounds(200,50,200,25);
		this.add(bFaceDroite);
		bFaceDroite.addActionListener(this);
		
		bFaceDevant.setBounds(400,0,200,25);
		this.add(bFaceDevant);
		bFaceDevant.addActionListener(this);
		
		bMilieuVFace.setBounds(400,25,200,25);
		this.add(bMilieuVFace);
		bMilieuVFace.addActionListener(this);
		
		bFaceDerriere.setBounds(400,50,200,25);
		this.add(bFaceDerriere);
		bFaceDerriere.addActionListener(this);
		
		
		
		// Etape 2 : Choisir le sens
		
		bTournerGauche.setBounds(10,10,180,55);
		this.add(bTournerGauche);
		bTournerGauche.addActionListener(this);
		
		bTournerDroite.setBounds(410,10,180,55);
		this.add(bTournerDroite);
		bTournerDroite.addActionListener(this);
		
		bTournerHaut.setBounds(10,10,180,55);
		this.add(bTournerHaut);
		bTournerHaut.addActionListener(this);
		
		bTournerBas.setBounds(410,10,180,55);
		this.add(bTournerBas);
		bTournerBas.addActionListener(this);
		
		bRetour.setBounds(200,10,200,55);
		this.add(bRetour);
		bRetour.addActionListener(this);
		
		
		
		// Etape 3 : Tourner le cube
		
		bZGauche.setBounds(0,height-89,200,30);
		this.add(bZGauche);
		bZGauche.addActionListener(this);
		
		bZDroite.setBounds(0,height-59,200,30);
		this.add(bZDroite);
		bZDroite.addActionListener(this);
		
		bYGauche.setBounds(200,height-89,200,30);
		this.add(bYGauche);
		bYGauche.addActionListener(this);
		
		bYDroite.setBounds(200,height-59,200,30);
		this.add(bYDroite);
		bYDroite.addActionListener(this);
		
		bXHaut.setBounds(400,height-89,200,30);
		this.add(bXHaut);
		bXHaut.addActionListener(this);
		
		bXBas.setBounds(400,height-59,200,30);
		this.add(bXBas);
		bXBas.addActionListener(this);
	}
	
	public void paintComponent(Graphics g) { // TODO paintComponent
		// Fond
		g.setColor(new Color(50,50,50));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
//		g.setColor(Color.white);
//		g.fillRect(0, 0, width, 75);
//		
//		g.setColor(Color.white);
//		g.fillRect(0,  height-60, width, 60);
		
		drawCenter(g);
		drawCorner(g);
		drawSide(g);
//		drawCube(g);
//		drawAll(g);
//		drawGrid(g);
	}

	private void drawCenter(Graphics g) {
		// Devant
		g.setColor(e1front || e1milh || e1milvtranche ? front[1][1].getColor().darker() : front[1][1].getColor());
		g.fillPolygon(front[1][1].xpoints, front[1][1].ypoints, front[1][1].npoints);
		
		// Derriere
		g.setColor(e1back || e1milh || e1milvtranche ? back[1][1].getColor().darker() : back[1][1].getColor());
		g.fillPolygon(back[1][1].xpoints, back[1][1].ypoints, back[1][1].npoints);
		
		// Gauche
		g.setColor(e1left || e1milh || e1milvface ? left[1][1].getColor().darker() : left[1][1].getColor());
		g.fillPolygon(left[1][1].xpoints, left[1][1].ypoints, left[1][1].npoints);
		
		// Droite
		g.setColor(e1right || e1milh || e1milvface ? right[1][1].getColor().darker() : right[1][1].getColor());
		g.fillPolygon(right[1][1].xpoints, right[1][1].ypoints, right[1][1].npoints);
		
		// Haut
		g.setColor(e1up || e1milvface || e1milvtranche ? up[1][1].getColor().darker() : up[1][1].getColor());
		g.fillPolygon(up[1][1].xpoints, up[1][1].ypoints, up[1][1].npoints);
		
		// Bas
		g.setColor(e1down || e1milvface || e1milvtranche ? down[1][1].getColor().darker() : down[1][1].getColor());
		g.fillPolygon(down[1][1].xpoints, down[1][1].ypoints, down[1][1].npoints);
	}
	
	private void drawCorner(Graphics g) {
		// U = Up ----- (Haut)
		// D = Down --- (Bas)
		// L = Left	--- (Gauche)
		// R = Right -- (Droite)
		// F = Front -- (Devant)
		// B = Back	--- (Derrière)
		
		
		
		// Corner UFL --> U=00 | F=00 | L=00
		g.setColor(e1up || e1left || e1front ? up[0][0].getColor().darker() : up[0][0].getColor());
		g.fillPolygon(up[0][0].xpoints, up[0][0].ypoints, up[0][0].npoints);
		
		g.setColor(e1up || e1left || e1front ? front[0][0].getColor().darker() : front[0][0].getColor());
		g.fillPolygon(front[0][0].xpoints, front[0][0].ypoints, front[0][0].npoints);
		
		g.setColor(e1up || e1left || e1front ? left[0][0].getColor().darker() : left[0][0].getColor());
		g.fillPolygon(left[0][0].xpoints, left[0][0].ypoints, left[0][0].npoints);
		
		
		
		// Corner UFR --> U=02 | F=00 | R=20
		g.setColor(e1up || e1right || e1front ? up[2][0].getColor().darker() : up[2][0].getColor());
		g.fillPolygon(up[2][0].xpoints, up[2][0].ypoints, up[2][0].npoints);
		
		g.setColor(e1up || e1right || e1front ? front[0][2].getColor().darker() : front[0][2].getColor());
		g.fillPolygon(front[0][2].xpoints, front[0][2].ypoints, front[0][2].npoints);
		
		g.setColor(e1up || e1right || e1front ? right[0][0].getColor().darker() : right[0][0].getColor());
		g.fillPolygon(right[0][0].xpoints, right[0][0].ypoints, right[0][0].npoints);
		
		
		
		// Corner UBL --> U=20 | B=02 | L=02
		g.setColor(e1up || e1left || e1back ? up[0][2].getColor().darker() : up[0][2].getColor());
		g.fillPolygon(up[0][2].xpoints, up[0][2].ypoints, up[0][2].npoints);
		
		g.setColor(e1up || e1left || e1back ? back[2][0].getColor().darker() : back[2][0].getColor());
		g.fillPolygon(back[2][0].xpoints, back[2][0].ypoints, back[2][0].npoints);
		
		g.setColor(e1up || e1left || e1back ? left[0][2].getColor().darker() : left[0][2].getColor());
		g.fillPolygon(left[0][2].xpoints, left[0][2].ypoints, left[0][2].npoints);
		
		
		
		// Corner UBR --> U=22 | B=02 | R=22
		g.setColor(e1up || e1right || e1back ? up[2][2].getColor().darker() : up[2][2].getColor());
		g.fillPolygon(up[2][2].xpoints, up[2][2].ypoints, up[2][2].npoints);
		
		g.setColor(e1up || e1right || e1back ? back[2][2].getColor().darker() : back[2][2].getColor());
		g.fillPolygon(back[2][2].xpoints, back[2][2].ypoints, back[2][2].npoints);
		
		g.setColor(e1up || e1right || e1back ? right[0][2].getColor().darker() : right[0][2].getColor());
		g.fillPolygon(right[0][2].xpoints, right[0][2].ypoints, right[0][2].npoints);
		
		
		
		// Corner DFL --> D=00 | F=20 | L=20
		g.setColor(e1down || e1front || e1left ? down[0][0].getColor().darker() : down[0][0].getColor());
		g.fillPolygon(down[0][0].xpoints, down[0][0].ypoints, down[0][0].npoints);
		
		g.setColor(e1down || e1front || e1left ? front[2][0].getColor().darker() : front[2][0].getColor());
		g.fillPolygon(front[2][0].xpoints, front[2][0].ypoints, front[2][0].npoints);
		
		g.setColor(e1down || e1front || e1left ? left[2][0].getColor().darker() : left[2][0].getColor());
		g.fillPolygon(left[2][0].xpoints, left[2][0].ypoints, left[2][0].npoints);
		
		
		
		// Corner DFR --> D=02 | F=22 | R=20
		g.setColor(e1down || e1front || e1right ? down[0][2].getColor().darker() :down[0][2].getColor());
		g.fillPolygon(down[0][2].xpoints, down[0][2].ypoints, down[0][2].npoints);
		
		g.setColor(e1down || e1front || e1right ? front[2][2].getColor().darker() : front[2][2].getColor());
		g.fillPolygon(front[2][2].xpoints, front[2][2].ypoints, front[2][2].npoints);
		
		g.setColor(e1down || e1front || e1right ? right[2][0].getColor().darker() : right[2][0].getColor());
		g.fillPolygon(right[2][0].xpoints, right[2][0].ypoints, right[2][0].npoints);
		
		
		
		// Corner DBL --> D=00 | B=00 | L=00
		g.setColor(e1down || e1back || e1left ? down[2][0].getColor().darker() : down[2][0].getColor());
		g.fillPolygon(down[2][0].xpoints, down[2][0].ypoints, down[2][0].npoints);
		
		g.setColor(e1down || e1back || e1left ? back[0][0].getColor().darker() : back[0][0].getColor());
		g.fillPolygon(back[0][0].xpoints, back[0][0].ypoints, back[0][0].npoints);
		
		g.setColor(e1down || e1back || e1left ? left[2][2].getColor().darker() : left[2][2].getColor());
		g.fillPolygon(left[2][2].xpoints, left[2][2].ypoints, left[2][2].npoints);
		
		
		
		// Corner DBR --> D=00 | B=00 | R=00
		g.setColor(e1down || e1back || e1right ? down[2][2].getColor().darker() : down[2][2].getColor());
		g.fillPolygon(down[2][2].xpoints, down[2][2].ypoints, down[2][2].npoints);

		g.setColor(e1down || e1back || e1right ? back[0][2].getColor().darker() : back[0][2].getColor());
		g.fillPolygon(back[0][2].xpoints, back[0][2].ypoints, back[0][2].npoints);
		
		g.setColor(e1down || e1back || e1right ? right[2][2].getColor().darker() : right[2][2].getColor());
		g.fillPolygon(right[2][2].xpoints, right[2][2].ypoints, right[2][2].npoints);
	}
	
	private void drawSide(Graphics g) {
		// U = Up ----- (Haut)
		// D = Down --- (Bas)
		// L = Left	--- (Gauche)
		// R = Right -- (Droite)
		// F = Front -- (Devant)
		// B = Back	--- (Derrière)
		
		
		
		// Side UF --> U=10 | F=01
		g.setColor(e1up || e1front || e1milvtranche ? up[1][0].getColor().darker() : up[1][0].getColor());
		g.fillPolygon(up[1][0].xpoints, up[1][0].ypoints, up[1][0].npoints);
		
		g.setColor(e1up || e1front || e1milvtranche ? front[0][1].getColor().darker() : front[0][1].getColor());
		g.fillPolygon(front[0][1].xpoints, front[0][1].ypoints, front[0][1].npoints);
		
		
		
		// Side UL --> U=01 | L=01
		g.setColor(e1up || e1left || e1milvface ? up[0][1].getColor().darker() : up[0][1].getColor());
		g.fillPolygon(up[0][1].xpoints, up[0][1].ypoints, up[0][1].npoints);
		
		g.setColor(e1up || e1left || e1milvface ? left[0][1].getColor().darker() : left[0][1].getColor());
		g.fillPolygon(left[0][1].xpoints, left[0][1].ypoints, left[0][1].npoints);
		
		
		
		// Side UR --> U=21 | R=01
		g.setColor(e1up || e1right || e1milvface ? up[2][1].getColor().darker() : up[2][1].getColor());
		g.fillPolygon(up[2][1].xpoints, up[2][1].ypoints, up[2][1].npoints);
		
		g.setColor(e1up || e1right || e1milvface ? right[0][1].getColor().darker() : right[0][1].getColor());
		g.fillPolygon(right[0][1].xpoints, right[0][1].ypoints, right[0][1].npoints);
		
		
		
		// Side UB --> U=12 | B=21
		g.setColor(e1up || e1back || e1milvtranche ? up[1][2].getColor().darker() : up[1][2].getColor());
		g.fillPolygon(up[1][2].xpoints, up[1][2].ypoints, up[1][2].npoints);
		
		g.setColor(e1up || e1back || e1milvtranche ? back[2][1].getColor().darker() : back[2][1].getColor());
		g.fillPolygon(back[2][1].xpoints, back[2][1].ypoints, back[2][1].npoints);
		
		
		
		// Side FL --> F=10 | L=10
		g.setColor(e1front || e1left || e1milh ? front[1][0].getColor().darker() : front[1][0].getColor());
		g.fillPolygon(front[1][0].xpoints, front[1][0].ypoints, front[1][0].npoints);
		
		g.setColor(e1front || e1left || e1milh ? left[1][0].getColor().darker() : left[1][0].getColor());
		g.fillPolygon(left[1][0].xpoints, left[1][0].ypoints, left[1][0].npoints);
		
		
		
		// Side FR --> F=12 | R=10
		g.setColor(e1front || e1right || e1milh ? front[1][2].getColor().darker() : front[1][2].getColor());
		g.fillPolygon(front[1][2].xpoints, front[1][2].ypoints, front[1][2].npoints);
		
		g.setColor(e1front || e1right || e1milh ? right[1][0].getColor().darker() : right[1][0].getColor());
		g.fillPolygon(right[1][0].xpoints, right[1][0].ypoints, right[1][0].npoints);
		
		
		
		// Side BL --> B=10 | L=12
		g.setColor(e1back || e1left || e1milh ? back[1][0].getColor().darker() : back[1][0].getColor());
		g.fillPolygon(back[1][0].xpoints, back[1][0].ypoints, back[1][0].npoints);
		
		g.setColor(e1back || e1left || e1milh ? left[1][2].getColor().darker() : left[1][2].getColor());
		g.fillPolygon(left[1][2].xpoints, left[1][2].ypoints, left[1][2].npoints);
		
		
		
		// Side BR --> B=12 | R=12
		g.setColor(e1back || e1right || e1milh ? back[1][2].getColor().darker() : back[1][2].getColor());
		g.fillPolygon(back[1][2].xpoints, back[1][2].ypoints, back[1][2].npoints);
		
		g.setColor(e1back || e1right || e1milh ? right[1][2].getColor().darker() : right[1][2].getColor());
		g.fillPolygon(right[1][2].xpoints, right[1][2].ypoints, right[1][2].npoints);
		
		
		
		// Side DF --> D=01 | F=21
		g.setColor(e1down || e1front || e1milvtranche ? down[0][1].getColor().darker() : down[0][1].getColor());
		g.fillPolygon(down[0][1].xpoints, down[0][1].ypoints, down[0][1].npoints);
		
		g.setColor(e1down || e1front || e1milvtranche ? front[2][1].getColor().darker() : front[2][1].getColor());
		g.fillPolygon(front[2][1].xpoints, front[2][1].ypoints, front[2][1].npoints);
		
		
		
		// Side DL --> D=10 | L=21
		g.setColor(e1down || e1left || e1milvface ? down[1][0].getColor().darker() : down[1][0].getColor());
		g.fillPolygon(down[1][0].xpoints, down[1][0].ypoints, down[1][0].npoints);
		
		g.setColor(e1down || e1left || e1milvface ? left[2][1].getColor().darker() : left[2][1].getColor());
		g.fillPolygon(left[2][1].xpoints, left[2][1].ypoints, left[2][1].npoints);
		
		
		
		// Side DR --> D=12 | R=21
		g.setColor(e1down || e1right || e1milvface ? down[1][2].getColor().darker() : down[1][2].getColor());
		g.fillPolygon(down[1][2].xpoints, down[1][2].ypoints, down[1][2].npoints);
		
		g.setColor(e1down || e1right || e1milvface ? right[2][1].getColor().darker() : right[2][1].getColor());
		g.fillPolygon(right[2][1].xpoints, right[2][1].ypoints, right[2][1].npoints);
		
		
		
		// Side DB --> D=21 | B=01
		g.setColor(e1down || e1back || e1milvtranche ? down[2][1].getColor().darker() : down[2][1].getColor());
		g.fillPolygon(down[2][1].xpoints, down[2][1].ypoints, down[2][1].npoints);
		
		g.setColor(e1down || e1back || e1milvtranche ? back[0][1].getColor().darker() : back[0][1].getColor());
		g.fillPolygon(back[0][1].xpoints, back[0][1].ypoints, back[0][1].npoints);
	}
	
	
	
	
	
	// Nom de méthode turn+ligne_face+direction // TODO rotations
	
	public void turnHautGauche() {
		// Corner UFL
		Color temp = front[0][0].getColor();
		front[0][0].setColor(right[0][0].getColor());
		right[0][0].setColor(back[2][2].getColor());
		back[2][2].setColor(left[0][2].getColor());
		left[0][2].setColor(temp);
		
		temp = front[0][2].getColor();
		front[0][2].setColor(right[0][2].getColor());
		right[0][2].setColor(back[2][0].getColor());
		back[2][0].setColor(left[0][0].getColor());
		left[0][0].setColor(temp);
		
		temp = up[0][0].getColor();
		up[0][0].setColor(up[2][0].getColor());
		up[2][0].setColor(up[2][2].getColor());
		up[2][2].setColor(up[0][2].getColor());
		up[0][2].setColor(temp);
		
		temp = front[0][1].getColor();
		front[0][1].setColor(right[0][1].getColor());
		right[0][1].setColor(back[2][1].getColor());
		back[2][1].setColor(left[0][1].getColor());
		left[0][1].setColor(temp);
		
		temp = up[1][0].getColor();
		up[1][0].setColor(up[2][1].getColor());
		up[2][1].setColor(up[1][2].getColor());
		up[1][2].setColor(up[0][1].getColor());
		up[0][1].setColor(temp);
	}
	
	public void turnHautDroite() {
		for (int i = 0; i < 3; i++) {
			turnHautGauche();
		}
	}
	
	public void turnBasGauche() {
		Color temp = front[2][0].getColor();
		front[2][0].setColor(right[2][0].getColor());
		right[2][0].setColor(back[0][2].getColor());
		back[0][2].setColor(left[2][2].getColor());
		left[2][2].setColor(temp);
		
		temp = front[2][2].getColor();
		front[2][2].setColor(right[2][2].getColor());
		right[2][2].setColor(back[0][0].getColor());
		back[0][0].setColor(left[2][0].getColor());
		left[2][0].setColor(temp);
		
		temp = down[0][2].getColor();
		down[0][2].setColor(down[2][2].getColor());
		down[2][2].setColor(down[2][0].getColor());
		down[2][0].setColor(down[0][0].getColor());
		down[0][0].setColor(temp);
		
		temp = front[2][1].getColor();
		front[2][1].setColor(right[2][1].getColor());
		right[2][1].setColor(back[0][1].getColor());
		back[0][1].setColor(left[2][1].getColor());
		left[2][1].setColor(temp);
		
		temp = down[0][1].getColor();
		down[0][1].setColor(down[1][2].getColor());
		down[1][2].setColor(down[2][1].getColor());
		down[2][1].setColor(down[1][0].getColor());
		down[1][0].setColor(temp);
	}
	
	public void turnBasDroite() {
		for (int i = 0; i < 3; i++) {
			turnBasGauche();
		}
	}
	
	public void turnMilieuHGauche() {
		Color temp = front[1][0].getColor();
		front[1][0].setColor(right[1][0].getColor());
		right[1][0].setColor(back[1][2].getColor());
		back[1][2].setColor(left[1][2].getColor());
		left[1][2].setColor(temp);
		
		temp = front[1][2].getColor();
		front[1][2].setColor(right[1][2].getColor());
		right[1][2].setColor(back[1][0].getColor());
		back[1][0].setColor(left[1][0].getColor());
		left[1][0].setColor(temp);
		
		temp = front[1][1].getColor();
		front[1][1].setColor(right[1][1].getColor());
		right[1][1].setColor(back[1][1].getColor());
		back[1][1].setColor(left[1][1].getColor());
		left[1][1].setColor(temp);
	}
	
	public void turnMilieuHDroite() {
		for (int i = 0; i < 3; i++) {
			turnMilieuHGauche();
		}
	}
	
	public void turnGaucheHaut() {
		Color temp = front[0][0].getColor();
		front[0][0].setColor(down[0][0].getColor());
		down[0][0].setColor(back[0][0].getColor());
		back[0][0].setColor(up[0][2].getColor());
		up[0][2].setColor(temp);
		
		temp = front[2][0].getColor();
		front[2][0].setColor(down[2][0].getColor());
		down[2][0].setColor(back[2][0].getColor());
		back[2][0].setColor(up[0][0].getColor());
		up[0][0].setColor(temp);
		
		temp = left[0][0].getColor();
		left[0][0].setColor(left[2][0].getColor());
		left[2][0].setColor(left[2][2].getColor());
		left[2][2].setColor(left[0][2].getColor());
		left[0][2].setColor(temp);
		
		temp = front[1][0].getColor();
		front[1][0].setColor(down[1][0].getColor());
		down[1][0].setColor(back[1][0].getColor());
		back[1][0].setColor(up[0][1].getColor());
		up[0][1].setColor(temp);
		
		temp = left[1][0].getColor();
		left[1][0].setColor(left[2][1].getColor());
		left[2][1].setColor(left[1][2].getColor());
		left[1][2].setColor(left[0][1].getColor());
		left[0][1].setColor(temp);
	}
	
	public void turnGaucheBas() {
		for (int i = 0; i < 3; i++) {
			turnGaucheHaut();
		}
	}
	
	public void turnDroiteHaut() {
		Color temp = front[0][2].getColor();
		front[0][2].setColor(down[0][2].getColor());
		down[0][2].setColor(back[0][2].getColor());
		back[0][2].setColor(up[2][2].getColor());
		up[2][2].setColor(temp);
		
		temp = front[2][2].getColor();
		front[2][2].setColor(down[2][2].getColor());
		down[2][2].setColor(back[2][2].getColor());
		back[2][2].setColor(up[2][0].getColor());
		up[2][0].setColor(temp);
		
		temp = right[0][0].getColor();
		right[0][0].setColor(right[2][0].getColor());
		right[2][0].setColor(right[2][2].getColor());
		right[2][2].setColor(right[0][2].getColor());
		right[0][2].setColor(temp);
		
		temp = front[1][2].getColor();
		front[1][2].setColor(down[1][2].getColor());
		down[1][2].setColor(back[1][2].getColor());
		back[1][2].setColor(up[2][1].getColor());
		up[2][1].setColor(temp);
		
		temp = right[1][0].getColor();
		right[1][0].setColor(right[2][1].getColor());
		right[2][1].setColor(right[1][2].getColor());
		right[1][2].setColor(right[0][1].getColor());
		right[0][1].setColor(temp);
	}
	
	public void turnDroiteBas() {
		for (int i = 0; i < 3; i++) {
			turnDroiteHaut();
		}
	}
	
	public void turnMilieuVTrancheHaut() {
		Color temp = front[0][1].getColor();
		front[0][1].setColor(down[0][1].getColor());
		down[0][1].setColor(back[0][1].getColor());
		back[0][1].setColor(up[1][2].getColor());
		up[1][2].setColor(temp);
		
		temp = front[2][1].getColor();
		front[2][1].setColor(down[2][1].getColor());
		down[2][1].setColor(back[2][1].getColor());
		back[2][1].setColor(up[1][0].getColor());
		up[1][0].setColor(temp);
		
		temp = front[1][1].getColor();
		front[1][1].setColor(down[1][1].getColor());
		down[1][1].setColor(back[1][1].getColor());
		back[1][1].setColor(up[1][1].getColor());
		up[1][1].setColor(temp);
	}
	
	public void turnMilieuVTrancheBas() {
		for (int i = 0; i < 3; i++) {
			turnMilieuVTrancheHaut();
		}
	}
	
	public void turnDevantGauche() {
		Color temp = up[0][0].getColor();
		up[0][0].setColor(right[0][0].getColor());
		right[0][0].setColor(down[0][2].getColor());
		down[0][2].setColor(left[2][0].getColor());
		left[2][0].setColor(temp);
		
		temp = up[2][0].getColor();
		up[2][0].setColor(right[2][0].getColor());
		right[2][0].setColor(down[0][0].getColor());
		down[0][0].setColor(left[0][0].getColor());
		left[0][0].setColor(temp);
		
		temp = front[0][0].getColor();
		front[0][0].setColor(front[0][2].getColor());
		front[0][2].setColor(front[2][2].getColor());
		front[2][2].setColor(front[2][0].getColor());
		front[2][0].setColor(temp);
		
		temp = up[1][0].getColor();
		up[1][0].setColor(right[1][0].getColor());
		right[1][0].setColor(down[0][1].getColor());
		down[0][1].setColor(left[1][0].getColor());
		left[1][0].setColor(temp);
		
		temp = front[0][1].getColor();
		front[0][1].setColor(front[1][2].getColor());
		front[1][2].setColor(front[2][1].getColor());
		front[2][1].setColor(front[1][0].getColor());
		front[1][0].setColor(temp);
	}
	
	public void turnDevantDroite() {
		for (int i = 0; i < 3; i++) {
			turnDevantGauche();
		}
	}
	
	public void turnDerriereGauche() {
		Color temp = up[0][2].getColor();
		up[0][2].setColor(right[0][2].getColor());
		right[0][2].setColor(down[2][2].getColor());
		down[2][2].setColor(left[2][2].getColor());
		left[2][2].setColor(temp);
		
		temp = up[2][2].getColor();
		up[2][2].setColor(right[2][2].getColor());
		right[2][2].setColor(down[2][0].getColor());
		down[2][0].setColor(left[0][2].getColor());
		left[0][2].setColor(temp);
		
		temp = back[0][0].getColor();
		back[0][0].setColor(back[2][0].getColor());
		back[2][0].setColor(back[2][2].getColor());
		back[2][2].setColor(back[0][2].getColor());
		back[0][2].setColor(temp);
		
		temp = up[1][2].getColor();
		up[1][2].setColor(right[1][2].getColor());
		right[1][2].setColor(down[2][1].getColor());
		down[2][1].setColor(left[1][2].getColor());
		left[1][2].setColor(temp);
		
		temp = back[0][1].getColor();
		back[0][1].setColor(back[1][0].getColor());
		back[1][0].setColor(back[2][1].getColor());
		back[2][1].setColor(back[1][2].getColor());
		back[1][2].setColor(temp);
	}
	
	public void turnDerriereDroite() {
		for (int i = 0; i < 3; i++) {
			turnDerriereGauche();
		}
	}
	
	public void turnMilieuVFaceGauche() {
		Color temp = up[0][1].getColor();
		up[0][1].setColor(right[0][1].getColor());
		right[0][1].setColor(down[1][2].getColor());
		down[1][2].setColor(left[2][1].getColor());
		left[2][1].setColor(temp);
		
		temp = up[2][1].getColor();
		up[2][1].setColor(right[2][1].getColor());
		right[2][1].setColor(down[1][0].getColor());
		down[1][0].setColor(left[0][1].getColor());
		left[0][1].setColor(temp);
		
		temp = up[1][1].getColor();
		up[1][1].setColor(right[1][1].getColor());
		right[1][1].setColor(down[1][1].getColor());
		down[1][1].setColor(left[1][1].getColor());
		left[1][1].setColor(temp);
	}
	
	public void turnMilieuVFaceDroite() {
		for (int i = 0; i < 3; i++) {
			turnMilieuVFaceGauche();
		}
	}
	
	public void basculerGauche() {
		turnDevantGauche();
		turnMilieuVFaceGauche();
		turnDerriereGauche();
	}
	
	public void basculerDroite() {
		turnDevantDroite();
		turnMilieuVFaceDroite();
		turnDerriereDroite();
	}
	
	public void tournerGauche() {
		turnHautGauche();
		turnMilieuHGauche();
		turnBasGauche();
	}
	
	public void tournerDroite() {
		turnHautDroite();
		turnMilieuHDroite();
		turnBasDroite();
	}
	
	public void basculerHaut() {
		turnGaucheHaut();
		turnMilieuVTrancheHaut();
		turnDroiteHaut();
	}
	
	public void basculerBas() {
		turnGaucheBas();
		turnMilieuVTrancheBas();
		turnDroiteBas();
	}
	
	public void melanger() throws InterruptedException { // TODO melanger
		for (int i = 0; i < 10; i++) {
			// Si ligne choisie
			if (e1up || e1front || e1left || e1down || e1back || e1right || e1milh || e1milvtranche || e1milvface) {
				switch ((int)(Math.random()*4)) {
				case 0 : bRetour.doClick(); i--;break;
				case 1 : if (e1left || e1right || e1milvtranche) bTournerHaut.doClick(); else bTournerGauche.doClick(); Thread.sleep(100);break;
				case 2 : if (e1left || e1right || e1milvtranche) bTournerBas.doClick(); else bTournerDroite.doClick(); Thread.sleep(100);break;
				case 3 : bRetour.doClick(); i--;break;
				}
			} else {
				switch ((int)(Math.random()*9)) {
				case 0 : bFaceGauche.doClick();break;
				case 1 : bFaceDroite.doClick();break;
				case 2 : bFaceHaut.doClick();break;
				case 3 : bFaceBas.doClick();break;
				case 4 : bFaceDevant.doClick();break;
				case 5 : bFaceDerriere.doClick();break;
				case 6 : bMilieuH.doClick();break;
				case 7 : bMilieuVTranche.doClick();break;
				case 8 : bMilieuVFace.doClick();break;
				}
				i--;
			}
		}
		bRetour.doClick();
	}

	
	
	
	public void actionPerformed(ActionEvent e) { // TODO actionPerformed
		if(e.getSource() == bRetour) {
			if (e1up || e1down || e1front || e1back || e1milh || e1milvface) {
				toggleGaucheDroite();
				e1up = false;
				e1down = false;
				e1front = false;
				e1back = false;
				e1milh = false;
				e1milvface = false;
			} else if (e1left || e1right || e1milvtranche) {
				toggleHautBas();
				e1left = false;
				e1right = false;
				e1milvtranche = false;
			}
			toggleFaces();
			frame.setTitle("Rubik's Cube solver");
		}
		
		if (e.getSource() == bFaceHaut) {
			toggleGaucheDroite();
			toggleFaces();
			e1up = true;
			frame.setTitle("Rubik's Cube solver - Face du haut");
		}
		
		if (e.getSource() == bFaceBas) {
			toggleGaucheDroite();
			toggleFaces();
			e1down = true;
			frame.setTitle("Rubik's Cube solver - Face du bas");
		}
		
		if (e.getSource() == bFaceGauche) {
			toggleHautBas();
			toggleFaces();
			e1left = true;
			frame.setTitle("Rubik's Cube solver - Face de gauche");
		}
		
		if (e.getSource() == bFaceDroite) {
			toggleHautBas();
			toggleFaces();
			e1right = true;
			frame.setTitle("Rubik's Cube solver - Face de droite");
		}
		
		if (e.getSource() == bFaceDevant) {
			toggleGaucheDroite();
			toggleFaces();
			e1front = true;
			frame.setTitle("Rubik's Cube solver - Face de devant");
		}
		
		if (e.getSource() == bFaceDerriere) {
			toggleGaucheDroite();
			toggleFaces();
			e1back = true;
			frame.setTitle("Rubik's Cube solver - Face de derrière");
		}
		
		if (e.getSource() == bMilieuH) {
			toggleGaucheDroite();
			toggleFaces();
			e1milh = true;
			frame.setTitle("Rubik's Cube solver - Milieu horizontal");
		}
		
		if (e.getSource() == bMilieuVFace) {
			toggleGaucheDroite();
			toggleFaces();
			e1milvface = true;
			frame.setTitle("Rubik's Cube solver - Mileu vertical face");
		}
		
		if (e.getSource() == bMilieuVTranche) {
			toggleHautBas();
			toggleFaces();
			e1milvtranche = true;
			frame.setTitle("Rubik's Cube solver - Milieu vertical tranche");
		}
		
		
		
		if (e.getSource() == bTournerGauche) {
			if (e1up) turnHautGauche();
			if (e1down) turnBasGauche();
			if (e1front) turnDevantGauche();
			if (e1back) turnDerriereGauche();
			if (e1milh) turnMilieuHGauche();
			if (e1milvface) turnMilieuVFaceGauche();
		}
		
		if (e.getSource() == bTournerDroite) {
			if (e1up) turnHautDroite();
			if (e1down) turnBasDroite();
			if (e1front) turnDevantDroite();
			if (e1back) turnDerriereDroite();
			if (e1milh) turnMilieuHDroite();
			if (e1milvface) turnMilieuVFaceDroite();
		}
		
		if (e.getSource() == bTournerHaut) {
			if (e1left) turnGaucheHaut();
			if (e1right) turnDroiteHaut();
			if (e1milvtranche) turnMilieuVTrancheHaut();
		}
		
		if (e.getSource() == bTournerBas) {
			if (e1left) turnGaucheBas();
			if (e1right) turnDroiteBas();
			if (e1milvtranche) turnMilieuVTrancheBas();
		}
		
		
		
		if (e.getSource() == bZGauche) {
			basculerGauche();
		}
		
		if (e.getSource() == bZDroite) {
			basculerDroite();
		}
		
		if (e.getSource() == bYGauche) {
			tournerGauche();
		}
		
		if (e.getSource() == bYDroite) {
			tournerDroite();
		}
		
		if (e.getSource() == bXHaut) {
			basculerHaut();
		}
		
		if (e.getSource() == bXBas) {
			basculerBas();
		}
		
		this.repaint();
	}
	
	// TODO getters
	public PolygonColor[][] getUp() {
		return up;
	}
	
	public PolygonColor[][] getDown() {
		return down;
	}
	
	public PolygonColor[][] getLeft() {
		return left;
	}
	
	public PolygonColor[][] getRight() {
		return right;
	}
	
	public PolygonColor[][] getFront() {
		return front;
	}
	
	public PolygonColor[][] getBack() {
		return back;
	}
	
	
	
	
	
	// TODO methodes commentaire
//	private void drawAll(Graphics g) {
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				// Devant
//				g.setColor(front[i][j].getColor());
//				g.fillPolygon(front[i][j].xpoints, front[i][j].ypoints, front[i][j].npoints);
//				
//				// Derriere
//				g.setColor(back[i][j].getColor());
//				g.fillPolygon(back[i][j].xpoints, back[i][j].ypoints, back[i][j].npoints);
//				
//				// Gauche
//				g.setColor(left[i][j].getColor());
//				g.fillPolygon(left[i][j].xpoints, left[i][j].ypoints, left[i][j].npoints);
//				
//				// Droite
//				g.setColor(right[i][j].getColor());
//				g.fillPolygon(right[i][j].xpoints, right[i][j].ypoints, right[i][j].npoints);
//				
//				// Haut
//				g.setColor(up[i][j].getColor());
//				g.fillPolygon(up[i][j].xpoints, up[i][j].ypoints, up[i][j].npoints);
//				
//				// Bas
//				g.setColor(down[i][j].getColor());
//				g.fillPolygon(down[i][j].xpoints, down[i][j].ypoints, down[i][j].npoints);
//			}
//		}
//	}
	
	
	
//	private void drawCube(Graphics g) {
//		// Dessiner le cube principal
//				g.setColor(Color.black);
//				g.drawRect(cote*2, cote*2, cote, cote); // Carré en face
//				g.drawRect(cote*2-cote/2, cote*2-cote/2, cote, cote); // Carré de derrière
//				g.drawLine(cote*2, cote*2, cote*2-cote/2, cote*2-cote/2); // Diagonale haut gauche
//				g.drawLine(cote*2+cote, cote*2, cote*2-cote/2+cote, cote*2-cote/2); // Diagonale haut droite
//				g.drawLine(cote*2, cote*2+cote, cote*2-cote/2, cote*2-cote/2+cote); // Diagonale bas gauche
//				
//				// Effacer les traits invisibles
//				g.setColor(new Color(50,50,50));
//				g.fillRect(cote*2+1, cote*2+1, cote/2, cote/2); // Carré au centre
//				g.drawLine(cote*2-cote/2+1, cote*2+cote/2, cote*2-1, cote*2+cote/2); // Trait de gauche
//				g.drawLine(cote*2+cote/2, cote*2-1, cote*2+cote/2, cote*2-cote/2+1); // Trait du haut
//				
//				drawGrid(g);
//	}
	
	
//	private void drawGrid(Graphics g) {
//		// Grille sur le devant
//		g.setColor(Color.black);
//		g.drawLine(cote*2, cote*2+cote/3, cote*2+cote, cote*2+cote/3); // Horizontal haut
//		g.drawLine(cote*2, cote*2+2*cote/3, cote*2+cote, cote*2+2*cote/3); // Horizontal bas
//		g.drawLine(cote*2+cote/3, cote*2, cote*2+cote/3, cote*2+cote); // Vertical gauche
//		g.drawLine(cote*2+2*cote/3, cote*2, cote*2+2*cote/3, cote*2+cote); // Vertical droit
//		
//		// grille sur le haut
//		g.drawLine(cote*2+cote/3, cote*2, cote*2-cote/2+cote/3, cote*2-cote/2); // Vertical gauche
//		g.drawLine(cote*2+2*cote/3, cote*2, cote*2-cote/2+2*cote/3, cote*2-cote/2); // Vertical droite
//		g.drawLine(cote*2 - (cote*2 - (cote*2-cote/2))/3, cote*2-cote/6, cote*2 - (cote*2 - (cote*2-cote/2))/3 + cote, cote*2-cote/6); // Horizontal bas
//		g.drawLine(cote*2 - 2*(cote*2 - (cote*2-cote/2))/3, cote*2-cote/3, cote*2 - 2*(cote*2 - (cote*2-cote/2))/3 + cote, cote*2-cote/3); // Horizontal haut
//		
//		// grille sur la gauche
//		g.drawLine(cote*2, cote*2+cote/3, cote*2-cote/2, cote*2-cote/2+cote/3); // Horizontal haut
//		g.drawLine(cote*2, cote*2+2*cote/3, cote*2-cote/2, cote*2-cote/2+2*cote/3); // Horizontal bas
//		g.drawLine(cote*2-cote/6, cote*2 - (cote*2 - (cote*2-cote/2))/3, cote*2-cote/6, cote*2 - (cote*2 - (cote*2-cote/2))/3 + cote); // Vertical droite
//		g.drawLine(cote*2-cote/3, cote*2 - 2*(cote*2 - (cote*2-cote/2))/3, cote*2-cote/3, cote*2 - 2*(cote*2 - (cote*2-cote/2))/3 + cote); // Vertical gauche
//	}
}