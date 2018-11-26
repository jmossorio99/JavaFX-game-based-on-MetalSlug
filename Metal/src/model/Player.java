package model;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {

	private static final long serialVersionUID = -8063827302843788016L;

	private String name;
	private Player left = null;
	private Player right = null;
	private Score rootScore;
	private long timePlayed = 0;
	private TimeList firstTime;

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
		Score s = new Score(score);
		if (rootScore == null) {
			rootScore = s;
		} else {
			rootScore.insertScore(s);
		}
	}

	public int getMaxScore() {
		int maxScore = 0;
		if (rootScore != null) {
			Score max = rootScore.searchMaxScore();
			maxScore = max.getScore();
		}
		return maxScore;
	}

	@Override
	public String toString() {
		String str = "";
		if (timePlayed < 10)
			str = String.format("%s%40s%41ss", name, getMaxScore(), timePlayed);
		else if (timePlayed < 100)
			str = String.format("%s%40s%40ss", name, getMaxScore(), timePlayed);
		else if (timePlayed >= 100)
			str = String.format("%s%40s%39ss", name, getMaxScore(), timePlayed);
		return str;
	}

	@Override
	public int compareTo(Player p) {
		if (name.compareTo(p.getName()) > 0)
			return 1;
		else if (name.compareTo(p.getName()) < 0)
			return -1;
		else
			return 0;
	}

	public boolean playerExists(String name2) {
		boolean a1 = false;
		boolean a2 = false;
		if (name.equalsIgnoreCase(name2)) {
			return true;
		}
		if (left != null) {
			a1 = left.playerExists(name2);
		}
		if (right != null) {
			a2 = right.playerExists(name2);
		}
		return a1 || a2;
	}

	public boolean sheet() {
		return (left == null) && (right == null);
	}

	public Player getLess() {
		return (left == null) ? this : left.getLess();
	}

	public Player deletePlayer(Player p) {
		if (sheet()) {
			return null;
		}
		if (getMaxScore() == p.getMaxScore()) {

			if (left == null) {
				return right;
			}
			if (right == null) {
				return left;
			}

			Player inheritor = right.getLess();
			right = right.deletePlayer(inheritor);

			inheritor.left = left;
			inheritor.right = right;
			return inheritor;
		} else if (getMaxScore() > p.getMaxScore()) {
			left = left.deletePlayer(p);
		} else {
			right = right.deletePlayer(p);
		}
		return this;
	}

	public long getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(long timePlayed) {
		this.timePlayed = timePlayed;
	}

	public void addTimeList(TimeList time) {

		boolean stop = false;
		if (firstTime == null) {
			firstTime = time;
		} else if (firstTime.compareTo(time) < 0) {
			time.setNext(firstTime);
			firstTime.setPrev(time);
			firstTime = time;
		} else {
			TimeList temp = firstTime;
			while (temp.getNext() != null && stop) {
				if (temp.compareTo(time) <= 0) {
					TimeList prev = temp.getPrev();
					prev.setNext(time);
					time.setNext(temp);
					stop = true;
				}
				temp = temp.getNext();
			}
			if (!stop) {
				temp.setNext(time);
				time.setPrev(temp);
			}
		}

	}

	public long getHighestTime() {

		long ret = firstTime.getData();
		TimeList temp = firstTime;
		while (temp != null) {
			if (temp.getData() > ret) {
				ret = temp.getData();
			}
			temp = temp.getNext();
		}
		return ret;

	}

}
