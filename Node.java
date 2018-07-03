package hashTable;


/* An inner node class stores the value for each Task
 * 
 * Each task should have a task name and a randomly generated priority code
 */
public class Node{

	public String name;  
	public int priorityCode; //randomly generated
	public Node next;
	
	//constructor
	public Node(String name) {
	    this.name = name;
	    this.priorityCode = (int)(Math.random()*1000);
	    this.next = null;
	   
	}
}


