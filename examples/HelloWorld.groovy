//Hello World.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'HelloWorld.pdf'
	def a = builder.document(filename:file){
		paragraph("Hello World")
	}
} catch (Exception e) {
	e.printStackTrace()
}
