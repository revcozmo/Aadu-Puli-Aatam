package board;

import java.io.Serializable;

import ai.MiniMax;

public class Board  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MiniMax MX = new MiniMax();
	
	public Coin p[]=new Coin[24];
	public boolean tigers_move = false;  //to find whether it is tiger's move or goat's move
	public boolean goatInsertionEnded = false;
	public boolean goatWon = false;		//Naming conversion courtesy to SivanSing
	public boolean tigerWon = false;
	
	public int noOfGoatsInserted = 0;
	public int goatKilled = 0;
	public int totalNoOfGoatOnTheBoard = 0;

	public Board(int i){
		// TODO Auto-generated method stub
  
     
    p[0] = new Coin(390,20,"0");
    
    p[1] = new Coin(60,235,"1");
    p[2] = new Coin(265,235,"2");
    p[3] = new Coin(355,235,"3");
    p[4] = new Coin(445,235,"4");
    p[5] = new Coin(520,235,"5");
    p[6] = new Coin(720,235,"6");
    
    p[7] = new Coin(63,335,"7");
    p[8] = new Coin(208,335,"8");
    p[9] = new Coin(330,335,"9");
    p[10] = new Coin(465,335,"10");
    p[11] = new Coin(575,335,"11");
    p[12] = new Coin(723,335,"12");
    
    p[13] = new Coin(65,420,"13");
    p[14] = new Coin(158,420,"14");
    p[15] = new Coin(315,420,"15");
    p[16] = new Coin(485,420,"16");
    p[17] = new Coin(630,420,"17");
    p[18] = new Coin(700,420,"18");
    
    p[19] = new Coin(55,600,"19");
    p[20] = new Coin(278,600,"20");
    p[21] = new Coin(525,600,"21");
    p[22] = new Coin(730,600,"22");    
     
    directions();
    
				//Default tiger position
	 initialCoinPlacement();
	 
	  
	 
	
	} 
	

	
	void initialCoinPlacement(){
		
		  add_tiger(p[0]);
		  add_tiger(p[3]);
		  add_tiger(p[4]);
	}
	
	
	void directions(){
		
			p[0].direction(p[2],p[3],p[4],p[5]);
		    p[1].direction(null, p[2], null, p[7]);  
		    p[2].direction(p[1], p[3], p[0], p[8]);
		    p[3].direction(p[2], p[4], p[0], p[9]);
		    p[4].direction(p[3], p[5], p[0], p[10]);
		    p[5].direction(p[4], p[6], p[0], p[11]);
		    p[6].direction(p[5], null, null, p[12]);
		    p[7].direction(null, p[8], p[1], p[13]);
		    p[8].direction(p[7],p[9],p[2],p[14]);
		    p[9].direction(p[8], p[10], p[3], p[15]);
		    p[10].direction(p[9], p[11], p[4], p[16]);
		    p[11].direction(p[10],p[12],p[5],p[17]);
		    p[12].direction(p[11], null, p[6], p[18]);
		    p[13].direction(null, p[14], p[7], null);
		    p[14].direction(p[13], p[15], p[8], p[19]);
		    p[15].direction(p[14], p[16], p[9], p[20]);
		    p[16].direction(p[15], p[17], p[10], p[21]);
		    p[17].direction(p[16], p[18], p[11], p[22]);
		    p[18].direction(p[17],null,p[12],null);
		    
		    p[19].direction(null,p[20],p[14],null);
		    p[20].direction(p[19], p[21], p[15], null);
		    p[21].direction(p[20], p[22], p[16], null);
		    p[22].direction(p[21],null,p[17],null);
		
	}
	
	
	public Board(Board toBeCopied){ 							//constructor for cloning object of Board 
		
		this.tigers_move = toBeCopied.tigers_move;  			//to find whether it is tiger's move or goat's move
		this.goatInsertionEnded = toBeCopied.goatInsertionEnded;
		this.goatWon = toBeCopied.goatWon;						//Naming conversion courtesy to SivanSing
		this.tigerWon = toBeCopied.tigerWon;
		this.totalNoOfGoatOnTheBoard = toBeCopied.totalNoOfGoatOnTheBoard;
		this.noOfGoatsInserted = toBeCopied.noOfGoatsInserted;
		this.goatKilled = toBeCopied.goatKilled;
		
		for(int i=0; i<23; i++)
			this.p[i] = new Coin(toBeCopied.p[i]); 
		
		directions();
		
	}
	
	public void takeDecision(int i,int j){   
	   if(isOverJumping(p[i],p[j]) == true){
		
	   }  
	    
	   else if(p[i].tiger==true && tigers_move == true){					//Checking is it tiger's turn
	    
	    
		    try{
		    
		    		if(move_coin(p[i],p[j])==true) {
		    			tigers_move=false;
		    		}
		    		else{
		    			
		    			return;
		    		}
		    }
		    catch(NullPointerException e){ 									// activated when move out of range
		    
		    }
	    }
	    else if(p[i].tiger==true && tigers_move == false){
	    	
	    	
	    }
	    else if(tigers_move == false){						//checking "is it goat's turn"
	    	
	    	
	    	if(noOfGoatsInserted<16 && goatInsertionEnded == false && i==j && p[i].vacant == true){		
	    	
	    		
	    		move_coin(p[i],p[j]);			// adds coin
	    		tigers_move = true;
	    		
	    		if(noOfGoatsInserted == 15){
	    			
	    			goatInsertionEnded = true;
	    			
	    			
	    		}
	    		return;
		    	
	    	}
	    	else if(goatInsertionEnded == true && i!=j){
	    		try{
			    	
			    	
			    	if(move_coin(p[i],p[j])==true){
			    		tigers_move=true;																//moves goat
			    	}
			    	else
			    		return;
			    }
			    catch(NullPointerException e){ 								// activated when move out of range
			    	
			    }
	    	}
	  	
	    }
	    else if(p[i].goat==true && tigers_move == true){
	    	
	    	
	    }
	   

	
		
 }						// End of getInput function
	
	boolean isOverJumping(Coin FROM,Coin TO){
		if(		   (FROM.equals(TO.left))   //Checking "over jump"
		|| (FROM.equals(TO.right))
		|| (FROM.equals(TO.top))
		|| (FROM.equals(TO.bottom)
		|| (FROM.equals(TO)	/*This line is for catching conditions i = j i.e. for condition of adding goat */))){
			return false;
			
		}
		
		else
		return true;
	} 									// end of isOverJumping function
	
	boolean isGoatWinner(Coin tiger_status[])throws NullPointerException{
		
		
		
		if ( isGoatWinner(tiger_status[0])==true && isGoatWinner(tiger_status[1]) == true  
				&& isGoatWinner(tiger_status[2]) == true)
			return true;
		else 
			return false;
		
		}
		
	public boolean isGoatWinner(Coin tiger_status){   // function overloading this checked the blocked tiger "tiger_staus"
		
		boolean rightNotClear=false,leftNotClear=false,topNotClear = false,bottomNotClear = false;
		
		if(tiger_status.equals(p[0])){		// special case for p[0]
			if(p[2].vacant == false && p[3].vacant == false && p[4].vacant== false  && p[5].vacant == false &&
					p[8].vacant == false && p[9].vacant == false && p[10].vacant == false && p[11].vacant== false )
				return true;		
			else
				return false;
		}
		
		
		{
			if(tiger_status.right == null){
				
					rightNotClear = true;
			}
			else if(tiger_status.right.tiger == true)
				rightNotClear = true;
			else if(tiger_status.right.goat == true){
				
				if(tiger_status.right.right == null)
					rightNotClear = true;
				else if(tiger_status.right.right.vacant == false)
					rightNotClear = true;
				else 
					rightNotClear = false;
			}
				
		}
		
		if(tiger_status.left == null){
			
			leftNotClear = true;
	}
	else if(tiger_status.left.tiger == true)
		leftNotClear = true;
	else if(tiger_status.left.goat == true){
		
		if(tiger_status.left.left == null)
			leftNotClear = true;
		else if(tiger_status.left.left.vacant == false)
			leftNotClear = true;
		else 
			leftNotClear = false;
	}
		
		if(tiger_status.top == null){
			
			topNotClear = true;
	}
	else if(tiger_status.top.tiger == true)
		topNotClear = true;
	else if(tiger_status.top.goat == true){
		
		if(tiger_status.top.top == null)
			topNotClear = true;
		else if(tiger_status.top.top.vacant == false)
			topNotClear = true;
		else 
			topNotClear = false;
	}
		
		if(tiger_status.bottom == null){
			
			bottomNotClear = true;
	}
	else if(tiger_status.bottom.tiger == true)
		bottomNotClear = true;
	else if(tiger_status.bottom.goat == true){
		
		if(tiger_status.bottom.bottom == null)
			bottomNotClear = true;
		else if(tiger_status.bottom.bottom.vacant == false)
			bottomNotClear = true;
		else 
			bottomNotClear = false;
	}
		
		if(rightNotClear && leftNotClear && topNotClear && bottomNotClear)
			return true;
		
		else 
			return false;
		       
	
	}
	

	
	boolean isTigerWinner(){
		
		if(goatKilled == 4)	{
			this.tigerWon = true;
			return true;
		}
		else 
			return false;
		
		
	}
	
	void display(){
		
		Coin[] Tiger = new Coin[3];
		Coin[] Goat  = new Coin[24];
		
		 Game T_Game = new Game();
		 int[][] a = new int[3][3];
		 
		 int j,i;
		 
		   for(j=0,i=0;i<=22;i++){	
			   if(p[i].tiger==true)
		   	{
		   
		      Tiger[j]=p[i];
		      j++;		 
		      if(j==3) break;
		   	}
			   
			  
		   }
		   
		 a[0][0] 	= 	Tiger[0].X;
		 a[0][1]	=	Tiger[0].Y;
		 
		 a[1][0] 	= 	Tiger[1].X;
		 a[1][1]	=	Tiger[1].Y;
		  
		 a[2][0] 	=	Tiger[2].X;
		 a[2][1]	=	Tiger[2].Y;
		  
		  
		   T_Game.move_tiger_gui(a);									//updates the gui function of tiger
		  
		   
		   	j=0;
		   for(i=0;i<=22;i++)
		   {
		  	 if(p[i].goat==true)
		  	 {	
		  		
		  		 Goat[j]=p[i];
		  		 j++;
		  		 if(j== totalNoOfGoatOnTheBoard) break;
		  		
		  	 }
		   }
	
	   
	 T_Game.move_goat_gui(Goat);  // updates the gui function of goat

	 if(isGoatWinner(Tiger)==true)
		 goatWon = true;
	 if(isTigerWinner()==true)
		 tigerWon = true;
	 
	}
	
	void add_goat(Coin positionForGoat){
		  positionForGoat.goat = true; 
		  positionForGoat.vacant = false;
		  totalNoOfGoatOnTheBoard++;
		  noOfGoatsInserted++;
	  	}
	

	  void add_tiger(Coin positionForTiger){
		  positionForTiger.tiger = true;
		  positionForTiger.vacant = false;
		}
	  
	  
	boolean moveCoinByForce(Coin origin, Coin destination){
		
		boolean moveByForceSuccess = false;
		
		if(origin.tiger == true){
			
			origin.tiger = false;
			destination.tiger = true;
			moveByForceSuccess = true;
			return moveByForceSuccess;
			
		}
		
		else if(origin.goat == true){
			
			origin.tiger = false;
			destination.tiger = true;
			moveByForceSuccess = true;
			return moveByForceSuccess;
		}
		
		else
			return moveByForceSuccess;
		
		
	}
	  
	
	public boolean move_coin(Coin origin,Coin destination){
		  boolean p0=false;
		  boolean moveSuccess = false;
		  
		  if(origin.vacant == true && origin.equals(destination)){
			  this.add_goat(origin);
			  moveSuccess = true;
			  return moveSuccess;
		  }
		  if(origin.vacant == true){  //Checking FROM point for coin
			 
			  moveSuccess = false;
			  return moveSuccess;
		  }
		  

			
			
		  if(destination.point_name.equalsIgnoreCase("0")){//Checking whether destination is p0
			  p0 = true;
		  }
			  
		  
		  if(destination.tiger==false && destination.goat==false){//change of coin position if destination is vacant
			  
			
			
			  if(origin.tiger==true){
				   
				  origin.tiger = false;
				  destination.tiger = true;
				  origin.vacant = true;								//changes the states of this and destination for moving
				  destination.vacant = false;
				  moveSuccess=true;
			  }
			  else if (origin.goat==true){
				  origin.goat=false;
				  destination.goat=true;
				  origin.vacant = true;
				  destination.vacant= false;
				  moveSuccess=true;
			  }
			
			  
		  }
		  else if(destination.tiger==true){
			 
			  moveSuccess = false;
			  return moveSuccess;
		  }
		  else if(destination.goat==true){//checking eat condition
			  
			  	if(p0==true){//if destination is p0 no need to continue checking conditions
			  		
			  		moveSuccess = false;
			  		return moveSuccess;
			  	}
			  	
			  
			  
			  				if((origin.equals(destination.right) 		
			            		 && destination.left.tiger==false
			            		 && destination.left.goat==false  )){
			            	 
			            	 destination.goat = false;
			            	
			            	 	move_coin(origin,destination);	
			            	 	//needs change while creating gui							//moving by 2 step while eating
			            	 move_coin(destination,destination.left);
			            	 
			            	 totalNoOfGoatOnTheBoard--;	//static int for number of goat 
			            	 ++goatKilled;
			            	 moveSuccess=true;
			             }
			             else if((origin.equals(destination.top)
			            		 && destination.bottom.tiger==false
			            		 && destination.bottom.goat==false)){
			            	 destination.goat = false;
			            	 
			            	
			            	 move_coin(origin,destination);
			            	 move_coin(destination,destination.bottom);
			            	
			            	
			            	 totalNoOfGoatOnTheBoard--;	//static int for number of goat 
			            	 ++goatKilled;
			            	 moveSuccess=true;
			             }
			             else if((origin.equals(destination.bottom)
			            		 && destination.top.tiger==false
			            		 && destination.top.goat == false)){
			            	 destination.goat = false;
			            	 
			            	
			            	
			            	 move_coin(origin,destination);
			            	 move_coin(destination,destination.top);
			            	 totalNoOfGoatOnTheBoard--;	//static int for number of goat 
			            	 ++goatKilled;
			            	 moveSuccess=true;
			             }
			             else if((origin.equals(destination.left)
			            		 && destination.right.tiger==false
			            		 && destination.right.goat==false)){
			            	 destination.goat = false;
			          
			            	 move_coin(origin,destination);
			            	 move_coin(destination,destination.right);
			            
			            	 totalNoOfGoatOnTheBoard--;	//static int for number of goat 
			            	 ++goatKilled;
			            	 moveSuccess=true;
			             }
			             else {
			            	
			            	 moveSuccess=false;
			             }
		  }

	  
		  				return moveSuccess;
	  }


}

