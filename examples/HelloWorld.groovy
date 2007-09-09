//Hello World.groovy
//
// @author jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'HelloWorld.pdf'
	def a = builder.document(filename:file){
		paragraph(text:"Hello World")
	}
} catch (Exception e) {
	e.printStackTrace()
}
