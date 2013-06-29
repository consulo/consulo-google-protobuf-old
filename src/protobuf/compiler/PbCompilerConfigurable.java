package protobuf.compiler;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jetbrains.annotations.Nls;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileElement;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import protobuf.PbBundle;

/**
 * @author Nikolay Matveev
 *         Date: Apr 5, 2010
 */
public class PbCompilerConfigurable implements Configurable
{

	private TextFieldWithBrowseButton pathField;
	private JPanel settingsPanel;
	private JCheckBox myCompilationEnabledCheckBox;

	PbCompilerConfiguration myCompilerConfiguration;
	Project myProject;

	public PbCompilerConfigurable(Project project)
	{
		myCompilerConfiguration = PbCompilerConfiguration.getInstance(project);
		myProject = project;

		pathField.addBrowseFolderListener(project, new ProtocExecutableBrowseFolderActionListener(project, pathField), false);
	}

	@Nls
	@Override
	public String getDisplayName()
	{
		return "Protocol Buffers Compiler";
	}

	@Override
	public String getHelpTopic()
	{
		return null;
	}

	@Override
	public JComponent createComponent()
	{
		return settingsPanel;
	}

	@Override
	public boolean isModified()
	{
		return !myCompilerConfiguration.PATH_TO_COMPILER.equals(pathField.getText()) || myCompilationEnabledCheckBox.isSelected() != myCompilerConfiguration.COMPILATION_ENABLED;
	}

	@Override
	public void apply() throws ConfigurationException
	{
		myCompilerConfiguration.PATH_TO_COMPILER = pathField.getText();
		myCompilerConfiguration.COMPILATION_ENABLED = myCompilationEnabledCheckBox.isSelected();
	}

	@Override
	public void reset()
	{
		pathField.setText(myCompilerConfiguration.PATH_TO_COMPILER);
		myCompilationEnabledCheckBox.setSelected(myCompilerConfiguration.COMPILATION_ENABLED);
	}

	@Override
	public void disposeUIResources()
	{
	}


	private static class ProtocExecutableBrowseFolderActionListener extends ComponentWithBrowseButton.BrowseFolderActionListener<JTextField>
	{

		private final Project project;

		public ProtocExecutableBrowseFolderActionListener(final Project project, final TextFieldWithBrowseButton textField)
		{
			super(null, PbBundle.message("file.chooser.description.select.protobuf.compiler"), textField, project, new ProtocFileChooseDescriptor(), TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT);
			this.project = project;
		}

		/**
		 * Gets the current module's content root folder as the starting point of the browse action.
		 *
		 * @return the module's content root folder
		 */
		@Override
		protected VirtualFile getInitialFile()
		{
			if(StringUtil.isEmpty(getComponentText()))
			{
				VirtualFile[] roots = ProjectRootManager.getInstance(this.project).getContentRoots();
				if(roots.length > 0)
				{
					return roots[0];
				}
			}
			return super.getInitialFile();
		}

		private static class ProtocFileChooseDescriptor extends FileChooserDescriptor
		{

			public ProtocFileChooseDescriptor()
			{
				this(true, false, false, false, false, false);
			}

			private ProtocFileChooseDescriptor(boolean chooseFiles, boolean chooseFolders, boolean chooseJars, boolean chooseJarsAsFiles, boolean chooseJarContents, boolean chooseMultiple)
			{
				super(chooseFiles, chooseFolders, chooseJars, chooseJarsAsFiles, chooseJarContents, chooseMultiple);
			}

			/**
			 * Limits the selectable file to files that match the platform-specific protobuf compiler name.
			 *
			 * @param file to check
			 * @return true if the file should be selectable
			 */
			@Override
			public boolean isFileSelectable(VirtualFile file)
			{
				return super.isFileSelectable(file) && PbCompiler.getCompilerExecutableName().equals(file.getName());
			}

			/**
			 * Limits file visibility to files that match the platform-specific protobuf compiler name.
			 *
			 * @param file to check
			 * @return true if the file should be selectable
			 */
			@Override
			public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles)
			{
				boolean showFile = file.isDirectory() || PbCompiler.getCompilerExecutableName().equals(file.getName());
				if(!showHiddenFiles && FileElement.isFileHidden(file))
				{
					showFile = false;
				}
				return showFile;
			}
		}

	}
}
