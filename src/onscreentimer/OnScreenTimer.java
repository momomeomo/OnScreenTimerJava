// Opens a small GUI window with a timer
package onscreentimer;

//No import *
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class OnScreenTimer extends JFrame implements ActionListener {

    long seconds;
    SimpleDateFormat df;
    boolean isRunning;
    JLabel lblTimer2;
    
    // Primary method contains the GUI code
    public void deploy() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
        lblTimer2 = new JLabel();
        lblTimer2.setForeground(new Color(199,36,177));
        lblTimer2.setFont(new Font("arial", Font.BOLD, 90));
        contentPane.add(lblTimer2,BorderLayout.NORTH);

/*****	    Timer function
    Set seconds != n to the time limit required
    5 minutes = 300 seconds;    30 minutes = 1800 seconds;
************************************************************/
        Timer tm2 = new Timer(1000, (ActionEvent e) -> {
	    if(seconds != 1800){
		seconds++;
		setTimer();
	    }else{
		complete();
	    }
	});
        JButton btnNewButton = new JButton("Start");
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.addActionListener((ActionEvent e) -> {
	    if(isRunning == true) {
		tm2.stop();
		btnNewButton.setText("Start");
		btnNewButton.setForeground(Color.RED);
	    }else if(isRunning == false) {
		tm2.start();
		btnNewButton.setText("Stop");
		btnNewButton.setForeground(Color.BLUE);
	    }
	    
	    isRunning = !isRunning;
	});
	
        contentPane.add(btnNewButton, BorderLayout.SOUTH);
	
        df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        seconds = 0;
        isRunning = false;
        setTimer();
        pack();
    }

    public void setTimer() {
        Date d = new Date(seconds * 1000L);
        String time = df.format(d);
        lblTimer2.setText(time);
    }
    
    public void complete(){
        try {
            Thread.sleep(120);
            Robot r = new Robot();
/***** 
	   Saves screenshot to path; will overwrite anything with the same name
	   Gets screen size and takes screenshot of the indicated region.
	   In this case the region is entirety of the primary monitor.
********************************************************************************/
            String path = "C:\\Users\\Geoffrey Weiss\\Desktop\\TestPictures\\TestPicture1.jpg";

            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "jpg", new File(path));
            System.out.println("Screenshot saved. Exiting...");
	    
	    dispose();
	    System.exit(0);
        }
        catch (AWTException | IOException | InterruptedException ex) {
            System.out.println("_Failure_");
	    System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
	OnScreenTimer ost = new OnScreenTimer();
	ost.deploy();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println("_Failure_");
	throw new UnsupportedOperationException("Not supported");
    }
}
