package Tools;


public class Maps
{
	protected static int i[] = new int[4];
	protected static int m[][] = new int[20][20];
	public static int[][] map(int type)
	{
		switch (type)
		{
			case 1:
				for(i[0]=0; i[0]<4; i[0]++)
					for(i[1]=3; i[1]<12; i[1]++)
						m[i[0]][i[1]] = 9;
					
				for(i[0]=7; i[0]<12; i[0]++)
					for(i[1]=5; i[1]<10; i[1]++)
						m[i[0]][i[1]] = 9;
						
				for(i[0]=15; i[0]<19; i[0]++)
					for(i[1]=3; i[1]<12; i[1]++)
						m[i[0]][i[1]] = 9;	
				
				m[0][2] = 5;
				m[0][4] = 6;
				m[0][7] = 6;
				m[0][10] = 6;
				m[0][12] = 5;
				
				m[1][2] = 6;
				m[1][4] = 6;
				m[1][10] = 6;
				m[1][12] = 6;
				
				m[2][2] = 6;
				m[2][4] = 6;
				m[2][5] = 5;
				m[2][6] = 5;
				m[2][7] = 7;
				m[2][8] = 5;
				m[2][9] = 5;
				m[2][10] = 6;
				m[2][12] = 6;
				
				m[3][2] = 6;
				m[3][12] = 6;
				
				m[4][2] = 6;
				m[4][3] = 7;
				m[4][4] = 7;
				m[4][5] = 7;
				m[4][6] = 5;
				m[4][7] = 5;
				m[4][8] = 5;
				m[4][9] = 7;
				m[4][10] = 7;
				m[4][11] = 7;
				m[4][12] = 6;
				
				m[6][6] = 7;
				m[6][7] = 7;
				m[6][8] = 7;
				
				m[7][5] = 7;
				m[7][9] = 7;
				
				m[8][4] = 5;
				m[8][10] = 5;
				
				m[9][0] = 5;
				m[9][1] = 7;
				m[9][2] = 7;
				m[9][3] = 7;
				m[9][4] = 7;
				m[9][5] = 7;
				m[9][6] = 5;
				m[9][7] = 5;
				m[9][8] = 5;
				m[9][9] = 7;
				m[9][10] = 7;
				m[9][11] = 7;
				m[9][12] = 7;
				m[9][13] = 7;
				m[9][14] = 5;
				
				m[10][4] = 5;
				m[10][10] = 5;
				
				m[11][5] = 7;
				m[11][9] = 7;
				
				m[12][6] = 7;
				m[12][7] = 7;
				m[12][8] = 7;
				
				m[14][2] = 6;
				m[14][3] = 7;
				m[14][4] = 7;
				m[14][5] = 7;
				m[14][6] = 5;
				m[14][7] = 5;
				m[14][8] = 5;
				m[14][9] = 7;
				m[14][10] = 7;
				m[14][11] = 7;
				m[14][12] = 6;
				
				m[15][2] = 6;
				m[15][12] = 6;
				
				m[16][2] = 6;
				m[16][4] = 6;
				m[16][5] = 5;
				m[16][6] = 5;
				m[16][7] = 7;
				m[16][8] = 5;
				m[16][9] = 5;
				m[16][10] = 6;
				m[16][12] = 6;
				
				m[17][2] = 6;
				m[17][4] = 6;
				m[17][10] = 6;
				m[17][12] = 6;
				
				m[18][2] = 5;
				m[18][4] = 6;
				m[18][7] = 6;
				m[18][10] = 6;
				m[18][12] = 5;
								
				break;
		}
		return m;
	}
}