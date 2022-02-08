package com.gui;

import com.martin.main.*;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class MyFrame {
    private ImageIcon icon;
    private JFrame frame;
    private JTextField time;
    private JLabel timerLabel;
    private JButton start;
    private JButton pause;
    private JButton stop;
    private JButton resume;
    private JLabel timeCount;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel contentPane;
    private JPanel mid1;
    private JPanel mid2;
    private Main main;
    public MyFrame(Main main) {
        this.icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/frameIcon.png")));
        this.setNimbusLookAndFeel();
        this.mid1 = new JPanel();
        this.mid1.setLayout(new BoxLayout(mid1, BoxLayout.X_AXIS));
        this.mid2 = new JPanel();
        this.mid2.setLayout(new BoxLayout(mid2, BoxLayout.X_AXIS));
        this.timeCount = new JLabel("Time (in seconds): ");
        this.timeCount.setFont(getBigBoldFont());
        this.timerLabel = new JLabel();
        timerLabel.setForeground(Color.RED);
        timerLabel.setText("Not active");
        this.timerLabel.setFont(getBigBoldFont());
        this.middlePanel = new JPanel();
        this.middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        this.bottomPanel = new JPanel();
        this.bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        this.contentPane = new JPanel(new BorderLayout());
        this.frame = new JFrame("Java Timer");
        this.frame.setIconImage(icon.getImage());
        this.time = new JTextField();
        this.start = new JButton("START");
        this.start.setFocusPainted(false);
        this.start.setFont(getBigBoldFont());
        this.pause = new JButton("PAUSE");
        this.pause.setFont(getBigBoldFont());
        this.pause.setFocusPainted(false);
        this.resume = new JButton("RESUME");
        this.resume.setFont(getBigBoldFont());
        this.resume.setFocusPainted(false);
        this.stop = new JButton("STOP");
        this.stop.setFont(getBigBoldFont());
        this.stop.setFocusPainted(false);
        this.main = main;
    }

    public void setupAndShowGUI() {
        this.start.addActionListener((e) -> {
            if(this.areSecondsCorrect()) {
                if (!main.startTimer(timerLabel, this.secondsFromJTextField())) { //if the app is already running, the timer won't start.
                    JOptionPane.showMessageDialog(null, "Sorry, but the timer is already running\n<html><font color=#FF0000> Please stop it first!</font></html>");
                }else {
                    time.setEditable(false);
                }
            }
        });

        this.stop.addActionListener((e) -> {
            main.stopTimer();
            time.setEditable(true);
        });

        this.pause.addActionListener((e) -> {
            main.pauseTimer();
        });

        this.resume.addActionListener((e) -> {
            main.resumeTimer();
        });

        bottomPanel.add(start);
        bottomPanel.add(pause);
        bottomPanel.add(resume);
        bottomPanel.add(stop);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);
        mid1.add(timeCount);
        mid1.add(time);
        mid2.add(timerLabel);
        middlePanel.add(mid1);
        middlePanel.add(mid2);
        contentPane.add(middlePanel, BorderLayout.PAGE_START);
        this.frame.setPreferredSize(new Dimension(700, 300));
        this.frame.setMinimumSize(new Dimension(700, 300));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        this.time.setPreferredSize(new Dimension(100, 23));
        frame.setVisible(true);
    }

    public static Font getBigBoldFont() {
        return new Font("", Font.BOLD, 16);
    }
    private int secondsFromJTextField() {
            return Integer.parseInt(time.getText());
    }
    public boolean areSecondsCorrect() {
        try {
            if(time.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry, but the time value cannot be empty.\n<html></font color=#FF0000>Please fix that!</font></html>", "ERROR", 0);
                return false;
            }
            Integer.parseInt(time.getText());
            return true;
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry, but the time value cannot contain any latter.\n<html><font color=#FF0000>Please fix that!</font></html>", "ERROR", 0);
            return false;
        }
    }
    public void setNimbusLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch(Exception e2) {

            }
        }
    }
}
