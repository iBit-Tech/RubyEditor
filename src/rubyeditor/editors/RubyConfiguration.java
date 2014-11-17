package rubyeditor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class RubyConfiguration extends SourceViewerConfiguration {
	private RubyDoubleClickStrategy doubleClickStrategy;
	private RubyStringScanner tagScanner;
	private RubyScanner scanner;
	private ColorManager colorManager;

	public RubyConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			RubyPartitionScanner.XML_COMMENT};
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new RubyDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected RubyScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new RubyScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected RubyStringScanner getXMLTagScanner() {
		if (tagScanner == null) {
			tagScanner = new RubyStringScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.TAG))));
		}
		return tagScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getXMLTagScanner());

		dr = new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IXMLColorConstants.RUBY_COMMENT)));
		reconciler.setDamager(ndr, RubyPartitionScanner.XML_COMMENT);
		reconciler.setRepairer(ndr, RubyPartitionScanner.XML_COMMENT);

		return reconciler;
	}

}