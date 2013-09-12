package org.consulo.google.protobuf;

import com.intellij.ide.IconDescriptor;
import com.intellij.ide.IconDescriptorUpdater;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import protobuf.PbIcons;
import protobuf.lang.psi.api.declaration.*;

/**
 * @author VISTALL
 * @since 12.09.13.
 */
public class PbIconDescriptorUpdater implements IconDescriptorUpdater {
	@Override
	public void updateIcon(@NotNull IconDescriptor iconDescriptor, @NotNull PsiElement element, int flags) {
		if (element instanceof PbMessageDef) {
			iconDescriptor.setMainIcon(PbIcons.MESSAGE);
		} else if (element instanceof PbEnumDef) {
			iconDescriptor.setMainIcon(PbIcons.ENUM);
		} else if (element instanceof PbServiceDef) {
			iconDescriptor.setMainIcon(PbIcons.SERVICE);
		} else if (element instanceof PbServiceMethodDef) {
			iconDescriptor.setMainIcon(PbIcons.SERVICE_METHOD);
		} else if (element instanceof PbFieldDef) {
			iconDescriptor.setMainIcon(PbIcons.FIELD);
		} else if (element instanceof PbGroupDef) {
			iconDescriptor.setMainIcon(PbIcons.GROUP);
		} else if(element instanceof PbEnumConstantDef){
			iconDescriptor.setMainIcon(PbIcons.ENUM_CONSTANT);
		}
	}
}
