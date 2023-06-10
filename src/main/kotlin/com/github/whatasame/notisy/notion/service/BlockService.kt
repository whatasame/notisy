package com.github.whatasame.notisy.notion.service

import com.github.whatasame.notisy.notion.repository.BlockRepository
import notion.api.v1.model.blocks.*

class BlockService(private val blockRepository: BlockRepository) {

    fun getBlocks(pageId: String): Blocks {
        return blockRepository.getBlocks(pageId)
    }

    fun getHtml(block: Block): String {
        return when (block.type) {
            BlockType.Paragraph -> getHtml(block as ParagraphBlock)
            BlockType.HeadingOne -> getHtml(block as HeadingOneBlock)
            BlockType.HeadingTwo -> getHtml(block as HeadingTwoBlock)
            BlockType.HeadingThree -> getHtml(block as HeadingThreeBlock)
            BlockType.BulletedListItem -> TODO()
            BlockType.NumberedListItem -> TODO()
            BlockType.LinkToPage -> TODO()
            BlockType.LinkPreview -> TODO()
            BlockType.Equation -> TODO()
            BlockType.Bookmark -> TODO()
            BlockType.Callout -> TODO()
            BlockType.Column -> TODO()
            BlockType.ColumnList -> TODO()
            BlockType.Breadcrumb -> TODO()
            BlockType.TableOfContents -> TODO()
            BlockType.Divider -> TODO()
            BlockType.Video -> TODO()
            BlockType.Quote -> TODO()
            BlockType.ToDo -> TODO()
            BlockType.Toggle -> TODO()
            BlockType.Code -> TODO()
            BlockType.Embed -> TODO()
            BlockType.Image -> TODO()
            BlockType.File -> TODO()
            BlockType.PDF -> TODO()
            BlockType.ChildPage -> TODO()
            BlockType.ChildDatabase -> TODO()
            BlockType.SyncedBlock -> TODO()
            BlockType.Table -> TODO()
            BlockType.TableRow -> TODO()
            BlockType.Template -> TODO()
            BlockType.Audio -> TODO()
            BlockType.Unsupported -> TODO()
        }
    }

    private fun getHtml(block: ParagraphBlock): String {
        return block.paragraph.richText
            .joinToString(separator = ("")) { richText ->
                "<p>${richText.plainText}</p>"
            }
    }

    private fun getHtml(block: HeadingOneBlock): String {
        return block.heading1.richText
            .joinToString(separator = ("")) { richText ->
                "<h1>${richText.plainText}</h1>"
            }
    }

    private fun getHtml(block: HeadingTwoBlock): String {
        return block.heading2.richText
            .joinToString(separator = ("")) { richText ->
                "<h2>${richText.plainText}</h2>"
            }
    }

    private fun getHtml(block: HeadingThreeBlock): String {
        return block.heading3.richText
            .joinToString(separator = ("")) { richText ->
                "<h3>${richText.plainText}</h3>"
            }
    }

}