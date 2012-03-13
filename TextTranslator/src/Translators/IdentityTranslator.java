package Translators;

import textTranslator.TranslatorInterface;

public class IdentityTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Identity";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Exactly the same as input";
	}

	@Override
	public String translate(String text) {

		// TODO Auto-generated method stub
		return text;
	}

}
