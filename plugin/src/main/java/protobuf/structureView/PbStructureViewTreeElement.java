package protobuf.structureView;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;
import protobuf.lang.psi.PbPsiElementVisitor;
import protobuf.lang.psi.api.PbFile;
import protobuf.lang.psi.api.PbPsiElement;
import protobuf.lang.psi.api.auxiliary.PbNamedElement;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Nikolay Matveev
 */
public class PbStructureViewTreeElement extends PsiTreeElementBase<PbPsiElement> {

    public PbStructureViewTreeElement(@NotNull PbFile psiElement) {
        super(psiElement);
    }

    public PbStructureViewTreeElement(@NotNull PbNamedElement psiElement) {
        super(psiElement);
    }

    @NotNull
    @Override
    public Collection<StructureViewTreeElement> getChildrenBase() {
        final Collection<StructureViewTreeElement> children = new ArrayList<StructureViewTreeElement>();
        //noinspection ConstantConditions
        getElement().acceptChildren(new PbPsiElementVisitor() {
            public void visitPbElement(final PbPsiElement element) {
                if (element instanceof PbNamedElement) {
                    children.add(new PbStructureViewTreeElement((PbNamedElement)element));
                } else {
                    element.acceptChildren(this);
                }
            }
        });
        return children;
    }

    @Override
    public String getPresentableText() {
        final PbPsiElement element = getElement();
        if (element instanceof PsiNamedElement) {
            return ((PsiNamedElement) element).getName();
        }
        assert false;
        return null;
    }
}
