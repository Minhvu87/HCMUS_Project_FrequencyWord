package Project;

import java.util.ArrayList;
import java.util.List;

public class WordFrequecyCollection {
	WordFrequencyAnalyzer w;
	//ket qua se duoc luu List<String>
	List<String> ketqua;

	public WordFrequecyCollection(WordFrequencyAnalyzer w) {
		super();
		this.w = w;
	}
    //Ham de lay bien string trong WordFrequencyAnalyzer
	public String toString() {
		return w.getStr();

	}
    //Ham Dung de Xu ly Dem ki tu va dem tu cung loai
	public List<String> getFequency() {
		try {
			if (ketqua == null) {
				ketqua = new ArrayList<>();
			}
			//Tao Doi tuong CountPunctInString dem dem ki tu
			CountPunctInString countPunct = new CountPunctInString(toString());
			//Tao Doi tuong CountStemInString dem tu cung loai
			CountStemInString countStem = new CountStemInString(toString());
			for (String s : countPunct.CountPunct()) {
				ketqua.add(s);
			}
			for (String s : countStem.CountStem()) {
				ketqua.add(s);
			}
		}catch(NullPointerException ex) {
			
		}

		return ketqua;
	}

//	public static void main(String[] args) {
//		String str = "Study, studies?... studied!   studying. Undertand, undertanding, mouse, mice.";
//		WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer(str);
//		System.out.println(analyzer.analyzeText());
//		WordFrequecyCollection collection = new WordFrequecyCollection(analyzer);
//		for (String s : collection.getFequency()) {
//			System.out.println(s);
//		}
//	}
}
