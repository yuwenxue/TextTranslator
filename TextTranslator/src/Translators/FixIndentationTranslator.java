package Translators;

import java.util.Stack;

import textTranslator.TranslatorInterface;

/**
 * FixIndentationTranslator
 * @author YuwenXue
 *
 */
public class FixIndentationTranslator implements TranslatorInterface {

	@Override
	public String getName() {
		return "Fix Indentation";
	}

	@Override
	public String getDescription() {
		
		return "Indent a Java (or Java-like) program";
	}

	@Override
	public String translate(String text) {
		String[] splitT = text.split("\n");
		StringBuffer sb = new StringBuffer();
		int n = splitT.length;
		int lastIndent = 0;
		int currentIndent = 0;
		//appendBlankCount to make sure only one space is appende when many consecutive lines are blank
		for(int i = 0; i < n; i++) {
			currentIndent = lastIndent + getIndent(splitT[i])[0];
			if(currentIndent > 0) {
				for(int j = 0; j < currentIndent; j ++) {
					sb.append("    ");
				}
			}
			sb.append(splitT[i]);
			sb.append("\n");
			lastIndent = currentIndent + getIndent(splitT[i])[1];
		}
		
		return sb.toString();
		//return sb.toString();
	}
	
	public int[] getIndent(String string) {
		int thisIndent = 0;
		int nextIndent = 0;
		int n = string.length();
		Stack<Character> ss = new Stack<Character>();
		for(int i = 0; i < n; i++) {
			if(string.charAt(i) == '{') {
				ss.push('{');
			}
			if(string.charAt(i) == '}') {
				if(ss.isEmpty()) {
					thisIndent --;
				}
				else {
					ss.pop();
				}
			}
			nextIndent = ss.size();
		}
		return new int[] {thisIndent, nextIndent}; 
	}

}
