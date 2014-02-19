package org.consulo.google.protobuf.java;

import javax.swing.JComponent;

import org.consulo.module.extension.MutableModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableRootModel;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public class GoogleProtobufJavaMutableModuleExtension extends GoogleProtobufJavaModuleExtension implements MutableModuleExtension<GoogleProtobufJavaModuleExtension>
{
	public GoogleProtobufJavaMutableModuleExtension(@NotNull String id, @NotNull Module module)
	{
		super(id, module);
	}

	@Nullable
	@Override
	public JComponent createConfigurablePanel(@NotNull ModifiableRootModel modifiableRootModel, @Nullable Runnable runnable)
	{
		return null;
	}

	@Override
	public void setEnabled(boolean b)
	{
		myIsEnabled = b;
	}

	@Override
	public boolean isModified(@NotNull GoogleProtobufJavaModuleExtension extension)
	{
		return myIsEnabled != extension.isEnabled();
	}
}
