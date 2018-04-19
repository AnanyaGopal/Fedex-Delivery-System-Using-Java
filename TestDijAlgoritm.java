import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestDijAlgoritm {

public static List<Vertex> nodes;
public static List<Edge> edges;


  public static LinkedList<Vertex> testExcute(int Source, int Destination) {
	  String[] cities = {
			  "Northborough(MA)","Edison(NJ)","Pittsburgh(PA)","Allentown(PA)",
			  "Martinsburg(WV)",
			  "Charlotte(NC)",
			  "Atlanta(GA)",
			  "Orlando(FL)",
			  "Memphis(TN)",
			  "GroveCity(OH)",
			  "Indianapolis(IN)",
			  "Detroit(MI)",
			  "NewBerlin(WI)",
			  "Minneapolis(MN)",
			  "St.Louis(MO)",
			  "Kansas(KS)",
			  "Dallas(TX)",
			  "Houston(TX)",
			  "Denver(CO)",
			  "SaltLakeCity(UT)",
			  "Phoenix(AZ)",
			  "LosAngeles(CA)",
			  "ChinO(CA)",
			  "Sacramento(CA)",
			  "Seattle(WA)"
};  
	  
	nodes = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();
    for (int i = 0; i < cities.length ; i++) {
      Vertex location = new Vertex("Node_"+i,cities[i]);
      nodes.add(location);
    }

    // From zero
    addLane("Edge_0", 0, 10, 10);
    addLane("Edge_1", 0, 4, 5);
    addLane("Edge_2", 0, 9, 13);
    addLane("Edge_3", 0, 5,6);
    
    addLane("Edge_0", 10, 0, 10);
    addLane("Edge_1", 4, 0, 5);
    addLane("Edge_2", 9, 0, 13);
    addLane("Edge_3", 5, 0,6);
    // From one
    addLane("Edge_4", 1, 2, 5);
    addLane("Edge_5", 1, 11,2);
    addLane("Edge_4", 2, 1, 5);
    addLane("Edge_5", 11, 1,2);
    // From two
    addLane("Edge_6", 2, 21, 10);
    addLane("Edge_6", 21, 2, 10);
    
    // From three
    addLane("Edge_7", 3, 10, 15);
    addLane("Edge_8", 3, 4, 16);
    addLane("Edge_7", 10, 3, 15);
    addLane("Edge_8", 4, 3, 16);
    
    
    //From five
    addLane("Edge_9", 5, 7, 7);
    addLane("Edge_9", 7, 5, 7);
    
    //From six 
    addLane("Edge_10", 6, 7, 5);
    addLane("Edge_11", 6, 8, 4);
    addLane("Edge_10", 7, 6, 5);
    addLane("Edge_11", 8, 6, 4);
    // From seven
    addLane("Edge_12", 7, 17, 8);
    addLane("Edge_12", 17, 7, 8);
    
   //From 8
    addLane("Edge_13", 8, 15, 5);
    addLane("Edge_13", 15, 8, 5);
    
    //From 9
    addLane("Edge_14", 9, 21,14);
    addLane("Edge_15", 9, 13,15);
    addLane("Edge_16", 9 , 14,2);
    addLane("Edge_14", 21, 9,14);
    addLane("Edge_15", 13, 9,15);
    addLane("Edge_16", 14 , 9,2);
    
    //From 10
    addLane("Edge_17", 10, 21, 15);
    addLane("Edge_17", 21, 10, 15);
    
    //From 11
    addLane("Edge_18", 11, 12, 4);
    addLane("Edge_18", 12, 11, 4);
    //From 12
    addLane("Edge_19", 12, 13, 8);
    addLane("Edge_20", 12, 21, 4);
    addLane("Edge_19", 13, 12, 8);
    addLane("Edge_20", 21, 12, 4);
    
    //From 13
    addLane("Edge_21", 13, 14,10);
    addLane("Edge_22", 13, 20, 13);
    addLane("Edge_21", 14, 13,10);
    addLane("Edge_22", 20, 13, 13);
    
    //From 14
    addLane("Edge_23", 14, 15, 8);
    addLane("Edge_24", 14, 19, 3);
    addLane("Edge_23", 15, 14, 8);
    addLane("Edge_24", 19, 14, 3);
    
    
    //From 16
    addLane("Edge_25", 16,17,9);
    addLane("Edge_26",16,18,7);
    addLane("Edge_25", 17,16,9);
    addLane("Edge_26",18,16,7);
    
    //From 18
    addLane("Edge_27", 18, 24, 10);
    addLane("Edge_28", 18, 22, 4);
    addLane("Edge_29", 18 , 19,4);
    addLane("Edge_27", 24, 18, 10);
    addLane("Edge_28", 22, 18, 4);
    addLane("Edge_29", 19 , 18,4);

    //From 19
    addLane("Edge_30", 19, 23, 12);
    addLane("Edge_30", 23, 19, 12);
    
    //from 20
    addLane("Edge_31", 20 ,23 ,4);
    addLane("Edge_31", 23 ,20 ,4);
    
   

    // Lets check frm location Loc_1 to Loc_10
    Graph graph = new Graph(nodes,edges);
    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
    dijkstra.execute(nodes.get(Source));
    LinkedList<Vertex> path = dijkstra.getPath(nodes.get(Destination));
   /*
    // Final list of nodes
    for (Vertex vertex : path) {
      System.out.println(vertex);
    } */
   return path;
  }
 
 public static void addLane(String laneId, int sourceLocNo, int destLocNo,
      int duration) {
    Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
    edges.add(lane);
  }
 
 //public static void main(String args[]){
	 
	//System.out.println("hey");
	
 //}
} 