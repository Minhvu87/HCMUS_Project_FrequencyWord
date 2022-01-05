package Project;

import org.apache.tika.language.LanguageIdentifier;  

public class Language_Identifier {
    static LanguageIdentifier languageidentifier;
    
    //Khoi tao LanguageIdentifier
	public Language_Identifier(String str) {
		languageidentifier = new LanguageIdentifier(str);
	}
	
    // Ham cho biet la loai ngon ngu nao
	public static String get_Language() {
		return languageidentifier.getLanguage();
	}
    
}
