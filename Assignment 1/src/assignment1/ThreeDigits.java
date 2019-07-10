package assignment1;
import java.io.*;
import java.util.*;
import java.lang.*;
 
public class ThreeDigits {
	
	public static class Node{
		String parent;
		String node;
		boolean isRoot;
		List<String> children;
		Node parent_pointer;
		int level;
		int heuristic;
 
		// Store the state of the 
		public Node(String node, String parent, boolean root){
			this.node=node;
			this.parent= parent;
			isRoot = root;
			children = new ArrayList<>();
			parent_pointer = null;
			level = 0;
			heuristic = 0;
		}
 
		// Get list of children of a node
		public List<String> getChildren(){
			return this.children;
		}
 
		// Get the node value
		public String getNode(){
			return this.node;
		}
 
		// Get the parent node value
		public String getParent(){
			return this.parent;
		}
 
		//check it root
		public boolean isThisRoot(){
			return isRoot;
		}
 
		public Node getParentPointer(){
			return this.parent_pointer;
		}
 
		public void putParentPointer(Node parent){
			this.parent_pointer = parent;
		}
		
		public void putLevel(int i){
			this.level = i;
		}
		
		public int getLevel(){
			return this.level;
		}
 
		public void putHeuristic(int i) {
			this.heuristic = i;
		}
 
		public int getHeuristic(){
			return this.heuristic;
		}
		// To generate its children for BFS
		public void generateChildrenBFS(LinkedList<Node> fringe, List<String> forbidden_state, LinkedList<Node> expanded_nodes){
 
			if(this.isThisRoot()){
				char[] node_to_expand = this.getNode().toCharArray();
				for(int i = 0; i < 3; ++i){
					char c = node_to_expand[i];
					int temp = Character.getNumericValue(c);
 
					// Further constraints, checking if its 0 or 9
					if(i == 0){
						if(temp !=0){
							String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(i == 1){
						if(temp !=0){
							String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]) ;
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(i == 2){
						if(temp !=0){
							String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
				}
				
				return;
			}
 
			
			char[] node_to_expand = this.getNode().toCharArray();
 
			for(int i = 0; i < 3; i++){
				
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
				
 
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(!temp_node.containsIn(fringe) && this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(!temp_node.containsIn(fringe) && this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
				}
				if(i == 1){
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(!temp_node.containsIn(fringe) && this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(!temp_node.containsIn(fringe) && this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
				}
				if(i == 2){
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(!temp_node.containsIn(fringe) && this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(!temp_node.containsIn(fringe) && this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
				}
			}
			return;
		}
 
		// To generate its children for DFS and add it to Fringe
		public void generateChildrenDFS(LinkedList<Node> fringe, List<String> forbidden_state, LinkedList<Node> expanded_nodes){
 
			if(this.isThisRoot()){
				char[] node_to_expand = this.getNode().toCharArray();
				for(int i = 0; i < 3; ++i){
					char c = node_to_expand[i];
					int temp = Character.getNumericValue(c);
 
					// Further constraints, checking if its 0 or 9
					if(i == 0){
						if(temp !=0){
							String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(i == 1){
						if(temp !=0){
							String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]) ;
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
					if(i == 2){
						if(temp !=0){
							String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								fringe.addLast(temp_node);
							}
						}
					}
				}
				return;
			}
 
			char[] node_to_expand = this.getNode().toCharArray();
			LinkedList<Node> sample = new LinkedList<>();
			for(int i = 2; i >= 0; i--){
				
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
				
 
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addFirst(temp_node);
								sample.addFirst(temp_node);
							}
						}
					}
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addFirst(temp_node);
								sample.addFirst(temp_node);
							}
						}
					}
					
				}
				if(i == 1){
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addFirst(temp_node);
								sample.addFirst(temp_node);
							}
						}
					}
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addFirst(temp_node);
								sample.addFirst(temp_node);
							}
						}
					}
				}
				if(i == 2){
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addFirst(temp_node);
								sample.addFirst(temp_node);
							}
						}
					}
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								fringe.addFirst(temp_node);
								sample.addFirst(temp_node);
							}
						}
					}
				}
			}
			return;
		
		}
 
		// To generate its children for IDS and add it to Fringe
		public void generateChildrenIDS(LinkedList<Node> fringe, List<String> forbidden_state, LinkedList<Node> expanded_nodes, int level){
 
			if(this.isThisRoot()){
				char[] node_to_expand = this.getNode().toCharArray();
				for(int i = 0; i < 3; ++i){
					char c = node_to_expand[i];
					int temp = Character.getNumericValue(c);
 
					// Further constraints, checking if its 0 or 9
					if(i == 0){
						if(temp !=0){
							String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addLast(temp_node);
							}
						}
					}
					if(i == 1){
						if(temp !=0){
							String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]) ;
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addLast(temp_node);
							}
						}
					}
					if(i == 2){
						if(temp !=0){
							String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addLast(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addLast(temp_node);
							}
						}
					}
				}
				for(int i =0; i<fringe.size(); i++){
					if(fringe.get(i).containsIn(expanded_nodes)){
						fringe.remove(i);
					}	
				}
				return;
			}
 
			char[] node_to_expand = this.getNode().toCharArray();
			for(int i = 2; i >= 0; i--){
				
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
				
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addFirst(temp_node);
							}
						}
					}
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addFirst(temp_node);
							}
						}
					}
				}
				if(i == 1){
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addFirst(temp_node);
							}
						}
					}
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addFirst(temp_node);
							}
						}
					}
				}
				if(i == 2){
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addFirst(temp_node);
							}
						}
					}
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								if(temp_node.getLevel() <= level && !temp_node.containsIn(expanded_nodes))
									fringe.addFirst(temp_node);
							}
						}
					}
				}
			}
			for(int i =0; i<fringe.size(); i++){
				if(fringe.get(i).containsIn(expanded_nodes)){
					fringe.remove(i);
				}	
			}
			return;
		
		}
 
		// Generating Children for Hill
		public void generateChildrenHill(LinkedList<Node> fringe, List<String> forbidden_state, LinkedList<Node> expanded_nodes, String goal_node){
			
			if(this.isThisRoot()){
				char[] node_to_expand = this.getNode().toCharArray();
				for(int i = 0; i < 3; ++i){
					char c = node_to_expand[i];
					int temp = Character.getNumericValue(c);
 
					// Further constraints, checking if its 0 or 9
					if(i == 0){
						if(temp !=0){
							String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
					if(i == 1){
						if(temp !=0){
							String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]) ;
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
					if(i == 2){
						if(temp !=0){
							String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
				}
				sortFringeForHill(fringe);
				return;
			}
			char[] node_to_expand = this.getNode().toCharArray();
			for(int i = 0; i <3; i++){
				
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
				
 
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
					
				}
				if(i == 1){
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
				}
				if(i == 2){
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);
							}
						}
					}
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);							
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								fringe.addFirst(temp_node);	
							}
						}
					}
				}
			}
			sortFringeForHill(fringe);
			return;
		}
 
		// Generate Children for Greedy
		public void generateChildrenGreedy(LinkedList<Node> fringe, List<String> forbidden_state, LinkedList<Node> expanded_nodes, String goal_node){
			
			if(this.isThisRoot()){
				char[] node_to_expand = this.getNode().toCharArray();
				for(int i = 0; i < 3; ++i){
					char c = node_to_expand[i];
					int temp = Character.getNumericValue(c);
 
					// Further constraints, checking if its 0 or 9
					if(i == 0){
						if(temp !=0){
							String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(i == 1){
						if(temp !=0){
							String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]) ;
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(i == 2){
						if(temp !=0){
							String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
				}
				return;
			}
			char[] node_to_expand = this.getNode().toCharArray();
			for(int i = 0; i <3; i++){
				
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
				
 
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					
				}
				if(i == 1){
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
				}
				if(i == 2){
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);							
								temp_node.putHeuristic(generateHuristic(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
				}
			}
			return;
		}
 
		// Generate Children for Greedy
		public void generateChildrenAStar(LinkedList<Node> fringe, List<String> forbidden_state, LinkedList<Node> expanded_nodes, String goal_node){
			
			if(this.isThisRoot()){
				char[] node_to_expand = this.getNode().toCharArray();
				for(int i = 0; i < 3; ++i){
					char c = node_to_expand[i];
					int temp = Character.getNumericValue(c);
 
					// Further constraints, checking if its 0 or 9
					if(i == 0){
						if(temp !=0){
							String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(i == 1){
						if(temp !=0){
							String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]) ;
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(i == 2){
						if(temp !=0){
							String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							if(!forbidden_state.contains(a)) {
								Node temp_node = new Node(a,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
						if(temp !=9){
							String b = String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							if(!forbidden_state.contains(b)) {
								Node temp_node = new Node(b,this.getNode(),false);
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
				}
				return;
			}
			char[] node_to_expand = this.getNode().toCharArray();
			for(int i = 0; i <3; i++){
				
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
				
 
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					
				}
				if(i == 1){
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
				}
				if(i == 2){
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
						if(!forbidden_state.contains(a)) {
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);
								temp_node.putLevel(this.getLevel()+1);
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
						if(!forbidden_state.contains(b)) {
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i) && !temp_node.containsIn(expanded_nodes)){
								temp_node.putParentPointer(this);	
								temp_node.putLevel(this.getLevel()+1);						
								temp_node.putHeuristic(generateHuristicAStar(temp_node, goal_node));
								sortFringeForGreedy(fringe, temp_node);
							}
						}
					}
				}
			}
			return;
		}
 
		// Generate Manhattan Heuristic between parent and its child
		public int generateHuristicAStar(Node child, String goal_node){
			char[] parents = goal_node.toCharArray();
			char[] childs = child.getNode().toCharArray();
			int heuristic = 0;
			
			for(int i =0; i< 3; i++){
				int goal_temp = Character.getNumericValue(parents[i]);
				int child_temp = Character.getNumericValue(childs[i]);
				heuristic +=  Math.abs(child_temp - goal_temp);
			}
			heuristic += child.getLevel();
			return heuristic; 
		}
 
		// Add node to Fringe in increasing order for Greedy
		public void sortFringeForGreedy(LinkedList<Node> fringe, Node node){
			if(fringe.size() == 0) { fringe.add(node); return;}
			if(fringe.size() == 1) {
				if(fringe.get(0).getHeuristic() >= node.getHeuristic()){
					fringe.addFirst(node);
				} else { fringe.addLast(node);}
				return;
			}
			for(int i = 0; i < fringe.size(); i++){
				if(fringe.get(i).getHeuristic() >= node.getHeuristic()){
					fringe.add(i, node);
					return;
				}
			}
			fringe.addLast(node);
			return;
 
		}
		// Sort the Fringe and delete non needed nodes from the fringe
		public void sortFringeForHill(LinkedList<Node> fringe){
			if (fringe.size() == 0 || fringe.size() == 1) return;
			int low = fringe.get(0).getHeuristic();
			int low_index = 0;
 
			for(int i = 1; i < fringe.size(); ++i){
				if(fringe.get(i).getHeuristic() < low){
					low = fringe.get(i).getHeuristic();
					low_index = i;
				}
			}
			LinkedList<Node> temp = new LinkedList<>();
			for(int i = 0; i < fringe.size(); ++i){
				if( low_index != i){
					temp.add(fringe.get(i));
				}
			}
			fringe.removeAll(temp);
		}
		// Generate Manhattan Heuristic between parent and its child
		public int generateHuristic(Node child, String goal_node){
			char[] parents = goal_node.toCharArray();
			char[] childs = child.getNode().toCharArray();
			int heuristic = 0;
			
			for(int i =0; i< 3; i++){
				int goal_temp = Character.getNumericValue(parents[i]);
				int child_temp = Character.getNumericValue(childs[i]);
				heuristic +=  Math.abs(child_temp - goal_temp);
			}
			return heuristic; 
		}
 
		// Checks if fringe or expanded nodes contains the given node
		public boolean containsIn(LinkedList<Node> fringe){
			for(int i = 0; i < fringe.size(); ++i){
				if(fringe.get(i).isEqual(this)){
					return true;
				}
			}
			return false;
		}
 
		// Checks if child are expandable
		public boolean isExpandable(int location){
 
			if(this.getParent().equals("root")) return true;
			char[] parent = this.getParent().toCharArray();
			char[] node = this.getNode().toCharArray();
			if(node[location] == parent[location]){
				return true;
			}
			return false;
		}
 
 
		// Checks if two states are equal or not
		public boolean isEqual(Node other){
			if(!this.getNode().equals(other.getNode())) return false;
	
			List<String> child1 = this.isEqualHelper();
			List<String> child2 = other.isEqualHelper();
			if(child1.size() != child2.size()) return false;
			return child1.containsAll(child2) && child2.containsAll(child1);
		}
 
		// Generate child for comparison
		public List<String> isEqualHelper(){
			char[] node_to_expand = this.getNode().toCharArray();
			List<String> children = new ArrayList<>();
			for(int i=0; i<3; ++i){
				char c = node_to_expand[i];
				int temp = Character.getNumericValue(c);
 
				// Further constraints, checking if its 0 or 9
				if(i == 0){
					if(temp !=0){
						String a = String.valueOf(temp - 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i)){
								children.add(temp_node.getNode());
							}
					}
					if(temp !=9){
						String b = String.valueOf(temp + 1)+ String.valueOf(node_to_expand[1]) + String.valueOf(node_to_expand[2]);						Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i)){
								children.add(temp_node.getNode());
							}
					}
				}
				if(i == 1){
					if(temp !=0){
						String a = String.valueOf(node_to_expand[0]) + String.valueOf(temp - 1)+ String.valueOf(node_to_expand[2]);
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i)){
								children.add(temp_node.getNode());
							}
					}
					if(temp !=9){
						String b = String.valueOf(node_to_expand[0]) + String.valueOf(temp + 1)+ String.valueOf(node_to_expand[2]);
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i)){
								children.add(temp_node.getNode());
							}
					}
				}
				if(i == 2){
					if(temp !=0){
						String a =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp - 1);
							Node temp_node = new Node(a,this.getNode(),false);
							if(this.isExpandable(i)){
								children.add(temp_node.getNode());
							}
					}
					if(temp !=9){
						String b =  String.valueOf(node_to_expand[0]) + String.valueOf(node_to_expand[1]) + String.valueOf(temp + 1);
							Node temp_node = new Node(b,this.getNode(),false);
							if(this.isExpandable(i)){
								children.add(temp_node.getNode());
							}
					}
				}
			}
			return children;
		}
 
		// Backtrack path for Path
		public List<String> backTrack(){
			List<String> backtrack = new ArrayList<>();
			Node temp = this;
			while(temp.getParentPointer() != null){
				backtrack.add(temp.getNode());
				temp = temp.getParentPointer();
			}
			return backtrack;
		}
	}
	public static void main (String[] args) throws IOException{
		
		// Reading Files from the command line
		String search_method = args[0];
		String file_name = args[1];
		String line_read;
		String start_node="";
		String goal_node="";
		String forbidden="";
		boolean is_forbidden = false;
 
		
		BufferedReader br = new BufferedReader(new FileReader(file_name));
		int i = 0;
 
		while((line_read = br.readLine()) != null){
			i++;
			if(i == 1){
				start_node = line_read;
			}
			if(i == 2){
				goal_node = line_read;
			}
			if(i == 3){
				forbidden = line_read;
			}
		}
		if(i==3){
			is_forbidden = true;
		}
		//if not enough arguments
		if(start_node.equals("") || goal_node.equals("")){
			System.out.println("Not enough arguments.");
		}
		String[] tokens = forbidden.split(",");		
		List<String> forbidden_state = new ArrayList<>(); 
		Collections.addAll(forbidden_state, tokens); 
		
		if(search_method.equals("B")){
			doBFS(start_node,goal_node,is_forbidden,forbidden_state);
		}
		if(search_method.equals("D")){
			doDFS(start_node,goal_node,is_forbidden,forbidden_state);
		}
		if(search_method.equals("I")){
			doIDS(start_node,goal_node,is_forbidden,forbidden_state);
		}
		if(search_method.equals("H")){
			doHillClimbing(start_node,goal_node,is_forbidden,forbidden_state);
		}
		if(search_method.equals("G")){
			doGreedy(start_node,goal_node,is_forbidden,forbidden_state);
		}
		if(search_method.equals("A")){
			doAStar(start_node,goal_node,is_forbidden,forbidden_state);
		}
		br.close();
	}		
	
	
	public static void doBFS(String start_node, String goal_node, boolean is_forbidden, List<String> forbidden_state){
		
		LinkedList<Node> expanded_nodes = new LinkedList<>();
		LinkedList<Node> fringe = new LinkedList<>();
 
		// First node to Expand will be the starting node;
		Node node_to_expand = new Node(start_node, start_node, true);
		fringe.add(node_to_expand);
		boolean node_found = true;
 
		while(!node_to_expand.getNode().equals(goal_node)){
			
			// adds nodes to the fringe for node_to_expand
			node_to_expand.generateChildrenBFS(fringe, forbidden_state, expanded_nodes);
			expanded_nodes.add(node_to_expand);
			fringe.remove(0);
 
			// Updating the expanded node and nodes to expand and checking other criteria
			if(fringe.size() == 0){
				node_found = false;
				break;
			}else{
				node_to_expand = fringe.get(0);
			}
			
			if(expanded_nodes.size() >= 1000) {
				node_found = false;
				break;
			}
 
		}
		if(node_to_expand.getNode().equals(goal_node)){
			List<String> path = node_to_expand.backTrack();
			System.out.print(start_node + ",");
			for(int i = path.size()-1; i>=0; --i){
				System.out.print(path.get(i));
				if(i!=0) System.out.print(",");
			}
			System.out.println();
			for(int i = 0; i<expanded_nodes.size(); ++i){
				System.out.print(expanded_nodes.get(i).getNode() + ",");
			}
			System.out.print(goal_node);
			System.out.println();
		}
		if(!node_found) { System.out.println("No solution found.");}
	}
 
	public static void doDFS(String start_node, String goal_node, boolean is_forbidden, List<String> forbidden_state){
		LinkedList<Node> expanded_nodes = new LinkedList<>();
		LinkedList<Node> fringe = new LinkedList<>();
 
		// First node to Expand will be the starting node;
		Node node_to_expand = new Node(start_node, "root", true);
		expanded_nodes.add(node_to_expand);
		
		boolean node_found = true;
 
		while(!node_to_expand.getNode().equals(goal_node)){
			
			node_to_expand.generateChildrenDFS(fringe, forbidden_state, expanded_nodes);
			// Updating the expanded node and nodes to expand and checking other criteria
			if(fringe.size() == 0){
				node_found = false;
				break;
			}else{
				node_to_expand = fringe.get(0);
				fringe.remove(0);
				expanded_nodes.add(node_to_expand);
			}
			
			if(expanded_nodes.size() >= 1000) {
				node_found = false;
				break;
			}
		}
		if(node_to_expand.getNode().equals(goal_node)){
			List<String> path = node_to_expand.backTrack();
			System.out.print(start_node + ",");
			for(int i = path.size()-1; i>=0; --i){
				System.out.print(path.get(i));
				if(i!=0) System.out.print(",");
			}
			System.out.println();
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
		if(!node_found) { 
			System.out.println("No solution found.");
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
 
	}
 
	public static void doIDS(String start_node, String goal_node, boolean is_forbidden, List<String> forbidden_state){
		LinkedList<Node> final_expanded_nodes = new LinkedList<>();
		LinkedList<Node> expanded_nodes = new LinkedList<>();
		LinkedList<Node> fringe = new LinkedList<>();
		boolean node_found = true;
 
		// First node to Expand will be the starting node;
		Node node_to_expand = new Node(start_node, "root", true);
		node_to_expand.putLevel(0);
		expanded_nodes.add(node_to_expand);
		int level = 0;
 
		while(!node_to_expand.getNode().equals(goal_node)){
 
			if(final_expanded_nodes.size() >= 1000 || (final_expanded_nodes.size()+expanded_nodes.size()) >= 1000) {
				final_expanded_nodes.addAll(expanded_nodes);
				node_found = false;
				break;
			}
 
			node_to_expand.generateChildrenIDS(fringe, forbidden_state, expanded_nodes, level);
			if(fringe.size() == 0){
				final_expanded_nodes.addAll(expanded_nodes);
				expanded_nodes.clear();
				node_to_expand = new Node(start_node,"root", true);
				node_to_expand.putLevel(0);
				expanded_nodes.add(node_to_expand);
				level++;
			}else{
				node_to_expand = fringe.get(0);
				fringe.remove(0);
				if(!node_to_expand.containsIn(expanded_nodes))
					expanded_nodes.add(node_to_expand);
			}
		}
 
		if(node_to_expand.getNode().equals(goal_node)){
			final_expanded_nodes.addAll(expanded_nodes);
			List<String> path = node_to_expand.backTrack();
			System.out.print(start_node + ",");
			for(int i = path.size()-1; i>=0; --i){
				System.out.print(path.get(i));
				if(i!=0) System.out.print(",");
			}
			System.out.println();
			for(int i = 0; i<final_expanded_nodes.size(); ++i){
				if( i!= final_expanded_nodes.size()-1) System.out.print(final_expanded_nodes.get(i).getNode() + ",");
				else System.out.print(final_expanded_nodes.get(i).getNode());
			}
			 System.out.println();
		}
		if(!node_found) { 
			System.out.println("No solution found.");
			if(final_expanded_nodes.size() > 1000){
				for(int i = 0; i<1000; ++i){
					if( i!= 999) System.out.print(final_expanded_nodes.get(i).getNode() + ",");
					else System.out.print(final_expanded_nodes.get(i).getNode());
				}
			}else{
				for(int i = 0; i<final_expanded_nodes.size(); ++i){
					if( i!= final_expanded_nodes.size()-1) System.out.print(final_expanded_nodes.get(i).getNode() + ",");
					else System.out.print(final_expanded_nodes.get(i).getNode());
				}
			}
 			System.out.println();
		}
	}
 
	public static void doHillClimbing(String start_node, String goal_node, boolean is_forbidden, List<String> forbidden_state){
		LinkedList<Node> expanded_nodes = new LinkedList<>();
		LinkedList<Node> fringe = new LinkedList<>();
 
		// First node to Expand will be the starting node;
		Node node_to_expand = new Node(start_node, "root", true);
		expanded_nodes.add(node_to_expand);
		
		boolean node_found = true;
		int j = 0;
 
		while(!node_to_expand.getNode().equals(goal_node)){
			
			node_to_expand.generateChildrenHill(fringe, forbidden_state, expanded_nodes, goal_node);
			// Updating the expanded node and nodes to expand and checking other criteria
			if(fringe.size() == 0){
				node_found = false;
				break;
			}else{
				if(j >= 1){
					if(node_to_expand.getHeuristic() < fringe.get(0).getHeuristic()){
						node_found = false;
						break;
					}
				}
				node_to_expand = fringe.get(0);
				fringe.remove(0);
				j++;
				expanded_nodes.add(node_to_expand);
			}
			
			if(expanded_nodes.size() >= 1000) {
				node_found = false;
				break;
			}
		}
		if(node_to_expand.getNode().equals(goal_node)){
			List<String> path = node_to_expand.backTrack();
			System.out.print(start_node + ",");
			for(int i = path.size()-1; i>=0; --i){
				System.out.print(path.get(i));
				if(i!=0) System.out.print(",");
			}
			System.out.println();
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
		if(!node_found) { 
			System.out.println("No solution found.");
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
	}	
 
	public static void doGreedy(String start_node, String goal_node, boolean is_forbidden, List<String> forbidden_state){
		LinkedList<Node> expanded_nodes = new LinkedList<>();
		LinkedList<Node> fringe = new LinkedList<>();
 
		// First node to Expand will be the starting node;
		Node node_to_expand = new Node(start_node, "root", true);
		expanded_nodes.add(node_to_expand);
		
		boolean node_found = true;
 
		while(!node_to_expand.getNode().equals(goal_node)){
			
			node_to_expand.generateChildrenGreedy(fringe, forbidden_state, expanded_nodes, goal_node);
			// Updating the expanded node and nodes to expand and checking other criteria
 
			if(fringe.size() == 0){
				node_found = false;
				break;
			}else{
				node_to_expand = fringe.get(0);
				fringe.remove(0);
				expanded_nodes.add(node_to_expand);
			}
			
			if(expanded_nodes.size() >= 1000) {
				node_found = false;
				break;
			}
		}
		if(node_to_expand.getNode().equals(goal_node)){
			List<String> path = node_to_expand.backTrack();
			System.out.print(start_node + ",");
			for(int i = path.size()-1; i>=0; --i){
				System.out.print(path.get(i));
				if(i!=0) System.out.print(",");
			}
			System.out.println();
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
		if(!node_found) { 
			System.out.println("No solution found.");
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
	}
	public static void doAStar(String start_node, String goal_node, boolean is_forbidden, List<String> forbidden_state){
		LinkedList<Node> expanded_nodes = new LinkedList<>();
		LinkedList<Node> fringe = new LinkedList<>();
 
		// First node to Expand will be the starting node;
		Node node_to_expand = new Node(start_node, "root", true);
		expanded_nodes.add(node_to_expand);
		
		boolean node_found = true;
 
		while(!node_to_expand.getNode().equals(goal_node)){
			
			node_to_expand.generateChildrenAStar(fringe, forbidden_state, expanded_nodes, goal_node);
			// Updating the expanded node and nodes to expand and checking other criteria
 
			if(fringe.size() == 0){
				node_found = false;
				break;
			}else{
				node_to_expand = fringe.get(0);
				fringe.remove(0);
				expanded_nodes.add(node_to_expand);
			}
			
			if(expanded_nodes.size() >= 1000) {
				node_found = false;
				break;
			}
		}
		if(node_to_expand.getNode().equals(goal_node)){
			List<String> path = node_to_expand.backTrack();
			System.out.print(start_node + ",");
			for(int i = path.size()-1; i>=0; --i){
				System.out.print(path.get(i));
				if(i!=0) System.out.print(",");
			}
			System.out.println();
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
		}
		if(!node_found) { 
			System.out.println("No solution found.");
			for(int i = 0; i<expanded_nodes.size(); ++i){
				if( i!= expanded_nodes.size()-1) System.out.print(expanded_nodes.get(i).getNode() + ",");
				else System.out.print(expanded_nodes.get(i).getNode());
			}
 			System.out.println();
	}
}
 
 
 
}