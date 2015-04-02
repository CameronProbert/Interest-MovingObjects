package main.ui;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.patterns.DrawingPattern;
import main.patterns.bouncingball.BouncingBallPattern;
import main.patterns.centremass.CentreOfMassPattern;

public class DrawingPad {

	JFrame frame;
	JPanel panel;
	DrawingPattern pattern;

	Loop loop;

	private DrawingPad() {
		createFrame();
		createPanel();
		createButtons();
		frame.pack();
		frame.setVisible(true);
		startLoop(new BouncingBallPattern(panel.getWidth(), panel.getHeight()));
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createPanel() {
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				if (pattern != null) {
					java.awt.Image offscreen = createImage(600, 600);
					Graphics offScrG = offscreen.getGraphics();
					drawImage(pattern, offScrG);
					g.drawImage(offscreen, 0, 0, this);
				}
			}
			
			public void drawImage(DrawingPattern pattern, Graphics g){
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				pattern.draw(g);
			}
		};
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(600, 600));
		frame.add(panel);
	}

	private void createButtons() {
		// TODO Auto-generated method stub

	}

	private void startLoop(DrawingPattern pattern) {
		if (loop != null) {
			Thread.currentThread().isInterrupted();
		}
		this.pattern = pattern;
		loop = new Loop();
		Thread thread = new Thread(loop);
		thread.run();
	}

	// //////////////////////////MAIN/////////////////////////////////
	public static void main(String[] args) {
		new DrawingPad();

	}

	private class Loop implements Runnable {

		@Override
		public void run() {
			while (true) {
				pattern.step();
				frame.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
