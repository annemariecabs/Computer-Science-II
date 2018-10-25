import java.util.TreeMap;

public class DocumentIndex {
	
	private TreeMap<String, IndexEntry> index;
	
	public DocumentIndex() {
		index = new TreeMap<String, IndexEntry>();
	}
	
	public void addWord(String word, int num) {
		IndexEntry temp;
		
		if(index.containsKey(word)) {
			temp = index.get(word);
			temp.add(num);
		}
		else {
			temp = new IndexEntry(word);
			temp.add(num);
			index.put(word, temp);
		}
	}

}
