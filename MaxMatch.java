import java.util.Scanner;
//import java.util.Arrays;
import java.util.LinkedList;

public class MaxMatch {

	public static int getMaxFlow(int[][] graph, int[] visited, int startI) {
		int maxFlow = 0;
		int mf = 0;

		if (startI >= graph.length - 1) {
			return 1;
		}
		if (visited[startI] == 1) {
			return 0;
		}

		for (int i = 0; i < graph.length; i++) {
			if (graph[startI][i] > 0 && visited[i] == 0) {
				visited[startI] = 1;
				//tempMax = Math.min(max, graph[startI][i]);
				mf = getMaxFlow(graph, visited, i);

				graph[i][startI] += mf;
				graph[startI][i] -= mf;
				visited[startI] = 0;
				maxFlow += mf;
				/*
				System.out.println(startI + " " + i + "|" + tempMax + ", " + mf + ", " + maxFlow);
				System.out.println(Arrays.toString(visited));
				for (int j = 0; j < graph.length; j++) {
					System.out.println(Arrays.toString(graph[j]));
				}
				System.out.println();
				*/
				if (startI == 0) {
					if (mf == 0) {
						graph[startI][i] = 0;
					} else {
						i = 0;
					}
				} else if (mf != 0) {
					break;
				}
			}
		}

		return maxFlow;
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int instances = scnr.nextInt();
		LinkedList<int[][]> graphs = new LinkedList<int[][]>();
		int[] perfects = new int[instances]; 

		for (int i = 0; i < instances; i++) {
			int lengthA = scnr.nextInt();
			int nodes = lengthA + scnr.nextInt() + 2;
			graphs.add(new int[nodes][nodes]);
			int edges = scnr.nextInt();
			for (int j = 0; j < edges; j++) {
				int a = scnr.nextInt();
				int b = scnr.nextInt();
				graphs.get(i)[a][b + lengthA] = 1;
				
			}
			for (int j = 0; j < nodes - 1; j++) {
				graphs.get(i)[0][j % lengthA + 1] = 1;
				if (j > lengthA)
					graphs.get(i)[j][graphs.get(i).length - 1] = 1;
			}
			if (Math.ceil((double)(nodes - 2)/2) == lengthA) {
				perfects[i] = lengthA;
			} else {
				perfects[i] = 0;
			}
		}
		scnr.close();

		/*
		 for (int i = 0; i < instances; i++) { for (int j = 0; j <
		 graphs.get(i).length; j++) {
		 System.out.println(Arrays.toString(graphs.get(i)[j])); }
		 System.out.println(); }
		 */
		
		for (int i = 0; i < instances; i++) {
			long start = System.currentTimeMillis();
			String perfect = "N";
			int maxFlow = getMaxFlow(graphs.get(i), new int[graphs.get(i).length], 0);
			if (perfects[i] == maxFlow) {
				perfect = "Y";
			}
			System.out.println(maxFlow + " " + perfect);
			//System.out.println(System.currentTimeMillis() - start);
		}
	}

}
