package Controller.graphics;

import Controller.SettingGame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

	public static final int DEFAULT_SIZE = 32;
	private static final int TRANSPARENT_COLOR = 0xffff00ff;
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private SpriteSheet _sheet;

	/*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite grass1 = new Sprite(DEFAULT_SIZE, 3, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite grass2 = new Sprite(DEFAULT_SIZE, 3, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite grass3 = new Sprite(DEFAULT_SIZE, 4, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite grass4 = new Sprite(DEFAULT_SIZE, 5, 6, SpriteSheet.tiles, 32, 32);

	public static Sprite brick1 = new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite brick2 = new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite brick3 = new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite brick4 = new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 32, 32);

	public static Sprite brick1_break1 = new Sprite(DEFAULT_SIZE, 6, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite brick1_break2 = new Sprite(DEFAULT_SIZE, 6, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite brick2_break1 = new Sprite(DEFAULT_SIZE, 7, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite brick2_break2 = new Sprite(DEFAULT_SIZE, 7, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite brick3_break1 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite brick3_break2 = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite brick4_break1 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite brick4_break2 = new Sprite(DEFAULT_SIZE, 9, 8, SpriteSheet.tiles, 32, 32);

	public static Sprite wall1 = new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 32, 32);
	public static Sprite wall2 = new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite wall3 = new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite wall4 = new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 32, 32);

	public static Sprite portal = new Sprite(DEFAULT_SIZE, 5, 7, SpriteSheet.tiles, 32, 32);

	public static Sprite gift = new Sprite(DEFAULT_SIZE, 5, 8, SpriteSheet.tiles, 32, 32);
	public static Sprite gift_break1 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 32, 32);
	public static Sprite gift_break2 = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 32, 32);


	/*
	|--------------------------------------------------------------------------
	| Bomber Sprites
	|--------------------------------------------------------------------------
	 */
	// First character
	public static Sprite child_back = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite child_back1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite child_back2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite child_right = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite child_right1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite child_right2 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite child_left = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite child_left1 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite child_left2 = new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite child = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite child1 = new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite child2 = new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 32, 32);
	// Second character
	public static Sprite girl_back = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite girl_back1 = new Sprite(DEFAULT_SIZE, 4, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite girl_back2 = new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite girl_right = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite girl_right1 = new Sprite(DEFAULT_SIZE, 5, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite girl_right2 = new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite girl_left = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite girl_left1 = new Sprite(DEFAULT_SIZE, 6, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite girl_left2 = new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite girl = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite girl1 = new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite girl2 = new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 32, 32);
	// Third character
	public static Sprite boy_back = new Sprite(DEFAULT_SIZE, 8, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite boy_back1 = new Sprite(DEFAULT_SIZE, 8, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite boy_back2 = new Sprite(DEFAULT_SIZE, 8, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite boy_right = new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite boy_right1 = new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite boy_right2 = new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite boy_left = new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite boy_left1 = new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite boy_left2 = new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite boy = new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite boy1 = new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite boy2 = new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 32, 32);
	// Fourth character
	public static Sprite gamer_back = new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer_back1 = new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer_back2 = new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite gamer_right = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer_right1 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer_right2 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite gamer_left = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer_left1 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer_left2 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 32, 32);

	public static Sprite gamer = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer1 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 32, 32);
	public static Sprite gamer2 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 32, 32);
	// Five character
	public static Sprite play_back = new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite play_back1 = new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite play_back2 = new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite play_right = new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite play_right1 = new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite play_right2 = new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite play_left = new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite play_left1 = new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite play_left2 = new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite play = new Sprite(DEFAULT_SIZE, 3, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite play1 = new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite play2 = new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 32, 32);

	/*
	|--------------------------------------------------------------------------
	| Character
	|--------------------------------------------------------------------------
	 */
	//BALLOM
	public static Sprite balloom_left1 = new Sprite(DEFAULT_SIZE, 4, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite balloom_left2 = new Sprite(DEFAULT_SIZE, 4, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite balloom_left3 = new Sprite(DEFAULT_SIZE, 4, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite balloom_right1 = new Sprite(DEFAULT_SIZE, 5, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite balloom_right2 = new Sprite(DEFAULT_SIZE, 5, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite balloom_right3 = new Sprite(DEFAULT_SIZE, 5, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite balloom_dead = new Sprite(DEFAULT_SIZE, 4, 6, SpriteSheet.tiles, 32, 32);

	//ONEAL
	public static Sprite oneal_left1 = new Sprite(DEFAULT_SIZE, 12, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite oneal_left2 = new Sprite(DEFAULT_SIZE, 12, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite oneal_left3 = new Sprite(DEFAULT_SIZE, 12, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite oneal_right1 = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite oneal_right2 = new Sprite(DEFAULT_SIZE, 13, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite oneal_right3 = new Sprite(DEFAULT_SIZE, 13, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite oneal_dead = new Sprite(DEFAULT_SIZE, 12, 6, SpriteSheet.tiles, 32, 32);

	//Doll
	public static Sprite doll_left1 = new Sprite(DEFAULT_SIZE, 6, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite doll_left2 = new Sprite(DEFAULT_SIZE, 6, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite doll_left3 = new Sprite(DEFAULT_SIZE, 6, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite doll_right1 = new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite doll_right2 = new Sprite(DEFAULT_SIZE, 7, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite doll_right3 = new Sprite(DEFAULT_SIZE, 7, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite doll_dead = new Sprite(DEFAULT_SIZE, 6, 6, SpriteSheet.tiles, 32, 32);

	//Minvo
	public static Sprite minvo_left1 = new Sprite(DEFAULT_SIZE, 10, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite minvo_left2 = new Sprite(DEFAULT_SIZE, 10, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite minvo_left3 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite minvo_right1 = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite minvo_right2 = new Sprite(DEFAULT_SIZE, 11, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite minvo_right3 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 32, 32);

	//Kondoria
	public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 8, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 8, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite kondoria_right1 = new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite kondoria_right2 = new Sprite(DEFAULT_SIZE, 9, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite kondoria_right3 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 32, 32);

	/*
	|--------------------------------------------------------------------------
	| Bomb Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite bomb = new Sprite(DEFAULT_SIZE, 14, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 14, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 14, 5, SpriteSheet.tiles, 32, 32);

	/*
	|--------------------------------------------------------------------------
	| FlameSegment Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite bomb_exploded = new Sprite(DEFAULT_SIZE, 15, 3, SpriteSheet.tiles, 32, 32);
	public static Sprite bomb_exploded1 = new Sprite(DEFAULT_SIZE, 15, 4, SpriteSheet.tiles, 32, 32);
	public static Sprite bomb_exploded2 = new Sprite(DEFAULT_SIZE, 15, 5, SpriteSheet.tiles, 32, 32);

	public static Sprite explosion_vertical = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_vertical1 = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_vertical2 = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 32, 32);

	public static Sprite explosion_horizontal = new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_horizontal1 = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_horizontal2 = new Sprite(DEFAULT_SIZE, 1, 11, SpriteSheet.tiles, 32, 32);

	public static Sprite explosion_horizontal_left_last = new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_horizontal_left_last1 = new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_horizontal_left_last2 = new Sprite(DEFAULT_SIZE, 0, 11, SpriteSheet.tiles, 32, 32);

	public static Sprite explosion_horizontal_right_last = new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_horizontal_right_last1 = new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_horizontal_right_last2 = new Sprite(DEFAULT_SIZE, 2, 11, SpriteSheet.tiles, 32, 32);

	public static Sprite explosion_vertical_top_last = new Sprite(DEFAULT_SIZE, 3, 9, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_vertical_top_last1 = new Sprite(DEFAULT_SIZE, 4, 9, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_vertical_top_last2 = new Sprite(DEFAULT_SIZE, 5, 9, SpriteSheet.tiles, 32, 32);

	public static Sprite explosion_vertical_down_last = new Sprite(DEFAULT_SIZE, 3, 11, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_vertical_down_last1 = new Sprite(DEFAULT_SIZE, 4, 11, SpriteSheet.tiles, 32, 32);
	public static Sprite explosion_vertical_down_last2 = new Sprite(DEFAULT_SIZE, 5, 11, SpriteSheet.tiles, 32, 32);

	/*
	|--------------------------------------------------------------------------
	| Powerups
	|--------------------------------------------------------------------------
	 */
	public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 12, SpriteSheet.tiles, 32, 32);
	public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 12, SpriteSheet.tiles, 32, 32);
	public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 3, 12, SpriteSheet.tiles, 32, 32);
	public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 6, 12, SpriteSheet.tiles, 32, 32);

	/*
	|--------------------------------------------------------------------------
	| Player
	|--------------------------------------------------------------------------
	 */
	public static Sprite player_up;
	public static Sprite player_up_1;
	public static Sprite player_up_2;
	public static Sprite player_right;
	public static Sprite player_right_1;
	public static Sprite player_right_2;
	public static Sprite player_down;
	public static Sprite player_down_1;
	public static Sprite player_down_2;
	public static Sprite player_left;
	public static Sprite player_left_1;
	public static Sprite player_left_2;

	/*
	|--------------------------------------------------------------------------
	| Brick_Break
	|--------------------------------------------------------------------------
	 */
	public static Sprite brick_break;
	public static Sprite brick_break1;
	public static Sprite brick_break2;

	/*
	|--------------------------------------------------------------------------
	| Map
	|--------------------------------------------------------------------------
	 */
	public static Sprite grass;
	public static Sprite brick;
	public static Sprite wall;
	public static Sprite spriteNull = new Sprite(DEFAULT_SIZE, 0, 13, SpriteSheet.tiles, 32, 32);

	public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}

	public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;

		if(calc < diff) {
			return normal;
		}

		if(calc < diff * 2) {
			return x1;
		}

		return x2;
	}

	public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2;
	}

	public int getSize() {
		return SIZE;
	}

	public int getPixel(int i) {
		return _pixels[i];
	}

	public Image getFxImage() {
		WritableImage wr = new WritableImage(SIZE, SIZE);
		PixelWriter pw = wr.getPixelWriter();
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				if ( _pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
					pw.setArgb(x, y, 0);
				}
				else {
					pw.setArgb(x, y, _pixels[x + y * SIZE]);
				}
			}
		}
		Image input = new ImageView(wr).getImage();
		return input;
	}

	public static Sprite[] setEnemy(char c) {
		Sprite[] sprites = new Sprite[7];
		switch (c) {
			case '1': {
				sprites[0] = balloom_right1;
				sprites[1] = balloom_right2;
				sprites[2] = balloom_right3;
				sprites[3] = balloom_left1;
				sprites[4] = balloom_left2;
				sprites[5] = balloom_left3;
				sprites[6] = balloom_dead;
			}
			break;
			case '2': {
				sprites[0] = oneal_right1;
				sprites[1] = oneal_right2;
				sprites[2] = oneal_right3;
				sprites[3] = oneal_left1;
				sprites[4] = oneal_left2;
				sprites[5] = oneal_left3;
				sprites[6] = oneal_dead;
			}
			break;
			case '3': {
				sprites[0] = doll_right1;
				sprites[1] = doll_right2;
				sprites[2] = doll_right3;
				sprites[3] = doll_left1;
				sprites[4] = doll_left2;
				sprites[5] = doll_left3;
				sprites[6] = doll_dead;
			}
			break;
			case '4': {
				sprites[0] = minvo_right1;
				sprites[1] = minvo_right2;
				sprites[2] = minvo_right3;
				sprites[3] = minvo_left1;
				sprites[4] = minvo_left2;
				sprites[5] = minvo_left3;
				sprites[6] = minvo_dead;
			}
			break;
			default: {
				sprites[0] = kondoria_right1;
				sprites[1] = kondoria_right2;
				sprites[2] = kondoria_right3;
				sprites[3] = kondoria_left1;
				sprites[4] = kondoria_left2;
				sprites[5] = kondoria_left3;
				sprites[6] = kondoria_dead;
			}
			break;
		}
		return sprites;
	}

	public static void setPlayer() {
		switch (SettingGame.player) {
			case 1:
			{
				player_up = child_back;
				player_up_1 = child_back1;
				player_up_2 = child_back2;
				player_right = child_right;
				player_right_1 = child_right1;
				player_right_2 = child_right2;
				player_down = child;
				player_down_1 = child1;
				player_down_2 = child2;
				player_left = child_left;
				player_left_1 = child_left1;
				player_left_2 = child_left2;
			}
			break;
			case 2:
			{
				player_up = girl_back;
				player_up_1 = girl_back1;
				player_up_2 = girl_back2;
				player_right = girl_right;
				player_right_1 = girl_right1;
				player_right_2 = girl_right2;
				player_down = girl;
				player_down_1 = girl1;
				player_down_2 = girl2;
				player_left = girl_left;
				player_left_1 = girl_left1;
				player_left_2 = girl_left2;
			}
			break;
			case 3:
			{
				player_up = boy_back;
				player_up_1 = boy_back1;
				player_up_2 = boy_back2;
				player_right = boy_right;
				player_right_1 = boy_right1;
				player_right_2 = boy_right2;
				player_down = boy;
				player_down_1 = boy1;
				player_down_2 = boy2;
				player_left = boy_left;
				player_left_1 = boy_left1;
				player_left_2 = boy_left2;
			}
			break;
			case 4:
			{
				player_up = gamer_back;
				player_up_1 = gamer_back1;
				player_up_2 = gamer_back2;
				player_right = gamer_right;
				player_right_1 = gamer_right1;
				player_right_2 = gamer_right2;
				player_down = gamer;
				player_down_1 = gamer1;
				player_down_2 = gamer2;
				player_left = gamer_left;
				player_left_1 = gamer_left1;
				player_left_2 = gamer_left2;
			}
			break;
			case 5:
			{
				player_up = play_back;
				player_up_1 = play_back1;
				player_up_2 = play_back2;
				player_right = play_right;
				player_right_1 = play_right1;
				player_right_2 = play_right2;
				player_down = play;
				player_down_1 = play1;
				player_down_2 = play2;
				player_left = play_left;
				player_left_1 = play_left1;
				player_left_2 = play_left2;
			}
			break;
		}
	}

	public static void setMap() {
		switch (SettingGame.typeMap) {
			case 1:
			{
				grass = grass1;
				brick = brick1;
				wall = wall1;
				brick_break = brick1;
				brick_break1 = brick1_break1;
				brick_break2 = brick1_break2;
			}
			break;
			case 2:
			{
				grass = grass2;
				brick = brick2;
				wall = wall2;
				brick_break = brick2;
				brick_break1 = brick2_break1;
				brick_break2 = brick2_break2;
			}
			break;
			case 3:
			{
				grass = grass3;
				brick = brick3;
				wall = wall3;
				brick_break = brick3;
				brick_break1 = brick3_break1;
				brick_break2 = brick3_break2;
			}
			break;
			case 4:
			{
				grass = grass4;
				brick = brick4;
				wall = wall4;
				brick_break = brick4;
				brick_break1 = brick4_break1;
				brick_break2 = brick4_break2;
			}
			break;
		}
	}
}
