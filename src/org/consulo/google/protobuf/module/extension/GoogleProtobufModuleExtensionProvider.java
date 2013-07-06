package org.consulo.google.protobuf.module.extension;

import org.consulo.module.extension.ModuleExtension;
import org.consulo.module.extension.ModuleExtensionProvider;
import org.consulo.module.extension.MutableModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import protobuf.PbIcons;

import javax.swing.*;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public abstract class GoogleProtobufModuleExtensionProvider<ImmutableModel extends ModuleExtension, MutableModel extends MutableModuleExtension> implements ModuleExtensionProvider<ImmutableModel,
    MutableModel> {
  @Nullable
  @Override
  public Icon getIcon() {
    return PbIcons.FILE_TYPE;
  }

  @NotNull
  @Override
  public String getName() {
    return "Google Protobuf";
  }
}
