package Translators;

import textTranslator.TranslatorInterface;

public class DetabTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Detab";
	}

	@Override
	public String getDescription() {
		return "Delete tabs at the begining of each line";
	}

	@Override
	public String translate(String text) {
		if(text.length() == 0) {
			return null;
		}
		if(text.charAt(0) == '	') {
			text = "    "+text.substring(1, text.length());
		}
		return text.replaceAll("\n\t", "\n    ");
	}

}
