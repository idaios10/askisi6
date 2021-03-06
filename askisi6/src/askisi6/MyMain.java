package askisi6;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMain {
	static boolean found= false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		search("TO + TO = FOR", 6);
		search("TOO + TOO = FOR", 6);
		search("WON + WON = TOO", 10);
	}


	public static void search(String string,int num) {
		boolean usedNumbers[] = new boolean[num];				
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();    
		ArrayList<Character> allCharacters = new ArrayList<Character>();  					

		String num1="" , num2= "", result= "";

		string = string.toUpperCase().replaceAll("\\s", "");
		num1 = string.substring(0, string.indexOf("+"));
		string = string.substring(string.indexOf("+") + 1);
		num2 = string.substring(0, string.indexOf("="));
		result = string.substring(string.indexOf("=") + 1);

		addCharacter(allCharacters, num1);
		addCharacter(allCharacters, num2);
		addCharacter(allCharacters, result);
		
		found = false;
		printAllChars(allCharacters);

		backtrack(num1, num2, result, allCharacters, hashMap, usedNumbers, 0, num);
		if (!found)
			System.out.println("No solution found");

		System.out.println();
	}

	public static void addCharacter(ArrayList<Character> allChars, String str) {
		for(char character : str.toCharArray()) {
			if (!allChars.contains(character)) {
				allChars.add(character);
			}
		}
	}

	public static void printAllChars(ArrayList<Character> allChars) {
		System.out.print("All the characters used are : ");
		for (Character c : allChars) {
			System.out.print( c + "  ");
		}
		System.out.println();
	}

	public static void backtrack(String num1, String num2, String result, ArrayList<Character> allChars,
			HashMap<Character, Integer> hashMap, boolean[] usedNumbers, int lettersHavingValue, int maxNum) {
		int no1,no2,res;
		if (lettersHavingValue == allChars.size()) {
			
			no1 = findNumber(hashMap, num1);
			no2 = findNumber(hashMap, num2);
			res = findNumber(hashMap, result);

			if (res == no1 + no2) {
				
				found = true;
				String solution = "";
				
				for (int i = 0; i < allChars.size(); i++) {
					solution += allChars.get(i) + "=" + hashMap.get(allChars.get(i)) + " ";
				}
				
				System.out.println("Solution is :  " + solution);
			}
			return ;
		}

		
		//next char
		char ch1 = allChars.get(lettersHavingValue);

		
		for (int i = 0; i < maxNum; i++) {
			if (usedNumbers[i] == false) {
				
				hashMap.put(ch1, i);
				usedNumbers[i] = true;
				
				backtrack(num1, num2, result, allChars, hashMap, usedNumbers, lettersHavingValue+1, maxNum);
				
				usedNumbers[i] = false;
				hashMap.remove(ch1);
			}
		}
	}

	public static int findNumber(HashMap<Character, Integer> hashMap, String str) {
		String strToNum = "";
		for (int i = 0; i < str.length(); i++) {
			strToNum += hashMap.get(str.charAt(i));
		}
		return Integer.parseInt(strToNum);
	}
}
