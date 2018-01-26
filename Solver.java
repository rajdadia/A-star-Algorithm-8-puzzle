import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

//import  edu.princeton.cs.algs4.*;

public class Solver
{
	private class Move implements Comparable<Move> {
        public Move previous;
        public Board board;
        public int nummoves = 0;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            this.nummoves = previous.nummoves + 1;
        }

        public int compareTo(Move move) {
            return (this.board.manhattan() - move.board.manhattan()) + (this.nummoves - move.nummoves);
        }
    }
	
    Board initial,b;
    Move x;
    MinPQ<Move> pq;
    Move y;
    
    Iterable<Board> als = new ArrayList<Board>();Iterator<Board> itrs;
    
    ArrayList<Move> alss = new ArrayList<Move>();Iterator<Move> itrss;
    
    int movess=-1;
    
    public Solver(Board initial) // find a solution to the initial board (using the A* algorithm)   
    {
    	int[][] block=new int[3][3];
    	int k=1;
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			block[i][j]=k;
    			k++;
    		}
    	}
    	block[2][2]=0;
    	
    	Board goal=new Board(block,0,null);
        
    	this.initial=initial;
        pq=new MinPQ<Move>();
        Move abc=new Move(initial);
        Move prev;boolean trip=false;
        
        abc.previous=new Move(goal);
        abc.previous.nummoves=0;
        
        pq.insert(abc);
        Move xyz;
        
        do
        {
        	
        	prev=x;
        	x=pq.delMin();
        	if(trip!=false)
        	{
        		x.previous=new Move(prev.board);
        		
        	}
        	movess++;
        	trip=true;
        	alss.add(x);
        	als=x.board.neighbours();
        	itrs=als.iterator();
        	while(itrs.hasNext())
        	{
        		xyz=new Move(itrs.next());
        		if(!xyz.board.equals(x.previous.board))
        		{
        			pq.insert(xyz);
        			
        		}
        	}
        }
        while(!x.board.isGoal());
        
    }
    
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
    	return movess;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	itrss=alss.iterator();
    	while(itrss.hasNext())
    	{
    		System.out.println(itrss.next().board.toString());
    	}
    	
     return null;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    	
    Scanner in=new Scanner(System.in);
     int[][] block=new int[3][3];
     int k=1;
     block[0][0]=0;
     block[0][1]=1;
     block[0][2]=3;
     block[1][0]=4;
     block[1][1]=2;
     block[1][2]=6;
     block[2][0]=7;
     block[2][1]=5;
     block[2][2]=8;
     
     
     Board board=new Board(block,0,null);
     Solver solv=new Solver(board);
     solv.solution();
     System.out.println(solv.moves());
     
    }

	
}