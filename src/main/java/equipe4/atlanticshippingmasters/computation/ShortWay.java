package equipe4.atlanticshippingmasters.computation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ShortWay {
	
	 
	    private static void getRoute(int[] prev, int i, List<Integer> route)
	    {
	        if (i >= 0)
	        {
	            getRoute(prev, prev[i], route);
	            route.add(i);
	        }
	    }
	    
	 
	    // Exécute l'algorithme de Dijkstra sur des ports (graph) donné
	    
	    public static void findShortestPaths(Graphe graph, int source, int n)
	    {
	        // crée un min-heap et pousse le nœud source ayant une distance de 0
	    	
	        PriorityQueue<Node> minHeap;
	        minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
	        minHeap.add(new Node(source, 0));
	 
			/* définit la distance initiale de la source à `v` comme infini */
	        
	        List<Integer> dist;
	        dist = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
	 
	        // la distance de la source à elle-même est nulle
	        dist.set(source, 0);
	 
	        // array booléen pour suivre les sommets pour lesquels le minimum
	        // le coût est déjà trouvé
	        boolean[] done = new boolean[n];
	        done[source] = true;
	 
	        // stocke le prédécesseur d'un sommet (dans un chemin d'impression)
	        int[] prev = new int[n];
	        prev[source] = -1;
	 
	        // exécuter jusqu'à ce que le Min-Heap soit vide
	        while (!minHeap.isEmpty())
	        {
	            // Supprime et renvoie le meilleur sommet
	            Node node = minHeap.poll();
	 
	            // récupère le numéro du sommet
	            int u = node.vertex;
	 
	            // faire pour chaque voisin `v` de `u`
	            
	            for (Edge edge: graph.adjList.get(u))
	            {
	                int v = edge.dest;
	                int weight = edge.weight;
	 
	                // Etape détente
	                if (!done[v] && (dist.get(u) + weight) < dist.get(v))
	                {
	                    dist.set(v, dist.get(u) + weight);
	                    prev[v] = u;
	                    minHeap.add(new Node(v, dist.get(v)));
	                }
	            }
	 
	            // marque le sommet `u` comme terminé pour qu'il ne soit plus repris
	            done[u] = true;
	        }
	 
	        List<Integer> route = new ArrayList<>();
	 
	        for (int i = 0; i < n; i++)
	        {
	            if (i != source && dist.get(i) != Integer.MAX_VALUE)
	            {
	                getRoute(prev, i, route);
	                System.out.printf("Path (%d —> %d): Chemin Minimal = %d, Etape = %s\n",
	                                source, i, dist.get(i), route);
	                route.clear();
	            }
	        }
	    }
	 
	    public static void main(String[] args)
	    {
	        // initialise les bords selon le diagramme ci-dessus
	        // (u, v, w) représente le bord du sommet `u` au sommet `v` ayant le poids `w`
	        List<Edge> edges = Arrays.asList(
	                new Edge(0, 1, 10), new Edge(0, 4, 3), new Edge(1, 2, 2),
	                new Edge(1, 4, 4), new Edge(2, 3, 9), new Edge(3, 2, 7),
	                new Edge(4, 1, 1), new Edge(4, 2, 8), new Edge(4, 3, 2)
	        );
	 
	        // nombre total de nœuds dans le graphe (étiquetés de 0 à 4)
	        
	        int n = 5;
	 
	        // construit le graphe
	        
	        Graphe graph = new Graphe(edges, n);
	 
	        // exécute l'algorithme de Dijkstra à partir de chaque nœud
	        
	        for (int source = 0; source < n; source++) {
	            findShortestPaths(graph, source, n);
	        }
	    }
	}

	

