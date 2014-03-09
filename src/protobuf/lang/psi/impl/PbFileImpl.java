package protobuf.lang.psi.impl;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import protobuf.file.PbFileType;
import protobuf.lang.psi.PbPsiElementVisitor;
import protobuf.lang.psi.api.PbFile;
import protobuf.lang.psi.api.declaration.PbImportDef;
import protobuf.lang.psi.api.declaration.PbMessageDef;
import protobuf.lang.psi.api.declaration.PbPackageDef;
import protobuf.lang.psi.api.member.PbOptionAssignment;

/**
 * @author Nikolay Matveev
 *         Date: Mar 9, 2010
 */
public class PbFileImpl extends PsiFileBase implements PbFile
{

	public PbFileImpl(FileViewProvider viewProvider)
	{
		super(viewProvider, PbFileType.PROTOBUF_FILE_TYPE.getLanguage());
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return PbFileType.PROTOBUF_FILE_TYPE;
	}

	@Override
	public void accept(PbPsiElementVisitor visitor)
	{
		visitor.visitPbFile(this);
	}

	@Override
	public String toString()
	{
		return "PROTO_FILE";
	}

	/**
	 * Gets the package definition declaration.
	 *
	 * @return the package definition
	 */
	@Override
	public PbPackageDef getPackageDefinition()
	{
		return findChildByClass(PbPackageDef.class);
	}

	/**
	 * Gets the package name from the package declaration.
	 *
	 * @return the package name, if present, or the empty string.
	 */
	@Override
	public String getPackageName()
	{
		final PbPackageDef packageDef = getPackageDefinition();
		return packageDef != null ? packageDef.getPackageName() : "";
	}

	@NotNull
	@Override
	public PbOptionAssignment[] getOptions()
	{
		return findChildrenByClass(PbOptionAssignment.class);
	}

	@NotNull
	@Override
	public PbImportDef[] getImportDefinitions()
	{
		return findChildrenByClass(PbImportDef.class);
	}

	@NotNull
	@Override
	public PbMessageDef[] getMessageDefinitions()
	{
		return findChildrenByClass(PbMessageDef.class);
	}

	@Override
	public PsiElement getContext()
	{
		return super.getContext();
	}

	public PbFile[] getImportedFiles(boolean onlyAliased)
	{
		PbImportDef[] importDefs = getImportDefinitions();
		ArrayList<PbFile> importFiles = new ArrayList<PbFile>(importDefs.length);
		for(PbImportDef importDef : importDefs)
		{
			PbFile aliasedFile = importDef.getAliasedFile();
			if(aliasedFile != null || !onlyAliased)
			{
				importFiles.add(aliasedFile);
			}

		}
		return importFiles.toArray(new PbFile[importFiles.size()]);
	}
}
