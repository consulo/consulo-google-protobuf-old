package protobuf.lang.psi.impl.auxiliary;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import protobuf.lang.psi.api.auxiliary.PbNamedElement;
import protobuf.lang.psi.impl.PbPsiElementImpl;
import protobuf.lang.psi.utils.PbPsiUtil;

/**
 * @author Nikolay Matveev
 */

public abstract class PbNamedElementImpl extends PbPsiElementImpl implements PbNamedElement {

    public PbNamedElementImpl(ASTNode node) {
        super(node);
    }

    @Override
    public abstract PsiElement getNameElement();

    @Override
    public final PsiElement setName(@NotNull String newName) throws IncorrectOperationException{
        PsiElement nameElement = getNameElement();
        if(nameElement == null){            
            throw new IncorrectOperationException();
        }
        ASTNode nameNode = nameElement.getNode();
        nameNode.getTreeParent().replaceChild(nameNode,PbPsiUtil.createSimpleNodeWithText(newName,getProject()));
        return this;
    }

    @Override
    public final String getName() {
        PsiElement nameElement = getNameElement();
        if (nameElement != null) {
            return nameElement.getText();
        }
        return null;
    }

    @Override
    public final int getTextOffset() {
        PsiElement nameElement = getNameElement();
        if(nameElement != null){
            return super.getTextOffset() + nameElement.getStartOffsetInParent();
        }
        return super.getTextOffset();
    }
}
