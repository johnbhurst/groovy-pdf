//DifferentFonts.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.Font;

def fonts
try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'DifferentFonts.pdf'
	def doc = builder.document(filename:file){
		chunk(text:"This text is in Times Roman. This is ZapfDingbats: ", font:new Font(Font.TIMES_ROMAN, 12))
        chunk(text:"abcdefghijklmnopqrstuvwxyz", font:new Font(Font.ZAPFDINGBATS, 12))
        chunk(text:". This is font Symbol: ", font:new Font(Font.TIMES_ROMAN, 12))
        chunk(text:"abcdefghijklmnopqrstuvwxyz", font:new Font(Font.SYMBOL, 12))
	}
} catch (Exception e) {
	e.printStackTrace()
}
