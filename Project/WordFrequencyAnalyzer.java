package Project;

import java.util.ArrayList;
import java.util.List;

//Doc File
public class WordFrequencyAnalyzer {
	private String str;
	Language_Identifier language;
	List<String> ketqua;
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
    //Khi Khoi tao ham thi dong thoi cung duoc dinh dang Language_Identifier
    public WordFrequencyAnalyzer(String str) {
    	this.str = str;
    	new Language_Identifier(str);
    }
    //Dung de phan tich la ngon ngu nao
    public List<String> analyzeText() {
    	if (ketqua == null) {
			ketqua = new ArrayList<>();
		}
    	try {
    		if(language.get_Language().equals("en")) {
    			ketqua.add("Language code: English");
    		}else {
    			ketqua.add("Language code: "+language.get_Language());
    		}
        	   
    	
    	}catch(NullPointerException ex) {
     	   
        }
    	return ketqua;
        
    }

}
