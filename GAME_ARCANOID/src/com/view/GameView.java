package com.view;

import com.model.Brick;
import com.model.GameObjectModel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URL;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GameView extends JFrame {
    private JPanel mainPanel;
    private JPanel gamePanel;
    private GameObjectModel model;
    private BonusView bonusView;
    private GameMusic music;
    private JPanel startMenu;
    private JPanel gameMenu;
    private JPanel looseMenu;
    private JPanel winMenu;
    private JLabel scoreLabel;


    private JButton start;
    private JButton about;
    private ArrayList<JButton> levels;
    private JButton resume;
    private ArrayList<JButton> restart;
    private JButton nextLevel;
    private ArrayList<JButton> exit;

    private final String LOOSE_MENU_NAME = "LOOSE_MENU";
    private final String WIN_MENU_NAME = "WIN_MENU";
    private final String MENU_NAME = "GAME_MENU";
    private final String GAME_NAME = "GAME";

    public GameView(GameObjectModel model) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.model = model;
        createMainPanel();
        createMenus();
        createGamePanel();
        createFrame();
    }

    private void createFrame() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.setUndecorated(true);
        this.setPreferredSize(model.getDimension());
        this.setLayout(new CardLayout());
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
        //src\com\files\sound1.wav
        URL soundFile = (getClass().getResource("/com/files/sound1.wav"));
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    private void setBackground(JPanel panel){
        panel.setBackground(new Color(100, 100, 100));
    }

    private void setButtonStyle(JButton button){
        button.setBackground(new Color(0, 0, 0));
        button.setForeground(new Color(255, 255, 255));
        button.setPreferredSize(new Dimension(500, 200));
        Font fx = new Font("serif", Font.BOLD, 60);
        button.setFont(fx);
    }

    private void createMenus(){
        startMenu = new JPanel();
        setBackground(startMenu);
        gameMenu = new JPanel();
        setBackground(gameMenu);
        winMenu = new JPanel();
        setBackground(winMenu);
        looseMenu = new JPanel();
        setBackground(looseMenu);

        start = new JButton("START");
        setButtonStyle(start);
        exit = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            JButton exitI = new JButton("EXIT");
            exit.add(exitI);
            setButtonStyle(exitI);
        }
        about = new JButton("ABOUT");
        setButtonStyle(about);
        levels = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            JButton levelsI = new JButton("LEVELS");
            levels.add(levelsI);
            setButtonStyle(levelsI);
        }
        restart = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            JButton restartI = new JButton("RESTART");
            restart.add(restartI);
            setButtonStyle(restartI);
        }
        resume = new JButton("RESUME");
        setButtonStyle(resume);
        nextLevel = new JButton("NEXT LEVEL");
        setButtonStyle(nextLevel);


        startMenu.setLayout(null);
        startMenu.add(start);
        int buttonWidth = 500;
        int buttonHeight = 150;
        int space = 20;
        int margin = 100;
        start.setBounds(model.getDimension().width / 2 - buttonWidth /2, margin,
                buttonWidth, buttonHeight);
        startMenu.add(levels.get(0));
        levels.get(0).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + buttonHeight + space,
                buttonWidth, buttonHeight);
        startMenu.add(about);
        about.setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + 2 * buttonHeight + 2 * space,
                buttonWidth, buttonHeight);
        startMenu.add(exit.get(0));
        exit.get(0).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + 3 * buttonHeight + 3 * space,
                buttonWidth, buttonHeight);

        gameMenu.setLayout(null);
        gameMenu.add(resume);
        resume.setBounds(model.getDimension().width / 2 - buttonWidth /2, margin,
                buttonWidth, buttonHeight);
        gameMenu.add(restart.get(0));
        restart.get(0).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + buttonHeight + space,
                buttonWidth, buttonHeight);
        gameMenu.add(levels.get(1));
        levels.get(1).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + 2 * buttonHeight + 2 * space,
                buttonWidth, buttonHeight);
        gameMenu.add(exit.get(1));
        exit.get(1).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + 3 * buttonHeight + 3 * space,
                buttonWidth, buttonHeight);

        margin = 300;
        space = 100;
        looseMenu.setLayout(null);
        looseMenu.add(restart.get(1));
        restart.get(1).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin,
                buttonWidth, buttonHeight);
        looseMenu.add(exit.get(2));
        exit.get(2).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + buttonHeight + space,
                buttonWidth, buttonHeight);
        scoreLabel = new JLabel();
        looseMenu.add(scoreLabel);
        int labelWidth = 300;
        int labelHeight = 50;
        Font fx = new Font("serif", Font.BOLD, 60);
        scoreLabel.setFont(fx);
        scoreLabel.setBounds(model.getDimension().width / 2 - labelWidth / 2, 100, labelWidth, labelHeight);

        margin = 150;
        space = 100;
        winMenu.setLayout(null);
        winMenu.add(nextLevel);
        nextLevel.setBounds(model.getDimension().width / 2 - buttonWidth /2, margin,
                buttonWidth, buttonHeight);
        winMenu.add(restart.get(2));
        restart.get(2).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + buttonHeight + space,
                buttonWidth, buttonHeight);
        winMenu.add(exit.get(3));
        exit.get(3).setBounds(model.getDimension().width / 2 - buttonWidth /2, margin + 2 * buttonHeight + 2 * space,
                buttonWidth, buttonHeight);
        JLabel label = new JLabel("You win!");
        winMenu.add(label);
        label.setFont(fx);
        label.setBounds(model.getDimension().width / 2 - 120, 20, 240, 100);
    }

    private void createMainPanel() {
        CardLayout layout = new CardLayout();
        mainPanel = new JPanel(layout);
    }

    private void createGamePanel(){
        gamePanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                render(g);
            }
        };
        gamePanel.setBackground(new Color(100, 100, 100));
        gamePanel.setPreferredSize(model.getDimension());
        gamePanel.setVisible(true);

        String START_MENU_NAME = "START_MENU";
        mainPanel.add(startMenu, START_MENU_NAME);
        mainPanel.add(gamePanel, GAME_NAME);
        mainPanel.add(gameMenu, MENU_NAME);
        mainPanel.add(looseMenu, LOOSE_MENU_NAME);
        mainPanel.add(winMenu, WIN_MENU_NAME);
    }

    private void drawBorders(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, model.getDimension().width, model.getDimension().height);
        g.setColor(new Color(35, 35, 35));
        g.fillRect(model.getLeftBorder(), model.getTopBorder(),
                model.getRightBorder() - model.getLeftBorder(),
                model.getDimension().height);
    }

    private void drawBricks(Graphics g) {
        try {
            ArrayList<Brick> bricks = model.getBricks();
            for (Brick brick : bricks) {
                if (brick != null) {
                    BrickView brickView = new BrickView(brick);
                    brickView.drawBrick(g);
                }
            }
        } catch (ConcurrentModificationException ex) {
        }

    }

    private void drawBoard(Graphics g) {
        BoardView boardView = new BoardView(model.getBoard());
        boardView.drawBoard(g);
    }

    private void drawBall(Graphics g) {
        BallView ballView = new BallView(model.getBall());
        ballView.drawBall(g);
    }

    public void render(Graphics g) {
        drawBorders(g);
        drawBoard(g);
        drawBall(g);
        drawBricks(g);
        showScore(g);
    }

    public void showScore(Graphics g){
        Font fx = new Font("serif", Font.BOLD, 20);
        AttributedString as = new AttributedString(String.valueOf(model.getScore()));
        as.addAttribute( TextAttribute.FONT, fx );
        g.drawString( as.getIterator(), model.getDimension().width - 150, 70);
    }

    public void showGame(){
        ((CardLayout)mainPanel.getLayout()).show(mainPanel, GAME_NAME);
        gamePanel.requestFocus();
    }

    public void showGameMenu(){
        ((CardLayout)mainPanel.getLayout()).show(mainPanel, MENU_NAME);
    }

    public void showLooseMenu(int score){
        scoreLabel.setText("Score: " + score);
        ((CardLayout)mainPanel.getLayout()).show(mainPanel, LOOSE_MENU_NAME);
    }

    public void showWinMenu(){
        ((CardLayout)mainPanel.getLayout()).show(mainPanel, WIN_MENU_NAME);
    }

    public JButton getStart() {
        return start;
    }

    public ArrayList<JButton> getExit() {
        return exit;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getResume() {
        return resume;
    }

    public ArrayList<JButton> getRestart() {
        return restart;
    }

    public JButton getNextLevel() {
        return nextLevel;
    }
}
