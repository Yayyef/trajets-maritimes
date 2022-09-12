package equipe4.atlanticshippingmasters.computation;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
	
	
	// Une classe pour représenter un objet graph
		
		    // Une liste de listes pour représenter une liste de Edge
		    List<List<Edge>> adjList = null;
		 
		    // Constructeur
		    Graphe(List<Edge> edges, int n)
		    {
		        adjList = new ArrayList<>();
		 
		        for (int i = 0; i < n; i++) {
		            adjList.add(new ArrayList<>());
		        }
		 
		        // ajoute des arêtes au graphe orienté
		        for (Edge edge: edges) {
		            adjList.get(edge.source).add(edge);
		        }
		    }
		}
		 


