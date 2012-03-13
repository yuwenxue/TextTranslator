package Translators;

import textTranslator.TranslatorInterface;

public class WrapTextTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		return "Wrap Text";
	}

	@Override
	public String getDescription() {
		return "Wraps lines so that no line is longer than 72 characters";
	}

	@Override
	public String translate(String text) {
		if(text.length() == 0) {return null;}
		StringBuffer buffer = new StringBuffer();
		String[] spl = text.split("\n");
		int n = spl.length;
		for(int i = 0; i < n; i++) {
			
			if(spl[i].length() > 72) {
				StringBuffer splWrap = wrap(spl[i]);
				buffer.append(splWrap);
				buffer.append("\n");
			}else {
				buffer.append(spl[i]);
				buffer.append("\n");
			}
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	public StringBuffer wrap(String line) {
		StringBuffer sb = new StringBuffer(line);
		int i = 0;
		int j = 72;
		int numberIndent = 0;
		while(line.charAt(i) == '	') {
			numberIndent ++;
			i++;
		}
		while(j < sb.length()) {
			if(sb.charAt(j) == ' ') {
				sb.insert(j + 1, "\n");
				for(int k = 0; k < numberIndent; k++) {
					sb.insert(j + 2, "	");
				}
				j = j + 72 + numberIndent + 1; //goto next 72 chars beyond the \n and tabs inserted
			}else {
				int jtemp = j;
				int minusCount = 0;
				while(minusCount < 71) {
					j --;
					minusCount ++;
					if(sb.charAt(j) == ' ') {
						break;
						
					}
				}
				if(minusCount == 71 && sb.charAt(j) != ' ') {
					j = jtemp;
					while(j < sb.length() && sb.charAt(j) != ' ') {
						j++;
					}
				}
				if(j == sb.length()) {break;}
				sb.insert(j + 1, "\n");
				for(int k = 0; k < numberIndent; k++) {
					sb.insert(j + 2, "	");
				}
				j = j + 72 + numberIndent + 1; 
			}
		}
		
		return sb;
	}
}
