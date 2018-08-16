package protobuf.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.LocalTimeCounter;
import protobuf.file.PbFileType;

import java.io.File;
import java.io.IOException;

/**
 * @author Nikolay Matveev
 * Date: Apr 7, 2010
 */
public abstract class PbTestUtil {
    public static final String REF_MARKER = "<ref>";
    public static final String AIM_MARKER = "<aim>";

    public static String testDataPath;
    static{
        try {
            testDataPath = new File(".").getCanonicalPath() + "/testdata/";
        } catch (IOException e) {            
            testDataPath = null;
        }
    }

    public static String getTestDataPath(){        
        return testDataPath;        
    }

    public static Pair<String, String> getSimpleTestMaterialsFromFile(final String filePath, boolean convertSeparators) throws IOException {
        Pair<String, String> results;
        String content = new String(FileUtil.loadFileText(new File(filePath)));
        String[] temp = content.split("------");
        if(convertSeparators){
            results = new Pair<String, String>(StringUtil.convertLineSeparators(temp[0].trim()),StringUtil.convertLineSeparators(temp[1].trim()));
        } else{
            results = new Pair<String, String>(temp[0].trim(), temp[1].trim());
        }
        return results;
    }

    public static PsiFile createPseudoProtoFile(final Project project, final String fileName, final String text) throws IncorrectOperationException {        
        return PsiFileFactory.getInstance(project).createFileFromText(
                "temp.proto",                
                PbFileType.PROTOBUF_FILE_TYPE,
                text,
                LocalTimeCounter.currentTime(),
                true);
    }

    public static String loadFromFile(final String filePath) throws IOException {        
        return StringUtil.convertLineSeparators(new String(FileUtil.loadFileText(new File(filePath))));
    }        
}
