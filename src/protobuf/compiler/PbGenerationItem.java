package protobuf.compiler;

import java.io.File;
import java.util.ArrayList;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.compiler.GeneratingCompiler;
import com.intellij.openapi.compiler.ValidityState;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import protobuf.lang.psi.api.PbFile;
import protobuf.lang.psi.impl.PbFileImpl;

/**
 * @author Nikolay Matveev
 *         Date: Apr 5, 2010
 */
public class PbGenerationItem implements GeneratingCompiler.GenerationItem
{

	Module myModule;
	VirtualFile myFile;
	boolean myIsTestSource;

	public PbGenerationItem(VirtualFile file, Module module, boolean isTestSource)
	{
		myModule = module;
		myFile = file;
		myIsTestSource = isTestSource;
	}

	@Override
	public String getPath()
	{
		return myFile.getPath();
	}

	/**
	 * Gets the base directory for the protoc command, e.g. the --proto_path argument for this item.  Returns the item's
	 * parent directory.
	 *
	 * @return the base directory
	 */
	public String getBaseDir()
	{
		return myFile.getParent().getPath();
	}

	/**
	 * Uses a {@link PbGenerationItemValidityState} based on the last modified timestamp of the .proto file and whether
	 * the destination files exist, so the compiler only generates the output source file when the .proto file changes
	 * or the destination files do not exist.
	 */
	@Override
	// Note that this method gets its information from inspecting the Psi element tree, so must use the runReadAction and
	// Computable because it's not run from the normal thread that deals with them.
	public ValidityState getValidityState()
	{
		final PbFile pbFile = ApplicationManager.getApplication().runReadAction(new Computable<PbFile>()
		{
			public PbFile compute()
			{
				return (PbFile) PsiManager.getInstance(myModule.getProject()).findFile(myFile);
			}
		});
		String packageName = ApplicationManager.getApplication().runReadAction(new Computable<String>()
		{
			public String compute()
			{
				return ((PbFileImpl) pbFile).getJavaPackageName();
			}
		});
		if(null == packageName)
		{
			packageName = "";
		}
		final ArrayList<String> fileNames = ApplicationManager.getApplication().runReadAction(new Computable<ArrayList<String>>()
		{
			public ArrayList<String> compute()
			{
				return ((PbFileImpl) pbFile).getJavaClassNames();
			}
		});
		final String sep = System.getProperty("file.separator");
		final String safePackageName = packageName.length() > 0 ? packageName.replaceAll("\\.", sep) : packageName;
		String outputPath = getOutputPath() + sep + safePackageName;
		boolean outputFilesExist = false;
		for(String fileName : fileNames)
		{
			String path = outputPath + sep + fileName + ".java";
			outputFilesExist = new File(path).exists();
			if(!outputFilesExist)
			{
				break;
			}
		}

		return new PbGenerationItemValidityState(myFile.getModificationStamp(), outputFilesExist);
	}

	@Override
	public Module getModule()
	{
		return myModule;
	}


	@Override
	public boolean isTestSource()
	{
		return myIsTestSource;
	}

	public String getUrl()
	{
		return myFile.getUrl();
	}

	public String getOutputPath()
	{
		return ""; //TODO [VISTALL]
	}
}
