package a8;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageEditor {
	public static void main(String[] args) throws IOException {
		Frame2D f = MutableFrame2D.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");

		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Image Editor");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageEditorModel model = new ImageEditorModel(f);
		ImageEditorView view = new ImageEditorView(main_frame, model);
		ImageEditorController controller = new ImageEditorController(view, model);


		JPanel top_panel = new JPanel();		
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.NORTH);

		JTextField url = new JTextField();
		top_panel.add(url, BorderLayout.CENTER);

		JButton open_image = new JButton("Click to open image from valid input URL.");
		
		//Opens a new window with input image.
		open_image.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				try{
					ImageEditor2.setURL(url.getText());
					ImageEditor2.main(null);
				}
				catch(Exception e1){	
				}
			}
		});
		
		top_panel.add(open_image, BorderLayout.SOUTH);
		
		main_frame.setContentPane(top_panel);
		main_frame.pack();
		main_frame.setVisible(true);
	}
}