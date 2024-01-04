import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;

public class MaxMatch2 {

	public static int getMaxMatch(LinkedList<LinkedList<Integer>> graph, int[] visited, int start) {
		int maxMatch = 0;
		int mm = 0;
		int currI = 0;
		
		if (visited[start] == 1) {
			return 0;
		}
		if (graph.get(start).get(0) == -1) {
			System.out.println("end " + start);
			graph.get(start).remove(0);
			return 1;
		}

		while (graph.get(start).size() != 0 && currI < graph.get(start).size()) {
			if (visited[graph.get(start).get(currI)] == 1) {
				currI++;
				continue;
			}
			visited[start] = 1;
			
			System.out.println(start + " " + currI + "|" + mm + ", " + maxMatch);
			System.out.println(Arrays.toString(visited));
			for (int j = 0; j < graph.size(); j++) {
				System.out.println(graph.get(j));
			}
			System.out.println();
			
			mm = getMaxMatch(graph, visited, graph.get(start).get(currI));
			
			System.out.println(start + " " + currI + "|" + mm + ", " + maxMatch);
			System.out.println(Arrays.toString(visited));
			for (int j = 0; j < graph.size(); j++) {
				System.out.println(graph.get(j));
			}
			System.out.println();
			
			maxMatch += mm;
			visited[start] = 0;

			if (mm != 0) {
				graph.get(graph.get(start).get(currI)).add(start);
				graph.get(start).remove(currI);
				if (start != 0) 
					break;
				
			} else {
				currI++;
			}

		}

		return maxMatch;
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int instances = scnr.nextInt();
		LinkedList<LinkedList<LinkedList<Integer>>> graphs = new LinkedList<LinkedList<LinkedList<Integer>>>();
		int[] perfects = new int[instances];

		for (int i = 0; i < instances; i++) {
			graphs.add(new LinkedList<LinkedList<Integer>>());
			int anodes = scnr.nextInt();
			int bnodes = scnr.nextInt();
			for (int j = 0; j < anodes + bnodes + 1; j++) {
				graphs.get(i).add(new LinkedList<Integer>());
			}
			int edges = scnr.nextInt();
			for (int j = 0; j < edges; j++) {
				int a = scnr.nextInt();
				int b = scnr.nextInt() + anodes;
				graphs.get(i).get(a).add(b);
			}
			for (int j = 1; j <= anodes; j++) {
				graphs.get(i).get(0).add(j);
			}
			for (int j = anodes + 1; j < bnodes + anodes + 1; j++) {
				graphs.get(i).get(j).add(-1);
			}
			if (Math.ceil((double) (anodes + bnodes) / 2) == anodes) {
				perfects[i] = anodes;
			} else {
				perfects[i] = 0;
			}
		}
		scnr.close();
		/*
		 * for (int i = 0; i < instances; i++) { for (int j = 0; j <
		 * graphs.get(i).size(); j++) { System.out.println(graphs.get(i).get(j)); }
		 * System.out.println(); }
		 */
		for (int i = 0; i < instances; i++) {
			// long start = System.currentTimeMillis();
			System.out.println(graphs.get(i));
			String perfect = "N";
			int maxMatch = getMaxMatch(graphs.get(i), new int[graphs.get(i).size()], 0);
			if (perfects[i] == maxMatch) {
				perfect = "Y";
			}
			System.out.println(maxMatch + " " + perfect);
			// System.out.println(System.currentTimeMillis() - start);
		}

	}

}
