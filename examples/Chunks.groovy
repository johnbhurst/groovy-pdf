//Chunks.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle
import com.lowagie.text.Chunk
import com.lowagie.text.Phrase
import java.awt.Color
try {
	def builder = new PDFBuilder(debug:true)
	def file = 'examples' + File.separator + 'output' + File.separator + 'Chunks.pdf'
	def a = builder.document(filename:file){
		chunk(textRise:8.0f, background:new Color(0xFF,0xDE,0xAD), text:"quick brown fox")
		chunk(text:" jumps over ")
		chunk(textRise:-8.0f, underline:[new Color(0xFF, 0x00, 0x00), 3.0f, 0.0f, -13.0f, 0.0f, PdfContentByte.LINE_CAP_ROUND], text:"the lazy dog")
	}
} catch (Exception e) {
	e.printStackTrace()
}
