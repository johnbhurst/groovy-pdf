//DefaultPageSize.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle


try {
	def builder = new PDFBuilder(debug:true)
	def file = 'examples' + File.separator + 'output' + File.separator + 'DefaultPageSize.pdf'
	def a = builder.document(filename:file){
		paragraph(text:"The default PageSize is DIN A4.")
		page(pageSize:PageSize.A3) {
			paragraph(text:'This PageSize is DIN A3.')	
		}
		page(pageSize:PageSize.A2) {
			println 'A2'
			paragraph(text:'This PageSize is DIN A2.')	
		}
		page(pageSize:PageSize.A1) {
			println 'A1'
			paragraph(text:'This PageSize is DIN A1.')	
		}
		page(pageSize:PageSize.A0) {
			paragraph(text:'This PageSize is DIN A0.')	
		}
		page(pageSize:PageSize.A5) {
			paragraph(text:'This PageSize is DIN A5.')	
		}
		page(pageSize:PageSize.A6) {
			paragraph(text:'This PageSize is DIN A6.')	
		}
		page(pageSize:PageSize.A7) {
			paragraph(text:'This PageSize is DIN A7.')	
		}
		page(pageSize:PageSize.A8) {
			paragraph(text:'This PageSize is DIN A8.')	
		}
		page(pageSize:PageSize.LETTER) {
			paragraph(text:'This PageSize is LETTER.')
			paragraph(text:'A lot of other standard PageSizes are available.')
		}
		
	}
} catch (Exception e) {
	e.printStackTrace()
}
