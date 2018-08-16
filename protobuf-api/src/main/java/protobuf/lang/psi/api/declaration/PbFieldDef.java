package protobuf.lang.psi.api.declaration;

import static protobuf.lang.psi.PbPsiEnums.FieldLabel;
import static protobuf.lang.psi.PbPsiEnums.FieldType;

import protobuf.lang.psi.api.PbPsiElement;
import protobuf.lang.psi.api.auxiliary.PbNamedElement;
import protobuf.lang.psi.api.reference.PbRef;

/**
 * @author Nikolay Matveev
 */
public interface PbFieldDef extends PbPsiElement, PbNamedElement {

    public FieldLabel getLabel();

    public FieldType getType();

    public FieldType getConcreteType();

    public PbRef getTypeRef();

}
