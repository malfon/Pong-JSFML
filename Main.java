import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.system.Vector2f;

public class Main
{
	//initializing window parameters
	public static float width = 800, height = 600;

	public static void main(String args [])
	{
		//instantiating RenderWindow
		RenderWindow window = new RenderWindow();

		//instantiating p1
		RectangleShape p1 = new RectangleShape(new Vector2f(20, 100));

		p1.setPosition(10, (height/2)-50);
		float p1x = p1.getPosition().x;
		float p1y = p1.getPosition().y;
		p1.setFillColor(Color.WHITE);

		//instantiating p2
		RectangleShape p2 = new RectangleShape(new Vector2f(20, 100));

		p2.setPosition(width-30, (height/2)-50);
		float p2x = p2.getPosition().x;
		float p2y = p2.getPosition().y;
		p2.setFillColor(Color.WHITE);

		//instantiating ball
		CircleShape ball = new CircleShape(10);

		ball.setPosition((width/2)-10, (height/2)-10);
		float ballx = ball.getPosition().x;
		float bally = ball.getPosition().y;
		float ballspeed = 0.5f;
		boolean left = true;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		ball.setFillColor(Color.WHITE);
		
		//instantiating wall
		RectangleShape wall = new RectangleShape(new Vector2f(10, 550));
		
		wall.setPosition((width/2)-5, 50);
		wall.setFillColor(new Color(255, 255, 255, 150));
		
		//instantiating scoreboard
		RectangleShape sb = new RectangleShape(new Vector2f(100, 50));
		
		sb.setPosition((width/2)-50, 0);
		sb.setFillColor(new Color(255, 255, 255));

		//creating window
		window.create(new VideoMode(800, 600), "JSFML");
		window.setFramerateLimit(1000);

		//main loop
		while(window.isOpen())
		{
			window.clear();

			window.draw(ball);
			window.draw(p1);
			window.draw(p2);
			window.draw(wall);
			window.draw(sb);

			window.display();
			
			for(Event event : window.pollEvents())
			{				
				switch(event.type)
				{
				case CLOSED:
					window.close();
					break;
				case KEY_PRESSED:
					KeyEvent keyEvent = event.asKeyEvent();

					switch(keyEvent.key)
					{
					case W:
						if(p1y >= 0 && p1y <= height-100)
						{
							p1y = p1.getPosition().y-5;
						}
						else
						{
							switch((int)p1y)
							{
							case 0:
								p1y = 0;
								break;
							case 500:
								p1y = 500;
								break;
							}
						}
						p1.setPosition(new Vector2f(p1.getPosition().x, p1y));
						break;
					case S:
						p1y = p1.getPosition().y+5;
						p1.setPosition(new Vector2f(p1.getPosition().x, p1y));
						break;
					case UP:
						p2y = p2.getPosition().y-5;
						p2.setPosition(new Vector2f(p2.getPosition().x, p2y));
						break;
					case DOWN:
						p2y = p2.getPosition().y+5;
						p2.setPosition(new Vector2f(p2.getPosition().x, p2y));
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}

			//reset
			if(ballx <= -20 || ballx >= width+20)
			{
				ballx = (width/2)-10;
				bally = (height/2)-10;
			}
			
			//updating motion
			if(left)
			{
				ballx -= ballspeed;
				ball.setPosition(new Vector2f(ballx, bally));
			}
			else if(right)
			{
				ballx += ballspeed;
				ball.setPosition(new Vector2f(ballx, bally));
			}
			else if(up)
			{
				bally += ballspeed;
				ball.setPosition(new Vector2f(ballx, bally));
			}
			else if(down)
			{
				bally -= ballspeed;
				ball.setPosition(new Vector2f(ballx, bally));
			}
			
			//updating horizontal direction
			if(ballx <= 30 && (bally >= p1y && bally <= p1y+100))
			{
				ballx = 30;
				right = true;
				left = false;
			}
			else if(ballx >= p2.getPosition().x-20 && (bally >= p2y && bally <= p2y+100))
			{
				ballx = width-50;
				left = true;
				right = false;
			}
			
			//updating vertical direction (not working at the moment)
			if(bally < 0)
			{
				bally = 0;
				down = true;
				up = false;
			}
			else if(bally > height)
			{
				bally = height-20;
				up = true;
				down = false;
			}
		}
	}
}
