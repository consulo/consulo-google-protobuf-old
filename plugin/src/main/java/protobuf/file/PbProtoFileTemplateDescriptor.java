package protobuf.file;

import org.jetbrains.annotations.NonNls;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import consulo.ui.image.Image;

/**
 * A file template descriptor for the ProtoFile.proto file template.
 * @author Travis Cripps
 */
public class PbProtoFileTemplateDescriptor extends FileTemplateDescriptor {

    public PbProtoFileTemplateDescriptor(@NonNls String fileName) {
        super(fileName);
    }

    public PbProtoFileTemplateDescriptor(@NonNls String fileName, Image icon) {
        super(fileName, icon);
    }

    @Override
    public String getDisplayName() {
        return "Protobuf file";
    }
}
