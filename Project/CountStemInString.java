package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CountStemInString implements CountWordInString {
	private String str;
	//Dung luu ket qua duoi dang list
	List<String> ketqua;

	public CountStemInString(String str) {
		// String lay tu Language Identifier
		this.str = str;
	}
    //Hàm  dùng để biến 1 chuỗi thành từng phần để biết được chúng là loại từ gì 
	public String[] Tokenizer() {
		StringTokenizer st = new StringTokenizer(str);
		String words[] = new String[str.length()];
		int n = st.countTokens();
		for (int i = 0; i < n; i++) {
			words[i] = st.nextToken();
			// Loai bo dau cau
			words[i] = words[i].replaceAll("\\p{Punct}", "");
		}

		return words;
	}
    //Ham nay duoc dung de Dem tu
	@Override
	public Map<String, Integer> FrequentWord(String str) {

		str = str.replace("[^a-zA-Z0-9]", " ");
		String[] allWords = str.split(" +");

		Map<String, Integer> countingMap = new HashMap<>();
		//put vo countting map
		for (String word : allWords) {
			// Biến 1 từ dưới dạng viết thường
			word = word.toLowerCase();
			countingMap.put(word, countingMap.getOrDefault(word, 0) + 1);
		}
        //So sanh xem có từ nào giống nhau không
		TreeMap<String, Integer> mostFrequentMap = new TreeMap<>((e1, e2) -> {
			int freq1 = countingMap.get(e1);
			int freq2 = countingMap.get(e2);

			if (freq1 != freq2) {
				return freq2 - freq1;
			}

			return e1.compareTo(e2);

		});
        //So sanh xong roi put vo countingMap
		mostFrequentMap.putAll(countingMap);

		return countingMap;

	}
    //Ham nay duoc dung de chuyen map sang list
	@Override
	public List<String> show(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		if (ketqua == null) {
			ketqua = new ArrayList<>();
		}
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			ketqua.add(key + " = " + map.get(key));
		}
		return ketqua;
	}
    // Ham dung de Dem cac tu cung loai(so it, so nhieu, qua khu, hien tai, tuong lai)
	public List<String> CountStem() {
        try {
        	if (ketqua == null) {
    			ketqua = new ArrayList<>();
    		}
        	ketqua.add("*****Cac tu va cac tu cung loai(Stem)*****");
    		String words[] = new String[str.length()];
    		words = Tokenizer();
    		// Goi thu vien PorterStemmer de dem tu cung loai
    		Porter_Stemmer ps = new Porter_Stemmer();
    		String stem = null;
    		// Dung StringBuilder de noi cac tu stem va dem chung
    		StringBuilder sb = new StringBuilder();
    		try {
    			for (String word : words) {
    				stem = ps.stem(word);
    				// Xu li neu mice va mouse
    				if (stem.equals("mice")) {
    					stem = "mous";
    				}
    				ketqua.add(word + " Stem: " + stem);
    				//Cac tu Stem sau khi duoc noi thi ngan cach nhau boi " "
    				sb.append(stem).append(" ");
    			}

    			// Xu ly loi NullPointerException
    		} catch (NullPointerException ex) {

    		}
    		ketqua.add("*****Dem tu cung loai(so it, so nhieu, qua khu, hien tai, tuong lai)*****");
    		show(FrequentWord(sb.toString()));
        }catch(NullPointerException e) {
        	
        }
		
		return ketqua;
	}

//	public static void main(String[] args) {
//	    String str = "Study, studies?... studied!   studying. Undertand, undertanding, mouse, mice.";
//	    CountStemInString count = new CountStemInString(str);
//	    for(String s:count.CountStem()) {
//	    	System.out.println(s);
//	    }
//    }

}
