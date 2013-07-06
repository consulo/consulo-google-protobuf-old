package org.consulo.google.protobuf.module.extension;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.psi.PsiElement;
import org.consulo.psi.PsiPackage;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public class GoogleProtobufModuleExtensionUtil {
  public static PsiPackage findPackage(PsiElement psiElement, String name) {
    Module moduleForPsiElement = ModuleUtilCore.findModuleForPsiElement(psiElement);
    if(moduleForPsiElement == null) {
      return null;
    }
    GoogleProtobufModuleExtension extension = ModuleUtilCore.getExtension(moduleForPsiElement, GoogleProtobufModuleExtension.class);
    if(extension == null) {
      return null;
    }
    return extension.findPackage(moduleForPsiElement, name);
  }
}
