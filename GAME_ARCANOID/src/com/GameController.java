package com;

import com.levels.Levels;
import com.model.GameObjectModel;
import com.view.GameView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements Observer {
    private GameObjectModel model;
    private GameView view;
    private Levels levels;
    private boolean gameOn = false;
    private boolean gamePaused = true;
    private Timer timer;


    public GameController() {
        try {
            model = new GameObjectModel(this);
            view = new GameView(model);
            setMenuListeners();
            setGamePlayListeners();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Wrong file parameters");
        } catch (UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Wrong audio parameters");
        } catch (LineUnavailableException ex) {
            JOptionPane.showMessageDialog(null, "Wrong line parameters");
        }

    }

    private void setMenuListeners() {
        view.getStart().addActionListener(startListener);
        view.getResume().addActionListener(startListener);
        view.getGamePanel().addKeyListener(escapeAdapter);
        view.getNextLevel().addActionListener(nextLevelListener);
        view.getGamePanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    model.cheat1();
                }
            }
        });
        for (JButton exit : view.getExit()) {
            setExitButton(exit);
        }
        for (JButton restart : view.getRestart()) {
            setRestartButton(restart);
        }
    }

    private void setExitButton(JButton button) {
        button.addActionListener(actionEvent -> System.exit(0));
    }

    private void setRestartButton(JButton button) {
        button.addActionListener(actionEvent -> {
            restartGame();
        });
    }

    private void setGamePlayListeners() {
        view.getGamePanel().addMouseListener(clickAdapter);
        view.getGamePanel().addMouseMotionListener(moveAdapter);
    }

    @Override
    public void update(Object o) {
        if (model.isLoose) {
            timer.cancel();
            view.showLooseMenu(model.getScore());
        }
        if (model.isWin) {
            timer.cancel();
            view.showWinMenu();
        }
    }

    private void setTimer() {
        timer = new Timer("Timer");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!gamePaused) {
                    model.update();
                    view.repaint();
                    checkIntersect();
                }
            }
        }, 0, 5 );
    }

    private MouseAdapter clickAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!gamePaused) {
                if (!gameOn) {
                    checkIntersect();
                    gameOn = true;
                    setTimer();
                }
            }
        }
    };

    private MouseAdapter moveAdapter = new MouseAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
            if (!gamePaused) {
                int mouseX = e.getX();
                if (!gameOn) {
                    model.setBallStartPosition(mouseX);
                }
                model.setBoardPosition(mouseX);
                checkIntersect();
                view.repaint();
            }
        }
    };

    private ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.showGame();
            gamePaused = false;
        }
    };


    private KeyAdapter escapeAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                view.showGameMenu();
                gamePaused = true;
            }
        }
    };

    private ActionListener nextLevelListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            restartGame();
            model.nextLevel();
        }
    };

    private void restartGame() {
        view.getGamePanel().removeMouseListener(clickAdapter);
        view.getGamePanel().removeMouseMotionListener(moveAdapter);
        gameOn = false;
        setGamePlayListeners();
        model.restart();
        view.repaint();
        view.showGame();
    }

    private void checkIntersect() {
        if (model.getBall().isIntersect(model.getBoard())) {
            model.getBall().boardTouch(model.getBoard());
        }
    }
}
