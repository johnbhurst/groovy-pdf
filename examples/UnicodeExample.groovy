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
	def file = 'examples' + File.separator + 'output' + File.separator + 'unicode.pdf'
	println "This will fail if not using a Linux distro"
	def bfComic = BaseFont.createFont("/usr/share/fonts/truetype/unfonts/UnBatang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
    def font = new Font(bfComic, 12)

	def a = builder.document(filename:file, pdfVersion:PdfWriter.VERSION_1_2){
		paragraph(text:"This is the True Type font 'UnBatang'.", font:font)
		paragraph(text:"Some greek characters: \u0393\u0394\u03b6", font:font)
		paragraph(text:"Some korean characters: ᄌㅓ", font:font)
		paragraph(text:"Some katakana characters: セザコガ", font:font)
	}
} catch (Exception e) {
	e.printStackTrace()
}
