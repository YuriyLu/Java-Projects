package com.view;

import com.model.Board;

import java.awt.*;

public class BoardView{
    private Color mainColor;
    private Color pillowColor;
    private Board board;

    public BoardView(Board board) {
        this.board = board;
        mainColor = new Color(0, 128, 125);
        pillowColor = new Color(255, 0, 25);
    }

    public void drawBoard(Graphics g){
        g.setColor(pillowColor);
        g.fillRoundRect(board.getX(), board.getY(), board.getWidth(), board.getHeight(), 10, 10);
        g.setColor(mainColor);
        g.fillRoundRect(board.getX(), board.getY() + 2, board.getWidth(), board.getHeight() - 2, 10, 10);
    }
}
