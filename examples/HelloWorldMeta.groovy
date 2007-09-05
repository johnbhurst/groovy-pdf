//HelloWorldMeta.groovy
//
// @ jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle


try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'HelloWorldMeta.pdf'
	def a = builder.document(filename:file, title:'Hello World example',
		keywords:'iText, Hello World, step 3, metadata',
		creator:'My program using iText', author:'James Williams'){
			paragraph(text:"Hello World")
	}
} catch (Exception e) {
	e.printStackTrace()
}
