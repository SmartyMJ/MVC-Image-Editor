package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PixelInspectorUI extends JPanel{

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private double clickedRed;
	private double clickedGreen;
	private double clickedBlue;
	private FrameView mag_preview;
	private JPanel info_panel;

	public PixelInspectorUI() {
		clickedRed = 10.0;
		clickedBlue = 10.0;
		clickedGreen = 10.0;
		
		
		info_panel = new JPanel();
		
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
	
		info_panel.setLayout(new GridLayout(4,1));
		info_panel.add(x_label);
		info_panel.add(y_label);
		info_panel.add(pixel_info);
		
		mag_preview = new FrameView(new ObservableFrame2DImpl(45,45));
		
		setLayout(new BorderLayout());
		add(info_panel, BorderLayout.WEST);
		add(mag_preview, BorderLayout.EAST);
		
		

	}

	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
		
		clickedRed = p.getRed();
		clickedGreen = p.getGreen();
		clickedBlue = p.getBlue();
	}
	
	public JPanel getInfoPanel(){
		return info_panel;
	}
	
	public double getClickedRed(){
		return clickedRed;
	}

	public double getClickedGreen(){
		return clickedGreen;
	}
	
	public double getClickedBlue(){
		return clickedBlue;
	}
	

}
