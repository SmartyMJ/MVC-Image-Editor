package a8;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PixelInspectorTool implements Tool {

	private PixelInspectorUI ui;
	private ImageEditorModel model;
	private ImageEditorController controller;
	private JButton copy_color;
	private FrameView mag_preview;
	private static int MAG_FACTOR = 2;
	

	public PixelInspectorTool(ImageEditorModel model, ImageEditorController controller) {
		this.model = model;
		this.controller = controller;
		ui = new PixelInspectorUI();
		
		copy_color = new JButton("Copy Selected Pixel to Paint Brush");
		copy_color.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				controller.getPaintBrushTool().getToolUI().setBrushColor(ui.getClickedRed(), ui.getClickedGreen(), ui.getClickedBlue());
			}
		});
		
		ui.getInfoPanel().add(copy_color);
		
		mag_preview = new FrameView(new ObservableFrame2DImpl(MAG_FACTOR*45,MAG_FACTOR*45));
		ui.add(mag_preview, BorderLayout.EAST);

	}
	
	//Magnifies 15x15 pixel area around pixel by a given MAG_FACTOR;
	public void updateMagnificationWindow(int x_, int y_){
		int pStartX = x_ - MAG_FACTOR*4;
		int pStartY = y_ - MAG_FACTOR*4;
		
		ObservableFrame2D preview_frame = mag_preview.getFrame();
		preview_frame.suspendObservable();
		
		for (int x= 0; x<preview_frame.getWidth(); x++) {
			for (int y= 0; y<preview_frame.getHeight(); y++) {
				//preview_frame width and height is a multiple of 45. Each pixel from the main image is repeated by a multiple of 3.
				//The multiple is equal to MAG_FACTOR
				preview_frame.setPixel(x, y, model.getPixel(pStartX + x/(MAG_FACTOR*3), pStartY+ y/(MAG_FACTOR*3)));
			}
		}
		
		preview_frame.resumeObservable();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
					
			updateMagnificationWindow(validateX(e.getX()), validateY(e.getY()));
		}
		catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}
	
	private int validateX(int x){ //If clicked pixel is to close to x-axis boundaries, returns closest pixel that fits full mag. window
		int xPos = 0;
		if (x > model.getCurrent().getWidth()-(MAG_FACTOR*4)-1) xPos = model.getCurrent().getWidth()-(MAG_FACTOR*4)-1;
		else if (x < (MAG_FACTOR*4)) xPos = (MAG_FACTOR*4);
		else xPos = x;
		
		//System.out.println(xPos);
		return xPos;
	
	}
	
	private int validateY(int y){ //If clicked pixel is to close to y-axis boundaries, returns closest pixel that fits full mag. window
		int yPos = 0;
		if (y > model.getCurrent().getHeight()-(MAG_FACTOR*4)-1) yPos = model.getCurrent().getHeight()-(MAG_FACTOR*4)-1;
		else if (y < (MAG_FACTOR*4)) yPos = (MAG_FACTOR*4);
		else yPos = y;
		
		//System.out.println(yPos);
		return yPos;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "Pixel Inspector";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));		

			updateMagnificationWindow(validateX(e.getX()), validateY(e.getY()));
		}
		catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
