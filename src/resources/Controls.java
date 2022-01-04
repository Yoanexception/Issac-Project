package resources;

import libraries.Keybinding;

import java.security.Key;

public class Controls
{
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');

	public static int giveCoin = Keybinding.keycodeOf('o');
	public static int killMonster = Keybinding.keycodeOf('k');
	public static int isInvincible = Keybinding.keycodeOf('i');
	public static int superSpeed = Keybinding.keycodeOf('l');
	public static int superDamage = Keybinding.keycodeOf('p');

	public static int shootUp = Keybinding.keycodeOf(Keybinding.SpecialKeys.UP);
	public static int shootDown = Keybinding.keycodeOf(Keybinding.SpecialKeys.DOWN);
	public static int shootLeft = Keybinding.keycodeOf(Keybinding.SpecialKeys.LEFT);
	public static int shootRight = Keybinding.keycodeOf(Keybinding.SpecialKeys.RIGHT);
}
