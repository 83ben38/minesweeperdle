import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class Game extends GraphicsProgram {
    //add a reset button / autofill button / removal
    GObject[] currentObjects;
    int dimensions;
    int numGuesses = 5;
    int currentGuess = 0;
    MineGrid answer;
    public static int currentNum = 0;
    @Override
    public void run() {
        GLabel title = new GLabel("Minesweeperdle");
        title.setFont(new Font(title.getFont().getFontName(),Font.BOLD,50));
        add(title,getWidth()/2.0-title.getWidth()/2.0,getHeight()/3.0);
        currentObjects = new GObject[4];
        currentObjects[0] = title;
        for (int i = 0; i < 3; i++) {
            GCompound c = new GCompound();
            GRect r = new GRect(50,30);
            c.add(r);
            GLabel l = new GLabel("Play " + ((i*2)+3) + "x" + ((i*2)+3));
            c.add(l,0,c.getHeight()/2);
            add(c,getWidth()*(i+1)/4.0-c.getWidth()/2,getHeight()*2/3.0);
            currentObjects[i+1] = c;
            int finalI = i*2;
            r.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dimensions = finalI + 3;
                    go();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
    public void go(){
        for (int i = 0; i < currentObjects.length; i++) {
            remove(currentObjects[i]);
        }
        double mineChance = 0.4 + (Math.random()*0.2);
        answer = new MineGrid(dimensions,mineChance);
//        System.out.println(answer);
//        System.out.println(mineChance);
        GNumSquare n = new GNumSquare();
        n.setNum(0);
        add(n,getWidth()*6/8.0-n.getWidth()/2,getHeight()*3/4.0-n.getHeight()/2);
        for (int i = 0; i < 2; i++) {
            GNumSquare z = new GNumSquare();
            z.setNum(i==0?"-":"+");
            int finalI = i;
            z.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (finalI == 0){
                        if (currentNum >= 0){
                            currentNum--;
                        }
                    }
                    else{
                        if (currentNum < 9){
                            currentNum++;
                        }
                    }
                    if (currentNum == -1){
                        n.setNum("");
                        return;
                    }
                    n.setNum(currentNum);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            add(z,getWidth()*(5+(i*2))/8.0-z.getWidth()/2,getHeight()*3/4.0-z.getHeight()/2);
        }
        GuessBoard notes = new GuessBoard(dimensions);
        add(notes,getWidth()*6/8.0-notes.getWidth()/2,getHeight()/2.0-notes.getHeight()/2);
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                GNumSquare finalSquare = notes.squares[i][j];
                finalSquare.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        finalSquare.setNum(finalSquare.num.getLabel().equals("x") ?" " : finalSquare.num.getLabel().equals(" ") ? "-" : "x");
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
        GNumSquare[] buttons = new GNumSquare[numGuesses];
        GuessBoard[] boards = new GuessBoard[numGuesses];
        for (int i = 0; i < numGuesses; i++) {
            buttons[i] = new GNumSquare();
            buttons[i].setNum(i+1);
            add(buttons[i],getWidth()/8.0-buttons[i].getWidth()/2,getHeight()*(i+1.0)/(numGuesses+1)-buttons[i].getHeight()/2);
            buttons[i].setColor(Color.gray);
            int finalI = i;
            buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (currentGuess >= finalI){
                        for (int j = 0; j < boards.length; j++) {
                            remove(boards[j]);
                        }
                        add(boards[finalI]);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
        buttons[0].setColor(Color.white);
        for (int i = 0; i < numGuesses; i++) {
            boards[i] = new GuessBoard(dimensions);
            for (int j = 0; j < dimensions; j++) {
                for (int k = 0; k < dimensions; k++) {
                    GNumSquare finalSquare = boards[i].squares[j][k];
                    boards[i].squares[j][k].addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            finalSquare.setNum(currentNum);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });
                }
            }
            add(boards[i],getWidth()*3/8.0-boards[i].getWidth()/2,getHeight()/2.0-boards[i].getHeight()/2);
            if (i != 0){
                remove(boards[i]);
            }
        }
        GCompound c = new GCompound();
        GRect r = new GRect(50,30);
        c.add(r);
        GLabel l = new GLabel("Guess");
        c.add(l,0,c.getHeight()/2);
        add(c,getWidth()*3/8.0-c.getWidth()/2,getHeight()*3/4.0-c.getHeight()/2);
        r.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < dimensions; i++) {
                    for (int j = 0; j < dimensions; j++) {
                        if (boards[currentGuess].squares[i][j].value == -1){
                            return;
                        }
                    }
                }
                boards[currentGuess].check(answer);
                currentGuess++;
                buttons[currentGuess].setColor(Color.white);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        {
            c = new GCompound();
            r = new GRect(50, 30);
            c.add(r);
            l = new GLabel("Clear");
            c.add(l, 0, c.getHeight() / 2);
            add(c, getWidth() * 6 / 8.0 - c.getWidth() / 2, getHeight() / 4.0 - c.getHeight() / 2);
            r.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int i = 0; i < dimensions; i++) {
                        for (int j = 0; j < dimensions; j++) {
                            notes.squares[i][j].setNum(" ");
                        }
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
        {
            c = new GCompound();
            r = new GRect(50, 30);
            c.add(r);
            l = new GLabel("Autofill");
            c.add(l, 0, c.getHeight() / 2);
            add(c, getWidth() * 3 / 8.0 - c.getWidth() / 2, getHeight() / 4.0 - c.getHeight() / 2);
            r.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (currentNum == 0){
                        return;
                    }
                    for (int i = 0; i < dimensions; i++) {
                        for (int j = 0; j < dimensions; j++) {
                            GNumSquare n = boards[currentGuess-1].squares[i][j];
                            if (n.value == answer.get(i,j)){
                                boards[currentGuess].squares[i][j].setNum(n.value);
                            }
                        }
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
}
