package Project;
import java.util.List;
import java.util.Map;

public interface CountWordInString {

	//Ham de liet ke tu/ki tu va so lan xuat hien cua chung
    public Map<String, Integer> FrequentWord(String str);
    //Ham de doi dang map sang list de show ra trang Frame
    public List<String> show(Map<String, Integer> map);
}
