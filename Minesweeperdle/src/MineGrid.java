public class MineGrid {
    private boolean[][] mines;
    private int[][] tiles;
    public MineGrid(int dimensions, double mineChance){
        mines = new boolean[dimensions][dimensions];
        tiles = new int[dimensions][dimensions];
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                mines[i][j] = Math.random() <= mineChance;
            }
        }
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                int num = 0;
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (i+k >= 0 && i+k<dimensions && l+j >= 0 && l+j<dimensions){
                            if (mines[i+k][l+j]) {
                                num++;
                            }
                        }
                    }
                }
                tiles[i][j] = num;
            }
        }
    }
    public int get(int x, int y){
        return tiles[x][y];
    }

    @Override
    public String toString() {
        String s= "";
        for (int i = 0; i < mines[0].length; i++) {
            for (int j = 0; j < mines.length; j++) {
                s += tiles[j][i];
                if (mines[j][i]){
                    s += "x";
                }
                else{
                    s += "-";
                }
                s+=" ";
            }
            s = s.substring(0,s.length()-1);
            s += "\n";
        }
        s = s.substring(0,s.length()-1);
        return s;
    }
}
