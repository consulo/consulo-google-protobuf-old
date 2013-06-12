package protobuf.settings.application;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * @author Nikolay Matveev
 * Date: Apr 5, 2010
 */

@State(
        name = "PbCompilerApplicationSettings",
        storages = {
                //@Storage(id = "default", file = "$APP_FILE$"),//todo [high] i am not sure about this annotation
                @Storage(id = "dir", file = "$APP_CONFIG$/compiler.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)
public class PbCompilerApplicationSettings implements PersistentStateComponent<PbCompilerApplicationSettings>, ApplicationComponent {
    
    public String PATH_TO_COMPILER = "";    

    public static PbCompilerApplicationSettings getInstance() {
        return ApplicationManager.getApplication().getComponent(PbCompilerApplicationSettings.class);
    }

    @Override
    public PbCompilerApplicationSettings getState() {
        return this;
    }

    @Override
    public void loadState(PbCompilerApplicationSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "PbCompilerApplicationSettings";
    }

    @Override
    public void initComponent() {        
    }

    @Override
    public void disposeComponent() {
    }
}
