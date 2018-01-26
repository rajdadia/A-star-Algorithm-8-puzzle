import java.util.*;

public class Board implements Cloneable
{
	
	int dimen,hamm=0,manh=0;
	int[][] prev;
	int[][] board;
	int moves=0; 
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
		} 
	 
    public Board(int[][] blocks,int mov,int[][] pre)           // construct a board from an n-by-n array of blocks. (where blocks[i][j] = block in row i, column j)
    {
    	prev=pre;
    	moves=mov;
    	board=blocks;
    	dimen=board[0].length;
    }
    
    public Board(Board b)
    {
    	prev=board=new int[b.dimen][b.dimen];
    	
    	for(int i=0;i<b.dimen;i++)
    	{
    		for(int j=0;j<b.dimen;j++)
    		{
    			this.prev[i][j]=this.board[i][j]=b.board[i][j];
    		}
    	}
    	
    	this.dimen=b.dimen;
    	this.moves=b.moves+1;
    	
    }
    
    
    public int dimension()                 // board dimension n
    {
    	return dimen;
    }
    
    
    public int hamming()                   // number of blocks out of place
    {
    	int x,col,row;
    	
    	for(int i=0;i<dimen;i++)
    	{
    		for(int j=0;j<dimen;j++)
    		{
    			if(board[i][j]==0)
    				break;
    			
    			x=board[i][j]-1;
    			col=x%3;
    			row=x/3;
    			if(col!=j || row!=i)
    			{
    				hamm++;
    			}
    		}
    	}
    	return hamm+moves;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	
    	int x,col,row;
    	for(int i=0;i<dimen;i++)
    	{
    		for(int j=0;j<dimen;j++)
    		{
    			if(board[i][j]==0)
    				break;
    			
    			x=board[i][j]-1;
    			col=x%3;
    			row=x/3;
    			manh+=Math.abs(col-j);
    			manh+=Math.abs(row-i);
    		}
    	}
    	return manh+moves;
    }
    public boolean isGoal()                // is this board the goal board?
    {
    	boolean flag=true;int k=1;
    	for(int i=0;i<dimen;i++)
    	{
    		for(int j=0;j<dimen;j++)
    		{
    			if (board[i][j]==0 && k==9)
    			{
        			break;
    			}
    			if(board[i][j]!=k)
    			{
    				flag=false;
        			break;
    			}
    			k++;
    		}
    		if(flag==false)
    		break;
    	}
    	return flag;
    }
//    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
//    {
//    	
//    }
    public boolean equals(Object y)        // does this board equal y?
    {
    	//System.out.println("equals called");
    	int m=0,i=0,j=0,n=0;
    	boolean flag=true;
    	Board x=(Board)y;
    	for(i=0,m=0;i<dimen;i++,m++)
    	{
    		for(j=0,n=0;j<dimen;j++,n++)
    		{
    			//System.out.println(x.board[m][n]+"x      this"+this.board[i][j]);
    			if(x.board[m][n]!=this.board[i][j])
    			{
    				flag=false;
    				break;
    			}
    		}
    		if(flag==false)
    		break;
    	}
    	return flag;
    }
   
    
    public Iterable<Board> neighbours()     // all neighboring boards
    {
    	boolean flag=false; int i=0,j=0;
    	
    	ArrayList<Board> al = new ArrayList<Board>();
    	
    	for(i=0;i<dimen;i++)
    	{
    		for(j=0;j<dimen;j++)
    		{
    			if(board[i][j]==0)
    			{
    				flag=true;
    				break;
    			}
    		}
    		if(flag==true)
    			break;
    	}
    	
    	Board[] x=new Board[4];
    	
    		int temp=0;
    		
    		for(int k=0;k<4;k++)
    		{
    			x[k] =new Board(this);
    		}
    		
    		if((i+1)<3)
        	{
        		temp=x[0].board[i][j];
        		x[0].board[i][j]=x[0].board[i+1][j];
        		x[0].board[i+1][j]=temp;
        		al.add(x[0]);
        		
        	}
    		
    		if((i-1)>-1)
        	{
        		temp=x[1].board[i][j];
        		x[1].board[i][j]=x[1].board[i-1][j];
        		x[1].board[i-1][j]=temp;
        		al.add(x[1]);
        		
        	}
    		
    		if((j-1)>-1)
        	{
        		temp=x[2].board[i][j];
        		x[2].board[i][j]=x[2].board[i][j-1];
        		x[2].board[i][j-1]=temp;
        		al.add(x[2]);
        		
        	}
    		
    		if((j+1)<3)
        	{
        		temp=x[3].board[i][j];
        		x[3].board[i][j]=x[3].board[i][j+1];
        		x[3].board[i][j+1]=temp;
        		al.add(x[3]);
        	}
    		
    		
    	

    	return al;
    }
    
    public String toString()               // string representation of this board (in the output format specified below)
    {
    	
    	String s=Arrays.deepToString(this.board);
    	
    	return s;
    }

    public static void main(String[] args) // unit tests (not graded)
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
    	
    	Board board=new Board(block,0,null);
    	System.out.println(board.equals(board));
    	
//    	Iterable<Board> al=board.neighbours();
//    	Iterator<Board> itr=al.iterator();
//    	while(itr.hasNext())
//    	{
//    		System.out.println(itr.next().toString());
//    	}
    	
    	
    }
}
