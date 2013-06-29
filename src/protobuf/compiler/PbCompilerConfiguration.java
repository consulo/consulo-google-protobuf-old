package protobuf.compiler;

import org.consulo.lombok.annotations.ProjectService;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.module.Module;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * @author Nikolay Matveev
 *         Date: Apr 5, 2010
 */

@ProjectService
@State(
		name = "PbCompilerConfiguration",
		storages = {
				@Storage(file = StoragePathMacros.PROJECT_FILE),
				@Storage(file = StoragePathMacros.PROJECT_CONFIG_DIR + "/compiler.xml", scheme = StorageScheme.DIRECTORY_BASED)
		})
public class PbCompilerConfiguration implements PersistentStateComponent<PbCompilerConfiguration>
{
	public boolean COMPILATION_ENABLED;
	public String PATH_TO_COMPILER = "";

	public String getCompilerOutput(Module module)
	{
		return module.getModuleDirPath(); //TODO [VISTALL]
	}

	@Override
	public PbCompilerConfiguration getState()
	{
		return this;
	}

	@Override
	public void loadState(PbCompilerConfiguration state)
	{
		XmlSerializerUtil.copyBean(state, this);
	}
}
