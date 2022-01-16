package gameloop;

import gameWorld.GameWorld;
import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Main
{
	public static void main(String[] args)
	{
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, ImagePaths.ISAAC);
		GameWorld world = new GameWorld(isaac, 100);
		initializeDisplay();

		boolean gameWin = false;
		// Main loop of the game
		while (!world.gameOver() && !gameWin)
		{
			processNextStep(world);
			gameWin = world.isGameWin();
		}

		if(gameWin){
			StdDraw.clear();
			StdDraw.picture(0.5,0.5, ImagePaths.WIN_SCREEN,1,1);
			StdDraw.show();
		} else {
			StdDraw.clear();
			StdDraw.picture(0.5,0.5, ImagePaths.LOSE_SCREEN,1,1);
			StdDraw.show();
		}


	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.ifHeroHitDoor();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
