package model;

import java.io.Serializable;

public class Score implements Serializable{
	public int score=0;
	public Score right;
	public Score left;
	
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
		
		if(compareScore(s)>0) {
			
			if(left==null) {
				left=s;
			}else {
				left.insertScore(s);
			}
		}else {
				if(right==null) {
					right=s;
				}else {
					right.insertScore(s);
				}
			}
			
		}
		
	
	
	public int compareScore(Score s) {
		int compare=0;
		if(score>s.getScore()) {
			compare=-1;
		}else{
			compare=1;
		}
		
		
		return compare;
	}
	
	public Score searchMaxScore() {
		return (right==null) ? this : right.searchMaxScore();
	}
	
	public boolean sheet() {
		return (right==null)&&(left==null);
	}
}
