package model;

import java.io.Serializable;

public class Player implements Serializable{

	private String name;
	private Player left = null;
	private Player right = null;
	
	private Score rootScore;

	public Player(String name) {
		this.name = name;
	}

	public void insertPlayer(Player p) {

		if (p != null) {

			if (getMaxScore() > p.getMaxScore()) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addScore(int score) {
		Score s=new Score (score); 
		
		if(rootScore==null) {
			rootScore=s;
		}else {
			rootScore.insertScore(s);
		}
		
	}
	
	public int getMaxScore() {
		int maxScore=0;
		
		if(rootScore!=null) {
			Score max = rootScore.searchMaxScore();
			maxScore=max.getScore();
		}
		
		return maxScore;
	}
	
	

}
