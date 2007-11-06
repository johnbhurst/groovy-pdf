//NewPage.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle
import com.lowagie.text.Chunk
import com.lowagie.text.Phrase

try {
	def builder = new PDFBuilder(debug:true)
	def file = 'examples' + File.separator + 'output' + File.separator + 'NewPage.pdf'
	def a = builder.document(filename:file){
		paragraph(text:"This is the first page.")
		page()
		paragraph(text:'This is a new page.')	
		page()
		page()
		paragraph(text:"We invoked new page twice, yet there was no blank page added. Between the second page and this one. This is normal behaviour.")
		page(empty:false)
		page()
		page()
		paragraph(text:"We told the writer the page wasn't empty.")
		page()
		chunk(new Phrase(Chunk.NEWLINE))
		page()
		paragraph("You can also add something invisible if you want a blank page.")
		page()
		chunk(new Phrase(Chunk.NEXTPAGE))
		paragraph("Using Chunk.NEXTPAGE also jumps to the next page")
	}
} catch (Exception e) {
	e.printStackTrace()
}
