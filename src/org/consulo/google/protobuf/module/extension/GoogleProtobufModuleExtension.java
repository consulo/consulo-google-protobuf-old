package org.consulo.google.protobuf.module.extension;

import org.consulo.module.extension.ModuleExtension;
import org.consulo.psi.PsiPackage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public interface GoogleProtobufModuleExtension<T extends ModuleExtension<T>> extends ModuleExtension<T>
{
	@Nullable
	PsiPackage findPackage(Module module, @NotNull String name);

	@NotNull
	String getCompileParameter();
}
