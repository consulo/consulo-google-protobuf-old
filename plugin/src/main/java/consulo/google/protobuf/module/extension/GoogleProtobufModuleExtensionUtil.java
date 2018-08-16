package consulo.google.protobuf.module.extension;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public class GoogleProtobufModuleExtensionUtil
{
	public static PsiElement findPackage(PsiElement psiElement, String name)
	{
		Module moduleForFile = ModuleUtilCore.findModuleForPsiElement(psiElement);
		if(moduleForFile == null)
		{
			return null;
		}

		GoogleProtobufSupportProvider protobufSupportProvider = getProvider(psiElement);

		if(protobufSupportProvider == null)
		{
			return null;
		}

		return protobufSupportProvider.findPackage(moduleForFile, name);
	}


	@Nullable
	public static GoogleProtobufSupportProvider getProvider(@Nullable PsiElement element)
	{
		if(element == null)
		{
			return null;
		}
		Module moduleForFile = ModuleUtilCore.findModuleForPsiElement(element);
		if(moduleForFile == null)
		{
			return null;
		}

		for(GoogleProtobufSupportProvider googleProtobufSupportProvider : GoogleProtobufSupportProvider.EP_NAME.getExtensions())
		{
			if(ModuleUtilCore.getExtension(moduleForFile, googleProtobufSupportProvider.getExtensionClass()) != null)
			{
				return googleProtobufSupportProvider;
			}
		}
		return null;
	}

	@Nullable
	public static GoogleProtobufSupportProvider getProvider(Project project, VirtualFile virtualFile)
	{
		if(project == null || virtualFile == null)
		{
			return null;
		}
		Module moduleForFile = ModuleUtilCore.findModuleForFile(virtualFile, project);
		if(moduleForFile == null)
		{
			return null;
		}

		for(GoogleProtobufSupportProvider googleProtobufSupportProvider : GoogleProtobufSupportProvider.EP_NAME.getExtensions())
		{
			if(ModuleUtilCore.getExtension(moduleForFile, googleProtobufSupportProvider.getExtensionClass()) != null)
			{
				return googleProtobufSupportProvider;
			}
		}
		return null;
	}
}
