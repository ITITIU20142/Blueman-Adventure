package BLUEMAN_ADVENTURE;

import java.awt.*;
import javax.imageio.ImageIO;
// import javax.swing.*;
// import java.awt.image.BufferedImage;
import java.io.*;
import Main.src.GamePanel;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum [][];

    public TileManager(GamePanel gp) {
        
        this.gp = gp;

        tile = new Tile[3];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("Map.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("water.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while(col < gp.maxScreenCol) {

                    String numbers[] = line.split("");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if(col == gp.maxScreenCol) {
                    col = 0; row++;
                }
                br.close();

            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2D) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2D.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0; x = 0;
                row++; y += gp.tileSize;
            }

        }

    }

}
