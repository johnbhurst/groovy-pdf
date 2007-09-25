//PdfVersion.groovy
//
// @author jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'PdfVersion.pdf'
	def a = builder.document(filename:file, pdfVersion:PdfWriter.VERSION_1_2){
		paragraph(text:"This is a PDF-1.2 document")
	}
} catch (Exception e) {
	e.printStackTrace()
}
