package com.martin.timer;

import com.martin.main.*;
import com.state.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class MITimer {

    private JLabel label;
    public Timer timer;
    private int time;
    private Main main;
    public MITimer(JLabel label, int time, Main main) {
        this.label = label;
        this.time = time;
        this.timer = new Timer();
        this.main = main;
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(getTimerTask(), 0, 1000);
        label.setForeground(Color.BLACK);
        main.setState(TimerState.doing);
    }
    public void stopTimer() {
        timer.cancel();
        timer.purge();
        label.setForeground(Color.RED);
        label.setText("Not active");
        main.setState(TimerState.stopped);
    }
    public void pauseTimer() {
        timer.cancel();
        timer.purge();
        main.setState(TimerState.paused);
    }
    public void resumeTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(getTimerTask(), 0, 1000);
        main.setState(TimerState.resumed);
    }
    private TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                time -= 1;
                if(!(time <= 0)) {
                    int hours = time / 3600;
                    int minutes = (time % 3600) / 60;
                    int seconds = time % 60;

                    label.setText("Hours: " + hours + " | Minutes: " + minutes + " | Seconds: " + seconds);
                }else {
                    label.setForeground(Color.BLUE);
                    label.setText("Time is up!");
                }
                if(time <= 0) {
                    Toolkit.getDefaultToolkit().beep();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            timer.cancel();
                        }
                    }, 5000);
                }
            }
        };
    }
}
