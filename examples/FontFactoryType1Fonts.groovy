//FontFactoryType1Fonts.groovy
//
// Generates a PDF with the 14 Standard Type 1 Fonts (using FontFactory).
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.Font
import com.lowagie.text.FontFactory

def fonts
try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'FontFactoryType1Fonts.pdf'
	fonts = [FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.NORMAL),
			FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.ITALIC),
			FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.BOLD),
			FontFactory.getFont(FontFactory.COURIER, Font.DEFAULTSIZE, Font.BOLD | Font.ITALIC),
			FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.NORMAL),
			FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.ITALIC),
			FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLD),
			FontFactory.getFont(FontFactory.HELVETICA, Font.DEFAULTSIZE, Font.BOLDITALIC),
			FontFactory.getFont(FontFactory.TIMES_ROMAN, Font.DEFAULTSIZE, Font.NORMAL),
			FontFactory.getFont(FontFactory.TIMES_ROMAN, Font.DEFAULTSIZE, Font.ITALIC),
			FontFactory.getFont(FontFactory.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD),
			FontFactory.getFont(FontFactory.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLDITALIC),
			FontFactory.getFont(FontFactory.SYMBOL),
			FontFactory.getFont(FontFactory.ZAPFDINGBATS)]
	def a = builder.document(filename:file, pdfVersion:PdfWriter.VERSION_1_2){
		for(f in fonts) 
			paragraph(text:"quick brown fox jumps over the lazy dog",font:f)
	}
} catch (Exception e) {
	e.printStackTrace()
}
