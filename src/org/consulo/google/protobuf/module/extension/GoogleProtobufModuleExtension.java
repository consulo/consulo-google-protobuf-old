package org.consulo.google.protobuf.module.extension;

import com.intellij.openapi.module.Module;
import org.consulo.module.extension.ModuleExtension;
import org.consulo.psi.PsiPackage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public interface GoogleProtobufModuleExtension<T extends ModuleExtension<T>> extends ModuleExtension<T>{
  @Nullable
  PsiPackage findPackage(Module module, @NotNull String name);
}
