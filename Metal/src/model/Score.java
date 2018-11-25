package model;

import java.io.Serializable;

public class Score implements Serializable{
	
	private int score = 0;
	private Score right;
	private Score left;
	
	public Score(int score) {
		this.score=score;
	}
	
    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void insertScore(Score s) {
		if(compareScore(s) > 0) {
			if(left == null) {
				left = s;
			}
			else {
				left.insertScore(s);
			}
		}
		else {
			if(right == null) {
				right = s;
			}
			else {
				right.insertScore(s);
			}
		}
	}
	
	
	public int compareScore(Score s) {
		int compare = 0;
		if(score > s.getScore()) {
			compare = 1;
		}else{
			compare = -1;
		}
		return compare;
	}
	
	public Score searchMaxScore() {
		return (right == null) ? this : right.searchMaxScore();
	}
	
	public boolean sheet() {
		return (right == null) && (left == null);
	}
	
	public int getWidth() {
		int w1 = ( left == null ) ? 0 : left.getWidth();
		int w2 = ( right == null ) ? 0 : right.getWidth();
		return 1 + w1 + w2;
	}
	
	public Score getLeft() {
		return left;
	}
	
	public Score getRight() {
		return right;
	}
}
