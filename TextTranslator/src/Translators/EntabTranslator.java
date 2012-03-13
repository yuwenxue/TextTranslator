package Translators;

import textTranslator.TranslatorInterface;

public class EntabTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Entab";
	}

	@Override
	public String getDescription() {
		return "Replaces each group of four spaces at the beginning of each line with a tab character";
	}

	@Override
	public String translate(String text) {
		StringBuffer sb = new StringBuffer();
		StringBuffer re = new StringBuffer();
		sb.append("\n");
		sb.append(text);
		int spaceCount = -1;
		boolean afterEnter = false;
		for(int i = 0; i < sb.length(); i++) {
			if(sb.charAt(i) == '\n') {
				afterEnter = true;
				spaceCount = 0;
			}else if(sb.charAt(i) == ' ') {
				if(afterEnter == true) {
					spaceCount++;
					if(spaceCount == 4) {
						re.append("	");
						spaceCount = 0;
					}
					continue;
				}
			}else if(afterEnter == true) {
				afterEnter = false;
				for(int j = 0; j < spaceCount; j++) {
						re.append(" ");
					}
				}
			re.append(sb.charAt(i));

		}
		re.deleteCharAt(0);
		return re.toString();
	}

}
