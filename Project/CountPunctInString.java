package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountPunctInString implements CountWordInString{

    private String str;
    //Tao bien List<String> de luu ket qua cua chuong trinh
    List<String>ketqua;
	public CountPunctInString(String str) {
		this.str = str;
	}


    //Ham duoc dung de dem ki tu
    public List<String> CountPunct() {
    	try {
    		if (ketqua == null) {
    			ketqua = new ArrayList<>();
    		}
        	ketqua.add("*****Dem dau cau(!,?.,...)*****");
        	show(FrequentWord(str));
    	}catch(NullPointerException ex){
    		
    	}
    	return ketqua;
    }
    //Ham dung de liet ke ki tu va so lan xuat hien chung
    @Override
	public Map<String, Integer> FrequentWord(String str) {
		// TODO Auto-generated method stub

    	int count_chamhoi = 0, count_chamthan = 0, count_cham = 0, count_champhay = 0, count_haicham = 0,
				count_phay = 0, count_bacham = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '!') {
				count_chamthan++;
			} else if (str.charAt(i) == '?') {
				count_chamhoi++;
			} else if (str.charAt(i) == '.') {
				count_cham++;
			} else if (str.charAt(i) == ',') {
				count_phay++;
			} else if (str.charAt(i) == ':') {
				count_haicham++;
			} else if (str.charAt(i) == ';') {
				count_champhay++;
			}
		}
        // tim ki tu co dau cham hoac dau cham lien nhau tren man hinh
		Pattern p = Pattern.compile("[...]+");
		Matcher m = p.matcher(str);
		while (m.find()) {
			count_bacham++;
		}
		Map<String, Integer> countingMap = new HashMap<>();

		countingMap.put(" , ", count_phay);
		countingMap.put(" . ", count_cham);
		countingMap.put(" ; ", count_champhay);
		countingMap.put(" : ", count_haicham);
		countingMap.put(" ! ", count_chamthan);
		countingMap.put(" ? ", count_chamhoi);
		//Dau 3 cham duoc ki tu co dau cham hoac dau cham lien nhau tren man hinh tru di so lan xuat hien dau cham 
		countingMap.put(" ...", count_cham - count_bacham);
		return countingMap;
	}
    //Ham dung de luu map thanh list de the hien len trang Frame
    @Override
    public List<String> show(Map<String, Integer> map) {
    	if (ketqua == null) {
			ketqua = new ArrayList<>();
		}
    	Set<String> keySet = map.keySet();
    	for (String key : keySet) {
    		ketqua.add("Ki tu" + key + " = " +map.get(key));
    	}
    	return ketqua;
    }
//	public static void main(String[] args) {
//		String str = "Study, studies?... studied!   studying. Undertand, undertanding, mouse, mice.";
//		CountPunctInString count = new CountPunctInString(str);
//		for(String s:count.CountPunct()) {
//			System.out.println(s);
//		}
//	}
}
