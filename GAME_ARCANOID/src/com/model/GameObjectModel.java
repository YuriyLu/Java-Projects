package com.model;

import com.GameController;
import com.Observer;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class GameObjectModel extends KeyAdapter {
    private Observer observer;
    private Ball ball;
    private Board board;
    private ArrayList<Brick> bricks;
    private int score;

    private final int leftBorder;
    private final int topBorder;
    private final int rightBorder;
    private final int bottomBorder;
    private final int indent;
    private final Dimension dimension;
    public boolean isLoose = false;
    public boolean isWin = false;



    public GameObjectModel(GameController controller) {
        score = 0;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();

        leftBorder = 50;
        topBorder = 50;
        rightBorder = dimension.width - 50;
        bottomBorder = dimension.height - 100;
        indent = 6;
        createBricks();
        createBoard();
        createBall();
        addObserver(controller);
    }

    public void addObserver(Observer observer){
        this.observer = observer;
    }

    private void createBricks() {
        bricks = new ArrayList<>();
        int bricksInRow = 15;
        int brickWidth = (int) Math.round((rightBorder - leftBorder - indent * (bricksInRow + 1)) / (double) bricksInRow);
        int brickHeight = (bottomBorder - topBorder) / 20;
        int x = leftBorder + indent + brickWidth + indent;
        int y = topBorder + indent + 2 * (brickHeight + indent);
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < bricksInRow - 1; j++) {
                Brick brick = new Brick(x, y, 1, brickWidth, brickHeight);
                x += brickWidth + indent;
                brick.setLevel(i);
                bricks.add(brick);
            }
            x = leftBorder + indent + brickWidth + indent;
            y += brickHeight + indent;
        }
    }

    private void createBoard() {
        int boardWidth = bricks.get(0).getWidth();
        int boardHeight = bricks.get(0).getHeight() / 4;
        board = new Board(dimension.width / 2 - boardWidth / 2,
                bottomBorder - boardHeight,
                boardWidth,
                boardHeight);
    }

    private void createBall() {
        int side = bricks.get(0).getHeight() / 2;
        int ballX = dimension.width / 2 - side / 2;
        int ballY = board.getY() - side - 1;
        ball = new Ball(ballX, ballY, side, side);
    }

    public void setBoardPosition(int x) {
        int pos = x - board.getWidth() / 2;
        if (pos <= leftBorder) {
            pos = leftBorder;
        } else if (pos >= rightBorder - board.getWidth()) {
            pos = rightBorder - board.getWidth();
        }
        board.setX(pos);
    }

    public void setBallStartPosition(int x) {
        int pos = x - ball.getWidth() / 2;
        if (pos <= leftBorder + board.getWidth() / 2 - ball.getWidth() / 2) {
            pos = leftBorder + board.getWidth() / 2 - ball.getWidth() / 2;
        } else if (pos > rightBorder - board.getWidth() / 2 - ball.getWidth() / 2)
            pos = rightBorder - board.getWidth() / 2 - ball.getWidth() / 2;
        ball.setX(pos);
    }

    public void update(){
        checkIntersect();
        ball.update();
        checkBallPos();
        checkLoose();
        checkWin();
        deleteBricks();
    }

    private void checkWin(){
        if (bricks.isEmpty()){
            isWin = true;
            observer.update(null);
        }
    }

    private void checkIntersect(){
        for(BaseObject i : bricks){
            if(ball.isIntersect(i)){
                ball.changeDirection(i);
                ((Brick)i).hit();

                if (((Brick)i).getStrength() == 0){
                    score += ((Brick)i).getScore();
                    ((Brick)i).setCollision();
                }
                break;

            }
        }
        if (ball.isIntersect(this.board)){
            ball.boardTouch(this.board);
        }

    }

    private void checkLoose(){
        if(ball.getY() + ball.getHeight() > board.getY() + 8){
            observer.update(null);
            isLoose = true;
        }
    }

    public void restart(){
        ball.setPosition(dimension.width / 2 - ball.getWidth() / 2,
                bottomBorder - board.getHeight() - ball.getHeight());
        setBoardPosition(dimension.width / 2);
        createBricks();
        score = 0;
        isLoose = false;
        isWin = false;
    }

    private void deleteBricks(){
        bricks.removeIf(Brick::destroyed);
    }

    public void checkBallPos(){
        if (ball.getX() <= leftBorder ){
            ball.wallTouch(0);
        } else if (ball.getX() + ball.getWidth() >= rightBorder){
            ball.wallTouch(1);
        } else if (ball.getY() <= topBorder){
            ball.wallTouch(2);
        } /*else if (ball.getY() + ball.getHeight() >= bottomBorder){
            ball.wallTouch(3);
        }*/

    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public int getTopBorder() {
        return topBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    public Board getBoard() {
        return board;
    }

    public Ball getBall() {
        return ball;
    }

    public int getScore() {
        return score;
    }

    public void cheat1(){
        Iterator<Brick> it = bricks.iterator();
        it.next();
        while (it.hasNext()){
            it.remove();
            it.next();
        }
    }

    public void nextLevel(){
        bricks = new ArrayList<>();
        int bricksInRow = 15;
        int brickWidth = (int) Math.round((rightBorder - leftBorder - indent * (bricksInRow + 1)) / (double) bricksInRow);
        int brickHeight = (bottomBorder - topBorder) / 20;
        int x = leftBorder + indent + brickWidth + indent;
        int y = topBorder + indent + 2 * (brickHeight + indent);
        for (int i = 0; i < 10; i++){
            for (int j = 1; j < bricksInRow - 1; j++) {
                if (j % 2 == 0){
                    Brick brick = new Brick(x, y, 2, brickWidth, brickHeight);
                    brick.setLevel(i);
                    bricks.add(brick);
                }
                x += brickWidth + indent;
            }
            x = leftBorder + indent + brickWidth + indent;
            y += brickHeight + indent;
        }

    }
}
