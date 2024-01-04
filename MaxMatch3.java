//import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MaxMatch3 {
	
	public static int getMaxMatch(LinkedList<LinkedList<Integer>> graph) {
		int maxFlow = 0;
		int curr = 0;
		int prev;
		int rmCount = 0;
		int[] visited = new int[graph.size() + 1];
		Stack<Integer> list = new Stack<Integer>();
		for (int x : graph.get(0)) {
			list.add(x);
		}
		visited[0] = 1;
		
		while (!list.empty()) {
			prev = (list.size() > 1)? list.elementAt(list.size() - 2): list.peek();
			curr = list.peek();
			
			if (visited[curr] == 1) {
				//System.out.println("catchv");
				list.pop();
				if (rmCount > 0) rmCount--;
				continue;
			}
			
			if (graph.get(curr).isEmpty()) {
				//System.out.println("catche");
				list.pop();
				visited[curr] = 1;
				if (rmCount > 0) rmCount--;
				continue;
			}
			
			visited[curr] = 1;
			if (graph.get(curr).get(0) == -1) {	
				//System.out.println("end " + prev + " " + curr + ", rCount:" + rmCount);
				maxFlow++;
				
				while (rmCount > 0) {
					graph.get(prev).remove();
					if (!graph.get(curr).isEmpty()) graph.get(curr).remove();
					graph.get(curr).add(prev);
					list.pop();
					curr = prev;
					prev = (list.size() > 1)? list.elementAt(list.size() - 2) : 0;
					if (--rmCount <= 0) {
						list.pop();
						rmCount = 0;
					}
				}
				
				visited = new int[graph.size()];
				visited[0] = 1;
				/*
				System.out.println("Stack: " + list);
				System.out.println("Visited: " + Arrays.toString(visited));
				for (int j = 0; j < graph.size(); j++) {
					System.out.println(j + "- " + graph.get(j));
				}
				System.out.println();*/
				
				continue;
			}
			
			boolean found = false;
			for (int x : graph.get(curr)) {
				if (visited[x] == 0 && !graph.get(x).isEmpty()) {
					list.add(x);
					rmCount++;
					found = true;
					break;
				}
			}
			if (!found) {
				visited[prev] = 0;
				list.pop();
				if (rmCount > 0) rmCount--;
			}
			/*
			System.out.println(prev + " " + curr + "|" + maxFlow + ", rCount: " + rmCount);
			System.out.println("Stack: " + list);
			System.out.println("Visited: " + Arrays.toString(visited));
			for (int j = 0; j < graph.size(); j++) {
				System.out.println(j + "- " + graph.get(j));
			}
			System.out.println();
			*/
		}
		
		return maxFlow;
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
			if (bnodes == anodes) {
				perfects[i] = anodes;
			} else {
				perfects[i] = -1;
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
			//System.out.println(graphs.get(i));
			String perfect = "N";
			int maxMatch = getMaxMatch(graphs.get(i));
			if (perfects[i] == maxMatch) {
				perfect = "Y";
			}
			System.out.println(maxMatch + " " + perfect);
			// System.out.println(System.currentTimeMillis() - start);
		}

	}

}
