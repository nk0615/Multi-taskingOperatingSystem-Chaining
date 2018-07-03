/*	Program: Multi-tasking Operating System
 * 	Language: Java
 * 	Date: May 2018
 * 	Simulation of a simple CPU Process Scheduling System using the Hash Table
 */
package hashTable;

import java.util.*;

    
public class Chaining {
    
    private Node[] hashTable = new Node[11];
    
    // initialize the values of the array to be null
    public void init() {
        for(int i = 0; i < 11; i++) {
    	hashTable[i] = null;
        }
    }
    
    /* insert a new task into the hash-table.
     * case 1: if the hash-table slot has been occupied
     * 		enable linked list
     * case 2: if the hash-table slot has not yet been occupied
     * 		put the task to the slot
     * Type boolean used for test case
     */
    public int insert(Node task) {
	int i = hashFunction(task);
	Node n = hashTable[i];
	if(n == null) {
	    hashTable[i] = task;
	    return task.priorityCode;
	}
	while(n.next != null) {
	    n = n.next;
	}
	
	n.next = task;
	
	return task.priorityCode;
    }
    
    // a hash function
    public int hashFunction(Node task) {
	return (int)(task.priorityCode % 11);
    }
    
    /* return the name of the task by entering its priority code
     * 	we should be able to know which hash-table slot the node should be at by using the hash function
     * 		we can immediately refer to that slot and do linear search in that slot using the linked list
     * 
     * case 1: slot is empty 
     * case 2: slot is not empty -> check thru the linked list
     */
    public String search(int priorityCode) {
	Node n = hashTable[priorityCode%11];
	
	while(n!= null) {
	    if(n.priorityCode == priorityCode)
		return n.name;
	    
	    n = n.next;
	}
        	return "no such key";
    }
    
    /* delete a given task
     * 
     * case 1: task not found
     * case 2: task found as the first element of the chain
     * case 3: task at somewhere of the chain
     */
    public String deleteByCode(int priorityCode) {
	Node n = hashTable[priorityCode % 11];
	
	if(n == null) {
	    return "Error: No such task.";
	}
	if(n.priorityCode == priorityCode) {
	    hashTable[priorityCode % 11] = n.next;
	    return n.name;
	}
	while(n.next != null) {
	    if(n.next.priorityCode == priorityCode) {
		Node tmp = n.next;
		n.next = n.next.next;
		return tmp.name;
	    }
	    n = n.next;
	}
	
	return "Error: No such task.";
    }
    

    
    
    // prints all the tasks in process with their names & priority code
    public void listAllProcess() {
	int i;
	
	for(i = 0; i < hashTable.length; i++) {
	    
	    Node n = hashTable[i];
	    
	    System.out.print("Hash-Table["+i+"]: ");
	    
	    // linear search thru the linked list
	    while(n!= null) {
		System.out.print("-> " + n.name + "("+ n.priorityCode+") ");
		n = n.next;
	    }
	    System.out.println();
	}
    }
  
    public static void main(String argu[]) {
	Scanner reader = new Scanner(System.in);
	Chaining chain = new Chaining();
	String c;
	
	do {
                	System.out.println("Please insert instruction by inputting:");
                	System.out.println("INSERT - insert a task to the database");
                	System.out.println("SEARCH - search a task from the database");
                	System.out.println("DELETE - delete a task from the database");
                	System.out.println("SHOWME - shows all the tasks from the database");
                	System.out.println("EXIT - quit the program");
                	
                	c = reader.next();
                	
                	if(c.equals("INSERT")) {
                	    System.out.println("Insert a node: please give it a name.");
                	    String d = reader.next();
                	    Node n = new Node(d);
                	    System.out.println("Tasks inserted: " + d + " ("+chain.insert(n)+")");
                	    System.out.println(" ");
                	}
        	
                	if(c.equals("SEARCH")) {
                	    System.out.println("Search a node: please enter a priority code");
                	    int e = reader.nextInt();
                	    System.out.println("The task is: " + chain.search(e)+" ("+e +")");
                	    System.out.println(" ");
                	}
                	
                	if(c.equals("DELETE")) {
                	    System.out.println("Delete a node: please enter the priority code of the task");
                	    int f = reader.nextInt();
                	    System.out.println("Task deleted: " +  chain.deleteByCode(f)+" ("+f+")");
                	    System.out.println(" ");
                	}
                	if(c.equals("SHOWME")) {
                	    System.out.println("-----------Existing Tasks in the list:-----------");
                	    chain.listAllProcess();
                	    System.out.println(" ");
                	}
                	if(c.equals("EXIT")) {
                	    System.out.println("Thank you, Bye Bye.");
                	}
	}
                	
        while(c.equals("INSERT") || c.equals("SEARCH") || c.equals("DELETE") ||
        		!c.equals("INSERT") || !c.equals("SEARCH") || c.equals("DELETE") ||c.equals("EXIT"));    	

   	reader.close();
    }
        	
}


