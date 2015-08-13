package org.consulo.google.protobuf.vfs;

import org.consulo.google.protobuf.module.extension.GoogleProtobufModuleExtensionUtil;
import org.consulo.google.protobuf.module.extension.GoogleProtobufSupportProvider;
import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.vfs.backgroundTask.BackgroundTaskByVfsChangeProvider;
import org.mustbe.consulo.vfs.backgroundTask.BackgroundTaskByVfsParameters;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.util.ArrayUtil;
import protobuf.file.PbFileType;
import protobuf.lang.psi.api.PbFile;

/**
 * @author VISTALL
 * @since 07.10.13.
 */
public class PbBackgroundTaskByVfsChangeProvider extends BackgroundTaskByVfsChangeProvider
{
	@NotNull
	@Override
	public String getTemplateName()
	{
		return "Google Protobuf";
	}

	@Override
	public boolean validate(@NotNull Project project, @NotNull VirtualFile virtualFile)
	{
		return GoogleProtobufModuleExtensionUtil.getProvider(project, virtualFile) != null && virtualFile.getFileType() == PbFileType.PROTOBUF_FILE_TYPE;
	}

	@Override
	public void setDefaultParameters(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull BackgroundTaskByVfsParameters backgroundTaskByVfsParameters)
	{
		GoogleProtobufSupportProvider provider = GoogleProtobufModuleExtensionUtil.getProvider(project, virtualFile);

		assert provider != null;

		backgroundTaskByVfsParameters.setExePath(provider.getExePath());

		backgroundTaskByVfsParameters.setProgramParameters(StringUtil.join(provider.getAdditionalArguments(), " "));
		backgroundTaskByVfsParameters.setWorkingDirectory("$FileParentPath$");
		backgroundTaskByVfsParameters.setOutPath("$FileParentPath$");
	}


	@NotNull
	@Override
	public String[] getGeneratedFiles(@NotNull PsiFile psiFile)
	{
		if(!(psiFile instanceof PbFile))
		{
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		GoogleProtobufSupportProvider provider = GoogleProtobufModuleExtensionUtil.getProvider(psiFile);
		if(provider == null )
		{
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		return provider.getGeneratedFiles((PbFile) psiFile);
	}
}
