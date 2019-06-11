

public abstract class Collidable{

	private int x, y;
	private int width, height;
	//public boolean visibility;

	public Collidable(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//visibility = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void moveLeft(){
		x -= 5;
	}

	public void moveRight(){
		x += 5;
	}

	public void moveUp(){
		y -= 5;
	}

	public void moveDown(){
		y += 5;
	}

	public boolean checkCollision(Collidable other) {

		int pX = other.getX();
		int pY = other.getY();
		int pWidth = other.getWidth();
		int pHeight = other.getHeight();

		if(pX + pWidth >= getX() && pX <= getX() + getWidth()
		&& pY + pHeight >= getY() && pY <= getY()+getHeight()){
	
			return true;

		}


		return false;
	}
}
