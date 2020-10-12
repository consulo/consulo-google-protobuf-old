package protobuf;

import com.intellij.icons.AllIcons;
import consulo.google.protobuf.icon.ProtobufIconGroup;
import consulo.ui.image.Image;

/**
 * @author Nikolay Matveev
 *         Date: Mar 5, 2010
 */
public interface PbIcons
{
	Image FILE_TYPE = ProtobufIconGroup.protobuf_16();
	Image MESSAGE = ProtobufIconGroup.protobuf_message_16();
	Image SERVICE = AllIcons.Nodes.Struct;
	Image ENUM = AllIcons.Nodes.Enum;
	Image SERVICE_METHOD = AllIcons.Nodes.Method;
	Image FIELD = AllIcons.Nodes.Field;
	Image GROUP = ProtobufIconGroup.protobuf_group_16();
	Image ENUM_CONSTANT = ProtobufIconGroup.protobuf_enum_constant_16();
}
