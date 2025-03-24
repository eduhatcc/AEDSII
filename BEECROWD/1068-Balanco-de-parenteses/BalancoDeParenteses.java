import java.util.Scanner;

public class BalancoDeParenteses {

	public static boolean balanco(String str) {
		boolean resp = false;
		int parentesesInicio = 0,
		    parentesesFim = 0;

		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				parentesesInicio++;
			}
			else if (str.charAt(i) == ')') {
				parentesesFim++;
			}
		}

		if (parentesesInicio == parentesesFim) {
			resp = true;
		}

		return resp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String str = sc.nextLine();

			if (balanco(str)) {
				System.out.printf("correct%n");
			} else {
				System.out.printf("incorrect%n");
			}
		}

		sc.close();
	}
}
