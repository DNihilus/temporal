// GAMELIB - Gfx
/*       	   INMA Entertainment Presens    
              			   a 
			   	SMILE BROS Production
			   
			          SMILE BROS
					  
 *
 * @(#)Gfx.java	1.0		 14/09/02
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

package GameLib;

import java.awt.*;
import java.applet.*;

public class Gfx
{
	protected Component	comp;
	protected Image		offImage, offBGImage;
	protected Graphics	offG;

	public Gfx(Component component, Color color)
	{
		comp = component;
		offImage = comp.createImage(comp.size().width,
			comp.size().height);
		
		offBGImage = comp.createImage(comp.size().width, 
			comp.size().height);
		
		Graphics bgG = offBGImage.getGraphics();
		bgG.setColor(color);
		bgG.fillRect(0, 0, comp.size().width, comp.size().height);
		
		offG = offImage.getGraphics();
	}

	public Gfx(Component component, Image backImg)
	{
		comp = component;
		offImage = comp.createImage(comp.size().width,
			comp.size().height);
		offBGImage = backImg;

		offG = offImage.getGraphics();
	}


	public void setBGImage(Image backImg)
	{
		offBGImage = backImg;
	}

	public Image getBGImage()
	{
		return (offBGImage);
	}

	public void drawImage(Image img, int x, int y)
	{
		offG.drawImage(img, x, y, comp);
	}

	public void cls(Color color)
	{
		offG.setColor(color);
		offG.fillRect(0, 0, comp.size().width, comp.size().height);
	}

	public void cls(int min)
	{
		offG.drawImage(offBGImage, 0-min, 0, comp);
	}


	public void refresh()
	{
		comp.getGraphics().drawImage(offImage, 0, 0, comp);
	}

	public Image getOffImage()
	{
		return (offImage);
	}	
}
