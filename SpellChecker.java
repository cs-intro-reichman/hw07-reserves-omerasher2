public class SpellChecker {
	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1,str.length());
	}

	public static int levenshtein(String word1, String word2) {
		String lowword1=word1.toLowerCase();
		String lowword2=word2.toLowerCase();
		if (lowword1.length()==0) return lowword2.length();
		if (lowword2.length()==0) return lowword1.length();
		if (lowword1.charAt(0)==word2.charAt(0)) {
			return levenshtein(tail(lowword1), tail(lowword2));
		} else {
			return 1+Math.min(Math.min(levenshtein(tail(lowword1), lowword2),levenshtein(lowword1, tail(lowword2))), levenshtein(tail(lowword1), tail(lowword2)));
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i<dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String lowword=word.toLowerCase();
		String toPrint=lowword;
		int minLev=1000;
		for (String dictWord : dictionary) {
			int lev = levenshtein(lowword, dictWord);
			if (lev < minLev && lev <= threshold) {
				minLev = lev;
				toPrint = dictWord;
			}
		}
		return toPrint;
	}
}