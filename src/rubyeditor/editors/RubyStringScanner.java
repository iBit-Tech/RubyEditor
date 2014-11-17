package rubyeditor.editors;

import org.eclipse.jface.text.*;
import org.eclipse.jface.text.rules.*;

public class RubyStringScanner extends RuleBasedScanner {

	public RubyStringScanner(ColorManager manager) {
		IToken string = new Token(new TextAttribute(manager.getColor(IRubyColorConstants.STRING)));

		IRule[] rules = new IRule[3];

		rules[0] = new SingleLineRule("\"", "\"", string, '\\');
		rules[1] = new SingleLineRule("'", "'", string, '\\');
		rules[2] = new WhitespaceRule(new RubyWhitespaceDetector());

		setRules(rules);
	}
}
