package com.martin.main;

import com.gui.*;
import com.martin.timer.*;
import com.state.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class Main {

    /*
    * The program was created entirely by martinpro1000 (github). You can use it anywhere you want, but you can't say that it is your own thing.
    * It uses look and feels for JFrame, You can remove them if you don't want them!
    * */

    private MyFrame frame;
    private MITimer timer;
    private boolean running;
    public static TimerState state = TimerState.stopped;
    public Main() {
        this.frame = new MyFrame(this);
        frame.setupAndShowGUI();
    }

    public static void main(String[] args) {
        new Main();
    }

    public boolean startTimer(JLabel label, int secs) {
        if(!isDoing() && !isResumed()) {
            this.timer = new MITimer(label, secs, this);
            timer.startTimer();
            return true;
        }else {
            return false;
        }
    }

    public void pauseTimer() {
        if(this.isDoing() || this.isResumed()) {
            timer.pauseTimer();
        }else {
            JOptionPane.showMessageDialog(null, "Sorry, but the timer is not active!", "WARNING", 2);
        }
    }

    public void resumeTimer() {
        if(this.isPaused()) {
            timer.resumeTimer();
        }else {
            JOptionPane.showMessageDialog(null, "Sorry, but the timer is not paused!", "ERROR", 2);
        }
    }

    public void stopTimer() {
        if(isDoing() || isResumed() || isPaused()) {
            timer.stopTimer();
        }else {
            JOptionPane.showMessageDialog(null, "Sorry, but the timer is not running!", "WARNING", 2);
        }
    }
    public boolean isDoing() {
        return state == TimerState.doing;
    }
    public boolean isPaused() {
        return state == TimerState.paused;
    }
    public boolean isResumed() {
        return state == TimerState.resumed;
    }
    public boolean isStopped() {
        return state == TimerState.stopped;
    }
    public void setState(TimerState state) {
        Main.state = state;
    }
}
