package org.consulo.google.protobuf.java;

import org.consulo.google.protobuf.module.extension.GoogleProtobufSupportProvider;
import org.consulo.java.module.extension.JavaModuleExtension;
import org.consulo.module.extension.ModuleExtension;
import org.consulo.psi.PsiPackage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.psi.JavaPsiFacade;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public class JavaGoogleProtobufSupportProvider implements GoogleProtobufSupportProvider
{
	@NotNull
	@Override
	public Class<? extends ModuleExtension> getExtensionClass()
	{
		return JavaModuleExtension.class;
	}

	@Nullable
	@Override
	public PsiPackage findPackage(Module module, @NotNull String name)
	{
		return JavaPsiFacade.getInstance(module.getProject()).findPackage(name);
	}

	@NotNull
	@Override
	public String getExePath()
	{
		return SystemInfo.isWindows ? "protoc.exe" : "protoc";
	}

	@NotNull
	@Override
	public String[] getAdditionalArguments()
	{
		return new String[] {"java_out=.", "$FileName$"};
	}
}
