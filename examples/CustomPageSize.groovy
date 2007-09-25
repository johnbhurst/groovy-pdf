//CustomPageSize.groovy
//
// @author jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle
import java.awt.Color
import com.lowagie.text.Document
import com.lowagie.text.DocumentException
import com.lowagie.text.Paragraph
import com.lowagie.text.PageSize
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.Chunk
import com.lowagie.text.Font
import com.lowagie.text.FontFactory
import com.lowagie.text.Phrase
import com.lowagie.text.Image


try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'CustomPageSize.pdf'
	def rect = new Rectangle(216, 720)
	rect.setBackgroundColor(new Color(0xFF, 0xFF, 0xDE))
	def a = builder.document(filename:file, pageSize:rect ){
		paragraph(text:'The size of this page is 216x720 points.')
		paragraph(text:'216pt / 72 points per inch = 3 inch')	
		paragraph(text:'720pt / 72 points per inch = 10 inch')	
		paragraph(text:'The size of this page is 3x10 inch.')	
		paragraph(text:'3 inch x 2.54 = 7.62 cm')	
		paragraph(text:'10 inch x 2.54 = 25.4 cm')	
		paragraph(text:'The size of this page is 7.62x25.4 cm.')	
		paragraph(text:'The backgroundcolor of the Rectangle used for this PageSize, is #FFFFDE.')	
		paragraph(text:"That's why the background of this document is yellowish...")	
	}
} catch (Exception e) {
	e.printStackTrace()
}
