package protobuf.lang.parser;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageVersion;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import protobuf.lang.parser.parsing.CompilationUnit;
import protobuf.lang.parser.util.PbPatchedPsiBuilder;

/**
 * @author Nikolay Matveev
 * Date: Mar 7, 2010
 */
public class PbParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(IElementType root, PsiBuilder builder, LanguageVersion languageVersion) {
        builder.setDebugMode(true);
        PsiBuilder.Marker rootMarker = builder.mark();
        CompilationUnit.parse(new PbPatchedPsiBuilder(builder));
        rootMarker.done(root);        
        return builder.getTreeBuilt();
    }
}
