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
import org.jsfml.system.Vector2i;

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
		Ball Pball = new Ball();
		Vector2f BallPos = new Vector2f(500, 290);
		CircleShape ball = new CircleShape(10);
		RectangleShape Box = new RectangleShape();
		ball.setPosition(BallPos.x, BallPos.y);
		
		
		Box.setSize(new Vector2f(20, 20));
		Box.setPosition(BallPos.x , BallPos.y);
		Color BBox = new Color(Color.WHITE, 80);
		Box.setFillColor(BBox);
		
		boolean Col;

		
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
			
			ball.move(1, 0);
		    Box.setPosition(ball.getPosition().x, ball.getPosition().y);
			
		  
		    Col = Collision(Box, p2);
			
			if(Col == true)
				System.out.println("Collision!");
			else
				System.out.println("No Collision");
			
			
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
		    Window.draw(Box);

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
			PaddVel.equals(0);
		
	}
	
	
	static boolean Collision(RectangleShape CBB, RectangleShape P2)
	{
		// The bounding box around the ball
		float BB_buttom = CBB.getPosition().y + CBB.getSize().y;
		float BB_left = CBB.getPosition().x;
		float BB_right = CBB.getPosition().x + CBB.getSize().x;
		float BB_top = CBB.getPosition().y;
		
		
		// The First Player
		float P2_buttom = P2.getPosition().y + P2.getSize().y;
		float P2_left = P2.getPosition().x;
		float P2_right = P2.getPosition().x + P2.getSize().x;
		float P2_top = P2.getPosition().y;
		
		if((BB_right < P2_left) || (BB_left > P2_right) || (BB_top > P2_buttom) || (BB_buttom < P2_top))
			return false;
		else
			return true;
       
		
	}

	
}
