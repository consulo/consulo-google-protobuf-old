package protobuf.lang.psi.api;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import protobuf.lang.psi.api.declaration.PbImportDef;
import protobuf.lang.psi.api.declaration.PbMessageDef;
import protobuf.lang.psi.api.declaration.PbPackageDef;
import protobuf.lang.psi.api.member.PbOptionAssignment;

/**
 * @author Nikolay Matveev
 */

public interface PbFile extends PsiFile, PbPsiElement
{
	PbPackageDef getPackageDefinition();

	String getPackageName();

	@NotNull
	PbOptionAssignment[] getOptions();

	@NotNull
	PbImportDef[] getImportDefinitions();

	@NotNull
	PbMessageDef[] getMessageDefinitions();
}
