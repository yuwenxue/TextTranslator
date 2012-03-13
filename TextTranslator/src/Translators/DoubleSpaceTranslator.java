package Translators;

import textTranslator.TranslatorInterface;

public class DoubleSpaceTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		return "Double Space";
	}

	@Override
	public String getDescription() {
		return "Replace new line char with two spaces";
	}

	@Override
	public String translate(String text) {
		return text.replaceAll("\n", "\n\n");
	}

}
