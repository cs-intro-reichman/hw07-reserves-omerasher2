

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0; i<dictionary.length; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i=0; i<dictionary.length; i++){
			if (dictionary[i].equals(word))
			return true;
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) return;
		String hashtagsmall = hashtag.toLowerCase();
		int first =0;
        for (int i=1; i<=hashtagsmall.length(); i++) {
			String word = hashtagsmall.substring(first,i);
			if (existInDictionary(word, dictionary)) {
				first=i;
				System.out.println(word);
				breakHashTag(hashtagsmall.substring(first,i), dictionary);
			}
        }
    }

}
