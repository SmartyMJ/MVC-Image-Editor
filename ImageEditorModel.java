package a8;

public class ImageEditorModel {

	private Frame2D original;
	private ObservableFrame2D current;
	
	public ImageEditorModel(Frame2D f) {
		original = f;
		current = original.copy().createObservable();
	}

	public ObservableFrame2D getCurrent() {
		return current;
	}
	
	//Added to get original frame
	public Frame2D getOriginal(){
		return original;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendObservable();;
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeObservable();
	}
}
