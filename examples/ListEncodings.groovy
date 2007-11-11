//ListEncodings-1.groovy
//
// Listing the encodings of font Unbatang
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.BaseFont

try {
	def writer = new File('./examples/output/encodings.txt').newWriter()
	println "This will fail if not using a Linux distro"
	def bfComic = BaseFont.createFont("/usr/share/fonts/truetype/unfonts/UnBatang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
    writer.write("postscriptname: " + bfComic.getPostscriptFontName())
	writer.write('\r\n\r\n')
	
	def codePages = bfComic.getCodePagesSupported()
	writer.write("All available encodings:\n\n")
	for(codePage in codePages) {
		writer.write(codePage)
		writer.write('\r\n')
	}
	writer.close()
} catch (Exception e) {
	e.printStackTrace()
}
