//StandardType1Fonts.groovy
//
// @author jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.Font;

def fonts
try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'StandardType1Fonts.pdf'
	fonts = [new Font(Font.COURIER, Font.DEFAULTSIZE, Font.NORMAL),
			new Font(Font.COURIER, Font.DEFAULTSIZE, Font.ITALIC),
			new Font(Font.COURIER, Font.DEFAULTSIZE, Font.BOLD),
			new Font(Font.COURIER, Font.DEFAULTSIZE, Font.BOLD | Font.ITALIC),
			new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL),
			new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.ITALIC),
			new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLD),
			new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLDITALIC),
			new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL),
			new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.ITALIC),
			new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD),
			new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLDITALIC),
			new Font(Font.SYMBOL),
			new Font(Font.ZAPFDINGBATS)]
	def a = builder.document(filename:file, pdfVersion:PdfWriter.VERSION_1_2){
		for(f in fonts) 
			paragraph(text:"quick brown fox jumps over the lazy dog",font:f)
	}
} catch (Exception e) {
	e.printStackTrace()
}
