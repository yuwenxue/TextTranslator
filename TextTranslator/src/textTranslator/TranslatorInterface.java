package textTranslator;

/**
 * interface of translator
 * @author YuwenXue
 *
 */
public interface TranslatorInterface {
	/**
	 * gets translator's name, used in listener, to know which translator it is
	 * @return - name as string
	 */
	String getName();
	/**
	 * gets translator's description
	 * @return - description as string
	 */
	String getDescription();
	/**
	 * translate
	 * @param text - input text
	 * @return output text
	 */
	String translate(String text);
}
