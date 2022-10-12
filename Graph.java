package hw11;

import java.util.*;

public class Graph {
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	public Vertex vertexList[];
	private int adjMat[][];
	private int nVerts;
	

	static class Vertex {
		public char label;
		public boolean isInTree;

		public Vertex(char lab)
		{
			label = lab;
			isInTree = false;
		}

	}

	static class Edge implements Comparable<Edge> {
		public int srcVert;
		public int destVert;
		public int distance;


		public Edge(int sv, int dv, int d)
		{
			srcVert = sv;
			destVert = dv;
			distance = d;
		}
		
		public int compareTo(Edge that){
			if (this.distance<that.distance) return -1;
			else if (this.distance==that.distance) return 0;
			else return 1;
		}
		
		public String toString(){
			return "(" + srcVert + "->" + destVert + ":" + distance + ")"; 
		}
	}
	
	public Graph()
	{
		vertexList = new Vertex[MAX_VERTS];

		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int j = 0; j < MAX_VERTS; j++)
			for (int k = 0; k < MAX_VERTS; k++)
				adjMat[j][k] = INFINITY;
	}
	
	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}


	public void addEdge_undirected(int start, int end, int weight) {
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
	}

	public void addEdge_directed(int start, int end, int weight) {
		adjMat[start][end] = weight;
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}
	
	public Queue<Edge> kruskalMST(){
		Queue<Edge> mst = new LinkedList<Edge>();
		Edge[] ea = new Edge[(nVerts * (nVerts-1) / 2)];
		int next = 0;
		
		for (int c = 0; c < nVerts; c++) {
			for (int c0 = c+1; c0 < nVerts; c0++) {
				if (adjMat[c][c0] != INFINITY) {
					ea[next]=(new Edge(c,c0,adjMat[c][c0]));
					next++;
				}
			}
		}
		
		for (int c0 = 0; c0 < next; c0++) {
			for (int c = 0; c < next-1; c++) {
				if (ea[c].compareTo(ea[c+1]) == 1) {
					Edge temp = ea[c];
					ea[c] = ea[c+1];
					ea[c+1] = temp;
				}
			}
		}
		
		for (int c = 0; c < next; c++) {
			mst.add(ea[c]);
		}
		
		UnionFind u = new UnionFind(nVerts);
		
		for (int c = 0; c < next; c++) {
			Edge e = mst.remove();
			if (u.find(e.srcVert) != u.find(e.destVert)) {
				mst.add(e);
				u.union(e.srcVert, e.destVert);
			}
		}
		
		
		
		return mst;
	}
	
	
	public String[] bellman_ford(int source){
		int[] distance = new int[nVerts];
		int[] parent = new int[nVerts];
		String[] shortestPaths = new String[nVerts-1];
		
		int next = 0;
		
		for (int c = 0; c < nVerts; c++) {
			distance[c] = INFINITY;
		}
		
		distance[source] = 0;
		
		for (int c = 0; c < nVerts; c++) {
			for (int c0 = 0; c0 < nVerts; c0++) {
				if (distance[c0] > distance[c] + adjMat[c][c0]) {
					distance[c0] = distance[c] + adjMat[c][c0];
					parent[c0] = c;
				}
			}
		}
		
		for (int c = 0; c < nVerts; c++) {
			for (int c0 = 0; c0 < nVerts; c0++) {
				if (distance[c0] > distance[c] + adjMat[c][c0]) {
					return null;
				}
			}
			if (distance[c] != 0) {
				shortestPaths[next] = vertexList[source].label + "->" + vertexList[c].label + ":" + path(c,parent,shortestPaths) + ":" + distance[c];
				next++;
			}
		}
		
		
		return shortestPaths;
	}
	
	String path(int c, int[] p, String[] sp) {
		if (c == 0) {
			return "" + vertexList[c].label;
		}
		else {
			return path(p[c], p, sp) + "->" + vertexList[c].label;
		}
	}
	
	public int[][] floyd_warshall(){
		int[][] distance = new int[nVerts][nVerts];
		for (int c = 0; c < nVerts; c++) {
			for (int c0 = 0; c0 < nVerts; c0++) {
				distance[c][c0] = INFINITY;
			}
		}
		
		
		
		for (int k = 0; k < nVerts; k++) {
			for (int j = 0; j < nVerts; j++) {
				for (int i = 0; i < nVerts; i++) {
					distance[i][i] = 0;
					for (int c = 0; c < nVerts; c++) {
						if (distance[i][c] > adjMat[i][c]) {
							distance[i][c] = adjMat[i][c];
						}
					}
					if ((distance[i][k]+distance[k][j]) < distance[i][j]) {
						distance[i][j] = (distance[i][k]+distance[k][j]);
					}
				}
			}
		}
		return distance;
	}

	public static void main(String[] args) {
		// Problem 1
		Graph theGraph1 = new Graph();
		theGraph1.addVertex('a'); // 0 (start for mst)
		theGraph1.addVertex('b'); // 1
		theGraph1.addVertex('c'); // 2
		theGraph1.addVertex('d'); // 3
		theGraph1.addVertex('e'); // 4
		theGraph1.addVertex('f'); // 5

		theGraph1.addEdge_undirected(0, 1, 6); // ab 6
		theGraph1.addEdge_undirected(0, 4, 4); // ae 4
		theGraph1.addEdge_undirected(1, 2, 10); //bc 10
		theGraph1.addEdge_undirected(1, 4, 7); // be 7
		theGraph1.addEdge_undirected(1, 5, 7); // bf 7
		theGraph1.addEdge_undirected(2, 3, 6); // cd 6
		theGraph1.addEdge_undirected(2, 4, 8); // ce 8
		theGraph1.addEdge_undirected(2, 5, 5); // cf 6
		theGraph1.addEdge_undirected(3, 5, 7); // df 12
		theGraph1.addEdge_undirected(4, 5, 12); // ef 12

		System.out.println("Minimum spanning tree: ");
		Queue<Edge> q = theGraph1.kruskalMST(); // minimum spanning tree
		System.out.println();
		while (!q.isEmpty()) {
			System.out.println(q.remove().toString());
		}
		
		// Problem 2
		Graph theGraph2 = new Graph();
		theGraph2.addVertex('a'); // 0
		theGraph2.addVertex('b'); // 1
		theGraph2.addVertex('c'); // 2
		theGraph2.addVertex('d'); // 3
		theGraph2.addVertex('e'); // 4

		theGraph2.addEdge_directed(0, 1, 2); // ab 2
		theGraph2.addEdge_directed(0, 2, 3); // ac 3
		theGraph2.addEdge_directed(1, 3, 1); // bd 1
		theGraph2.addEdge_directed(2, 1, 2); //cb -2
		theGraph2.addEdge_directed(2, 4, 1); // ce 1
		theGraph2.addEdge_directed(4, 3, 4); // cd 6
		
		System.out.println("Single-source Shortest Paths ");
		String[] a = theGraph2.bellman_ford(0); // shortest paths
		System.out.println();
		for (int c = 0; c < a.length; c++)
		{
			System.out.println(a[c]);
		}
		Graph theGraph3 = new Graph();
		theGraph3.addVertex('1'); // 0 (start for mst)
		theGraph3.addVertex('2'); // 1
		theGraph3.addVertex('3'); // 2
		theGraph3.addVertex('4'); // 3
		
		theGraph3.addEdge_directed(0, 1, 8); // '1-2' 8
		theGraph3.addEdge_directed(0, 3, 1); // '1-4' 1
		theGraph3.addEdge_directed(1, 2, 1); // '2-1' 1
		theGraph3.addEdge_directed(2, 0, 4); // '3-1' 4 
		theGraph3.addEdge_directed(3, 1, 2); // '4-2' 2
		theGraph3.addEdge_directed(3, 2, 9); // '4-3' 9
		
		System.out.println("All Pairs Shortest Paths ");
		int[][] fw = theGraph3.floyd_warshall();
		for (int c = 0; c < fw.length; c++) {
			for (int c0 = 0; c0 < fw[c].length; c0++) {
				System.out.print(fw[c][c0] + " ");
			}
			System.out.println();
		}
	}
}