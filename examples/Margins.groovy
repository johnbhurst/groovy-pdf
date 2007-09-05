//Margins.groovy
//
// @ jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'Margins.pdf'
	def helloSun
	def a = builder.document(filename:file,pageSize:PageSize.A5, margins:[36,72,108,180]){
		paragraph(text:"The left margin of this document is 36pt (0.5 inch); the right margin 72pt (1 inch); the top margin 108pt (1.5 inch); the bottom margin 180pt (2.5 inch). ")
		 helloSun = paragraph(text:"") {
			(0..20).each {
				chunk(text:"Hello World, Hello Sun, Hello Moon, Hello Stars, Hello Sea, Hello Land, Hello People. ")	
			}
		}
		paragraph(margins:[180, 108, 72, 36], text:"Now we change the margins. You will see the effect on the next page.")
		widget(value:helloSun)
		paragraph(text:"Starting on the next page, the margins will be mirrored.", marginMirroring:true)
		widget(value:helloSun)
	}
} catch (Exception e) {
	e.printStackTrace()
}
