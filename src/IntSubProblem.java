import java.util.ArrayList;
import java.util.List;

public class IntSubProblem extends Problem {
	private int term1, term2;
	private int solution;
	private int[] first, second;
	
	public IntSubProblem(int t1, int t2) {
		term1 = t1;
		term2 = t2;
		solution = t1 - t2;
		int length = Math.max(("" + term1).length(), ("" + term2).length());
		first = arrayFromInt(term1, length);
		second = arrayFromInt(term2, length);
		
	}
	
	/*
	 * Mistakes:
	 * -Not decrementing on a borrow.
	 * -Doing the column-subtraction incorrectly
	 */
	
	public int noDecrementOnBorrow() {
		int[] result = new int[first.length];
		for (int i = 1; i <= result.length; i++) {
			int index = result.length - i;
			if (second[index] > first[index])
				result[index] = (first[index] + 10) - second[index];
			else
				result[index] = first[index] - second[index];
		}

		return ProblemDescriptor.intFromArray(result);
	}
	
	public int getWrongAnswer() {
		return 0;
	}
	
	public int getSolution() {
		return solution;
	}
	
	public int getTerm1() {
		return term1;
	}
	
	public int getTerm2() {
		return term2;
	}
	
	public String toString() {
		return term1 + " - " + term2 + " = " + solution;
	}
	
	
	public String toHTML() {
		List<int[]> termsArrays = new ArrayList<int[]>();
		termsArrays.add(first);
		termsArrays.add(second);
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div><table class=\"problem\">");
		
		for (int i = 0; i < termsArrays.size(); i++) {
			int[] curr = termsArrays.get(i);
			sb.append("<tr>");
			sb.append("<td>");
			if (i == termsArrays.size() - 1)
				sb.append("-");
			sb.append("</td>");
			
			boolean seenNonZero = false;
			for (int j = 0; j < curr.length; j++) {
				
				sb.append("<td>");
				if (curr[j] != 0)
					seenNonZero = true;
				
				if (seenNonZero)
					sb.append(curr[j]);
				
				sb.append("</td>");
				
			}
			sb.append("</tr>");
		}
		
		
		sb.append("</table></div>");
		return sb.toString();
		
	}
	
	public String toMathML(String id) {
		StringBuilder result = new StringBuilder();
		
		if (id == null)
			result.append("<math>");
		else
			result.append("<math id=" + id + ">");
		
		result.append("<mstack>");
		
		result.append("<mn>");
		result.append(term1);
		result.append("</mn>");
		
		result.append("<msrow>");
		result.append("<mo>-</mo><none/>");
		
		result.append("<mn>");
		result.append(term2);
		result.append("</mn>");
		
		result.append("</msrow>");
		
		result.append("<msline/>");
		result.append("</mstack>");
		
		result.append("</math>");
		
		return result.toString();
	}

}
