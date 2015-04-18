import java.awt.Color;
import java.awt.Polygon;


public class PolygonColor extends Polygon {
	private static final long serialVersionUID = 1L;
	
	private Color color;
	
	public PolygonColor(int[] x, int[] y, int nb, Color color) {
		super(x,y,nb);
		this.color = color;
	}
	
	public PolygonColor(PolygonColor poly) {
		this(poly.xpoints, poly.ypoints, poly.npoints, poly.color);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
