import java.awt.Color;


public class ColorUpdate extends Color {
	private static final long serialVersionUID = 1L;
	
	
	public ColorUpdate(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}
	
	public ColorUpdate(Color color) {
		super(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public Color sombre() {
		int red = this.getRed() - (this.getRed() >= 50 ? 50 : this.getRed());
		int green = this.getGreen() - (this.getGreen() >= 50 ? 50 : this.getGreen());;
		int blue = this.getBlue() - (this.getBlue() >= 50 ? 50 : this.getBlue());;
		return new Color(red, green, blue);
	}
	
}
