package protobuf.highlighter;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

/**
 * @author Nikolay Matveev
 * Date: Mar 6, 2010
 */
public interface PbDefaultHighlighter {
    TextAttributesKey TEXT_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    TextAttributesKey LINE_COMMENT_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    TextAttributesKey STRING_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_STRING", DefaultLanguageHighlighterColors.STRING);
    TextAttributesKey WRONG_STRING_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_WRONG_STRING", DefaultLanguageHighlighterColors.STRING);
    TextAttributesKey BAD_CHARACTER_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    TextAttributesKey NUMBER_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    TextAttributesKey KEYWORD_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    TextAttributesKey ENUM_CONSTANT_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_ENUM_CONSTANT", DefaultLanguageHighlighterColors.STATIC_FIELD);
    TextAttributesKey ERROR_INFO_ATTR_KEY = TextAttributesKey.createTextAttributesKey("PROTOBUF_REF", CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
}

