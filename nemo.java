import java.io.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import oracle.sql.*; 


public class NemoDemo extends JFrame {
	
	Connection conn; 
	public  Connection connectdatabase(){ 
		   String url;
		    url = "jdbc:oracle:thin:@localhost:1521:xe";	    	    
		    try {   // loads drivers
		      Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		    }
		    catch (Exception e) {
		      System.out.println("MR.UnitSitQueries.constructor.Exception: " + e);
		    }
		    try{
		      conn = DriverManager.getConnection(url,"system","oracle");}
		    catch (SQLException e) {System.out.println("OOPS");}
		   return conn;
	 }
	String s="Please select a menu for topological query :)";
    JTextArea res=new JTextArea(5,40);   
    Font font=new Font("Arial", Font.BOLD, 16);    
	public NemoDemo() {
	      super("Nemo VS 8");
	      this.setBounds(10,10,750,850);//x 10 y 30 length1200 width800 
	      
	      JMenuBar mbar=new JMenuBar();
	      setJMenuBar(mbar);
	      JMenu queryMenu= new JMenu("spatial");
	      JMenu dbMenu= new JMenu("database");
	      JMenu clearMenu= new JMenu("clear");
	      mbar.add(queryMenu);
	      mbar.add(dbMenu);
	      mbar.add(clearMenu);
	      String dir="C:\\Users\\Administrator\\Desktop\\cs615 spatial\\icon\\";
	      JMenuItem addrow=new JMenuItem("add bird", new ImageIcon(dir+"addrow.gif"));
	      JMenuItem delrow=new JMenuItem("delete bird",new ImageIcon(dir+"delete.gif"));
	      JMenuItem smile=new JMenuItem("smile",new ImageIcon(dir+"smile.jpg"));
	      JMenuItem sad=new JMenuItem("sad",new ImageIcon(dir+"sad.jpg"));
	      JMenuItem relate=new JMenuItem("relate",new ImageIcon(dir+"relate.gif"));
	      JMenuItem intersection=new JMenuItem("intersection",new ImageIcon(dir+"intersection.jpg"));
	      JMenuItem mbr=new JMenuItem("mbr",new ImageIcon(dir+"mbr.jpg"));
	      JMenuItem area=new JMenuItem("area",new ImageIcon(dir+"area.jpg"));
	      JMenuItem distance=new JMenuItem("distance",new ImageIcon(dir+"distance.gif"));
	      JMenuItem length=new JMenuItem("length",new ImageIcon(dir+"len.jpg"));
	      JMenuItem diff=new JMenuItem("difference",new ImageIcon(dir+"diff.jpg"));
	      JMenuItem near=new JMenuItem("near",new ImageIcon(dir+"near.gif"));
	      JMenuItem convexhull=new JMenuItem("convexhull",new ImageIcon(dir+"convexhull.jpg"));
	      JMenuItem arcdensity=new JMenuItem("arcdensity",new ImageIcon(dir+"density.jpg"));
	     
	  	      
	      
	      dbMenu.add(addrow);
	      dbMenu.add(delrow);
	      dbMenu.add(smile);
	      dbMenu.add(sad);
	      queryMenu.add(relate);
	      queryMenu.add(intersection);
	      queryMenu.add(mbr);
	      queryMenu.add(area);
	      queryMenu.add(distance);
	      queryMenu.add(length);
	      queryMenu.add(diff);
	      queryMenu.add(near);
	      queryMenu.add(convexhull);
	      queryMenu.add(arcdensity);
	      

	      JScrollPane scrollpane=new JScrollPane(res);
	      res.setText(s);
	      res.setEditable(false);
	      res.setLineWrap(true);
	      res.setFont(font);
	      res.setForeground(Color.BLACK);
	      res.setBackground(Color.GREEN);
	      this.add(scrollpane, BorderLayout.CENTER);
	      
	      MyPanel mypanel=new MyPanel();	     	  
	      getContentPane().add(mypanel);	         
	      getContentPane().add(res,BorderLayout.SOUTH);
	      
	      
	      this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				String str = "Mouse is at:(X:" + e.getX() + ",Y:" + e.getY() +")";
				res.setText(str);
				System.out.println(str);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	  
	      });
	      clearMenu.addMenuListener(
	              new MenuListener(){
	                 
					@Override
					public void menuSelected(MenuEvent e) {
						res.setText("");
						if(res.getBackground()==Color.WHITE)
						res.setBackground(Color.YELLOW);
						else
					    res.setBackground(Color.WHITE);
						
					}
					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						//res.setBackground(Color.GREEN);
					}
					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
	          });
	      addrow.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	
	            	//System.out.println(conn);	           

	            	String insert="INSERT INTO picture VALUES (50,'bird2',MDSYS.SDO_GEOMETRY (2004,null,null,MDSYS.SDO_ELEM_INFO_ARRAY(1,4,2, 1,2,2, 5,2,2),MDSYS.SDO_ORDINATE_ARRAY(400,612,410,620,420,612,430,620,440,612)))";
	            	Statement statement;
					try {
						statement = conn.createStatement();					
						statement.executeUpdate(insert);
						conn.commit();
						res.setText("You inserted a bird");
						getContentPane().repaint();
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
						res.setText("You have already INSERTED a bird,  dummy!!!");
					}
					
					
	                System.out.println("Inserted record in the table");
	                
	            }
	        });
	      
	      
	      delrow.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           

	            	Statement statement;
	            	String delete="delete from picture where id=50";
					try {
						statement = conn.createStatement();					
						statement.executeUpdate(delete);
						conn.commit();
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					res.setText("You deleted a bird");
					getContentPane().repaint();
					
	                System.out.println("Delete record in the table");
	            }
	            
	       });
	      smile.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           

	            	Statement statement;
	            	String delete="delete from picture where id=49";
	            	String smile="INSERT INTO picture VALUES (51,'smile',MDSYS.SDO_GEOMETRY (2002,null,null,MDSYS.SDO_ELEM_INFO_ARRAY(1,2,2), MDSYS.SDO_ORDINATE_ARRAY(428,402,408,408,388,424)))";
					try {
						statement = conn.createStatement();					
						statement.executeUpdate(delete);
						statement.executeUpdate(smile);
						conn.commit();
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					res.setText("Smile Nemo  :)");
					getContentPane().repaint();
					
	                System.out.println("Smile Nemo  :) ");
	            }
	            
	       });
	      sad.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           

	            	Statement statement;
	            	String delete="delete from picture where id=51";
	            	String sad="INSERT INTO picture VALUES (49,'nemomouth',MDSYS.SDO_GEOMETRY (2006, null,null,MDSYS.SDO_ELEM_INFO_ARRAY(1,2,1),MDSYS.SDO_ORDINATE_ARRAY(381,417,391,422,391,411,401,416,401,405,411,411,411,399,421,406,421,393)))";
					try {
						statement = conn.createStatement();					
						statement.executeUpdate(delete);
						statement.executeUpdate(sad);
						conn.commit();
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					res.setText("Sad Nemo  :( ");
					getContentPane().repaint();
					
	                System.out.println("Sad Nemo");
	            }
	            
	       });
	      relate.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;
	            	String query="SELECT c.name,SDO_GEOM.RELATE(c.shape, 'determine', c_b.shape, 0.005) relationship FROM picture c, picture c_b WHERE c_b.name = '81'";	            
	            		            		         	
	            	try {
						statement = conn.createStatement();					
						ResultSet rs=statement.executeQuery(query);
						//res.setText("");
						
						 while (rs.next()) {
						        String s1 = rs.getString("NAME");
						        String s2 = rs.getString("RELATIONSHIP");						       
						        res.setText(s1+"   "+s2+"\n");
						        System.out.println(s1+"   "+s2);						        
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
           
	            }
	            
	       });
	      intersection.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;	                  
	            	String query="SELECT SDO_GEOM.SDO_MBR(c.shape, m.diminfo) as shp FROM picture c, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c.name = 'M'";	            	
	            	try {
						statement = conn.createStatement();					
						 ResultSet rs=statement.executeQuery(query);
						 res.setText("");					
						 while (rs.next()) {
							   java.sql.Struct o = (java.sql.Struct) rs.getObject("shp");
						        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o.getAttributes()[3];
						        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o.getAttributes()[4];
						        int len = oa3.length();
						        int [] ia3 = oa3.getIntArray();
						        int [] ia4 = oa4.getIntArray();
						        res.append("The interection has info array: ");
						        System.out.println("The interection has info array: ");
						        for (int j = 0;j<len;j++)
						          res.append(ia3[j] + ",");
						         // System.out.print(ia3[j] + ",");
						        res.append("\n");
						        System.out.println();
						        res.append("The interection has coordinate array: ");
						        System.out.println("The interection has coordinate array: ");
						        for (int i = 0;i<oa4.length();i++)
						        	res.append(ia4[i] + ",");
						          //System.out.print(ia4[i] + ",");
						        System.out.println();		        
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
         
	            }
	            
	       });
	      mbr.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;	                  
	            	String query="SELECT SDO_GEOM.SDO_MBR(c.shape, m.diminfo) as shp FROM picture c, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c.name = 'sky1'";	            	
	            	try {
						statement = conn.createStatement();					
						 ResultSet rs=statement.executeQuery(query);
						 res.setText("");
						 while (rs.next()) {
							   java.sql.Struct o = (java.sql.Struct) rs.getObject("shp");
						        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o.getAttributes()[3];
						        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o.getAttributes()[4];
						        int len = oa3.length();
						        int [] ia3 = oa3.getIntArray();
						        int [] ia4 = oa4.getIntArray();
						        System.out.println("The interection has info array: ");
						        res.append("The interection has info array: ");
						        for (int j = 0;j<len;j++)
						          //System.out.print(ia3[j] + ",");
						          res.append(ia3[j] + ",");
						        System.out.println();
						        res.append("\n"+"The interection has coordinate array: ");
						        System.out.println("The interection has coordinate array: ");
						        for (int i = 0;i<oa4.length();i++)
						         // System.out.print(ia4[i] + ",");
						        	 res.append(ia4[i] + ",");
						        System.out.println();		        
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
       
	            }
	            
	       });
	      area.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;	                  
	            	String query="SELECT name, SDO_GEOM.SDO_AREA(shape, 0.005) as area FROM picture";	            	
	            	try {
						statement = conn.createStatement();					
						 ResultSet rs=statement.executeQuery(query);
						 res.setText("");
						 while (rs.next()) {
							   String s1 = rs.getString("name"); 
							   Float f = rs.getFloat("area");
							   if(f>0)
							   res.append(s1+"  "+f+"\n");
							   //System.out.println(s1+"  "+f);							   
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
     
	            }
	            
	       });
	      distance.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;	                  
	            	String query="SELECT SDO_GEOM.SDO_DISTANCE(c_b.shape, c_d.shape, 0.005) as dis FROM picture c_b, picture c_d WHERE c_b.name = 'bird' AND c_d.name = 'sky1'";	            	
	            	try {
						statement = conn.createStatement();					
						 ResultSet rs=statement.executeQuery(query);
						 res.setText("");
						 while (rs.next()) {
							   Float f = rs.getFloat("dis");
							   res.append("the distance between bird and sky is:"+f+"\n");
							   System.out.println("the distance between bird and sky is:"+f);							   
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
   
	            }
	            
	       });
	     length.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;	                  
	            	String query="SELECT c.name, SDO_GEOM.SDO_LENGTH(c.shape, m.diminfo) as len FROM picture c, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c.name='sky1'";	            	
	            	try {
						statement = conn.createStatement();					
						 ResultSet rs=statement.executeQuery(query);
						 res.setText("");
						 while (rs.next()) {
							   Float f = rs.getFloat("len");
							   res.append("the length of sky is:"+f+"\n");
							   System.out.println("the length of sky is:"+f);							   
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
 
	            }
	            
	       });
	  
	    diff.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {	           
	            	Statement statement;	                  
	            	String query="SELECT SDO_GEOM.SDO_DIFFERENCE(c_a.shape, m.diminfo, c_c.shape, m.diminfo) as shp FROM PICTURE c_a, PICTURE c_c, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c_a.name = 'nemoeye3' AND c_c.name = '81'";	            	
	            	try {
						statement = conn.createStatement();					
						 ResultSet rs=statement.executeQuery(query);
						 res.setText("");
						 while (rs.next()) {
							   java.sql.Struct o = (java.sql.Struct) rs.getObject("shp");
						        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o.getAttributes()[3];
						        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o.getAttributes()[4];
						        int len = oa3.length();
						        int [] ia3 = oa3.getIntArray();
						        int [] ia4 = oa4.getIntArray();
						        System.out.println("The difference between nemo's eye and letter has info array: ");
						        res.append("The difference between nemo's eye and letter has info array: "+"\n");
						        for (int j = 0;j<len;j++)
						          //System.out.print(ia3[j] + ",");
						        	 res.append(ia3[j] + ",");
						        System.out.println();
						        res.append("\n"+"The difference between nemo's eye and letter has coordinate array: "+"\n");
						        System.out.println("The difference between nemo's eye and letter has coordinate array: ");
						        for (int i = 0;i<oa4.length();i++)
						          //System.out.print(ia4[i] + ",");
						           res.append(ia4[i] + ",");
						        System.out.println();		        
						      }
						 
					    } 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
      
	            }
	            
	       });
	    near.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {	           
            	Statement statement;	                  
            	String query="SELECT SDO_GEOM.WITHIN_DISTANCE(c_b.shape, m.diminfo, 1,c_d.shape, m.diminfo) as bool FROM picture c_b, picture c_d, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c_b.name = 'E1' AND c_d.name = 'E2'";	            	
            	try {
					statement = conn.createStatement();					
					 ResultSet rs=statement.executeQuery(query);
					 res.setText("");
					 while (rs.next()) {
						   String b = rs.getString("bool");
						   res.append("wheather letter e is within unit 1 distance:"+b+"\n");
						   System.out.println("wheather letter e is within unit 1 distance:"+b);							   
					      }
					 
				    } 
				catch (SQLException e1) {
					e1.printStackTrace();
				}

            }
            
       });
	    
	    convexhull.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {	           
            	Statement statement;	                  
            	String query="SELECT SDO_GEOM.SDO_CONVEXHULL(c.shape, m.diminfo) as shp FROM picture c, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c.name = 'ship1'";	            	
            	try {
					statement = conn.createStatement();					
					 ResultSet rs=statement.executeQuery(query);
					 res.setText("");
					 while (rs.next()) {						 
						   java.sql.Struct o = (java.sql.Struct) rs.getObject("shp");
					        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o.getAttributes()[3];
					        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o.getAttributes()[4];
					        int len = oa3.length();
					        int [] ia3 = oa3.getIntArray();
					        int [] ia4 = oa4.getIntArray();
					        System.out.println("The convex hull of ship has info array: ");
					        res.append("The convex hull of ship has info array: "+"\n");
					        for (int j = 0;j<len;j++)
					          //System.out.print(ia3[j] + ",");
					          res.append(ia3[j] + ",");
					        System.out.println();
					        System.out.println("The convex hull of ship has coordinate array: ");
					        res.append("\n"+"The convex hull of ship has coordinate array: "+"\n");
					        for (int i = 0;i<oa4.length();i++)
					          //System.out.print(ia4[i] + ",");
					          res.append(ia4[i]  + ",");
					        System.out.println();		        
					      }
					 
				    } 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
  
            }
            
       });
	    
	    arcdensity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {	           
            	Statement statement;	                  
            	String query="SELECT SDO_GEOM.SDO_ARC_DENSIFY(c.shape, m.diminfo, 'arc_tolerance=0.05') as shp FROM picture c, user_sdo_geom_metadata m WHERE m.table_name = 'PICTURE' AND m.column_name = 'SHAPE' AND c.name = 'S1'";	            	
            	try {
					statement = conn.createStatement();					
					 ResultSet rs=statement.executeQuery(query);
					 res.setText("");
					 while (rs.next()) {						 
						   java.sql.Struct o = (java.sql.Struct) rs.getObject("shp");
					        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o.getAttributes()[3];
					        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o.getAttributes()[4];
					        int len = oa3.length();
					        int [] ia3 = oa3.getIntArray();
					        int [] ia4 = oa4.getIntArray();
					        System.out.println("The arc densification of digit 8 has info array: ");
					        res.append("The convex hull of ship has info array: "+"\n");
					        for (int j = 0;j<len;j++)
					          //System.out.print(ia3[j] + ",");
					        	res.append(ia3[j] + ",");
					        System.out.println();
					        System.out.println("The arc densification of digit 8 has coordinate array: ");
					        res.append("\n"+"The arc densification of digit 8 has coordinate array: "+"\n");
					        for (int i = 0;i<oa4.length();i++)
					          //System.out.print(ia4[i] + ",");
					          res.append(ia4[i] + ",");
					        System.out.println();		        
					      }
					 
				    } 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
  
            }
            
       });
	  
	}
	      

	public static void main(String [] args) {
		
		NemoDemo Nemo8= new NemoDemo();

		Nemo8.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Thanks, Nemo VS 8 exits");
                System.exit(0);
            }
        });
		Nemo8.setVisible(true);	
	
	}
			  


class MyPanel extends JPanel
{
	public MyPanel()
    {
    	setBackground(Color.white);
    	
    }

 public void paintComponent (Graphics g)
 {
     super.paintComponent (g);
     g.setColor(Color.black);
     draw(g);
 
 }
 

 
 public void draw(Graphics g){

		    Statement stmt;
		    Connection conn=connectdatabase();
		    String query = "select * from picture";

		    try {
	      
		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(query);
		      while (rs.next()) {
		        String s1 = rs.getString("id");
		        String s2 = rs.getString("NAME");
		        java.sql.Struct o1 = (java.sql.Struct) rs.getObject("shape");
		     
		        oracle.sql.ARRAY oa3 = (oracle.sql.ARRAY) o1.getAttributes()[3];
		        oracle.sql.ARRAY oa4 = (oracle.sql.ARRAY) o1.getAttributes()[4];

		        int [] ia3 = oa3.getIntArray();
		        int [] ia4 = oa4.getIntArray();
		        
		        if(ia3[1]==4||(ia3[1]==2&&ia3[2]==2))
		        {
		        	
		        	 if(ia3[1]==2)
		        	 {
		        		if(ia4[0]==ia4[4]||ia4[1]==ia4[5]){
		        			
		        			if(ia4[0]<ia4[4]){
		        				
		        				if(ia4[3]>ia4[1]){g.drawArc(ia4[0],750-ia4[3],ia4[4]-ia4[0],(ia4[3]-ia4[1])*2,180,-180);}
		        				else{g.drawArc(ia4[0],750-ia4[1]-(ia4[1]-ia4[3]),ia4[4]-ia4[0],(ia4[1]-ia4[3])*2,180,180);}
		        			}
		        			if(ia4[1]>ia4[5]){
		        			
		        				if(ia4[2]<ia4[0]){g.drawArc(ia4[2],750-ia4[1],(ia4[0]-ia4[2])*2,ia4[1]-ia4[5],90,180);}
		        				else{g.drawArc(ia4[0]-(ia4[2]-ia4[0]),750-ia4[1],(ia4[2]-ia4[0])*2,ia4[1]-ia4[5],90,-180);}
		        			}		      	
		        			
		        		}
		        		else{
		        			if((ia4[0]<ia4[4])&&(ia4[1]<ia4[5])&&((float)(ia4[3]-ia4[1])/(float)(ia4[2]-ia4[0])>(float)(ia4[5]-ia4[1])/(float)(ia4[4]-ia4[0]))){
		        				
		        				g.drawArc(ia4[0],750-ia4[5],(ia4[4]-ia4[0])*2,(ia4[5]-ia4[1])*2,90,90);
		        			}
                            if((ia4[0]>ia4[4])&&(ia4[1]>ia4[5])&&((float)(ia4[3]-ia4[5])/(float)(ia4[2]-ia4[4])<(float)(ia4[1]-ia4[5])/(float)(ia4[0]-ia4[4]))){
		        				
		        				g.drawArc(ia4[4]-(ia4[0]-ia4[4]),750-(ia4[1]+(ia4[1]-ia4[5])),(ia4[0]-ia4[4])*2,(ia4[1]-ia4[5])*2,0,-90);
                            }
                            if((ia4[0]<ia4[4])&&(ia4[1]>ia4[5])&&((float)(ia4[3]-ia4[5])/(float)(ia4[4]-ia4[2])>(float)(ia4[1]-ia4[5])/(float)(ia4[4]-ia4[0]))){
                            	                                                    
		        				g.drawArc(ia4[0]-(ia4[4]-ia4[0]),750-ia4[1],(ia4[4]-ia4[0])*2,(ia4[1]-ia4[5])*2,0,90);
                            }
                            if((ia4[0]>ia4[4])&&(ia4[1]<ia4[5])&&((float)(ia4[5]-ia4[3])/(float)(ia4[2]-ia4[4])>(float)(ia4[5]-ia4[1])/(float)(ia4[0]-ia4[4]))){
                                                                                  
		        				g.drawArc(ia4[4],750-(ia4[5]+ia4[5]-ia4[1]),(ia4[0]-ia4[4])*2,(ia4[5]-ia4[1])*2,180,90);
                            }
                            
		        		}
		        		
		        	 }
		        	 else{
		        		 if(ia4[0]==ia4[4]||ia4[1]==ia4[5]){
		        		   for(int i=0;i<=(oa4.length()-5)/4;i++){		        			
		        			 if(ia4[4*i+3]>ia4[4*i+1]){g.drawArc(ia4[4*i+0],750-ia4[4*i+3],ia4[4*i+4]-ia4[4*i+0],(ia4[4*i+3]-ia4[4*i+1])*2,180,-180);}
			        		 else{g.drawArc(ia4[4*i+0],750-ia4[4*i+1]-(ia4[4*i+1]-ia4[4*i+3]),ia4[4*i+4]-ia4[4*i+0],(ia4[4*i+1]-ia4[4*i+3])*2,180,180);}
		        		     }
		        			
		        		   }
		        		
		        	     }
		        	    		        				         
		        	 }
		          if(ia3[1]==1003&&ia3[2]==4){
		        	  g.drawOval(ia4[0],750-(ia4[1]+ia4[2]-ia4[4]),ia4[2]-ia4[0],ia4[2]-ia4[0]);
		        	
		          }
		          if(ia3[1]==1003&&ia3[2]==1){
		        	  int num=oa4.length()/2;
		        	  int j=0;
		        	  int k=0;
		        	  int [ ] x=new int[num];
		        	  int [ ] y=new int[num];
		        	  for(int i=0;i<num*2;)
		        	  {
		        		  x[j++]=ia4[i++];
		        		  y[k++]=750-ia4[i++];
		        				        		
		        	  }
		        	  g.drawPolygon(x, y, num);
		        	  		        		        
		          }
		          if(ia3[1]==2&&ia3[2]==1)
		              {
		        	  for(int j=0;j<oa4.length()/2-1;j++){
		        		  g.drawLine(ia4[j*2+0],750-(ia4[j*2+1]),ia4[j*2+2],750-(ia4[j*2+3]));
		        	  }
		          }		        	
		        	
		        }
		      
		    }
		   catch (SQLException e) {System.out.println("OOPS");}
		  
 }
 
}    

}