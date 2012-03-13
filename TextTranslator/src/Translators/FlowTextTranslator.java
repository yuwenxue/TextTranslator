package Translators;

import textTranslator.TranslatorInterface;

public class FlowTextTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Flow Text";
	}

	@Override
	public String getDescription() {
		return "Flows text";
	}

	@Override
	public String translate(String text) {
		String[] splitT = text.split("\n");
		StringBuffer sb = new StringBuffer();
		int n = splitT.length;
		boolean appendBlankCount = false; 
		//appendBlankCount to make sure only one space is appende when many consecutive lines are blank
		for(int i = 0; i < n; i++) {
			if(isBlank(splitT[i])) {
				if(appendBlankCount == false) {
					
					sb.append(" ");
					appendBlankCount = true;
				}
				
			}else {
				System.out.println("appending ("+splitT[i]+")");
				appendBlankCount = false;
				sb.append(splitT[i]);
			}
		}
		
		return wrapText(sb.toString());
		//return sb.toString();
	}
	
	public boolean isBlank(String string) {
		if(string.length() == 0) {
			return true;
		}else {
			int n = string.length();
			for(int i = 0; i < n; i++) {
				int ascii = (int)(string.charAt(i));
				if(ascii < 127 && ascii > 32) {
					return false;
				}
			}
			return true;
		}
	}

	
	public String wrapText(String text) {
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
		int j = 71;
		int numberIndent = 0;
		while(line.charAt(i) == '	') {
			numberIndent ++;
			i++;
		}
		while(j < sb.length()) {
			if(sb.charAt(j) == ' ') {
				sb.insert(j, "\n");
				for(int k = 0; k < numberIndent; k++) {
					sb.insert(j + 1, "	");
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
				if(minusCount == 71) {
					j = jtemp;
					while(j - 1 < sb.length() && sb.charAt(j - 1) != ' ') {
						j++;
					}
				}
				if(j == sb.length()) {break;}
				sb.insert(j, "\n");
				for(int k = 0; k < numberIndent; k++) {
					sb.insert(j + 1, "	");
				}
				j = j + 72 + numberIndent + 1; 
			}
		}
		
		return sb;
	}
}
