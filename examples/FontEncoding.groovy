//FontEncoding.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.Font

def fonts
try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'FontEncoding.pdf'
	def helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED)
    def font = new Font(helvetica, 12, Font.NORMAL)
	def doc = builder.document(filename:file){
		chunk(text:'Sponsor this example and send me 1\u20ac. These'+ 
		' are some special characters: \u0152\u0153\u0160\u0161\u0178'+
		'\u017D\u0192\u02DC\u2020\u2021\u2030', font:font)
	}
} catch (Exception e) {
	e.printStackTrace()
}
