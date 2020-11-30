

import java.awt.Color;
import java.util.Random;

public class Life
{
    private int x;            
    private static int mag = 2;  
    private static int[][] grid;     
    private static int[][] newgrid;
    private Picture pic;        

    public Life(int x)
    {
        this.x = x;
        pic = new Picture(x * mag, x * mag);
    }
    
    public int sizeOfPic() {
    	return this.x;
    }
    
    private void drawCell(int i, int j, int x)
    {
        Color white = new Color(255,255,255);
        Color black = new Color(0,0,0);
        
        for (int offsetX = 0; offsetX < mag; offsetX++)
        {
            for (int offsetY = 0; offsetY < mag; offsetY++)
            {
                if (x==1) {
                	pic.set((i*mag)+offsetX,
                			(j*mag)+offsetY, black);
                }else {
                	pic.set((i*mag)+offsetX,
                			(j*mag)+offsetY, white);
                }
            }
        }
    }
    
    public int updata(int i, int j) {
    	int count = 0;
    	int x_a = 0;
    	int y_a = 0;
    	for(int yc = -1; yc < 2; yc++) {
    		for(int xc = -1; xc < 2; xc++) {
    			if(yc == 0 & xc == 0) {
    				continue;
    			}
    			else {
    				if(xc+i<0 & yc+i<0) {
    					x_a = this.x - 1;
    					y_a = this.x - 1;
    				}
    				if(xc+i<0 & yc+i>=this.x) {
    					x_a = this.x - 1;
    					y_a = 0;
    				}
    				if(xc+i >= this.x & yc+i<0) {
    					x_a = 0;
    					y_a = this.x - 1;
    				}
    				if(xc+i >= this.x & yc+j>=this.x) {
    					x_a = 0;
    					y_a = 0;
    				}
    				if(xc+i>=0 & xc+i<this.x & yc+j<0){
    					x_a = xc + i;
    					y_a = this.x - 1;
    				}
    				if(xc+i>=0 & xc+i<this.x & yc+j>=this.x) {
    					x_a = xc + i;
    					y_a = 0;
    				}
    				if(yc+j>=0 & yc+j<this.x & xc+i<0) {
    					x_a = this.x - 1;
    					y_a = yc + j;
    				}
    				if(yc+j>=0 & yc+j<this.x & xc+i >= this.x) {
    					y_a = yc + j;
    					x_a = 0;
    				}
    				if(xc+i>=0 & xc+i<this.x & yc+j>=0 & yc+j<this.x){
    					x_a = xc + i;
    					y_a = yc + j;
    				}
					if(grid[x_a][y_a]==1) {
						count++;
					}
    			}
    		}
    	}
    	if(grid[i][j]==1) {
    		if (count == 2 | count == 3) {
    			return 1;
    		}else {
    			return 0;
    		}
    	}
    	if(grid[i][j]==0) {
    		if(count == 3) {
    			return 1;
    		}else {
    			return 0;
    		}
    	}
    	
    	return 0;
    }
    
    
    
    public void show()
    {
        pic.show();     
    }


    public static void main(String[] args)
    {
        int x = Integer.parseInt(args[0]);
        int iteration = Integer.parseInt(args[1]);
        String m = args[2];
        Random rand = new Random();
        grid = new int[x][x];
        newgrid = new int[x][x];
        
        Life picDemo = new Life(x);

        if(m.equals("R")) {
        	for(int j = 0; j < x; j++) {
        		for(int i = 0; i < x; i++) {
        			int n = rand.nextInt(2);
        			if(n==0) {
        				grid[i][j] = 1;
        			}
        			else {
        				grid[i][j] = 0;
        			}
        		}
        	}
        }
        
        if(m.equals("L")) {
        	for(int j = 0; j<x; j++) {
        		for(int i = 0; i<x; i++) {
        			grid[i][j] = 0;
        		}
        	}
        	grid[3][2] = 1;
        	grid[6][2] = 1;
        	grid[7][3] = 1;
        	grid[7][4] = 1;
        	grid[7][5] = 1;
        	grid[6][5] = 1;
        	grid[5][5] = 1;
        	grid[4][5] = 1;
        	grid[3][4] = 1;
        	
        	for(int i = 0; i<x; i++) {
        		for(int j = 0; j<x; j++) {
        			picDemo.drawCell(i, j, grid[i][j]);
        		}
        	}
        }
        
        if(m.equals("P")) {
        	for(int j = 0; j<x; j++) {
        		for(int i = 0; i<x; i++) {
        			grid[i][j] = 0;
        		}
        	}
        	grid[5][3] = 1;
        	grid[6][3] = 1;
        	grid[7][3] = 1;
        	grid[12][3] = 1;
    		grid[11][3] = 1;
    		grid[13][3] = 1;
    		grid[3][7] = 1;
    		grid[3][6] = 1;
    		grid[3][5] = 1;
    		grid[8][5] = 1;
    		grid[8][6] = 1;
    		grid[8][7] = 1;
    		grid[10][5] = 1;
    		grid[10][6] = 1;
    		grid[10][7] = 1;
    		grid[15][7] = 1;
    		grid[15][5] = 1;
    		grid[15][6] = 1;
    		grid[7][8] = 1;
    		grid[6][8] = 1;
    		grid[5][8] = 1;
    		grid[11][8] = 1;
    		grid[12][8] = 1;
    		grid[13][8] = 1;
    		
    		grid[5][10] = 1;
    		grid[6][10] = 1;
    		grid[7][10] = 1;
    		grid[12][10] = 1;
    		grid[11][10] = 1;
    		grid[13][10] = 1;
    		grid[3][11] = 1;
    		grid[3][12] = 1;
    		grid[3][13] = 1;
    		grid[8][11] = 1;
    		grid[8][12] = 1;
    		grid[8][13] = 1;
    		grid[10][11] = 1;
    		grid[10][12] = 1;
    		grid[10][13] = 1;
    		grid[15][11] = 1;
    		grid[15][12] = 1;
    		grid[15][13] = 1;
    		grid[7][15] = 1;
    		grid[6][15] = 1;
    		grid[5][15] = 1;
    		grid[11][15] = 1;
    		grid[12][15] = 1;
    		grid[13][15] = 1;
        }
        
    	for(int c = 0; c < iteration; c++) {
    		picDemo.show();
                        
            try
            {
                Thread.sleep(50);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

    		for(int j = 0; j < x; j++) {
    			for(int i = 0; i < x; i++) {
    				newgrid[i][j] = picDemo.updata(i, j);
    				picDemo.drawCell(i, j, newgrid[i][j]);
    			}
    		}
            
    		for(int j = 0; j < x; j++) {
    			for(int i = 0; i < x; i++) {
    				grid[i][j]=newgrid[i][j];
    			}
    		}
    	}    	
    }
}
