package org.consulo.google.protobuf.java;

import org.consulo.google.protobuf.module.extension.GoogleProtobufModuleExtension;
import org.consulo.module.extension.impl.ModuleExtensionImpl;
import org.consulo.psi.PsiPackage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public class GoogleProtobufJavaModuleExtension extends ModuleExtensionImpl<GoogleProtobufJavaModuleExtension> implements GoogleProtobufModuleExtension<GoogleProtobufJavaModuleExtension>
{
	public GoogleProtobufJavaModuleExtension(@NotNull String id, @NotNull Module module)
	{
		super(id, module);
	}

	@Nullable
	@Override
	public PsiPackage findPackage(Module module, @NotNull String name)
	{
		return JavaPsiFacade.getInstance(module.getProject()).findPackage(name);
	}

	@NotNull
	@Override
	public String getCompileParameter()
	{
		return "java_out";
	}
}
