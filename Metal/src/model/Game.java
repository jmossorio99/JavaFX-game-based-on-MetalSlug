package model;

import java.util.ArrayList;

public class Game {

	private Player firstPlayer = null;
	private Hero hero;
	private ArrayList<Block> blocks = new ArrayList<>();
	private Robot robotRoot;
	private Robot listRoot;

	public Game(Hero hero) {

		this.hero = hero;

	}
	
	public void addRobotToList(Robot r) {
		
		if(listRoot==null) {
		listRoot=r;	
		}else {
			addRobotToListR(listRoot,r);
		}	
	}
	
	public void addRobotToListR(Robot aux,Robot r) {
		
		if(aux.getNext()==null) {
			aux.setNext(r);
		}else {
			addRobotToListR(aux.getNext(),r);
		}
		
	}
	
	public Robot getRobotRoot() {
		return robotRoot;
	}
	
	public void addBlocks(Block block) {
		
		blocks.add(block);
		
	}
	
	public ArrayList<Block> getBlocks(){
		
		return blocks;
		
	}
	
	public void addRobot(Robot r) {
		
		if(robotRoot==null) {
			robotRoot=r;
		}
		else {
			addRobotR(robotRoot,r);
		}
		
	}
	
	public void addRobotR(Robot aux,Robot r) {
		
		if(aux!=null) {
			int compare = r.compareByDirection(aux);
			if(compare>0) {
				
				if(aux.getRight()==null) {
					aux.setRight(r);
				}
				else {
					addRobotR(aux.getRight(),r);
				}
				
			}
			else {
				
				if(aux.getLeft()==null) {
					aux.setLeft(r);
				}
				else {
					addRobotR(aux.getLeft(),r);
				}
				
			}
			
		}
		
	}
	
	public Robot searchByOrderList(int number) {
		Robot found=null;
		
		if(listRoot!=null) {
			found=searchByOrderListR(listRoot,number);
		}
		
		return found;
	}
	
	public Robot searchByOrderListR(Robot aux,int number) {
		Robot r=aux;
		if(aux!=null) {
			if(r.getOrder()==number) {
			}
			else {
			r=searchByOrderListR(aux.getNext(),number);
			}
		}
		
		
		return r;
	}

}
