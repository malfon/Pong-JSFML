import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;

public class Main
{
	//initializing window parameters
	public static float width = 800, height = 600;
	

	public static void main(String args [])
	{
		//instantiating RenderWindow
		RenderWindow Window = new RenderWindow();

		//creating window
		Window.create(new VideoMode(800, 600), "JSFML");
		Window.setFramerateLimit(30);    // I think this handles the framerate. It shouldn't be set to 1000. 30 is good enough. Max should be 60
		
		
		// Common Variables
		Vector2f PaddleVelocity = new Vector2f(0, 400);
		
		//instantiating p1
		Vector2f P1Pos = new Vector2f(10, 250);	
		RectangleShape p1 = new RectangleShape(new Vector2f(20, 100));
		p1.setPosition(P1Pos.x, P1Pos.y);
		p1.setFillColor(Color.WHITE);

		//instantiating p2
		Vector2f P2Pos = new Vector2f(770, 250);
		RectangleShape p2 = new RectangleShape(new Vector2f(20, 100));
		p2.setPosition(P2Pos.x, P2Pos.y);
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


		// The Clock of the game
		Clock clock = new Clock();
		
		
		
		//main loop
		while(Window.isOpen())
		{
			
			Time dt = clock.restart();
			
			Window.clear();
			
			for(Event event : Window.pollEvents())
			{				
			
				switch(event.type)
				{
				case CLOSED:
					Window.close();
					break;
					
				case KEY_PRESSED:
					 Input(dt, PaddleVelocity, p2, p1);
					break;

				}
				
			}


			Window.draw(ball);
			Window.draw(p1);
			Window.draw(p2);
			Window.draw(wall);
			Window.draw(sb);

			Window.display();
		}
	}
	
	
	static void Input(Time dt, Vector2f PaddVel, RectangleShape P2, RectangleShape P1)
	{
		
		
		if(Keyboard.isKeyPressed(Keyboard.Key.UP))
			P2.move(0 , -PaddVel.y * dt.asSeconds());
		if(Keyboard.isKeyPressed(Keyboard.Key.DOWN))
			P2.move(0, PaddVel.y * dt.asSeconds());
		
		if(Keyboard.isKeyPressed(Keyboard.Key.W))
			P1.move(0, -PaddVel.y * dt.asSeconds() );
		if(Keyboard.isKeyPressed(Keyboard.Key.S))
			P1.move(0, PaddVel.y * dt.asSeconds());
		
		if(P2.getPosition().y <= 3)
			P2.move(0, -PaddVel.y * dt.asSeconds() * 0);
		
	}
	
	
}
