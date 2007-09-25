//LandscapePortrait.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle


try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'LandscapePortrait.pdf'
	def a = builder.document(filename:file,pageSize:PageSize.LETTER.rotate()){
		paragraph(text:"To create a document in landscape format, just make the height smaller than the width. For instance by rotating the PageSize Rectangle: PageSize.LETTER.rotate()")

		page(pageSize:PageSize.LETTER) {
			paragraph(text:"This should be portrait again.")
		}
	}
} catch (Exception e) {
	e.printStackTrace()
}
