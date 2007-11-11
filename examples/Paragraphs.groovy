//Paragraphs.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.Font
import com.lowagie.text.FontFactory

def fonts
try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'Paragraphs.pdf'

	def a = builder.document(filename:file){
		paragraph() {
			chunk(text:"This is my first paragraph. ", font:FontFactory.getFont(FontFactory.HELVETICA, 10))
			chunk(text:"The leading of this paragraph is calculated automagically. ", font:FontFactory.getFont(FontFactory.HELVETICA, 10))
			chunk("The default leading is 1.5 times the fontsize. ", font:FontFactory.getFont(FontFactory.HELVETICA, 10))
			chunk(text:"You can add chunks ", font:FontFactory.getFont(FontFactory.HELVETICA, 10))
			phrase(text:"or you can add phrases. ", font:FontFactory.getFont(FontFactory.HELVETICA, 10))
			phrase(text:"Unless you change the leading with the method setLeading, the leading doesn't change if you add text with another leading. This can lead to some problems.", font:FontFactory.getFont(FontFactory.HELVETICA, 18))
		}
		paragraph() {
			phrase(text:"This is my second paragraph. ", font:FontFactory.getFont(FontFactory.HELVETICA, 12))
			phrase("As you can see, it started on a new line.")
		}
		paragraph(text:"This is my third paragraph.", font:FontFactory.getFont(FontFactory.HELVETICA, 12))
	}
} catch (Exception e) {
	e.printStackTrace()
}
