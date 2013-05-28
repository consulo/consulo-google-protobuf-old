package protobuf.compiler;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import protobuf.file.PbFileType;

/**
 * @author Nikolay Matveev
 * Date: Apr 5, 2010
 */
public class PbCompilerLoader implements ProjectComponent {

    Project myProject;

    public PbCompilerLoader(Project project) {
        myProject = project;
    }

    @Override
    public void projectOpened() {
        CompilerManager compilerManager = CompilerManager.getInstance(myProject);
        compilerManager.addCompilableFileType(PbFileType.PROTOBUF_FILE_TYPE);
        compilerManager.addBeforeTask(new PbPrecompileTask());
       // compilerManager.addCompiler(new PbCompiler(myProject));
    }

    @Override
    public void projectClosed() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "PbCompilerLoader";
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }
}
