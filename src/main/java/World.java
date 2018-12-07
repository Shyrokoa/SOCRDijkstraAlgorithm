

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World extends JPanel{
    private final java.util.List<Vertex> population;
    private BufferedImage image,image2;

    static final int WIDTH = 900;
    static final int HEIGHT = 700;

    private World(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        //my Program
        Vertex vertexA = new Vertex("A",100,150);
        Vertex vertexB = new Vertex("B",300,150);
        Vertex vertexC = new Vertex("C",500,150);
        Vertex vertexD = new Vertex("D",100,350);
        Vertex vertexE = new Vertex("E",100,550);
        Vertex vertexF = new Vertex("F",300,350);
        Vertex vertexI = new Vertex("I",500,350);
        Vertex vertexH = new Vertex("H",300,550);
        Vertex vertexG = new Vertex("G",500,550);

        vertexA.addNeighbour(new Edge(10,vertexA,vertexB));
        vertexA.addNeighbour(new Edge(8,vertexA,vertexD));
        vertexD.addNeighbour(new Edge(7,vertexD,vertexF));
        vertexB.addNeighbour(new Edge(6,vertexB,vertexF));
        vertexB.addNeighbour(new Edge(3,vertexB,vertexC));
        vertexF.addNeighbour(new Edge(2,vertexF,vertexI));
        vertexC.addNeighbour(new Edge(4,vertexC,vertexI));
        vertexD.addNeighbour(new Edge(10,vertexD,vertexE));
        vertexE.addNeighbour(new Edge(5,vertexE,vertexH));
        vertexF.addNeighbour(new Edge(7,vertexF,vertexH));
        vertexH.addNeighbour(new Edge(3,vertexH,vertexG));
        vertexI.addNeighbour(new Edge(4,vertexI,vertexG));

        vertexB.addNeighbour(new Edge(10,vertexB,vertexA));
        vertexD.addNeighbour(new Edge(8,vertexD,vertexA));
        vertexF.addNeighbour(new Edge(7,vertexF,vertexD));
        vertexF.addNeighbour(new Edge(6,vertexF,vertexB));
        vertexC.addNeighbour(new Edge(3,vertexC,vertexB));
        vertexI.addNeighbour(new Edge(2,vertexI,vertexF));
        vertexI.addNeighbour(new Edge(4,vertexI,vertexC));
        vertexE.addNeighbour(new Edge(10,vertexE,vertexD));
        vertexH.addNeighbour(new Edge(5,vertexH,vertexE));
        vertexH.addNeighbour(new Edge(7,vertexH,vertexF));
        vertexG.addNeighbour(new Edge(3,vertexG,vertexH));
        vertexG.addNeighbour(new Edge(4,vertexG,vertexI));

        DijkstraShortestPath shortestPath = new DijkstraShortestPath();
        shortestPath.computeShortestPaths(vertexA);                             //put your start position

        population = shortestPath.getShortestPathTo(vertexG);                   //put your destination
        /*final Timer timer = new Timer(5, (ActionEvent e) -> {
            //this.population.update();
            repaint();
        });
        timer.start();*/

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/2.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/home.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.CYAN);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawString("PUSSYCAT GO HOME",215,50);
        g.setColor(Color.ORANGE);
        g.fillOval(100,150, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("A",100,150);
        g.setColor(Color.ORANGE);
        g.fillOval(300,150, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("B",300,150);
        g.setColor(Color.ORANGE);
        g.fillOval(500,150, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("C",500,150);
        g.setColor(Color.ORANGE);
        g.fillOval(100,350, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("D",100,350);
        g.setColor(Color.ORANGE);
        g.fillOval(100,550, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("E",100,550);
        g.setColor(Color.ORANGE);
        g.fillOval(300,350, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("F",300,350);
        g.setColor(Color.ORANGE);
        g.fillOval(500,550, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("G",500,550);
        g.setColor(Color.ORANGE);
        g.fillOval(300,550, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("H",300,550);
        g.setColor(Color.ORANGE);
        g.fillOval(500,350, 40, 40);
        g.setColor(Color.CYAN);
        g.drawString("I",500,350);
        //g.drawLine(500,150,300,350);
        drawShortstPath(g);
        g.drawImage(image,10,50,image.getWidth()/5,image.getHeight()/5,null);
        g.drawImage(image2,650,450,image.getWidth()/3,image.getHeight()/3,null);


    }

    private void drawShortstPath(final Graphics2D g) {
        final java.util.List<Vertex> chromosome = this.population;
        g.setColor(Color.RED);
        for(int i = 0; i < chromosome.size() - 1; i++) {
            Vertex vertex = chromosome.get(i);
            Vertex neighbor = chromosome.get(i + 1);
            g.drawLine(vertex.getX()+20, vertex.getY()+20, neighbor.getX()+20, neighbor.getY()+20);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString(String.valueOf(chromosome.get(i+1).getDistance()),(vertex.getX()+20+neighbor.getX()+20)/2,(vertex.getY()+20+neighbor.getY()+20)/2);
        }
        g.setColor(Color.YELLOW);
        for(final Vertex gene : chromosome) {
            g.fillOval(gene.getX(), gene.getY(), 5, 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("PUSSYCAT GO HOME");
            frame.setResizable(false);
            frame.add(new World(), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
