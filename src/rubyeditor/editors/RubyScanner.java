package rubyeditor.editors;

import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.*;

public class RubyScanner extends RuleBasedScanner {

	public RubyScanner(ColorManager manager) {
		IToken procInstr = new Token(new TextAttribute(manager.getColor(IXMLColorConstants.PROC_INSTR)));

		IRule[] rules = new IRule[2];
		rules[0] = new EndOfLineRule("#", procInstr);
		rules[1] = new WhitespaceRule(new RubyWhitespaceDetector());

		setRules(rules);
	}
}
