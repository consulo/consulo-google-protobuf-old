package protobuf.file;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.ui.image.Image;
import protobuf.PbIcons;
import protobuf.PbLanguage;

/**
 * @author Nikolay Matveev
 */
public class PbFileType extends LanguageFileType {

    public static final PbFileType PROTOBUF_FILE_TYPE = new PbFileType();
    public static final Language PROTOBUF_LANGUAGE = PROTOBUF_FILE_TYPE.getLanguage();
    public static final String[] DEFAULT_ASSOCIATED_EXTENSIONS = new String[]{"proto"};

    private PbFileType(){
        super(new PbLanguage());
    }

    @NotNull
    public String getName() {
        return "Protobuf";
    }

    @NotNull
    public String getDescription() {
        return "Protocol Buffers file";
    }

    @NotNull
    public String getDefaultExtension() {
        return "proto";
    }

    public Image getIcon() {
        return PbIcons.FILE_TYPE;
    }
}
