package model;

public class Player {

	private String name;
	private Player left = null;
	private Player right = null;
	private int score = 0;

	public Player(String name) {
		this.name = name;
	}

	public void insertPlayer(Player p) {

		if (p != null) {

			if (getScore() > p.getScore()) {
				if (left == null) {
					left = p;
				} else {
					left.insertPlayer(p);
				}
			} else {
				if (right == null) {
					right = p;
				} else {
					right.insertPlayer(p);
				}
			}

		}

	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void robotKilled() {
		setScore(getScore() + 10);
	}

}
