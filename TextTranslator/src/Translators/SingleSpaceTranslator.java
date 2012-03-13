package Translators;

import textTranslator.TranslatorInterface;

public class SingleSpaceTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		return "Single Space";
	}

	@Override
	public String getDescription() {
		return "Replaces consecutive pairs of newlines with a single newline";
	}

	@Override
	public String translate(String text) {
		return text.replaceAll("\n{2}", "\n");
	}

}
