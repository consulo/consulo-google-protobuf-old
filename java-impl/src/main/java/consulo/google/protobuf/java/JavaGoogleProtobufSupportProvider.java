package consulo.google.protobuf.java;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.util.ArrayUtil;
import consulo.google.protobuf.module.extension.GoogleProtobufSupportProvider;
import consulo.java.module.extension.JavaModuleExtension;
import consulo.module.extension.ModuleExtension;
import consulo.psi.PsiPackage;
import protobuf.lang.psi.api.PbFile;
import protobuf.lang.psi.api.declaration.PbMessageDef;
import protobuf.lang.psi.api.member.PbOptionAssignment;

/**
 * @author VISTALL
 * @since 06.07.13.
 */
public class JavaGoogleProtobufSupportProvider implements GoogleProtobufSupportProvider
{
	@NotNull
	@Override
	public Class<? extends ModuleExtension> getExtensionClass()
	{
		return JavaModuleExtension.class;
	}

	@Nullable
	@Override
	public PsiPackage findPackage(Module module, @NotNull String name)
	{
		return JavaPsiFacade.getInstance(module.getProject()).findPackage(name);
	}

	@NotNull
	@Override
	public String getExePath()
	{
		return SystemInfo.isWindows ? "protoc.exe" : "protoc";
	}

	@NotNull
	@Override
	public String[] getAdditionalArguments()
	{
		return new String[] {"--java_out=.", "$FileName$"};
	}

	@NotNull
	@Override
	public String[] getGeneratedFiles(@NotNull PbFile pbFile)
	{
		String packageName = getJavaPackageName(pbFile);
		List<String> list = new ArrayList<String>();
		for(String f : getJavaClassNames(pbFile))
		{
			StringBuilder builder = new StringBuilder();
			builder.append("$OutPath$");
			if(!StringUtil.isEmpty(packageName))
			{
				builder.append("/").append(packageName.replace(".", "/"));
			}
			builder.append("/").append(f).append(".java");

			list.add(builder.toString());
		}
		return ArrayUtil.toStringArray(list);
	}

	/**
	 * Gets the Java package name for this file, looking first for the <code>option java_package...</code> declaration
	 * and falling back to the regular package definition.
	 *
	 * @return the Java package name
	 */
	private String getJavaPackageName(PbFile pbFile)
	{
		String packageName = pbFile.getPackageName();
		final PbOptionAssignment[] optionAssignments = pbFile.getOptions();
		for(PbOptionAssignment assignment : optionAssignments)
		{
			if("java_package".equals(assignment.getOptionName()))
			{
				packageName = assignment.getOptionValue();
				break;
			}
		}
		return packageName;
	}


	/**
	 * <p>Gets the Java class name(s) that will be generated from this file.  This must be a List to allow for the behavior
	 * when the <code>option java_multiple_files...</code> declaration is used.</p>
	 * <p/>
	 * <p>Mimics the behavior of the protobuf compiler, considering the <code>option java_outer_classname...</code>
	 * declaration, and the <code>option java_multiple_files...</code> declaration, falling back to the camel-cased name
	 * of the .proto file.</p>
	 *
	 * @return the Java class name(s)
	 */
	public List<String> getJavaClassNames(PbFile pbFile)
	{
		List<String> classNames = new ArrayList<String>();
		final PbOptionAssignment[] optionAssignments = pbFile.getOptions();
		String name = pbFile.getName();
		String fileName = name.substring(0, name.indexOf(".proto"));

		boolean useMultipleFiles = false;
		for(PbOptionAssignment assignment : optionAssignments)
		{
			// Note: java_outer_class_name and java_multiple_files are NOT mutually exclusive.
			if("java_outer_classname".equals(assignment.getOptionName()))
			{
				fileName = assignment.getOptionValue();
			}
			else if("java_multiple_files".equals(assignment.getOptionName()))
			{
				useMultipleFiles = Boolean.valueOf(assignment.getOptionValue());
			}
		}

		if(useMultipleFiles)
		{
			PbMessageDef[] messageDefs = pbFile.getMessageDefinitions();
			for(PbMessageDef messageDef : messageDefs)
			{
				classNames.add(messageDef.getName());
			}
			// When using multiple files, a class containing the .proto file's descriptor and initialization code is also
			// generated for the file containing the .proto defs.
			classNames.add(StringUtil.capitalizeWords(fileName, "_", true, false));
		}
		else
		{
			classNames.add(StringUtil.capitalizeWords(fileName, "_", true, false));
		}

		return classNames;
	}
}
