/*       	   INMA Entertainment Presens    
              			   a 
			   	SMILE BROS Production
			   
			          Tank's Massacre
					  
 *
 * @(#)TanksMassacre.java	1.1		 6/10/02
 *
 * Copyright (c) 2002 INMA Entertainment, Inc. All Rights Reserved.
 *
 * INMA grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to INMA.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. INMA AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL INMA OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */


//package TanksMassacre;

import java.applet.Applet;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.*;
import GameLib.*;
import Tools.*;
import java.applet.AudioClip;



public class TanksMassacre extends Applet 
implements KeyListener, Runnable
{
		//Gfx de la librería GameLib
	Gfx gfx;
		//imágenes
	Image img[] = new Image[45];
		//clips de audio
	AudioClip ad[] = new AudioClip[18];
	
	int j[] = new int[10];
	int x[] = new int[20];
	int y[] = new int[20];
	int x1 = 0, y1 = 0;
		//variable que representa el mapa
	int z[][] = new int[20][20];
		//variable que representa el status del mapa
	int zs[][] = new int[20][20];
		//Contadores
	int counter[] ={0,0,0,0,0,0};
		//variables de los powerUps
	int powerUp = 0, pUX = 0, pUY = 0, pUXE = 0, pUYE = 0;
	boolean pUp = false, pUpD = false, editing = false;
	
		//para saber si el jugador 3 y 4 están activos
	boolean player3IsActive = false, player4IsActive = false;
	
		//El administrador de Imágenes
	MediaTracker mt1 = new MediaTracker(this);
		//El thread principal del juego
	Thread thread1;
		//Instanciar la clase Player de la librería Tools
	Player p1 = new Player();
	Player p2 = new Player();
	Player p3 = new Player();
	Player p4 = new Player();
	
	
		//--------------------INIT---------------------
	public void init()
	{
		newGame();
		
//---------IMAGES--------

		img[0] = getImage(getCodeBase(), "Resources/backGround.jpg");
		img[1] = getImage(getCodeBase(), "Resources/brick.jpg");
		img[2] = getImage(getCodeBase(), "Resources/metal.jpg");
		img[3] = getImage(getCodeBase(), "Resources/water.jpg");
		img[4] = getImage(getCodeBase(), "Resources/grass.jpg");
//player 1
		img[5] = getImage(getCodeBase(), "Resources/player1Up.jpg");
		img[6] = getImage(getCodeBase(), "Resources/player1Down.jpg");
		img[7] = getImage(getCodeBase(), "Resources/player1Right.jpg");
		img[8] = getImage(getCodeBase(), "Resources/player1Left.jpg");
//player 2
		img[9] = getImage(getCodeBase(), "Resources/player2Up.jpg");
		img[10] = getImage(getCodeBase(), "Resources/player2Down.jpg");
		img[11] = getImage(getCodeBase(), "Resources/player2Right.jpg");
		img[12] = getImage(getCodeBase(), "Resources/player2Left.jpg");
//player 3
		img[13] = getImage(getCodeBase(), "Resources/player3Up.jpg");
		img[14] = getImage(getCodeBase(), "Resources/player3Down.jpg");
		img[15] = getImage(getCodeBase(), "Resources/player3Right.jpg");
		img[16] = getImage(getCodeBase(), "Resources/player3Left.jpg");
//player 4
		img[17] = getImage(getCodeBase(), "Resources/player4Up.jpg");
		img[18] = getImage(getCodeBase(), "Resources/player4Down.jpg");
		img[19] = getImage(getCodeBase(), "Resources/player4Right.jpg");
		img[20] = getImage(getCodeBase(), "Resources/player4Left.jpg");
		
		img[21] = getImage(getCodeBase(), "Resources/shot.jpg");
		img[22] = getImage(getCodeBase(), "Resources/brick2.jpg");

//Explosion
		for(j[0] = 1; j[0] < 9; j[0]++)
			img[22 + j[0]] = getImage(getCodeBase(), "Resources/ex"+j[0]+".jpg");
		
//Reliquias	
		img[31] = getImage(getCodeBase(), "Resources/pSpeed.jpg");
		img[32] = getImage(getCodeBase(), "Resources/pPower.jpg");
		img[33] = getImage(getCodeBase(), "Resources/pDefense.jpg");
		img[34] = getImage(getCodeBase(), "Resources/pVengance.jpg");
		
//Venganza
		for(j[0] = 1; j[0] < 4; j[0]++)
			img[34 + j[0]] = getImage(getCodeBase(), "Resources/V"+j[0]+".jpg");
		
		
//----------SOUNDS-----------

		ad[0] = getAudioClip(getCodeBase(), "Resources/explosion1.wav");
		ad[1] = getAudioClip(getCodeBase(), "Resources/explosion2.wav");
		ad[2] = getAudioClip(getCodeBase(), "Resources/explosion3.wav");
//-----
		ad[3] = getAudioClip(getCodeBase(), "Resources/hello.wav");
		ad[4] = getAudioClip(getCodeBase(), "Resources/bye.wav");
//-----
		ad[5] = getAudioClip(getCodeBase(), "Resources/fire1.wav");
		ad[6] = getAudioClip(getCodeBase(), "Resources/fire2.wav");
//-----
		ad[7] = getAudioClip(getCodeBase(), "Resources/laugh1.wav");
		ad[8] = getAudioClip(getCodeBase(), "Resources/laugh2.wav");
		ad[9] = getAudioClip(getCodeBase(), "Resources/menace1.wav");
		ad[10] = getAudioClip(getCodeBase(), "Resources/menace2.wav");
		ad[11] = getAudioClip(getCodeBase(), "Resources/menace3.wav");
//-----
		ad[12] = getAudioClip(getCodeBase(), "Resources/startRound.wav");
		ad[13] = getAudioClip(getCodeBase(), "Resources/suddenDeath.wav");
//-----
		ad[14] = getAudioClip(getCodeBase(), "Resources/scalesOfJustice.wav");
		ad[15] = getAudioClip(getCodeBase(), "Resources/collect1.wav");
		ad[16] = getAudioClip(getCodeBase(), "Resources/collect2.wav");
		ad[17] = getAudioClip(getCodeBase(), "Resources/reliqPop.wav");
		
		
			//Adherir las imágenes al Administrador
		for(j[0] = 0; j[0] < 38; j[0]++)
			mt1.addImage(img[j[0]],(j[0] + 1));
		
		try
		{
				//Esperar a que carguen todas las imágenes
			mt1.waitForAll();
		}	
		catch(InterruptedException e)
		{
		}
		
			//con ésta operación obtenemos la localización exacta de
			//cada cuadro del mapa en pixeles
		for(j[0] = 0; j[0] <20 ; j[0]++)
		{
			x[j[0]] = j[0]*36;
			y[j[0]] = j[0]*36;
		}
			//Generar un mapa al azar
			/*********************
				En caso de querer jugar en mapas prediseñados por el usuario
				es sólo cuestión de poner la siguiente instrucción como
				comentario y entonces quitar la propiedad de comentario de
				las siguientes 8 líneas, con lo que se llama el método
				Maps.map() y el número que se lleva es el número de mapa
				que se desea obtener, 
			*********************/
		generateMap();
		
		/*z = Maps.map(1);*/
		for(j[0]=0; j[0]<19;j[0]++)
			for(j[1] = 0; j[1]<15; j[1]++)
			{
				if (z[j[0]][j[1]] == 5)
					zs[j[0]][j[1]] = 3;
			}
		
		


		
			//Ponemos el fondo
		gfx= new Gfx(this, img[0]);
			//El Sonido de Inicio de batalla
		ad[Misc.rnd(12,14)].play();
			//y adherimos el KeyListener
		addKeyListener(this);
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (editing)
				{
					x1 = (int)Math.floor(e.getX()/36);
					y1 = (int)Math.floor(e.getY()/36);
					if(x1 >= 0 && y1 >= 0)
						z[x1][y1]++;
					if(z[x1][y1]==Ctes.BRICK2) z[x1][y1] = Ctes.GRASS;
					else if(z[x1][y1]>Ctes.GRASS) z[x1][y1] = Ctes.BRICK - 1;
					repaint();
				}
			}
			
		});

	}
	
	public void newGame()
	{
			//La colocación inicial del jugador 2
		p1.setXa(0);
		p1.setYa(0);
		p1.setXb(0);
		p1.setYb(0);
		p1.setEx(0);
		p1.setEy(0);
			//La colocación inicial del jugador 2
		p2.setXa(18);
		p2.setYa(14);
		p2.setXb(18);
		p2.setYb(14);
		p2.setEx(648);
		p2.setEy(504);
		
			//La colocación inicial del jugador 3
		p3.setXa(18);
		p3.setXb(18);
		p3.setEx(648);
		p3.setYa(0);
		p3.setYb(0);
		p3.setEy(0);
		
			//La colocación inicial del jugador 4
		p4.setYa(14);
		p4.setYb(14);
		p4.setEy(504);
		p4.setXa(0);
		p4.setXb(0);
		p4.setEx(0);
		
			//Mapa Limpio
		for(j[0]=0; j[0]<19;j[0]++)
			for(j[1] = 0; j[1]<15; j[1]++)
				z[j[0]][j[1]] = Ctes.BRICK - 1;
		for(j[0]=0; j[0]<19;j[0]++)
			for(j[1] = 0; j[1]<15; j[1]++)
				zs[j[0]][j[1]] = 0;
		repaint();
		


	}
	public void editMap()
	{
	}
		//-------------------GENERATE MAP----------------
	public void generateMap()
	{
			//Con esto se logra generar un mapa al azar
		for(j[0]=0; j[0]<19;j[0]++)
			for(j[1] = 0; j[1]<15; j[1]++)
			{
				z[j[0]][j[1]] = Misc.rnd(Ctes.FREE,Ctes.GRASS);	
					//En dado caso de que el obstáculo a dibujar sea un ladrillo
				if (z[j[0]][j[1]] == Ctes.BRICK)
						//La vida del ladrillo es de 3
					zs[j[0]][j[1]] = 3;
					//Esto es sólo para cambiar de ladrillo dañado a pasto
				else if (z[j[0]][j[1]] == Ctes.BRICK2)
					z[j[0]][j[1]] = Ctes.GRASS;
			}
				//Con esto	borramos los posibles obstáculos que estén en las 
				//colocaciones iniciales de los jugadores
		z[0][0] = Ctes.FREE;
		z[18][14] = Ctes.FREE;
		z[0][14] = Ctes.FREE;
		z[18][0] = Ctes.FREE;
	}
	
		//--------------UPDATE---------------
	public void update(Graphics g)
	{
			//Llamámos al Método paint()
		paint(g);
	}
	
		//-----------------PAINT-------------
	public void paint(Graphics g)
	{
		gfx.cls(0);
		
			//Con ëste for se dibuja el mapa
		for(j[0]=0; j[0]<19;j[0]++)
			for(j[1] = 0; j[1]<15; j[1]++)
				if (z[j[0]][j[1]] >= Ctes.BRICK)
					switch (z[j[0]][j[1]])
					{
						case Ctes.BRICK: //El ladrillo normal
							gfx.drawImage(img[1],x[j[0]],y[j[1]]);
							break;
						case Ctes.METAL: //El metal
							gfx.drawImage(img[2],x[j[0]],y[j[1]]);
							break;
						case Ctes.WATER: //El agua
							gfx.drawImage(img[3],x[j[0]],y[j[1]]);
							break;
						case Ctes.BRICK2: //El ladrillo dañado
							gfx.drawImage(img[22],x[j[0]],y[j[1]]);
							break;
						case Ctes.GRASS: //El pasto
							gfx.drawImage(img[4],x[j[0]],y[j[1]]);
							break;
					}
if(!editing)
{
					
//---player 1------
			//Si el jugador es golpeado
		if (p1.getHitted()) 
			if (p1.getPVengance())
				if (p1.getI(2) < 4)
					gfx.drawImage(img[35], p1.getEx(), p1.getEy());
				else if (p1.getI(2) < 6)
					gfx.drawImage(img[36], p1.getEx(), p1.getEy());
				else
					gfx.drawImage(img[37], p1.getEx() - img[5].getWidth(this)*3/2, p1.getEy() - img[5].getHeight(this)*3/2);
			else
				gfx.drawImage(img[22 + p1.getI(2)], p1.getEx(), p1.getEy());
			//Si nó, dependiedo de la dirección se dibuja el tanque	
		else if (p1.getD() == 1)
			gfx.drawImage(img[5],p1.getEx(),p1.getEy());
		else if (p1.getD() == 2)
			gfx.drawImage(img[6],p1.getEx(),p1.getEy());	
		else if (p1.getD() == 3)
			gfx.drawImage(img[7],p1.getEx(),p1.getEy());		
		else if (p1.getD() == 4)
			gfx.drawImage(img[8],p1.getEx(),p1.getEy());	
//---player 2------	
		if (p2.getHitted())
			if (p2.getPVengance())
				if (p2.getI(2) < 4)
					gfx.drawImage(img[35], p2.getEx(), p2.getEy());
				else if (p2.getI(2) < 6)
					gfx.drawImage(img[36], p2.getEx(), p2.getEy());
				else
					gfx.drawImage(img[37], p2.getEx() - img[5].getWidth(this)*3/2, p2.getEy() - img[5].getHeight(this)*3/2);
			else
				gfx.drawImage(img[22 + p2.getI(2)], p2.getEx(), p2.getEy());
		else if (p2.getD() == 1)
			gfx.drawImage(img[9],p2.getEx(), p2.getEy());
		else if (p2.getD() == 2)
			gfx.drawImage(img[10],p2.getEx(), p2.getEy());	
		else if (p2.getD() == 3)
			gfx.drawImage(img[11],p2.getEx(), p2.getEy());		
		else if (p2.getD() == 4)
			gfx.drawImage(img[12],p2.getEx(), p2.getEy());
//---player 3------
if (player3IsActive)
{
		if (p3.getHitted())
			if (p3.getPVengance())
				if (p3.getI(2) < 4)
					gfx.drawImage(img[35], p3.getEx(), p3.getEy());
				else if (p3.getI(2) < 6)
					gfx.drawImage(img[36], p3.getEx(), p3.getEy());
				else
					gfx.drawImage(img[37], p3.getEx() - img[5].getWidth(this)*3/2, p3.getEy() - img[5].getHeight(this)*3/2);
			else
				gfx.drawImage(img[22 + p3.getI(2)], p3.getEx(), p3.getEy());
		else if (p3.getD() == 1)
			gfx.drawImage(img[13],p3.getEx(), p3.getEy());
		else if (p3.getD() == 2)
			gfx.drawImage(img[14],p3.getEx(), p3.getEy());	
		else if (p3.getD() == 3)
			gfx.drawImage(img[15],p3.getEx(), p3.getEy());		
		else if (p3.getD() == 4)
			gfx.drawImage(img[16],p3.getEx(), p3.getEy());	
}
//---player 4------	
if (player4IsActive)
{
		if (p4.getHitted())
			if (p4.getPVengance())
				if (p4.getI(2) < 4)
					gfx.drawImage(img[35], p4.getEx(), p4.getEy());
				else if (p4.getI(2) < 6)
					gfx.drawImage(img[36], p4.getEx(), p4.getEy());
				else
					gfx.drawImage(img[37], p4.getEx() - img[5].getWidth(this)*3/2, p4.getEy() - img[5].getHeight(this)*3/2);
			else
				gfx.drawImage(img[22 + p4.getI(2)], p4.getEx(), p4.getEy());
		else if (p4.getD() == 1)
			gfx.drawImage(img[17],p4.getEx(), p4.getEy());
		else if (p4.getD() == 2)
			gfx.drawImage(img[18],p4.getEx(), p4.getEy());	
		else if (p4.getD() == 3)
			gfx.drawImage(img[19],p4.getEx(), p4.getEy());		
		else if (p4.getD() == 4)
			gfx.drawImage(img[20],p4.getEx(), p4.getEy());	
}
					
//---player 1------
				//Dibujar el Disparo del jugador
		if (p1.getShot())
			gfx.drawImage(img[21],p1.getSx(), p1.getSy());
//---player 2------	
		if (p2.getShot())
			gfx.drawImage(img[21],p2.getSx(), p2.getSy());
//---player 3------
if (player3IsActive)
		if (p3.getShot())
			gfx.drawImage(img[21],p3.getSx(), p3.getSy());
//---player 4------	
if (player4IsActive)
		if (p4.getShot())
			gfx.drawImage(img[21],p4.getSx(), p4.getSy());
		
			//Dibujar las reliquias
		if (pUpD)
		{
			switch (powerUp)
			{
				case Ctes.SPEED:
					gfx.drawImage(img[31],pUXE,pUYE);
					break;
				case Ctes.POWER:
					gfx.drawImage(img[32],pUXE,pUYE);
					break;
				case Ctes.DEFENSE:
					gfx.drawImage(img[33],pUXE,pUYE);
					break;
				case Ctes.VENGANCE:
					gfx.drawImage(img[34],pUXE,pUYE);
					break;
			}
		}
}				
		gfx.refresh();
	}
	
	
	//----------------KEY PRESSED----------------
	public void keyPressed(KeyEvent e)
	{
	  if(editing)
	  {
	  	if (e.getKeyCode() == KeyEvent.VK_F10)
		{
			editing = false;
			for(j[0]=0; j[0]<19;j[0]++)
				for(j[1] = 0; j[1]<15; j[1]++)
				{
					if (z[j[0]][j[1]] == 5)
						zs[j[0]][j[1]] = 3;
				}
			z[0][0] = Ctes.FREE;
			z[18][14] = Ctes.FREE;
			z[0][14] = Ctes.FREE;
			z[18][0] = Ctes.FREE;

			repaint();
		}
	  }
	  else
	  {

		if (e.getKeyCode() == KeyEvent.VK_F9)
		{
			
			editing = true;
			newGame();
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_F5)
		{
			
			newGame();
			generateMap();
			repaint();
		}
	  //-----player 1-----
			//Cada uno de los siguientes if's identifica la tecla presionada
			//y efectúa la acción establecida
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			p1.setLeft(true);
			p1.setD(Ctes.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			p1.setRight(true);
			p1.setD(Ctes.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			p1.setUp(true);
			p1.setD(Ctes.UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			p1.setDown(true);	
			p1.setD(Ctes.DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_CONTROL && !p1.getShot())
		{
				//Sonido de disparo
			ad[5].play();
				//El jugador ha disparado
			p1.setShot(true);	
				//La dirección del disparo
			p1.setSd(p1.getD());
				//las variables que detectan colisión del disparo
			p1.setSbUp(false);
			p1.setSbDown(false);
			p1.setSbRight(false);
			p1.setSbLeft(false);
				//Las coordenadas iniciales del disparo
			p1.setSx(p1.getEx() + img[5].getWidth(this)/2 - img[21].getWidth(this)/2);
			p1.setSy(p1.getEy() + img[5].getHeight(this)/2 - img[21].getHeight(this)/2);
			
		}
//-----player 2-----

		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			p2.setLeft(true);
			p2.setD(Ctes.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			p2.setRight(true);
			p2.setD(Ctes.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W)
		{
			p2.setUp(true);	
			p2.setD(Ctes.UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			p2.setDown(true);	
			p2.setD(Ctes.DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT && !p2.getShot())
		{
			ad[5].play();
			p2.setShot(true);	
			p2.setSd(p2.getD());
			p2.setSbUp(false);
			p2.setSbDown(false);
			p2.setSbRight(false);
			p2.setSbLeft(false);
			p2.setSx(p2.getEx() + img[9].getWidth(this)/2 - img[21].getWidth(this)/2);
			p2.setSy(p2.getEy() + img[9].getHeight(this)/2 - img[21].getHeight(this)/2);
		}
		
//-----player 3-----

		if (e.getKeyCode() == KeyEvent.VK_F && player3IsActive)
		{
			p3.setLeft(true);
			p3.setD(Ctes.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_H && player3IsActive)
		{
			p3.setRight(true);
			p3.setD(Ctes.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_T && player3IsActive)
		{
			p3.setUp(true);	
			p3.setD(Ctes.UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_G && player3IsActive)
		{
			p3.setDown(true);	
			p3.setD(Ctes.DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !p3.getShot() && player3IsActive)
		{
			ad[5].play();
			p3.setShot(true);	
			p3.setSd(p3.getD());
			p3.setSbUp(false);
			p3.setSbDown(false);
			p3.setSbRight(false);
			p3.setSbLeft(false);
			p3.setSx(p3.getEx() + img[9].getWidth(this)/2 - img[21].getWidth(this)/2);
			p3.setSy(p3.getEy() + img[9].getHeight(this)/2 - img[21].getHeight(this)/2);
		}
		
		
//-----player 4-----

		if (e.getKeyCode() == KeyEvent.VK_I && player4IsActive)
		{
			p4.setLeft(true);
			p4.setD(Ctes.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_P && player4IsActive)
		{
			p4.setRight(true);
			p4.setD(Ctes.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_9 && player4IsActive)
		{
			p4.setUp(true);
			p4.setD(Ctes.UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_O && player4IsActive)
		{
			p4.setDown(true);	
			p4.setD(Ctes.DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_0 && !p4.getShot() && player4IsActive)
		{
			ad[5].play();
			p4.setShot(true);	
			p4.setSd(p4.getD());
			p4.setSbUp(false);
			p4.setSbDown(false);
			p4.setSbRight(false);
			p4.setSbLeft(false);
			p4.setSx(p4.getEx() + img[9].getWidth(this)/2 - img[21].getWidth(this)/2);
			p4.setSy(p4.getEy() + img[9].getHeight(this)/2 - img[21].getHeight(this)/2);
		}

//---New Incommers---
		if (e.getKeyCode() == KeyEvent.VK_F11 && !player4IsActive)
		{
			ad[3].play();
			if (!player3IsActive)
				player3IsActive = true;
			else
				player4IsActive = true;	
		}
		if (e.getKeyCode() == KeyEvent.VK_F12 && player3IsActive)
		{
			ad[4].play();
			if (player4IsActive)
			{
					//El Hitted se lo he puesto para prevenir que los
					//jugadores desaparezcan y aparezcan con alevosía
				p4.setHitted(true);
				player4IsActive = false;
			}
			else
			{
				p3.setHitted(true);
				player3IsActive = false;
			}	
		}
	  }
		
	}
	
	public void keyReleased(KeyEvent e)
	{
	if(!editing)
	{
//----Player 1---

		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			p1.setLeft(false);
			if (p1.getUp())
				p1.setD(Ctes.UP);
			else if (p1.getDown())
				p1.setD(Ctes.DOWN);
			else if (p1.getRight())
				p1.setD(Ctes.RIGHT);	
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			p1.setRight(false);
			if (p1.getUp())
				p1.setD(Ctes.UP);
			else if (p1.getDown())
				p1.setD(Ctes.DOWN);
			else if (p1.getLeft())
				p1.setD(Ctes.LEFT);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			p1.setUp(false);
			if (p1.getLeft())
				p1.setD(Ctes.LEFT);
			else if (p1.getDown())
				p1.setD(Ctes.DOWN);
			else if (p1.getRight())
				p1.setD(Ctes.RIGHT);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			p1.setDown(false);	
			if (p1.getUp())
				p1.setD(Ctes.UP);
			else if (p1.getLeft())
				p1.setD(Ctes.LEFT);
			else if (p1.getRight())
				p1.setD(Ctes.RIGHT);
		}
		
//----Player 2---

		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			p2.setLeft(false);
			if (p2.getUp())
				p2.setD(Ctes.UP);
			else if (p2.getDown())
				p2.setD(Ctes.DOWN);
			else if (p2.getRight())
				p2.setD(Ctes.RIGHT);
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			p2.setRight(false);
			if (p2.getUp())
				p2.setD(Ctes.UP);
			else if (p2.getDown())
				p2.setD(Ctes.DOWN);
			else if (p2.getLeft())
				p2.setD(Ctes.LEFT);
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			p2.setUp(false);	
			if (p2.getLeft())
				p2.setD(Ctes.LEFT);
			else if (p2.getDown())
				p2.setD(Ctes.DOWN);
			else if (p2.getRight())
				p2.setD(Ctes.RIGHT);
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			p2.setDown(false);
			if (p2.getUp())
				p2.setD(Ctes.UP);
			else if (p2.getLeft())
				p2.setD(Ctes.LEFT);
			else if (p2.getRight())
				p2.setD(Ctes.RIGHT);
		}
//----Player 3---
		if (player3IsActive)
		{
			if (e.getKeyCode() == KeyEvent.VK_F)
			{
				p3.setLeft(false);
				if (p3.getUp())
					p3.setD(Ctes.UP);
				else if (p3.getDown())
					p3.setD(Ctes.DOWN);
				else if (p3.getRight())
					p3.setD(Ctes.RIGHT);	
			}
			if (e.getKeyCode() == KeyEvent.VK_H)
			{
				p3.setRight(false);
				if (p3.getUp())
					p3.setD(Ctes.UP);
				else if (p3.getDown())
					p3.setD(Ctes.DOWN);
				else if (p3.getLeft())
					p3.setD(Ctes.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_T)
			{	
				p3.setUp(false);
				if (p3.getLeft())
					p3.setD(Ctes.LEFT);
				else if (p3.getDown())
					p3.setD(Ctes.DOWN);
				else if (p3.getRight())
					p3.setD(Ctes.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_G)
			{
				p3.setDown(false);	
				if (p3.getUp())
					p3.setD(Ctes.UP);
				else if (p3.getLeft())
					p3.setD(Ctes.LEFT);
				else if (p3.getRight())
					p3.setD(Ctes.RIGHT);
			}
		}
//----Player 4---
		if (player4IsActive)
		{

			if (e.getKeyCode() == KeyEvent.VK_I)
			{
				p4.setLeft(false);
				if (p4.getUp())
					p4.setD(Ctes.UP);
				else if (p4.getDown())
					p4.setD(Ctes.DOWN);
				else if (p4.getRight())
					p4.setD(Ctes.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_P)
			{
				p4.setRight(false);
				if (p4.getUp())
					p4.setD(Ctes.UP);
				else if (p4.getDown())
					p4.setD(Ctes.DOWN);
				else if (p4.getLeft())
					p4.setD(Ctes.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_9)
			{
				p4.setUp(false);	
				if (p4.getLeft())
					p4.setD(Ctes.LEFT);
				else if (p4.getDown())
					p4.setD(Ctes.DOWN);
				else if (p4.getRight())
					p4.setD(Ctes.RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_O)
			{
				p4.setDown(false);
				if (p4.getUp())
					p4.setD(Ctes.UP);
				else if (p4.getLeft())
					p4.setD(Ctes.LEFT);
				else if (p4.getRight())
					p4.setD(Ctes.RIGHT);
			}
		}
	}
	}
	
	public void keyTyped(KeyEvent e)
	{

	}
	
	//-----------RUN----------------
	public void run()
	{
			//Declarar un Thread paralelo al inicial
		Thread thread2 = Thread.currentThread();
		while (thread1 == thread2)
		{
		if(!editing)
		{
				//Obtener las posiciones exactas en pixeles de cada 
				//uno de los jugadores y de sus disparos correspondientes
	//----player 1----
			p1.setXa((int)(Math.floor((p1.getEx() + 1) / 36) ));
			p1.setYa((int)(Math.floor((p1.getEy() + 1) / 36) ));
			
			p1.setSex((int)(Math.floor((p1.getSx() + 1) / 36) ));
			p1.setSey((int)(Math.floor((p1.getSy() + 1) / 36) ));
	//----player 2----
			p2.setXa((int)(Math.floor((p2.getEx() + 1) / 36) ));
			p2.setYa((int)(Math.floor((p2.getEy() + 1) / 36) ));
			
			p2.setSex((int)(Math.floor((p2.getSx() + 1) / 36) ));
			p2.setSey((int)(Math.floor((p2.getSy() + 1) / 36) ));
	//----player 3----
			if (player3IsActive)
			{
				p3.setXa((int)(Math.floor((p3.getEx() + 1) / 36) ));
				p3.setYa((int)(Math.floor((p3.getEy() + 1) / 36) ));
				
				p3.setSex((int)(Math.floor((p3.getSx() + 1) / 36) ));
				p3.setSey((int)(Math.floor((p3.getSy() + 1) / 36) ));
			}
	//----player 4----
			if (player4IsActive)
			{
				p4.setXa((int)(Math.floor((p4.getEx() + 1) / 36) ));
				p4.setYa((int)(Math.floor((p4.getEy() + 1) / 36) ));
				
				p4.setSex((int)(Math.floor((p4.getSx() + 1) / 36) ));
				p4.setSey((int)(Math.floor((p4.getSy() + 1) / 36) ));
			}
		
				//colocar los jugadores
			colocar();				
				//detectar si se puede o no mover el tanque en alguna dirección
			block();
				//Mover el tanque
			mover();
				//Disparar
			disparar();
			
				//Detectar si el jugador ha golpeado un bloque o si el
				//jugador mismo ha sido golpeado
//----Player 1---
			p1 = bHitted(p1);
			p1 = pHitted(p1);

//----Player 2---
			p2 = bHitted(p2);
			p2 = pHitted(p2);

//----Player 3---
			if (player3IsActive)
			{
				p3 = bHitted(p3);
				p3 = pHitted(p3);
			}

//----Player 4---
			if (player4IsActive)
			{
				p4 = bHitted(p4);
				p4 = pHitted(p4);
			}
			
				//función para los powerUps
			powerUps();
			
				//para hacer que el dibujo del PowerUp esté parpadeando
			if (pUp)
			{
				counter[4]++;
				if (counter[4]<11)
				{
					counter[4] = 0;
					if(pUpD)
						pUpD = false;
					else
						pUpD = true;	
				}
			}
				
				//Llamar nuevamente al método paint()
			repaint();
			
		}	
				//El tiempo entre un fotograma 	y otro
			try
			{
				if(!editing)
					thread1.sleep(50);
				else
					thread1.sleep(200);
			}
			catch(InterruptedException e)
			{
			}
			
		
		}
	}
	
	//----------------BLOCK HITTED--------------
	public Player bHitted(Player pa)
	{
			//Si alguno de los jugadores ha golpeado un bloque de ladrillo
			//Ëste es dañado y disminuido en su vida, despues de tres
			//golpes es destruido
		if (pa.getBlockHitted())
		{
			pa.setI(pa.getI(1) + 1, 1);
			if (pa.getI(1) >= 3)
			{
				pa.setI(0,1);
				pa.setBlockHitted(false);
				if (zs[pa.getBhx()][pa.getBhy()] > 0)
					z[pa.getBhx()][pa.getBhy()] = Ctes.BRICK;
			}
		}
		return pa;
	}
	
	//-----------------PLAYER HITTED-----------
	public Player pHitted(Player pa)
	{
			//Si el jugador ha sido golpeado
		if (pa.getHitted())
		{
				//Este contador nos sirve para llevar a cabo la explosión
			pa.setI(pa.getI(2) + 1, 2);
			if (pa.getI(2) > 8)
			{
				if (pa.getPVengance())
				{
					pa.setVX((int)(Math.floor((pa.getEx() + 1 + img[5].getWidth(this)/2) / 36) ));
					pa.setVY((int)(Math.floor((pa.getEy() + 1 + img[5].getHeight(this)/2) / 36) ));
				}
					//Y se busca una nueva posisión desocupada para el jugador
				while (true)
				{
					pa.setXa(Misc.rnd(0,19));
					pa.setYa(Misc.rnd(0,15));
					if (z[pa.getXa()][pa.getYa()] < 5)
					{
							//Se coloca el tanque
						pa.setEx(pa.getXa() * 36);
						pa.setEy(pa.getYa() * 36);
						pa.setShot(false);
							//Se muestra la puntuación
						if (!player3IsActive)
							showStatus("1Player  "+p1.getScore()+"     2Player  "+p2.getScore());
						else if (!player4IsActive)
							showStatus("1Player  "+p1.getScore()+"     2Player  "+p2.getScore()+"     3Player  "+p3.getScore());
						else 
							showStatus("1Player  "+p1.getScore()+"     2Player  "+p2.getScore()+"     3Player  "+p3.getScore()+"     4Player  "+p4.getScore());	
						break;
					}
				}
					//Si tenía la reliquia de venganza je je je... 
				if (pa.getPVengance())
					pa.setScore(justice(pa.getVX(), pa.getVY(), pa.getScore()));
					
				//Se le quita los powerUps
				pa.setPSpeed(false);
				pa.setPPower(false);
				pa.setPDefense(false);
				pa.setPVengance(false);	
					//el contador se inicia
				pa.setI(0,2);
				pa.setHitted(false);
				counter[5] = 0;
				
			}
		}
		return pa;
	}
	
	public int justice(int vonx, int vony, int pts)
	{
			//Matar todo en un radio de 2 alrededor de la explosión...
			// ja, que lindo! kill, kill, kill
			
			//Todos los if's son para prevenir un 
			//ArrayIndexOutOfBoundsException
		pts = kill(vonx, vony, pts);
		if (vonx > 0)
			pts = kill(vonx - 1, vony, pts);
		if (vonx > 1)	
			pts = kill(vonx - 2, vony, pts);
		if (vonx > 2)	
			pts = kill(vonx - 3, vony, pts);	
		if (vonx < 18)	
			pts = kill(vonx + 1, vony, pts);
		if (vonx < 17)	
			pts = kill(vonx + 2, vony, pts);
		if (vonx < 16)	
			pts = kill(vonx + 3, vony, pts);	
		if (vony > 0)	
			pts = kill(vonx, vony - 1, pts);
		if (vony > 1)	
			pts = kill(vonx, vony - 2, pts);
		if (vony > 2)	
			pts = kill(vonx, vony - 3, pts);	
		if (vony < 18)	
			pts = kill(vonx, vony + 1, pts);
		if (vony < 17)	
			pts = kill(vonx, vony + 2, pts);
		if (vony < 16)	
			pts = kill(vonx, vony + 3, pts);	
		if (vonx > 0 && vony > 0)	
			pts = kill(vonx - 1, vony - 1, pts);
		if (vonx > 1 && vony > 0)	
			pts = kill(vonx - 2, vony - 1, pts);
		if (vonx > 0 && vony > 1)	
			pts = kill(vonx - 1, vony - 2, pts);
				
		if (vonx < 18 && vony > 0)	
			pts = kill(vonx + 1, vony - 1, pts);
		if (vonx < 17 && vony > 0)	
			pts = kill(vonx + 2, vony - 1, pts);
		if (vonx < 18 && vony > 1)	
			pts = kill(vonx + 1, vony - 2, pts);
				
		if (vonx > 0 && vony < 18)	
			pts = kill(vonx - 1, vony + 1, pts);
		if (vonx > 1 && vony < 18)	
			pts = kill(vonx - 2, vony + 1, pts);
		if (vonx > 0 && vony < 17)	
			pts = kill(vonx - 1, vony + 2, pts);
				
		if (vonx < 18 && vony < 18)		
			pts = kill(vonx + 1, vony + 1, pts);
		if (vonx < 17 && vony < 18)		
			pts = kill(vonx + 2, vony + 1, pts);
		if (vonx < 18 && vony < 17)		
			pts = kill(vonx + 1, vony + 2, pts);	
			
			
		return pts;	
	}
	
	public int kill(int konx, int kony, int pt)
	{
			//Ver si le dá al jugador 1
		if ((p1.getXa() == konx && p1.getYa() == kony || p1.getXb() == konx && p1.getYa() == kony || p1.getXa() == konx && p1.getYb() == kony || p1.getXb() == konx && p1.getYb() == kony) && !p1.getHitted())
		{
				//si le dió, pero tenía la reliquia de defensa =(
			if (p1.getPDefense())
				p1.setPDefense(false);
				//Si le dió y no tenía la reliquia de defensa! =D	
			else 
			{
					//El jugador 1 es golpeado
				p1.setHitted(true);
					//Y la puntuación aumentada
				pt++;
					//y un "ja ja ja"
				if (counter[5] == 0)
					ad[14].play();
				counter[5] = 2;
			}
		}
			//Y lo mismo para los otros 3 jugadores
		if ((p2.getXa() == konx && p2.getYa() == kony || p2.getXb() == konx && p2.getYa() == kony || p2.getXa() == konx && p2.getYb() == kony || p2.getXb() == konx && p2.getYb() == kony) && !p2.getHitted())
		{
			if (p2.getPDefense())
				p2.setPDefense(false);
			else 
			{
				p2.setHitted(true);
				pt++;
				if (counter[5] == 0)
					ad[14].play();
				counter[5] = 2;
			}
		}
		if ((p3.getXa() == konx && p3.getYa() == kony || p3.getXb() == konx && p3.getYa() == kony || p3.getXa() == konx && p3.getYb() == kony || p3.getXb() == konx && p3.getYb() == kony) && !p3.getHitted() && player3IsActive)
		{
			if (p3.getPDefense())
				p3.setPDefense(false);
			else 
			{
				p3.setHitted(true);
				pt++;
				if (counter[5] == 0)
					ad[14].play();
				counter[5] = 2;
			}
		}
		if ((p4.getXa() == konx && p4.getYa() == kony || p4.getXb() == konx && p4.getYa() == kony || p4.getXa() == konx && p4.getYb() == kony || p4.getXb() == konx && p4.getYb() == kony) && !p4.getHitted()  && player4IsActive)
		{
			if (p4.getPDefense())
				p4.setPDefense(false);
			else 
			{
				p4.setHitted(true);
				pt++;
				if (counter[5] == 0)
					ad[14].play();
				counter[5] = 2;
			}	
		}
		return pt;
	}
	
	public void start()
	{
		if (thread1 == null)
		{
				//Se inicializa el nuevo Thread
			thread1 = new Thread(this);
			thread1.start();
		}
	}
	
//----------BLOCK-----------
	public void block()
	{
			//Detectar si el jugador tiene posibilidad de movimiento en
			//determinadas direcciones
		p1 = pBlock(p1);
		p2 = pBlock(p2);
		
		if (player3IsActive)
			p3 = pBlock(p3);
			
		if (player4IsActive)
			p4 = pBlock(p4);
	}
	
	public Player pBlock(Player pa)
	{
			//Si el jugador se encuentra en el límite izquierdo del mapa
		if (pa.getEx() <= 0) 
				//Se bloquea su mov a la izquierda
			pa.setBLeft(true);
				//el siguiete 'if' sirve para prevenir el error
				//IndexOutOfBoundsException provocada por buscar
				//en la posición -1 de un arreglo
		else if (pa.getXa() != 0)
				// se analiza si existe algun obstáculo a la izq del jugador 
			if(z[pa.getXa() - 1][pa.getYa()] > 4 && z[pa.getXa() - 1][pa.getYa()] < 9 && pa.getEx() - ((pa.getXa())* 36)  <= 1
				|| z[pa.getXa() - 1][pa.getYb()] > 4 && z[pa.getXa() - 1][pa.getYb()] < 9 && pa.getEx() - ((pa.getXa() )* 36) <= 1)
					// de ser asi es bloqueado su mov a la izquierda
				pa.setBLeft(true);	
			else
				pa.setBLeft(false);	
		else
			pa.setBLeft(false);	
			
			//Si el jugador se encuentra en el extremo derecho del mapa
		if (pa.getEx() >= 648)
			pa.setBRight(true);
		else if (pa.getXa() != 18) 
				//Si hay algún onstáculo a la derecha
			if(z[pa.getXa() + 1][pa.getYa()] > 4 && z[pa.getXa() + 1][pa.getYa()] < 9
				|| z[pa.getXa() + 1][pa.getYb()] > 4 && z[pa.getXa() + 1][pa.getYb()] < 9)
				pa.setBRight(true);	
			else
				pa.setBRight(false);	
		else
			pa.setBRight(false);
			
			//Si el jugador se encuentra en la parte superior del mapa
		if (pa.getEy() <= 0)
			pa.setBUp(true);
		else if (pa.getYa() != 0) 
				//Si hay algún obstáculo hacia arriba
			if (z[pa.getXa()][pa.getYa() - 1] > 4 && z[pa.getXa()][pa.getYa() - 1] < 9 && pa.getEy() - ((pa.getYa())* 36) <= 1
				|| z[pa.getXb()][pa.getYa() - 1] > 4 && z[pa.getXb()][pa.getYa() - 1] < 9 && pa.getEy() - ((pa.getYa())* 36) <= 1)
				pa.setBUp(true);
			else
				pa.setBUp(false);
		else
			pa.setBUp(false);	
			
			//Si se encuentra en la parte inferior del mapa
		if (pa.getEy() >= 504)
			pa.setBDown(true);
		else if (pa.getYa() != 14)
				//Si hay algún obstáculo hacia abajo
			if (z[pa.getXa()][pa.getYa() + 1] > 4 && z[pa.getXa()][pa.getYa() + 1] < 9
				|| z[pa.getXb()][pa.getYa() + 1] > 4 && z[pa.getXb()][pa.getYa() + 1] < 9)
				pa.setBDown(true);
			else
				pa.setBDown(false);	
		else
			pa.setBDown(false);
			
			//Si el jugador ha una reliquia
		if ((pa.getXa() == pUX && pa.getYa() == pUY || pa.getXb() == pUX && pa.getYa() == pUY || pa.getXa() == pUX && pa.getYb() == pUY || pa.getXb() == pUX && pa.getYb() == pUY) && pUp)
		{
			switch (powerUp)
			{
				case Ctes.SPEED:
					pa.setPSpeed(true);
					break;
				case Ctes.POWER:
					pa.setPPower(true);
					break;
				case Ctes.DEFENSE:
					pa.setPDefense(true);
					break;
				case Ctes.VENGANCE:
					pa.setPVengance(true);
					break;
			}
			pUp = false;
			pUpD = false;
			counter[0] = 0;
			counter[3] = 0;
			ad[Misc.rnd(15,17)].play();
		}
		return pa;	
	}
	public void colocar()
	{
			//Se colocan a los jugadores
		p1 = pAlloc(p1);
		p2 = pAlloc(p2);
		
		if (player3IsActive)
			p3 = pAlloc(p3);

		if (player4IsActive)
			p4 = pAlloc(p4);
	}
	public Player pAlloc(Player pa)
	{
			/************************
				En El juego los participantes tienen dos posiciones en x
				y dos en y, debido a que habrá momentos en los que se encuentren
				abarcando dos cuadritos de espacio ya sean verticales
				u horizontales, con estas instrucciónes se adecúan las
				dos posiciones en x y y de acuerdo a la posición exacta
				en pixeles del tanque en cuestión
			************************/
		if ((pa.getEx() - (pa.getXa()*36)) > 5)
			pa.setXb(pa.getXa() + 1);
		else if ((pa.getEx() - (pa.getXa()*36)) < -5)
			pa.setXb(pa.getXa() - 1);
		else
			pa.setXb(pa.getXa());
				
		if ((pa.getEy() - (pa.getYa()*36)) > 5)
			pa.setYb(pa.getYa() + 1);
		else if ((pa.getEy() - (pa.getYa()*36)) < -5)
			pa.setYb(pa.getYa() - 1);
		else
			pa.setYb(pa.getYa());
			
		return pa;	
	}
	
	//---------------MOVER---------
	public void mover()
	{
			//Recorrer los tanques 2 pixeles en la dirección indicada
			//O 3 en caso de poseer la reliquia de velocidad
		p1 = mov(p1);
		p2 = mov(p2);
		
		if (player3IsActive)
			p3 = mov(p3);	
			
		if (player4IsActive)
			p4 = mov(p4);
	}
	
	public Player mov(Player pa)
	{
		if (pa.getUp() && !pa.getBUp())
			if (pa.getPSpeed())
				pa.setEy(pa.getEy() - 3);
			else	
				pa.setEy(pa.getEy() - 2);
		if (pa.getDown() && !pa.getBDown())
			if (pa.getPSpeed())
				pa.setEy(pa.getEy() + 3);
			else	
				pa.setEy(pa.getEy() + 2);	
		if (pa.getRight() && !pa.getBRight())
			if (pa.getPSpeed())
				pa.setEx(pa.getEx() + 3);
			else	
				pa.setEx(pa.getEx() + 2);	
		if (pa.getLeft() && !pa.getBLeft())
			if (pa.getPSpeed())
				pa.setEx(pa.getEx() - 3);
			else	
				pa.setEx(pa.getEx() - 2);
		
		return pa;	
	}
	
	//----------BLOCK SHOT--------------
	public void blocks()
	{
		//Primero se detecta colisión del disparo con algún contrincante
//----player 1-----

		if (p1.getShot())
		{
			if (p1.getSx() > p2.getEx() && p1.getSx() < (p2.getEx() + img[9].getWidth(this)) && p1.getSy() > p2.getEy() && p1.getSy() < (p2.getEy() + img[9].getHeight(this)))
			{
					//Se detiene el disparo despues del impacto
				p1.setShot(false);
				
					//Si no tiene la reliquia de defensa
				if (!p2.getPDefense())
				{
						//El jugador es golpeado
					p2.setHitted(true);
						//y se suma la puntuación
					p1.setScore(p1.getScore() + 1);	
						//Sonido de explosión
					ad[Misc.rnd(0,3)].play();
						//Y Sonido de amenaza
					ad[Misc.rnd(7,12)].play();
				}
					//Si si tiene la reliquia de defensa
				else
						//Se la quitamos
					p2.setPDefense(false);
			}
			else if (p1.getSx() > p3.getEx() && p1.getSx() < (p3.getEx() + img[9].getWidth(this)) && p1.getSy() > p3.getEy() && p1.getSy() < (p3.getEy() + img[9].getHeight(this)) && player3IsActive)
			{
				p1.setShot(false);
				if (!p3.getPDefense())
				{
					p3.setHitted(true);
					p1.setScore(p1.getScore() + 1);	
					ad[Misc.rnd(0,3)].play();
					ad[Misc.rnd(7,12)].play();
				}
				else
					p3.setPDefense(false);
			}
			else if (p1.getSx() > p4.getEx() && p1.getSx() < (p4.getEx() + img[9].getWidth(this)) && p1.getSy() > p4.getEy() && p1.getSy() < (p4.getEy() + img[9].getHeight(this)) && player4IsActive)
			{
				p1.setShot(false);
				if (!p4.getPDefense())
				{
					p4.setHitted(true);
					p1.setScore(p1.getScore() + 1);	
					ad[Misc.rnd(0,3)].play();
					ad[Misc.rnd(7,12)].play();					
				}
				else
					p4.setPDefense(false);
			}
				
				//Si no se impacta con algún jugador, se pasa a detectar
				//El impacto contra paredes u obstáculos
			else 
				p1 = sBlock(p1);		

		}
			//Y ASi con los 4 jugadores
//----player 2-----
		if (p2.getShot())
		{
			if (p2.getSx() > p1.getEx() && p2.getSx() < (p1.getEx() + img[9].getWidth(this)) && p2.getSy() > p1.getEy() && p2.getSy() < (p1.getEy() + img[9].getHeight(this)))
			{
				p2.setShot(false);
				if (!p1.getPDefense())
				{
					p1.setHitted(true);	
					p2.setScore(p2.getScore() + 1);
					ad[Misc.rnd(0,3)].play();
					ad[Misc.rnd(7,12)].play();
				}
				else
					p1.setPDefense(false);
			}
			else if (p2.getSx() > p3.getEx() && p2.getSx() < (p3.getEx() + img[9].getWidth(this)) && p2.getSy() > p3.getEy() && p2.getSy() < (p3.getEy() + img[9].getHeight(this)) && player3IsActive)
			{
				p2.setShot(false);
				if (!p3.getPDefense())
				{
					p3.setHitted(true);	
					p2.setScore(p2.getScore() + 1);
					ad[Misc.rnd(0,3)].play();
					ad[Misc.rnd(7,12)].play();
				}
				else
					p3.setPDefense(false);
			}
			else if (p2.getSx() > p4.getEx() && p2.getSx() < (p4.getEx() + img[9].getWidth(this)) && p2.getSy() > p4.getEy() && p2.getSy() < (p4.getEy() + img[9].getHeight(this)) && player4IsActive)
			{
				p2.setShot(false);
				if (!p4.getPDefense())
				{
					p4.setHitted(true);	
					p2.setScore(p2.getScore() + 1);
					ad[Misc.rnd(0,3)].play();
					ad[Misc.rnd(7,12)].play();
				}
				else
					p4.setPDefense(false);
			}	
			
			else 
				p2 = sBlock(p2);

		}
//----player 3-----
		if (player3IsActive)
		{
			if (p3.getShot())
			{
				if (p3.getSx() > p2.getEx() && p3.getSx() < (p2.getEx() + img[9].getWidth(this)) && p3.getSy() > p2.getEy() && p3.getSy() < (p2.getEy() + img[9].getHeight(this)))
				{
					p3.setShot(false);
					if (!p2.getPDefense())
					{
						p2.setHitted(true);	
						p3.setScore(p3.getScore() + 1);
						ad[Misc.rnd(0,3)].play();
						ad[Misc.rnd(7,12)].play();
					}
					else
						p2.setPDefense(false);
				}
				else if (p3.getSx() > p1.getEx() && p3.getSx() < (p1.getEx() + img[9].getWidth(this)) && p3.getSy() > p1.getEy() && p3.getSy() < (p1.getEy() + img[9].getHeight(this)))
				{
					p3.setShot(false);
					if (!p1.getPDefense())
					{
						p1.setHitted(true);	
						p3.setScore(p3.getScore() + 1);
						ad[Misc.rnd(0,3)].play();
						ad[Misc.rnd(7,12)].play();
					}
					else
						p1.setPDefense(false);
				}
				else if (p3.getSx() > p4.getEx() && p3.getSx() < (p4.getEx() + img[9].getWidth(this)) && p3.getSy() > p4.getEy() && p3.getSy() < (p4.getEy() + img[9].getHeight(this)) && player4IsActive)
				{
					p3.setShot(false);
					if (!p4.getPDefense())
					{
						p4.setHitted(true);	
						p3.setScore(p3.getScore() + 1);
						ad[Misc.rnd(0,3)].play();
						ad[Misc.rnd(7,12)].play();
					}
					else
						p4.setPDefense(false);
				}
			
				else 
					p3 = sBlock(p3);
	
			}
		}
//----player 4-----
		if (player4IsActive)
		{
			if (p4.getShot())
			{
				if (p4.getSx() > p1.getEx() && p4.getSx() < (p1.getEx() + img[9].getWidth(this)) && p4.getSy() > p1.getEy() && p4.getSy() < (p1.getEy() + img[9].getHeight(this)))
				{
					p4.setShot(false);
					if (!p1.getPDefense())
					{
						p1.setHitted(true);	
						p4.setScore(p4.getScore() + 1);
						ad[Misc.rnd(0,3)].play();
						ad[Misc.rnd(7,12)].play();
					}
					else
						p1.setPDefense(false);
				}
				else if (p4.getSx() > p3.getEx() && p4.getSx() < (p3.getEx() + img[9].getWidth(this)) && p4.getSy() > p3.getEy() && p4.getSy() < (p3.getEy() + img[9].getHeight(this)))
				{
					p4.setShot(false);
					if (!p3.getPDefense())
					{
						p3.setHitted(true);	
						p4.setScore(p4.getScore() + 1);
						ad[Misc.rnd(0,3)].play();
						ad[Misc.rnd(7,12)].play();
					}
					else
						p3.setPDefense(false);
				}
				else if (p4.getSx() > p2.getEx() && p4.getSx() < (p2.getEx() + img[9].getWidth(this)) && p4.getSy() > p2.getEy() && p4.getSy() < (p2.getEy() + img[9].getHeight(this)))
				{
					p4.setShot(false);
					if (!p2.getPDefense())
					{
						p2.setHitted(true);	
						p4.setScore(p4.getScore() + 1);
						ad[Misc.rnd(0,3)].play();
						ad[Misc.rnd(7,12)].play();
					}
					else
						p2.setPDefense(false);	
				}
				else 
					p4 = sBlock(p4);
			}
		}
	}
	
	public Player sBlock(Player pa)
	{
			//Primero se divide el método de acuerdo a la dirección
			//del disparo
		if (pa.getSd() == Ctes.UP)
		{
				//Si choca contra la pared superior
			if (pa.getSy() <= 0) 
				pa.setSbUp(true);
			else if (pa.getSey() != 0) 
					//O contra algún obstaculo
				if (z[pa.getSex()][pa.getSey() - 1] > 4 && z[pa.getSex()][pa.getSey() - 1] < 7 && pa.getSy() - ((pa.getSey())* 36)  <= 6)
				{
						//El disparo es bloqueado
					pa.setSbUp(true);	
						//Y desaparece
					pa.setShot(false);
						//de acuerdo al obstáculo impactado, si és una pared
						//de ladrillo
					if (z[pa.getSex()][pa.getSey() - 1] == Ctes.BRICK || z[pa.getSex()][pa.getSey() - 1] == Ctes.BRICK2)
					{
							//El ladrillo es golpeado
						pa.setBlockHitted(true);
						pa.setBhx(pa.getSex());
						pa.setBhy(pa.getSey() - 1);
							//Y dañado
						zs[pa.getSex()][pa.getSey() - 1] -= 1;
						z[pa.getSex()][pa.getSey() - 1] = Ctes.BRICK2;
						if (zs[pa.getSex()][pa.getSey() - 1] <= 0)
								//Posiblemente destruido
							z[pa.getSex()][pa.getSey() - 1] = 0;					
					}	
				}
				else
					pa.setSbUp(false);	
			else
				pa.setSbUp(false);
		}
			//Y se realiza el mismo estudio anterior con las 4 direcciones
			//Posibles del disparo en cuestión
		else if (pa.getSd() == Ctes.DOWN)
		{
			if (pa.getSy() >= 540 - img[21].getHeight(this)) 
				pa.setSbDown(true);
			else if (pa.getSey() != 14) 
				if(z[pa.getSex()][pa.getSey() + 1] > 4 && z[pa.getSex()][pa.getSey() + 1] < 7 && pa.getSy() - ((pa.getSey())* 36)  >= 34 - img[21].getHeight(this))
				{
					pa.setShot(false);
					pa.setSbDown(true);	
					if (z[pa.getSex()][pa.getSey() + 1] == Ctes.BRICK || z[pa.getSex()][pa.getSey() + 1] == Ctes.BRICK2)
					{
						pa.setBlockHitted(true);
						pa.setBhx(pa.getSex());
						pa.setBhy(pa.getSey() + 1);
					
						zs[pa.getSex()][pa.getSey() + 1] -= 1;
						z[pa.getSex()][pa.getSey() + 1] = Ctes.BRICK2;
						if (zs[pa.getSex()][pa.getSey() + 1] <= 0)
							z[pa.getSex()][pa.getSey() + 1] = 0;
					}
				}	
				else
					pa.setSbDown(false);	
			else
				pa.setSbDown(false);
		}
		else if (pa.getSd() == Ctes.RIGHT)
		{
			if (pa.getSx() >= 684 - img[21].getWidth(this)) 
				pa.setSbRight(true);
			else if (pa.getSex() != 18) 
				if(z[pa.getSex() + 1][pa.getSey()] > 4 && z[pa.getSex() + 1][pa.getSey()] < 7 && pa.getSx() - ((pa.getSex())* 36)  >= 34 - img[21].getWidth(this))
				{
					pa.setShot(false);
					pa.setSbRight(true);	
					if (z[pa.getSex() + 1][pa.getSey()] == Ctes.BRICK || z[pa.getSex() + 1][pa.getSey()] == Ctes.BRICK2)
					{
						pa.setBlockHitted(true);
						pa.setBhx(pa.getSex() + 1);
						pa.setBhy(pa.getSey());
					
						zs[pa.getSex() + 1][pa.getSey()] -= 1;
						z[pa.getSex() + 1][pa.getSey()] = Ctes.BRICK2;
						if (zs[pa.getSex() + 1][pa.getSey()] <= 0)
							z[pa.getSex() + 1][pa.getSey()] = 0;
					}
				}	
				else
					pa.setSbRight(false);	
			else
				pa.setSbRight(false);
		}
		else if (pa.getSd() == Ctes.LEFT)
		{
			if (pa.getSx() <= 0) 
				pa.setSbLeft(true);
			else if (pa.getSex() != 0) 
				if(z[pa.getSex() - 1][pa.getSey()] > 4 && z[pa.getSex() - 1][pa.getSey()] < 7 && pa.getSx() - ((pa.getSex())* 36)  <= 6)
				{
					pa.setShot(false);
					pa.setSbLeft(true);	
					if (z[pa.getSex() - 1][pa.getSey()] == Ctes.BRICK || z[pa.getSex() - 1][pa.getSey()] == Ctes.BRICK2)
					{
						pa.setBlockHitted(true);
						pa.setBhx(pa.getSex() - 1);
						pa.setBhy(pa.getSey());
					
						zs[pa.getSex() - 1][pa.getSey()] -= 1;
						z[pa.getSex() - 1][pa.getSey()] = Ctes.BRICK2;
						if (zs[pa.getSex() - 1][pa.getSey()] <= 0)
							z[pa.getSex() - 1][pa.getSey()] = 0;
					}
				}	
				else
					pa.setSbLeft(false);	
			else
				pa.setSbLeft(false);
		}
		return pa;
	}
	
	//--------------DISPARAR-------------	
	public void disparar()
	{
			// si Los jugadores disparan
		p1 = pShot(p1);
		p2 = pShot(p2);

		if (player3IsActive)
			p3 = pShot(p3);
	
		if (player4IsActive)
			p4 = pShot(p4);
	}
	
	//---------------PLAYER SHOT-----------
	public Player pShot(Player pa)
	{
		if (pa.getShot())
		{
				//Se detecta si el disparo es bloqueado
			blocks();
				
				//Si no esta bloqueado se mueve 5 pixeles o 7 en caso de
				//tener la reliquia de poder
			if (pa.getSd() == Ctes.UP && !pa.getSbUp())
				if (pa.getPPower())
					pa.setSy(pa.getSy() - 7);
				else	
					pa.setSy(pa.getSy() - 5);
			else if (pa.getSd() == Ctes.DOWN && !pa.getSbDown())
				if (pa.getPPower())
					pa.setSy(pa.getSy() + 7);
				else	
					pa.setSy(pa.getSy() + 5);
			else if (pa.getSd() == Ctes.RIGHT && !pa.getSbRight())
				if (pa.getPPower())
					pa.setSx(pa.getSx() + 7);
				else	
					pa.setSx(pa.getSx() + 5);
			else if (pa.getSd() == Ctes.LEFT && !pa.getSbLeft())
				if (pa.getPPower())
					pa.setSx(pa.getSx() - 7);
				else	
					pa.setSx(pa.getSx() - 5);
			
				//Si está bloqueado, el disparo desaparece
			if (pa.getSbUp() || pa.getSbDown() || pa.getSbRight() || pa.getSbLeft()) 
			{
				pa.setShot(false);
				pa.setI(0,0);
				
			}
			pa.setI(pa.getI(0) + 1,0);
			
		}
		return pa;
	}
	
	//-----------POWER UPS--------------
	public void powerUps()
	{
			//Contadores
		if (counter[0] == 0)
		{
			if (counter[1] == 0)
			{
					//Tiempo de espera para la aparición de una reliquia
					//y como en un segundo caben 20 fotogramas, el numero de
					//segundos deseado, se multiplica por 20
				counter[2] = Misc.rnd(7*20,15*20);
				counter[1]++;
			}
			else if (counter[1] < counter[2])
			{
				counter[1]++;
			}
			else 
			{
				counter[0] = 1;
				counter[1] = 0;
					//Power Up que aparecerá
				powerUp = Misc.rnd(Ctes.SPEED,(Ctes.VENGANCE + 1));
				pUp = true;
				pUpD = true;
				ad[17].play();
					//Se coloca en algún espacio vacío
				while (true)
				{
					pUX = Misc.rnd(0,19);
					pUY = Misc.rnd(0,15);
					if (z[pUX][pUY] < Ctes.BRICK)
					{
						pUXE= pUX * 36;
						pUYE= pUY * 36;
						break;
					}
				}
			}
		}
		
		//Y este es el tiempo que la reliquia se mantendrá 
		//en el mapa
		else if (counter[0] == 1)
		{
			counter[3]++;
			if (counter[3] > 300)
			{
				counter[3] = 0;
				pUp = false;
				powerUp = 0;
				counter[0] = 0;
			}
		}
	}
}
