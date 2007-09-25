//iTextVersion.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle
import com.lowagie.text.Document

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'version.pdf'
	def a = builder.document(filename:file){
			paragraph(text:"This page was made using " + Document.getVersion())
	}
} catch (Exception e) {
	e.printStackTrace()
}
