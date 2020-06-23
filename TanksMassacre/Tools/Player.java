package Tools;


public class Player
{
	private int vX, vY=0;
	private int xa, ya=0;	
	private int xb=0, yb=0;
	private int ex=0, ey=0;
	private boolean Up = false, Down = false, Right = false, Left = false,
			sbUp = false, sbDown = false, sbRight = false, sbLeft = false,
			bUp = false, bDown = false, bRight = false, bLeft = false,
			Shot = false, BlockHitted = false, Hitted = false, pSpeed,
			pPower = false, pDefense = false, pVengance = false;
			
	private int d =2, sd=2;
	private int sx=0, sy=0;
	private int sex=0, sey=0;
	private int bhx=0, bhy=0;
	private int Score = 0;
	private int i[] = new int[10];
	
	public Player()
	{			
	}

//Metodos para insertar y obtener valores
	
	public boolean getBDown()
	{
	return this.bDown;
	}

	public void setBDown(boolean bDown)
	{
	this.bDown = bDown;
	}

	
	public int getBhx()
	{
	return this.bhx;
	}

	public void setBhx(int bhx)
	{
	this.bhx = bhx;
	}

	
	public int getBhy()
	{
	return this.bhy;
	}

	public void setBhy(int bhy)
	{
	this.bhy = bhy;
	}

	
	public boolean getBLeft()
	{
	return this.bLeft;
	}

	public void setBLeft(boolean bLeft)
	{
	this.bLeft = bLeft;
	}

	
	public boolean getBlockHitted()
	{
	return this.BlockHitted;
	}

	public void setBlockHitted(boolean BlockHitted)
	{
	this.BlockHitted = BlockHitted;
	}

	
	public boolean getBRight()
	{
	return this.bRight;
	}

	public void setBRight(boolean bRight)
	{
	this.bRight = bRight;
	}

	
	public boolean getBUp()
	{
	return this.bUp;
	}

	public void setBUp(boolean bUp)
	{
	this.bUp = bUp;
	}

	
	public int getD()
	{
	return this.d;
	}

	public void setD(int d)
	{
	this.d = d;
	}

	
	public boolean getDown()
	{
	return this.Down;
	}

	public void setDown(boolean Down)
	{
	this.Down = Down;
	}

	
	public int getEx()
	{
	return this.ex;
	}

	public void setEx(int ex)
	{
	this.ex = ex;
	}

	
	public int getEy()
	{
	return this.ey;
	}

	public void setEy(int ey)
	{
	this.ey = ey;
	}

	
	public boolean getHitted()
	{
	return this.Hitted;
	}

	public void setHitted(boolean Hitted)
	{
	this.Hitted = Hitted;
	}

	
	public int[] getI()
	{
	return this.i;
	}

	public void setI(int[] i)
	{
	this.i = i;
	}
	
	public int getI(int p)
	{
	return this.i[p];
	}

	public void setI(int i, int p)
	{
	this.i[p] = i;
	}

	
	public boolean getLeft()
	{
	return this.Left;
	}

	public void setLeft(boolean Left)
	{
	this.Left = Left;
	}

	
	public boolean getPDefense()
	{
	return this.pDefense;
	}

	public void setPDefense(boolean pDefense)
	{
	this.pDefense = pDefense;
	}

	
	public boolean getPPower()
	{
	return this.pPower;
	}

	public void setPPower(boolean pPower)
	{
	this.pPower = pPower;
	}

	
	public boolean getPSpeed()
	{
	return this.pSpeed;
	}

	public void setPSpeed(boolean pSpeed)
	{
	this.pSpeed = pSpeed;
	}

	
	public boolean getPVengance()
	{
	return this.pVengance;
	}

	public void setPVengance(boolean pVengance)
	{
	this.pVengance = pVengance;
	}

	
	public boolean getRight()
	{
	return this.Right;
	}

	public void setRight(boolean Right)
	{
	this.Right = Right;
	}

	
	public boolean getSbDown()
	{
	return this.sbDown;
	}

	public void setSbDown(boolean sbDown)
	{
	this.sbDown = sbDown;
	}

	
	public boolean getSbLeft()
	{
	return this.sbLeft;
	}

	public void setSbLeft(boolean sbLeft)
	{
	this.sbLeft = sbLeft;
	}

	
	public boolean getSbRight()
	{
	return this.sbRight;
	}

	public void setSbRight(boolean sbRight)
	{
	this.sbRight = sbRight;
	}

	
	public boolean getSbUp()
	{
	return this.sbUp;
	}

	public void setSbUp(boolean sbUp)
	{
	this.sbUp = sbUp;
	}

	
	public int getScore()
	{
	return this.Score;
	}

	public void setScore(int Score)
	{
	this.Score = Score;
	}

	
	public int getSd()
	{
	return this.sd;
	}

	public void setSd(int sd)
	{
	this.sd = sd;
	}

	
	public int getSex()
	{
	return this.sex;
	}

	public void setSex(int sex)
	{
	this.sex = sex;
	}

	
	public int getSey()
	{
	return this.sey;
	}

	public void setSey(int sey)
	{
	this.sey = sey;
	}

	
	public boolean getShot()
	{
	return this.Shot;
	}

	public void setShot(boolean Shot)
	{
	this.Shot = Shot;
	}

	
	public int getSx()
	{
	return this.sx;
	}

	public void setSx(int sx)
	{
	this.sx = sx;
	}

	
	public int getSy()
	{
	return this.sy;
	}

	public void setSy(int sy)
	{
	this.sy = sy;
	}

	
	public boolean getUp()
	{
	return this.Up;
	}

	public void setUp(boolean Up)
	{
	this.Up = Up;
	}

	
	public int getVX()
	{
	return this.vX;
	}

	public void setVX(int vX)
	{
	this.vX = vX;
	}

	
	public int getVY()
	{
	return this.vY;
	}

	public void setVY(int vY)
	{
	this.vY = vY;
	}

	
	public int getXa()
	{
	return this.xa;
	}

	public void setXa(int xa)
	{
	this.xa = xa;
	}

	
	public int getXb()
	{
	return this.xb;
	}

	public void setXb(int xb)
	{
	this.xb = xb;
	}

	
	public int getYa()
	{
	return this.ya;
	}

	public void setYa(int ya)
	{
	this.ya = ya;
	}

	
	public int getYb()
	{
	return this.yb;
	}

	public void setYb(int yb)
	{
	this.yb = yb;
	}
}
