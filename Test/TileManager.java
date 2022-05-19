// import java.awt.event.KeyListener;
import java.awt.*;
import javax.imageio.ImageIO;
// import javax.swing.*;
// import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    int mapTileNum [][];

    public TileManager(GamePanel gp) {
        
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("world01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("sand.png"));
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

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col], 10);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if(col == gp.maxWorldCol) {
                    col = 0; row++;
                }   

            }
            br.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2D) {

        int worldCol = 0;
        int worldRow = 0;
        // int x = 0;
        // int y = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                g2D.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0; 
                worldRow++; 
            }

        }

    }

}
