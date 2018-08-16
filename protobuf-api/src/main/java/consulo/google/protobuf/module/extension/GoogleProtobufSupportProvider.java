package consulo.google.protobuf.module.extension;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import consulo.module.extension.ModuleExtension;
import protobuf.lang.psi.api.PbFile;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public interface GoogleProtobufSupportProvider
{
	ExtensionPointName<GoogleProtobufSupportProvider> EP_NAME = ExtensionPointName.create("consulo.google.protobuf.supportProvider");

	@NotNull
	Class<? extends ModuleExtension> getExtensionClass();

	@Nullable
	PsiElement findPackage(Module module, @NotNull String name);

	@NotNull
	String getExePath();

	@NotNull
	String[] getAdditionalArguments();

	@NotNull
	String[] getGeneratedFiles(@NotNull PbFile pbFile);
}
