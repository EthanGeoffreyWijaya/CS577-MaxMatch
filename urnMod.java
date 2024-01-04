//import java.util.Scanner;

public class urnMod {

	public static void main(String[] args) {
		// Scanner scnr = new Scanner(System.in);
		int n = 1;
		double[] frequencies = { 11.16, 8.5, 7.58, 7.54, 7.16, 6.95, 6.65, 5.74, 5.49, 4.54, 3.63, 3.38, 3.17, 3, 3,
				2.47, 2.07, 1.81, 1.78, 1.29, 1.1, 1, 0.29, 0.27, 0.19, 0.19};
		double sum = 0;
		double sumF = 0;
		
		for (int i = 0; i < 25; i++) {
			sum += n/ (n - sumF);
			sumF += frequencies[i] / 100;
			//System.out.println(sum);
		}

		System.out.println(sum);
	}

}
