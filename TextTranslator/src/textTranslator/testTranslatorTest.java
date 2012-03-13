package textTranslator;

import static org.junit.Assert.*;

import org.junit.Test;

import Translators.DetabTranslator;
import Translators.DoubleSpaceTranslator;
import Translators.EntabTranslator;
import Translators.FixIndentationTranslator;
import Translators.FlowTextTranslator;
import Translators.IdentityTranslator;
import Translators.SingleSpaceTranslator;
import Translators.WrapTextTranslator;

/**
 * test cases for eight translators
 * 
 * @author YuwenXue
 * 
 */
public class testTranslatorTest {
	TranslatorInterface ti1 = new IdentityTranslator();
	TranslatorInterface ti2 = new DetabTranslator();
	TranslatorInterface ti3 = new EntabTranslator();
	TranslatorInterface ti4 = new SingleSpaceTranslator();
	TranslatorInterface ti5 = new DoubleSpaceTranslator();
	TranslatorInterface ti6 = new WrapTextTranslator();
	FlowTextTranslator ti7 = new FlowTextTranslator();
	TranslatorInterface ti8 = new FixIndentationTranslator();

	@Test
	public void testTranslate() {
		String identity = "this is identity";
		String detab = "	jkflajkflajklaj	jsafk;lajkflsa;\njklfsajkfl;a		jfk;lajkl;fajka\n\n\n	jkfl;j\njkfa;aj	\njkf;lajfkla;jkflajkfls";
		String detabT = "    jkflajkflajklaj	jsafk;lajkflsa;\njklfsajkfl;a		jfk;lajkl;fajka\n\n\n    jkfl;j\njkfa;aj	\njkf;lajfkla;jkflajkfls";
		String singleSpace = "jfksaljfsal;kj\n\najfkl;ajkl;jk\njakl;fjk\njklf;afk\n\n\n\n\nfjak;ldsjk;\n\njaf;l";
		String singleSpaceT = "jfksaljfsal;kj\najfkl;ajkl;jk\njakl;fjk\njklf;afk\n\n\nfjak;ldsjk;\njaf;l";
		String doubleSpace = "\n\n\najfk;laj\n\njfk;laj\n\n";
		String doubleSpaceT = "\n\n\n\n\n\najfk;laj\n\n\n\njfk;laj\n\n\n\n";
		String wrap = "test wrap text:\n\n		jkslfj  1234567890123456789012345678901234567890123456789012345678901234567890123   jfkdsljfakl jfkldsjfkl;ajk djfkd lajf lsajkfldjsaf ljsakfl jsak fjsa ;lfjsa lfs\njkdlajfkla;fdsa\njdk;lajkfld;sa\nsjfdklaj;lfsa\nafjkla;jfk;lsa\nafjalfj;lsaf\nfdjsalkf;j;laf\n		jkslfj  1234567890123456789012345678901234567890123456789012345678901234567890123   jfkdsljfakl jfkldsjfkl;ajk djfkd lajf lsajkfldjsaf ljsakfl jsak fjsa ;lfjsa lfs\najjalkfioqrnmkljdskfalnkbjkl;af\nfjkdlsaufiowklnfda    dsjfakl;sakmlfdsjaifouioqjworeukdlskfndkjsa;jfdljk;lq\njkal;fjkdslfiowmjflk jfdksla;jfkdl jflkds jfls fjkl;jaoiwmjklf sjf djskl ;fjkofjwkfj saf skjf kdosjfkwlj kfsa";
		String wrapT = "test wrap text:\n\n		jkslfj  \n		1234567890123456789012345678901234567890123456789012345678901234567890123 \n		  jfkdsljfakl jfkldsjfkl;ajk djfkd lajf lsajkfldjsaf ljsakfl jsak fjsa \n		;lfjsa lfs\njkdlajfkla;fdsa\njdk;lajkfld;sa\nsjfdklaj;lfsa\nafjkla;jfk;lsa\nafjalfj;lsaf\nfdjsalkf;j;laf\n		jkslfj  \n		1234567890123456789012345678901234567890123456789012345678901234567890123 \n		  jfkdsljfakl jfkldsjfkl;ajk djfkd lajf lsajkfldjsaf ljsakfl jsak fjsa \n		;lfjsa lfs\najjalkfioqrnmkljdskfalnkbjkl;af\nfjkdlsaufiowklnfda    \ndsjfakl;sakmlfdsjaifouioqjworeukdlskfndkjsa;jfdljk;lq\njkal;fjkdslfiowmjflk jfdksla;jfkdl jflkds jfls fjkl;jaoiwmjklf sjf djskl \n;fjkofjwkfj saf skjf kdosjfkwlj kfsa";
		String flowInput = "\n  \n	 	 	\nafdafafa \nsfd 	 sfs  	\n\n\nd";
		String flowInputT = " afdafafa sfd 	 sfs  	 d";
		String fixIndent = "while(){\njfksaljflajkl{{{}}}{}{}}}}}{{{{{{{\n{\n{]\n{{{\n{\n{\n{}}}}\n}";
		String fixIndentT = "while(){\njfksaljflajkl{{{}}}{}{}}}}}{{{{{{{\n                {\n                    {]\n                        {{{\n                                    {\n                                        {\n                                {}}}}\n                            }\n";
		assertEquals(identity, ti1.translate(identity));
		assertEquals(detabT, ti2.translate(detab));
		assertEquals(detab, ti3.translate(detabT));
		assertEquals(singleSpaceT, ti4.translate(singleSpace));
		assertEquals(doubleSpaceT, ti5.translate(doubleSpace));
		assertEquals(wrapT, ti6.translate(wrap));
		assertEquals(flowInputT, ti7.translate(flowInput));
		assertEquals(fixIndentT, ti8.translate(fixIndent));
	}

}
