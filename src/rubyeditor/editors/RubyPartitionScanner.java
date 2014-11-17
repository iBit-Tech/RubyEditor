package rubyeditor.editors;

import org.eclipse.jface.text.rules.*;

public class RubyPartitionScanner extends RuleBasedPartitionScanner {
	public final static String XML_COMMENT = "__xml_comment";

	public RubyPartitionScanner() {

		IToken rubyComment = new Token(XML_COMMENT);

		IPredicateRule[] rules = new IPredicateRule[1];

		rules[0] = new MultiLineRule("=begin", "=end", rubyComment);

		setPredicateRules(rules);
	}
}
