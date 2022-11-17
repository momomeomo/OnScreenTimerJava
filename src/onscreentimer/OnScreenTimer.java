/*
    On screen timer that sends input to screen when time is up
    Used for 5800X3D benchmarking in simulation games
*/

package onscreentimer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.border.EmptyBorder;

public class OnScreenTimer extends JFrame implements ActionListener {

    private int seconds;
    private SimpleDateFormat df;
    private boolean isRunning;
    private JLabel lblTimer2;

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

        Timer tm2 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setTimer();
                seconds++;
            }
        });

        JButton btnNewButton = new JButton("Start");
        btnNewButton.setBackground(Color.LIGHT_GRAY);
        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isRunning) {
                    tm2.stop();
                    btnNewButton.setText("Start");
		    btnNewButton.setForeground(Color.RED);
                }else {
                    tm2.start();
                    btnNewButton.setText("Stop");
		    btnNewButton.setForeground(Color.GREEN);
                }

                isRunning = !isRunning;
            }
        });
        contentPane.add(btnNewButton, BorderLayout.SOUTH);

        df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));

        seconds = 0;
        isRunning = false;
        setTimer();
        pack();
    }


    private void setTimer() {
        Date d = new Date(seconds * 1000L);
        String time = df.format(d);
        lblTimer2.setText(time);
    }

    public static void main(String[] args) {
	OnScreenTimer ost = new OnScreenTimer();
	ost.deploy();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	throw new UnsupportedOperationException("Not supported"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
