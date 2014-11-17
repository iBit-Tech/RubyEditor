package rubyeditor.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class RubyEditor extends TextEditor {

	private ColorManager colorManager;

	public RubyEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new RubyConfiguration(colorManager));
		setDocumentProvider(new RubyDocumentProvider());
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
