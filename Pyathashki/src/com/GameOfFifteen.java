package com;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameOfFifteen extends JPanel {
    public static final int SIDE_SIZE = 2;
    private int sideSize;
    private int amountOfPieces;
    private static final Color FOREGROUND_COLOR = Color.BLUE;
    private static final Random RANDOM = new Random();
    private int[] pieces;
    private BufferedImage[] icons;
    private int pieceSize;
    private int whitePiece;
    private int gridSize;
    private boolean win;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    void setIcons() {
        icons = new BufferedImage[sideSize * sideSize];
        BufferedImage part;
        for (int i = 0; i < sideSize; i++) {
            for (int j = 0; j < sideSize; j++) {
                part = image.getSubimage(j * pieceSize, i * pieceSize, pieceSize, pieceSize);
                icons[i * sideSize + j] = part;
            }
        }
    }

    public GameOfFifteen(int sideSize, int dim) throws IOException {
        this.sideSize = sideSize;
        amountOfPieces = sideSize * sideSize - 1;
        pieces = new int[sideSize * sideSize];
        gridSize = (dim);
        pieceSize = gridSize / sideSize;
        File file = new File("src/kartinki-04102017-001.jpg");
        image = ImageIO.read(file);
        setIcons();
        setPreferredSize(new Dimension(dim, dim));
        setBackground(new Color(249, 198, 72));
        setForeground(FOREGROUND_COLOR);
        setFont(new Font("SansSerif", Font.BOLD, 60));
        win = true;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (win) {
                    restart();
                } else {
                    int xCoord = e.getX();
                    int yCoord = e.getY();
                    if (xCoord < 0 || xCoord > gridSize || yCoord < 0 || yCoord > gridSize)
                        return;
                    int xGrid = xCoord / pieceSize;
                    int yGrid = yCoord / pieceSize;
                    int xWhite = whitePiece % sideSize;
                    int yWhite = whitePiece / sideSize;
                    int direction = 0;
                    if (xGrid == xWhite && Math.abs(yGrid - yWhite) == 1)
                        direction = (yGrid - yWhite) > 0 ? sideSize : -sideSize;
                    else if (yGrid == yWhite && Math.abs(xGrid - xWhite) == 1)
                        direction = (xGrid - xWhite) > 0 ? 1 : -1;
                    if (direction != 0) {
                        pieces[whitePiece] = pieces[whitePiece + direction];
                        whitePiece = whitePiece + direction;
                        pieces[whitePiece] = 0;
                    }
                    win = isWin();
                }
                repaint();
            }
        });

        restart();
    }

    private void restart() {
        do {
            reset();
            randomise();
        } while (!isSolvable());
        win = false;
    }

    private void reset() {
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = (i + 1) % pieces.length;
        }
        whitePiece = pieces.length - 1;
    }

    private void randomise() {
        int n = amountOfPieces;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = pieces[r];
            pieces[r] = pieces[n];
            pieces[n] = tmp;
        }
    }

    private boolean isSolvable() {
        int countInversions = 0;
        for (int i = 0; i < amountOfPieces; i++) {
            for (int j = 0; j < i; j++) {
                if (pieces[j] > pieces[i])
                    countInversions++;
            }
        }
        return countInversions % 2 == 0;
    }

    private boolean isWin() {
        if (pieces[pieces.length - 1] != 0)
            return false;
        for (int i = amountOfPieces - 1; i >= 0; i--) {
            if (pieces[i] != i + 1)
                return false;
        }
        return true;
    }

    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < pieces.length; i++) {
            int xGrid = i / sideSize;
            int yGrid = i % sideSize;
            int x = yGrid * pieceSize;
            int y = xGrid * pieceSize;
            if (pieces[i] == 0) {
                if (win) {
                    g.setColor(FOREGROUND_COLOR);
                    drawOK(g, x, y);
                }
                continue;
            }
            drawPiece(g, i, x, y);
        }
    }

    private void winMessage(Graphics2D g) {
        if (win) {
            g.setFont(getFont().deriveFont(Font.BOLD, 30));
            g.setColor(FOREGROUND_COLOR);
            String s = "Click to start new game";
            g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2,
                    getHeight() - 20);
        }
    }

    private void drawOK(Graphics2D g, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        g.drawString("OK", x + (pieceSize - fm.stringWidth("OK")) / 2,
                y + (asc + (pieceSize - (asc + desc)) / 2)) ;
    }

    private void drawPiece(Graphics2D g, int i, int x, int y) {
        g.drawImage(icons[Integer.parseInt(String.valueOf(pieces[i])) - 1], null, x, y);
        g.setColor(FOREGROUND_COLOR);
        g.drawRect(x, y, pieceSize, pieceSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2D);
        winMessage(g2D);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Game of Fifteen");
            frame.setResizable(false);
            frame.setBackground(new Color(249, 198, 72));
            frame.setLayout(new GridLayout(1, 2));
            frame.setPreferredSize(new Dimension(1650, 900));
            try {
                GameOfFifteen game = new GameOfFifteen(SIDE_SIZE, 800);
                MyPannel mainPanel = new MyPannel(game.getImage());
                frame.add(game);
                frame.add(mainPanel);

            } catch (IOException e) {
                e.printStackTrace();
            }
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}