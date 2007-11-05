//Hello World.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'NestingTextArgs-new.pdf'
	def a = builder.document(filename:file){
		paragraph("Hello World") {
			phrase("Hi")
			phrase("Bye")
		}
	}
} catch (Exception e) {
	e.printStackTrace()
}
